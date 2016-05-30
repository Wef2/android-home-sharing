package mcl.jejunu.ac.homesharing.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.CommentListAdapter;
import mcl.jejunu.ac.homesharing.adapter.ImageSliderAdapter;
import mcl.jejunu.ac.homesharing.model.Comment;

public class HomeInformationActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private int homeId;
    private ViewPager viewPager;
    private ImageSliderAdapter imageSliderAdapter;

    private FloatingActionButton floatingActionButton;
    private Button commentButton, ratingButton, moreButton;

    private ArrayList<Comment> comments;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private GoogleMap mGoogleMap;

    private double latitude = 33.499234, longitude = 126.530714;
    private String title = "제주시";

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

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        imageSliderAdapter = new ImageSliderAdapter(this);
        viewPager.setAdapter(imageSliderAdapter);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.reservation_button);
        commentButton = (Button) findViewById(R.id.comment_button);
        ratingButton = (Button) findViewById(R.id.rating_button);
        moreButton = (Button) findViewById(R.id.more_button);

        floatingActionButton.setOnClickListener(this);
        commentButton.setOnClickListener(this);
        ratingButton.setOnClickListener(this);
        moreButton.setOnClickListener(this);

        comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());

        adapter = new CommentListAdapter(comments);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
        } else {

        }
        LatLng jeju = new LatLng(latitude, longitude);
        mGoogleMap.addMarker(new MarkerOptions().position(jeju).title(title));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jeju, 15));
        mGoogleMap.getUiSettings().setAllGesturesEnabled(false);
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                H
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == commentButton) {
            Intent intent = new Intent(HomeInformationActivity.this, WriteCommentActivity.class);
            startActivity(intent);
        } else if (v == ratingButton) {

        } else if (v == floatingActionButton) {
            Intent intent = new Intent(HomeInformationActivity.this, ReservationActivity.class);
            startActivity(intent);
        } else if (v == moreButton) {
            Intent intent = new Intent(HomeInformationActivity.this, CommentListActivity.class);
            startActivity(intent);
        }
    }
}