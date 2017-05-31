package co.netguru.todolist.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import co.netguru.todolist.data.local.model.ChecklistItemDb;
import io.reactivex.Flowable;

@Dao
public interface ChecklistDao {

    @Insert
    void insertAll(List<ChecklistItemDb> checklistItemDbs);

    @Query("SELECT * FROM check_list_item WHERE task_id = :taskId")
    Flowable<List<ChecklistItemDb>> getChecklistItems(int taskId);
}
