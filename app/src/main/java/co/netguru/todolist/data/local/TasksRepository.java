package co.netguru.todolist.data.local;

import org.threeten.bp.LocalDate;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.netguru.todolist.data.local.model.ChecklistItemDb;
import co.netguru.todolist.data.local.model.TaskDb;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class TasksRepository {

    private final AppDatabase appDatabase;

    @Inject
    public TasksRepository(AppDatabase appDatabase) {

        this.appDatabase = appDatabase;
    }

    public Completable saveTasks(TaskDb... taskDbs) {
        return Completable.fromAction(() -> appDatabase.tasksDao().insertAll(taskDbs));
    }

    public Completable saveTaskWithChecklist(TaskDb taskDb, List<ChecklistItemDb> checklistItemDbs) {
        return Completable.fromAction(() -> {
            appDatabase.beginTransaction();
            try {
                appDatabase.tasksDao().insertAll(taskDb);
                appDatabase.checklistDao().insertAll(checklistItemDbs);
                appDatabase.setTransactionSuccessful();
            } finally {
                appDatabase.endTransaction();
            }
        });
    }

    /**
     * Flowable will emit new list of tasks whenever tasks table is modified
     */
    public Flowable<List<TaskDb>> getToDoTasks() {
        return appDatabase.tasksDao().getToDoTasks();
    }

    public Flowable<List<TaskDb>> getDoneTasks() {
        return appDatabase.tasksDao().getDoneTasks();
    }

    public Flowable<List<TaskDb>> getTasksWithDueDateBefore(LocalDate date) {
        return appDatabase.tasksDao().getTaskWithDueDateBefore(date);
    }

    public Single<List<ChecklistItemDb>> getSingleCheckListItems(TaskDb taskDb) {
        return appDatabase.checklistDao().getChecklistItems(taskDb.getId()).firstOrError();
    }
}
