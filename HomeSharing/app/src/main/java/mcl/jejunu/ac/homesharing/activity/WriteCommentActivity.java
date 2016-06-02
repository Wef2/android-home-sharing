package mcl.jejunu.ac.homesharing.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.model.Comment;

public class WriteCommentActivity extends AppCompatActivity {

    private int userId = 2;
    private int homeId;

    private EditText editText;
    private InputMethodManager inputMethodManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

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

        editText = (EditText) findViewById(R.id.editText);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
                new WriteCommentTask().execute();
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class WriteCommentTask extends AsyncTask<Void, Void, String> {

        private MultiValueMap<String, String> message;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            message = new LinkedMultiValueMap<String, String>();
            message.add("user_id", String.valueOf(userId));
            message.add("home_id", String.valueOf(homeId));
            message.add("content", editText.getText().toString());
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/comment/add";
                RestTemplate restTemplate = new RestTemplate(true);
                ResponseEntity<String> response = restTemplate.postForEntity(url, message, String.class);
                return response.getBody();
            }
            catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            Log.i("Result", result);
        }

    }
}