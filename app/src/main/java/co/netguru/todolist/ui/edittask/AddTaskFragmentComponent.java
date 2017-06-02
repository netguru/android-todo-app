package co.netguru.todolist.ui.edittask;

import co.netguru.todolist.common.di.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent
public interface AddTaskFragmentComponent {

    void inject(EditTaskFragment addTaskFragment);

    EditTaskPresenter getPresenter();
}
