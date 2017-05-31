package co.netguru.todolist.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import org.threeten.bp.LocalDate;

import java.util.List;

import co.netguru.todolist.data.local.model.TaskDb;
import io.reactivex.Flowable;

@Dao
public interface TasksDao {

    @Insert
    void insertAll(TaskDb... taskDbs);

    @Query("SELECT * FROM task WHERE is_done = 0")
    Flowable<List<TaskDb>> getToDoTasks();

    @Query("SELECT * FROM task WHERE is_done = 1")
    Flowable<List<TaskDb>> getDoneTasks();

    @Query("SELECT * FROM task WHERE due_date < :localDate")
    Flowable<List<TaskDb>> getTaskWithDueDateBefore(LocalDate localDate);
}
