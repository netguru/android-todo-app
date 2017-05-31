package co.netguru.todolist.ui.addtask.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import co.netguru.todolist.R;
import co.netguru.todolist.domain.ChecklistItem;
import co.netguru.todolist.ui.base.BaseViewHolder;

class CheckListItemViewHolder extends BaseViewHolder<ChecklistItem> {

    private final AddChecklistItemListener addChecklistItemListener;
    private final GetIsLastObserver getIsLastObserver;
    private final RemoveChecklistItemListener removeChecklistItemListener;

    @BindView(R.id.sub_task_name_edit_text) EditText subTaskNameEditText;
    @BindView(R.id.is_done_checkbox) AppCompatCheckBox isDoneCheckbox;

    @Nullable
    private ChecklistItem currentItem;

    public CheckListItemViewHolder(ViewGroup parent, AddChecklistItemListener addChecklistItemListener,
                                   GetIsLastObserver getIsLastObserver, RemoveChecklistItemListener removeChecklistItemListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_list_item, parent, false));
        this.addChecklistItemListener = addChecklistItemListener;
        this.getIsLastObserver = getIsLastObserver;
        this.removeChecklistItemListener = removeChecklistItemListener;
    }

    @Override
    public void bind(ChecklistItem item) {
        currentItem = item;
        subTaskNameEditText.setText(item.getName());
        isDoneCheckbox.setChecked(item.isDone());
    }

    @OnTextChanged(R.id.sub_task_name_edit_text)
    void onTextChanged(CharSequence charSequence) {
        if (currentItem != null) {
            currentItem.setName(charSequence.toString());
        }
    }

    @OnCheckedChanged(R.id.is_done_checkbox)
    void onChangeIsDoneCheckboxState(boolean isDone) {
        if (currentItem != null) {
            currentItem.setDone(isDone);
        }
    }

    @OnClick(R.id.remove_button)
    void onRemoveClick() {
        removeChecklistItemListener.removeChecklistItemBasedOnLayoutPosition(getLayoutPosition());
    }

    @OnEditorAction(R.id.sub_task_name_edit_text)
    boolean onSubTaskNameEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_NEXT && getIsLastObserver.isLastChecklistItem(getLayoutPosition())) {
            addChecklistItemListener.addChecklistItem();
        }
        //return false on purpose so we get next item focused
        return false;
    }
}
