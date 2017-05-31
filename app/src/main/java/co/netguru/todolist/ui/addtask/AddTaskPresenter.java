package co.netguru.todolist.ui.addtask;

import org.threeten.bp.LocalDate;

import javax.inject.Inject;

import co.netguru.todolist.common.RxTransformers;
import co.netguru.todolist.domain.Task;
import co.netguru.todolist.domain.usecase.SaveTaskUseCase;
import co.netguru.todolist.ui.base.BasePresenter;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class AddTaskPresenter extends BasePresenter<AddTaskView> {

    private static final String MONTH_SHORT_DAY_AND_YEAR_FORMAT = "MMM dd, yyyy";
    private final SaveTaskUseCase saveTaskUseCase;
    private Disposable saveTaskDisposable;

    @Inject
    public AddTaskPresenter(SaveTaskUseCase saveTaskUseCase) {
        this.saveTaskUseCase = saveTaskUseCase;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (saveTaskDisposable != null) {
            saveTaskDisposable.dispose();
        }
    }

    public void handleDateSetByUser(int year, int monthIndexedFromZero, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, monthIndexedFromZero + 1, dayOfMonth);
        getMvpView().setTaskDueDate(localDate);
    }

    public void saveTask(Task task) {
        saveTaskDisposable = saveTaskUseCase.execute(task)
                .compose(RxTransformers.applyCompletableIoSchedulers())
                .subscribe(
                        getMvpView()::finish,
                        throwable -> Timber.e(throwable, "Error while saving task")
                );
    }
}
