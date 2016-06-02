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

import java.sql.Date;
import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.ReservationListAdapter;
import mcl.jejunu.ac.homesharing.model.Reservation;
import mcl.jejunu.ac.homesharing.model.User;

/**
 * Created by neo-202 on 2016-05-31.
 */
public class ReservationListActivity extends AppCompatActivity implements View.OnClickListener {

    private int homeId;

    private ArrayList<Reservation> reservations;
    private RecyclerView recyclerView;
    private ReservationListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

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

        reservations = new ArrayList<>();
        adapter = new ReservationListAdapter(reservations, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this);
        new ReservationListRequestTask().execute();
    }

    @Override
    public void onClick(View v) {
        Reservation reservation = (Reservation) v.getTag();
        Intent intent = new Intent(ReservationListActivity.this, ReservationInfoActivity.class);
        intent.putExtra("id", reservation.getId());
        startActivity(intent);
    }


    private class ReservationListRequestTask extends AsyncTask<Void, Void, String> {

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
                String url = "http://61.99.246.80:8080/home/" + homeId + "/reservations";
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
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Reservation reservation = new Reservation();
                    reservation.setId((int) jsonObject.get("id"));
                    JSONObject jsonUser = jsonObject.getJSONObject("user");
                    User user = new User();
                    user.setNickname(jsonUser.getString("nickname"));
                    reservation.setUser(user);
                    reservation.setCheck_in(jsonObject.getString("check_in"));
                    reservation.setCheck_out(jsonObject.getString("check_out"));
                    reservation.setPeople(jsonObject.getInt("people"));
                    reservations.add(reservation);
                }
                adapter.replaceWith(reservations);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

}