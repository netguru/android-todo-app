package co.netguru.todolist.ui.main;

import co.netguru.todolist.common.di.ActivityScope;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    MainPresenter getPresenter();
}
