package mcl.jejunu.ac.homesharing.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.CommentListAdapter;
import mcl.jejunu.ac.homesharing.model.Comment;
import mcl.jejunu.ac.homesharing.model.User;

/**
 * Created by Kim on 2016-05-27.
 */
public class CommentListActivity extends AppCompatActivity {

    private ArrayList<Comment> comments;
    private RecyclerView recyclerView;
    private CommentListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressDialog progressDialog;

    private int homeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comment);

        Intent intent = getIntent();
        homeId = intent.getIntExtra("id", 0);

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
        adapter = new CommentListAdapter(comments);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        new CommentsRequestTask().execute();
    }


    private class CommentsRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute(){
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/home/"+homeId +"/comments";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String string = restTemplate.getForObject(url, String.class);
                return string;
            }
            catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Comment comment = new Comment();
                    comment.setId((int) jsonObject.get("id"));
                    JSONObject jsonUser = jsonObject.getJSONObject("user");
                    User user = new User();
                    user.setNickname(jsonUser.getString("nickname"));
                    comment.setUser(user);
                    comment.setContent((String) jsonObject.get("content"));
                    comments.add(comment);
                }
                adapter.replaceWith(comments);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

}