package co.netguru.todolist.ui.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import co.netguru.todolist.R;
import co.netguru.todolist.app.App;
import co.netguru.todolist.data.local.model.TaskDb;
import co.netguru.todolist.domain.Task;
import co.netguru.todolist.ui.base.BaseMvpFragment;
import co.netguru.todolist.ui.tasks.adapter.TasksAdapter;

public class TasksFragment extends BaseMvpFragment<TasksPresenter> implements TasksView {

    private static final String TASK_TYPE_ARG = "task_type_arg";

    private final TasksAdapter tasksAdapter = new TasksAdapter();

    @BindView(R.id.tasks_recycler_view) RecyclerView recyclerView;

    private TasksType tasksType;

    public static TasksFragment newInstance(TasksType tasksType) {
        Bundle args = new Bundle();
        args.putInt(TASK_TYPE_ARG, tasksType.ordinal());

        TasksFragment fragment = new TasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        tasksType = TasksType.fromOrdinal(getArguments().getInt(TASK_TYPE_ARG));
        getPresenter().setupTasksSubscription(tasksType);
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(tasksAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected TasksPresenter createPresenter() {
        return App.getAppComponent(getContext()).tasksFragmentComponent().getPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tasks;
    }

    @Override
    public void displayTasks(List<Task> tasks) {
        tasksAdapter.showTasks(tasks);
    }
}
