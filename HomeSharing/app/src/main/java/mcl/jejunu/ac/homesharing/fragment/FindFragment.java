package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.activity.HomeInformationActivity;
import mcl.jejunu.ac.homesharing.adapter.HomeListAdapter;
import mcl.jejunu.ac.homesharing.model.HomeModel;

/**
 * Created by Kim on 2016-04-23.
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    private ArrayList<HomeModel> homeList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        homeList = new ArrayList<>();
        homeList.add(new HomeModel());

        adapter = new HomeListAdapter(homeList, this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View v) {
        HomeModel homeModel = (HomeModel)v.getTag();
        Intent intent = new Intent(getActivity(), HomeInformationActivity.class);
        intent.putExtra("id", homeModel.getId());
        startActivity(intent);
    }
}
