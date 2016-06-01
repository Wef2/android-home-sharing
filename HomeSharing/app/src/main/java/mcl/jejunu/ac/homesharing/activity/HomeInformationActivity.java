package mcl.jejunu.ac.homesharing.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

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

    private GoogleMap map;

    private double latitude = 33.499234, longitude = 126.530714;
    private String title = "제주시";

    private Snackbar snackbar;

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
        map = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
        } else {

        }
        LatLng jeju = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(jeju).title(title));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(jeju, 15));
        map.setMyLocationEnabled(false);
        map.getUiSettings().setAllGesturesEnabled(false);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(HomeInformationActivity.this, MapsActivity.class);
                intent.putExtra("latlng", latLng);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == commentButton) {
            Intent intent = new Intent(HomeInformationActivity.this, WriteCommentActivity.class);
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
            startActivity(intent);
        } else if (v == moreButton) {
            Intent intent = new Intent(HomeInformationActivity.this, CommentListActivity.class);
            startActivity(intent);
        }
    }

}