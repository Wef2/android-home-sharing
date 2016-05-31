package mcl.jejunu.ac.homesharing.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import mcl.jejunu.ac.homesharing.R;

/**
 * Created by neo-202 on 2016-05-26.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_LOCATION = 2;

    private GoogleMap map;
    private double latitude = 33.499234, longitude = 126.530714;
    private LatLng latLng = null;

    private String name = "나의 집", description = "안녕하세요";
    private int people = 1, charge = 0;

    private ImageView imageView;
    private Bitmap bitmap;
    private LinearLayout nameLayout, descriptionLayout, peoepleLayout, chargeLayout;
    private TextView nameText, descriptionText, peopleText, chargeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imageView = (ImageView) findViewById(R.id.home_image);
        nameLayout = (LinearLayout) findViewById(R.id.name_layout);
        descriptionLayout = (LinearLayout) findViewById(R.id.description_layout);
        peoepleLayout = (LinearLayout) findViewById(R.id.people_layout);
        chargeLayout = (LinearLayout) findViewById(R.id.charge_layout);
        imageView.setOnClickListener(this);
        nameLayout.setOnClickListener(this);
        descriptionLayout.setOnClickListener(this);
        peoepleLayout.setOnClickListener(this);
        chargeLayout.setOnClickListener(this);
        nameText = (TextView) findViewById(R.id.name_text);
        descriptionText = (TextView) findViewById(R.id.description_text);
        peopleText = (TextView) findViewById(R.id.people_text);
        chargeText = (TextView) findViewById(R.id.charge_text);
    }

    @Override
    public void onClick(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View view = layoutInflater.inflate(R.layout.dialog_edit_text, null);
        final EditText input = (EditText) view.findViewById(R.id.edit_text);
        alert.setView(view);
        alert.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        if (v == imageView) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE);
        } else if (v == nameLayout) {
            alert.setTitle("이름");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    name = input.getText().toString();
                    nameText.setText(name);
                }
            });
            alert.show();
        } else if (v == descriptionLayout) {
            alert.setTitle("소개");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    description = input.getText().toString();
                    descriptionText.setText(description);
                }
            });
            alert.show();
        }else if (v == peoepleLayout) {
            input.setInputType(2);
            alert.setTitle("인원");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    people = Integer.parseInt(input.getText().toString());
                    peopleText.setText(String.valueOf(people));
                }
            });
            alert.show();
        } else if (v == chargeLayout) {
            input.setInputType(2);
            alert.setTitle("요금");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    charge = Integer.parseInt(input.getText().toString());
                    chargeText.setText(String.valueOf(charge));
                }
            });
            alert.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                InputStream stream = getContentResolver().openInputStream(
                        data.getData());
                bitmap = BitmapFactory.decodeStream(stream);
                stream.close();
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        else if(requestCode == REQUEST_LOCATION && resultCode == Activity.RESULT_OK){
            latLng = data.getParcelableExtra("latlng");
            map.clear();
            map.addMarker(new MarkerOptions().position(latLng));
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
        super.onActivityResult(requestCode, resultCode, data);
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        map.getUiSettings().setAllGesturesEnabled(false);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
        } else {

        }
        LatLng jeju = new LatLng(latitude, longitude);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(jeju, 15));

    }

    @Override
    public void onMapClick(LatLng latLng) {
        Intent intent = new Intent(RegistrationActivity.this, SelectLocationActivity.class);
        startActivityForResult(intent, REQUEST_LOCATION);
    }

}