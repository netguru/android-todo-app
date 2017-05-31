package co.netguru.todolist.ui.main;

import javax.inject.Inject;

import co.netguru.todolist.common.di.ActivityScope;
import co.netguru.todolist.data.local.TasksRepository;
import co.netguru.todolist.ui.base.BasePresenter;

@ActivityScope
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public MainPresenter(TasksRepository tasksRepository) {

    }

    public void test() {
        getMvpView().doSomething();
    }

    public void handleAddTaskClick() {
        getMvpView().showAddTaskView();
    }
}
