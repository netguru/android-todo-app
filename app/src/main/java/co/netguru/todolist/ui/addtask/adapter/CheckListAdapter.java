package co.netguru.todolist.ui.addtask.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.netguru.todolist.domain.ChecklistItem;
import co.netguru.todolist.domain.Task;
import co.netguru.todolist.ui.base.BaseViewHolder;

public class CheckListAdapter extends RecyclerView.Adapter<BaseViewHolder>
        implements AddChecklistItemListener, GetIsLastObserver, RemoveChecklistItemListener {

    private static final int HEADER_VIEW_TYPE = 1;
    private static final int CHECKLIST_ITEM_VIEW_TYPE = 2;
    private static final int ADD_CHECKLIST_ITEM_BUTTON_VIEW_HOLDER = 3;

    private static final int HEADER_ITEMS_COUNT = 1;
    private static final int FOOTER_ITEMS_COUNT = 1;

    private static final int HEADER_POSITION = 0;

    private final Task task;
    private final List<ChecklistItem> checklistItems;
    private final DueDateRequestListener dueDateRequestListener;

    private Set<RecyclerView> attachedRecyclers = new HashSet<>();

    public CheckListAdapter(DueDateRequestListener dueDateRequestListener) {
        this.dueDateRequestListener = dueDateRequestListener;
        task = new Task();
        checklistItems = new ArrayList<>();
        task.setChecklistItemList(checklistItems);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_VIEW_TYPE:
                return new TaskHeaderItemViewHolder(parent, dueDateRequestListener);
            case CHECKLIST_ITEM_VIEW_TYPE:
                return new CheckListItemViewHolder(parent, this, this, this);
            case ADD_CHECKLIST_ITEM_BUTTON_VIEW_HOLDER:
                return new AddChecklistItemViewHolder(parent, this);
            default:
                throw new IllegalArgumentException("Unknown view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case HEADER_VIEW_TYPE:
                holder.bind(task);
                break;
            case CHECKLIST_ITEM_VIEW_TYPE:
                final int checklistListItemPosition = position - HEADER_ITEMS_COUNT;
                holder.bind(checklistItems.get(checklistListItemPosition));
                break;
            case ADD_CHECKLIST_ITEM_BUTTON_VIEW_HOLDER:
                break;
            default:
                throw new IllegalArgumentException("Unknown view type: " + viewType);
        }
    }

    @Override
    public int getItemCount() {
        return checklistItems.size() + HEADER_ITEMS_COUNT + FOOTER_ITEMS_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == HEADER_POSITION) {
            return HEADER_VIEW_TYPE;
        } else if (position < checklistItems.size() + HEADER_ITEMS_COUNT) {
            return CHECKLIST_ITEM_VIEW_TYPE;
        } else {
            return ADD_CHECKLIST_ITEM_BUTTON_VIEW_HOLDER;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        attachedRecyclers.add(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        attachedRecyclers.remove(recyclerView);
    }

    @Override
    public void removeChecklistItemBasedOnLayoutPosition(int layoutPosition) {
        final int checklistItemPosition = layoutPosition - HEADER_ITEMS_COUNT;
        if (checklistItems.size() > checklistItemPosition) {
            checklistItems.remove(checklistItemPosition);
            notifyItemRemoved(layoutPosition);
        }
    }

    @Override
    public void addChecklistItem() {
        int sizeBeforeInsertion = checklistItems.size();
        checklistItems.add(new ChecklistItem());
        notifyItemInserted(sizeBeforeInsertion + HEADER_ITEMS_COUNT);
        scrollBottomIfPossible();
    }

    @Override
    public boolean isLastChecklistItem(int layoutPosition) {
        return layoutPosition == getItemCount() - HEADER_ITEMS_COUNT - 1 && !checklistItems.isEmpty();
    }

    public void setTaskDate(LocalDate taskDate) {
        task.setDueDate(taskDate);
        notifyItemChanged(HEADER_POSITION);
    }

    private void scrollBottomIfPossible() {
        int itemCount = getItemCount();
        if (itemCount > 0) {
            for (RecyclerView attachedRecycler : attachedRecyclers) {
                attachedRecycler.scrollToPosition(itemCount - 1);
            }
        }
    }

    public Task getTask() {
        return task;
    }
}
