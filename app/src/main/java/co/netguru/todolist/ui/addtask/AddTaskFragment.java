package co.netguru.todolist.ui.addtask;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;

import org.threeten.bp.LocalDate;

import butterknife.BindView;
import co.netguru.todolist.R;
import co.netguru.todolist.app.App;
import co.netguru.todolist.ui.addtask.adapter.CheckListAdapter;
import co.netguru.todolist.ui.addtask.adapter.DueDateRequestListener;
import co.netguru.todolist.ui.base.BaseMvpFragment;

public class AddTaskFragment extends BaseMvpFragment<AddTaskPresenter>
        implements AddTaskView, DatePickerDialog.OnDateSetListener, DueDateRequestListener {

    public static final String TAG = AddTaskFragment.class.getSimpleName();

    private final CheckListAdapter checkListAdapter = new CheckListAdapter(this);

    @BindView(R.id.check_list_recycler) RecyclerView checkListRecyclerView;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkListRecyclerView.setAdapter(checkListAdapter);
        checkListRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_add_task;
    }

    @Override
    protected AddTaskPresenter createPresenter() {
        return App.getAppComponent(getContext()).addTaskFragmentComponent().getPresenter();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthIndexedFromZero, int dayOfMonth) {
        getPresenter().handleDateSetByUser(year, monthIndexedFromZero, dayOfMonth);
    }

    public void onAcceptClick() {
        getPresenter().saveTask(checkListAdapter.getTask());
    }

    @Override
    public void setTaskDueDate(LocalDate localDate) {
        checkListAdapter.setTaskDate(localDate);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void onTaskRequestDate() {
        DatePickerDialogFragment.newInstance(this).show(getFragmentManager(), DatePickerDialogFragment.TAG);
    }
}
