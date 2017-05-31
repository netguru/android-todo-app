package co.netguru.todolist.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import co.netguru.todolist.data.local.model.ChecklistItemDb;
import co.netguru.todolist.domain.ChecklistItem;

public class ChecklistItemMapper {

    private ChecklistItemMapper() {
        throw new AssertionError();
    }

    public static ChecklistItemDb toChecklistItemDb(int taskId, ChecklistItem checklistItem) {
        return new ChecklistItemDb(checklistItem.getId(), taskId, checklistItem.getName(), checklistItem.isDone());
    }

    public static List<ChecklistItemDb> toChecklistItemDbList(int taskId, List<ChecklistItem> checklistItems) {
        List<ChecklistItemDb> checklistItemDbList = new ArrayList<>(checklistItems.size());

        for (ChecklistItem checklistItem : checklistItems) {
            checklistItemDbList.add(toChecklistItemDb(taskId, checklistItem));
        }

        return checklistItemDbList;
    }

    public static ChecklistItem toChecklistItem(ChecklistItemDb checklistItemDb) {
        return new ChecklistItem(checklistItemDb.getId(), checklistItemDb.getName(), checklistItemDb.isDone());
    }

    public static List<ChecklistItem> toChecklistItemList(List<ChecklistItemDb> checklistItemDbs) {
        List<ChecklistItem> checklistItems = new ArrayList<>(checklistItemDbs.size());
        for (ChecklistItemDb checklistItemDb : checklistItemDbs) {
            checklistItems.add(toChecklistItem(checklistItemDb));
        }
        return checklistItems;
    }
}
