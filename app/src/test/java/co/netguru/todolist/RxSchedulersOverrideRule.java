package co.netguru.todolist;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulersOverrideRule implements TestRule {

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(currentScheduler -> Schedulers.trampoline());
                RxJavaPlugins.setComputationSchedulerHandler(currentScheduler -> Schedulers.trampoline());
                RxJavaPlugins.setIoSchedulerHandler(currentScheduler -> Schedulers.trampoline());
                RxJavaPlugins.setNewThreadSchedulerHandler(currentScheduler -> Schedulers.trampoline());
                RxJavaPlugins.setSingleSchedulerHandler(currentScheduler -> Schedulers.trampoline());
                base.evaluate();

                RxJavaPlugins.reset();
                RxAndroidPlugins.reset();
            }
        };
    }
}