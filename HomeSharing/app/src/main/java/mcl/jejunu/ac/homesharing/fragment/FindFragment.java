package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.activity.HomeInformationActivity;
import mcl.jejunu.ac.homesharing.adapter.HomeListAdapter;
import mcl.jejunu.ac.homesharing.model.Home;

/**
 * Created by Kim on 2016-04-23.
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Home> homeList;
    private RecyclerView recyclerView;
    private HomeListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        homeList = new ArrayList<>();

        adapter = new HomeListAdapter(homeList, this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        progressDialog = new ProgressDialog(getActivity());
        new HomeListRequestTask().execute();
        return view;
    }

    @Override
    public void onClick(View v) {
        Home homeModel = (Home) v.getTag();
        Intent intent = new Intent(getActivity(), HomeInformationActivity.class);
        intent.putExtra("id", homeModel.getId());
        startActivity(intent);
    }

    private class HomeListRequestTask extends AsyncTask<Void, Void, String> {

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
                String url = "http://61.99.246.80:8080/homes";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                String result = restTemplate.getForObject(url, String.class);
                return result;
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    Home home = new Home();
                    home.setId((int) jsonObject.get("id"));
                    home.setName((String) jsonObject.get("name"));
                    home.setPeople((int) jsonObject.get("people"));
                    home.setCharge((int) jsonObject.get("charge"));
                    homeList.add(home);
                }
                adapter.replaceWith(homeList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
