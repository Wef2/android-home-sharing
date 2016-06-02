package mcl.jejunu.ac.homesharing.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.formatter.DayFormatter;

public class ReservationInfoActivity extends AppCompatActivity {

    private int reservationId;

    private TextView checkInText, checkOutText, peopleText, nicknameText;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_info);

        Intent intent = getIntent();
        reservationId = intent.getIntExtra("id", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkInText = (TextView) findViewById(R.id.check_in_text);
        checkOutText = (TextView) findViewById(R.id.check_out_text);
        peopleText = (TextView) findViewById(R.id.people_text);
        nicknameText = (TextView) findViewById(R.id.nickname_text);

        progressDialog = new ProgressDialog(this);
        new ReservationInfoRequestTask().execute();
    }

    private class ReservationInfoRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/reservation/" + reservationId;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String string = restTemplate.getForObject(url, String.class);
                return string;
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            try {
                JSONObject jsonObject = new JSONObject(string);
                JSONObject jsonUser = jsonObject.getJSONObject("user");
                checkInText.setText(jsonObject.getString("check_in"));
                checkOutText.setText(jsonObject.getString("check_out"));
                peopleText.setText(jsonObject.getInt("people") + "명");
                nicknameText.setText(jsonUser.getString("nickname"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

}