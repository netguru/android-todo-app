package co.netguru.todolist.ui.addtask;

import org.threeten.bp.LocalDate;

import co.netguru.todolist.ui.base.MvpView;

interface AddTaskView extends MvpView {

    void setTaskDueDate(LocalDate localDate);

    void finish();
}
