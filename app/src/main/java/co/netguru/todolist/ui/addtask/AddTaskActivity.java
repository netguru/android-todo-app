package co.netguru.todolist.ui.addtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import co.netguru.todolist.R;
import co.netguru.todolist.ui.base.BaseActivity;

public class AddTaskActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AddTaskActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolbar();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AddTaskFragment.newInstance(), AddTaskFragment.TAG)
                .commit();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_task_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_accept) {
            AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag(AddTaskFragment.TAG);
            addTaskFragment.onAcceptClick();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_task;
    }
}
