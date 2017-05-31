package co.netguru.todolist.ui.addtask;

import co.netguru.todolist.common.di.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent
public interface AddTaskFragmentComponent {

    void inject(AddTaskFragment addTaskFragment);

    AddTaskPresenter getPresenter();
}
