package co.netguru.todolist.app;

import javax.inject.Singleton;

import co.netguru.todolist.data.local.LocalDataModule;
import co.netguru.todolist.ui.addtask.AddTaskFragmentComponent;
import co.netguru.todolist.ui.main.MainActivityComponent;
import co.netguru.todolist.ui.tasks.TasksFragmentComponent;
import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        LocalDataModule.class
})
public interface ApplicationComponent {

    MainActivityComponent mainActivityComponent();

    TasksFragmentComponent tasksFragmentComponent();

    AddTaskFragmentComponent addTaskFragmentComponent();
}
