package co.netguru.todolist.ui.main;

import javax.inject.Inject;

import co.netguru.todolist.common.di.ActivityScope;
import co.netguru.todolist.ui.base.BasePresenter;

@ActivityScope
public class MainPresenter extends BasePresenter<MainView> {

    @Inject
    public MainPresenter() {
        //di
    }

    public void handleAddTaskClick() {
        getMvpView().showAddTaskView();
    }
}
