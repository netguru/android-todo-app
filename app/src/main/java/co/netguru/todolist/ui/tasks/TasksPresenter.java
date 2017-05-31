package co.netguru.todolist.ui.tasks;

import org.threeten.bp.LocalDate;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.List;

import javax.inject.Inject;

import co.netguru.todolist.common.RxTransformers;
import co.netguru.todolist.data.local.TasksRepository;
import co.netguru.todolist.data.local.model.TaskDb;
import co.netguru.todolist.ui.base.BasePresenter;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class TasksPresenter extends BasePresenter<TasksView> {

    private static final int FINISHING_TASKS_PERIOD = 7;

    private final TasksRepository tasksRepository;
    private TasksType taskType;
    private Disposable tasksDisposable;

    @Override
    public void onDetach() {
        super.onDetach();
        if (tasksDisposable != null) {
            tasksDisposable.dispose();
        }
    }

    @Inject
    public TasksPresenter(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void setupTasksSubscription(TasksType taskType) {
        this.taskType = taskType;

        tasksDisposable = getTaskFlowableBasedOnType(taskType)
                .compose(RxTransformers.applyFlowableIoSchedulers())
                .subscribe(
                        //tasks -> getMvpView().displayTasks(tasks),
                        //throwable -> Timber.d("Error occurred while loading tasks", throwable)
                );
    }

    private Flowable<List<TaskDb>> getTaskFlowableBasedOnType(TasksType tasksType) {
        switch (tasksType) {
            case TODO:
                return tasksRepository.getToDoTasks();
            case DONE:
                return tasksRepository.getDoneTasks();
            case FINISHING:
                return tasksRepository.getTasksWithDueDateBefore(LocalDate.now().plus(FINISHING_TASKS_PERIOD, ChronoUnit.DAYS));
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}
