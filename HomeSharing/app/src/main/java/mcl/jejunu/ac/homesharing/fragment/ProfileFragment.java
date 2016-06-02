package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.activity.RegistrationActivity;

/**
 * Created by Kim on 2016-04-23.
 */
public class ProfileFragment extends Fragment {

    private int userId = 2;
    private TextView nicknameText, descriptionText;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.registration_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nicknameText = (TextView) view.findViewById(R.id.nickname_text);
        descriptionText = (TextView) view.findViewById(R.id.description_text);
        progressDialog = new ProgressDialog(getActivity());
        new UserInformationRequestTask().execute();
        return view;
    }

    private class UserInformationRequestTask extends AsyncTask<Void, Void, String> {

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
                String url = "http://61.99.246.80:8080/user/"+userId;
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
                nicknameText.setText(jsonObject.getString("nickname"));
                descriptionText.setText(jsonObject.getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }
}