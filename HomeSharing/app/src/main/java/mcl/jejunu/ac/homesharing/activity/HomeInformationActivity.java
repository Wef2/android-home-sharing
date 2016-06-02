package mcl.jejunu.ac.homesharing.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.CommentListAdapter;
import mcl.jejunu.ac.homesharing.adapter.ImageSliderAdapter;
import mcl.jejunu.ac.homesharing.model.Comment;
import mcl.jejunu.ac.homesharing.model.Filedata;
import mcl.jejunu.ac.homesharing.model.Home;
import mcl.jejunu.ac.homesharing.model.User;

public class HomeInformationActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private int homeId;
    private Home myHome;

    private FloatingActionButton floatingActionButton;
    private Button commentButton, ratingButton, moreButton;

    private ArrayList<Comment> comments;
    private RecyclerView recyclerView;
    private CommentListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ImageView imageView;
    private TextView descriptionText, peopleText, chargeText, ratingText;

    private GoogleMap map;
    private ProgressDialog progressDialog;

    private String imageUrl = "http://61.99.246.80:8080/image/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_information);

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

        progressDialog = new ProgressDialog(this);
        new HomeInformationRequestTask().execute();
        new CommentsRequestTask().execute();
        new AverageRatingRequestTask().execute();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.reservation_button);
        commentButton = (Button) findViewById(R.id.comment_button);
        ratingButton = (Button) findViewById(R.id.rating_button);
        moreButton = (Button) findViewById(R.id.more_button);

        imageView = (ImageView) findViewById(R.id.image_view);

        descriptionText = (TextView) findViewById(R.id.description_text);
        chargeText = (TextView) findViewById(R.id.charge_text);
        peopleText = (TextView) findViewById(R.id.people_text);
        ratingText = (TextView) findViewById(R.id.rating_text);

        floatingActionButton.setOnClickListener(this);
        commentButton.setOnClickListener(this);
        ratingButton.setOnClickListener(this);
        moreButton.setOnClickListener(this);

        comments = new ArrayList<>();

        adapter = new CommentListAdapter(comments);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {

        }
        map.setMyLocationEnabled(false);
    }

    @Override
    public void onClick(View v) {
        if (v == commentButton) {
            Intent intent = new Intent(HomeInformationActivity.this, WriteCommentActivity.class);
            intent.putExtra("id", homeId);
            startActivity(intent);
        } else if (v == ratingButton) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = layoutInflater.inflate(R.layout.dialog_rating, null);
            final RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            alert.setView(view);
            alert.setTitle("평가");
            alert.setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alert.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(HomeInformationActivity.this, "별점 : " + String.valueOf(ratingBar.getRating()), Toast.LENGTH_SHORT).show();
                        }
                    });
            alert.show();
        } else if (v == floatingActionButton) {
            Intent intent = new Intent(HomeInformationActivity.this, ReservationMakeActivity.class);
            intent.putExtra("id", homeId);
            startActivity(intent);
        } else if (v == moreButton) {
            Intent intent = new Intent(HomeInformationActivity.this, CommentListActivity.class);
            intent.putExtra("id", homeId);
            startActivity(intent);
        }
    }

    private class HomeInformationRequestTask extends AsyncTask<Void, Void, String> {

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
                String url = "http://61.99.246.80:8080/home/"+homeId;
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
                JSONObject jsonObject = new JSONObject(string);
                Log.i("json", jsonObject.toString());

                myHome = new Home();
                myHome.setId(jsonObject.getInt("id"));
                myHome.setName(jsonObject.getString("name"));
                myHome.setPeople(jsonObject.getInt("people"));
                myHome.setCharge(jsonObject.getInt("charge"));
                myHome.setDescription(jsonObject.getString("description"));
                myHome.setLatitude(jsonObject.getDouble("latitude"));
                myHome.setLongitude(jsonObject.getDouble("longitude"));

                JSONObject filedataObject = jsonObject.getJSONObject("filedata");
                Log.i("json", filedataObject.toString());
                Filedata filedata = new Filedata();
                filedata.setId(filedataObject.getInt("id"));
                myHome.setFiledata(filedata);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            descriptionText.setText(myHome.getDescription());
            chargeText.setText(String.valueOf(myHome.getCharge()) + "원");
            peopleText.setText(String.valueOf(myHome.getPeople()) + "명");
            Picasso.with(imageView.getContext()).load(imageUrl + myHome.getFiledata().getFilename()).into(imageView);

            LatLng latLng = new LatLng(myHome.getLatitude(), myHome.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title(myHome.getName()));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            map.getUiSettings().setAllGesturesEnabled(false);
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    Intent intent = new Intent(HomeInformationActivity.this, MapsActivity.class);
                    intent.putExtra("latlng", latLng);
                    intent.putExtra("title", myHome.getName());
                    startActivity(intent);
                }
            });
        }
    }


    private class CommentsRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/home/"+homeId +"/comments/top3";
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
        }
    }

    private class AverageRatingRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/rating/home/"+homeId +"/average";
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
            if(!(string == null)){
                ratingText.setText(string + "점");
            }
            progressDialog.dismiss();
        }
    }


}