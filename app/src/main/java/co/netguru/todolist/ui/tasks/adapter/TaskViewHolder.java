package co.netguru.todolist.ui.tasks.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import co.netguru.todolist.R;
import co.netguru.todolist.common.LocalDateFormatterUtil;
import co.netguru.todolist.domain.Task;
import co.netguru.todolist.ui.base.BaseViewHolder;

public class TaskViewHolder extends BaseViewHolder<Task> {

    @BindView(R.id.title_text_view) TextView titleTextView;
    @BindView(R.id.description_text_view) TextView descriptionTextView;
    @BindView(R.id.due_date_text_view) TextView dueDateTextView;

    public TaskViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void bind(Task item) {
        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());
        dueDateTextView.setText(LocalDateFormatterUtil.getShortMonthDayAndYearFormattedDate(item.getDueDate()));
    }
}
