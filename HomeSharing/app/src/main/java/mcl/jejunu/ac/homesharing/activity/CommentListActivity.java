package mcl.jejunu.ac.homesharing.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.CommentListAdapter;
import mcl.jejunu.ac.homesharing.adapter.HomeListAdapter;
import mcl.jejunu.ac.homesharing.model.Comment;
import mcl.jejunu.ac.homesharing.model.HomeModel;

/**
 * Created by Kim on 2016-05-27.
 */
public class CommentListActivity extends AppCompatActivity {

    private ArrayList<Comment> comments;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        comments = new ArrayList<>();
        comments.add(new Comment());

        adapter = new CommentListAdapter(comments);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}