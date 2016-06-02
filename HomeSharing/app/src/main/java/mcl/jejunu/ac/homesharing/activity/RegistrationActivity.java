package mcl.jejunu.ac.homesharing.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import mcl.jejunu.ac.homesharing.R;

/**
 * Created by neo-202 on 2016-05-26.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private int userId = 2;

    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_LOCATION = 2;

    private GoogleMap map;
    private double latitude = 33.499234, longitude = 126.530714;
    private LatLng latLng = null;

    private String name = "나의 집", description = "안녕하세요";
    private int people = 1, charge = 0;

    private ImageView imageView;
    private Bitmap bitmap;
    private File imageFile;
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
                    peopleText.setText(String.valueOf(people) + "명");
                }
            });
            alert.show();
        } else if (v == chargeLayout) {
            input.setInputType(2);
            alert.setTitle("요금");
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    charge = Integer.parseInt(input.getText().toString());
                    chargeText.setText(String.valueOf(charge) + "원");
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
                Uri uri = data.getData();
                String imagePath = getRealPathFromURI(uri);
                imageFile = new File(imagePath);
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
                new AddHomeTask().execute();
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

    private class AddHomeTask extends AsyncTask<Void, Void, String> {

        private MultiValueMap<String, String> message;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            message = new LinkedMultiValueMap<String, String>();
            message.add("user_id", String.valueOf(userId));
            message.add("name", name);
            message.add("description", description);
            message.add("people", String.valueOf(people));
            message.add("charge", String.valueOf(charge));
            message.add("latitude", String.valueOf(latLng.latitude));
            message.add("longitude", String.valueOf(latLng.longitude));
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                String url = "http://61.99.246.80:8080/home/add";
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
            try {
                JSONObject jsonObject = new JSONObject(result);
                new AddImageTask(jsonObject.getInt("id")).execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private class AddImageTask extends AsyncTask<Void, Void, String> {

        private int homeId;

        public AddImageTask(int homeId){
            this.homeId = homeId;
        }

        private MultiValueMap<String, Object> message;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            message = new LinkedMultiValueMap<String, Object>();
            message.add("file", new FileSystemResource(imageFile));
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                formHttpMessageConverter.setCharset(Charset.forName("UTF8"));

                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters().add(formHttpMessageConverter);
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

                String uri = "http://61.99.246.80:8080/imageupload/home/"+homeId;

                MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
                map.add("file", new FileSystemResource(imageFile));
                map.add("filename", imageFile.getName());

                HttpHeaders imageHeaders = new HttpHeaders();
                imageHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

                HttpEntity<MultiValueMap<String, Object>> imageEntity = new HttpEntity<MultiValueMap<String, Object>>(map, imageHeaders);

                String string = restTemplate.postForObject(uri, imageEntity, String.class);
                return string;
            }
            catch (Exception e){

            }
            return "";
        }

        @Override
        protected void onPostExecute(String result){
            Log.i("Result", result);
        }

    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}