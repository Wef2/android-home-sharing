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
public class FindFragment extends Fragment {

    private ArrayList<HomeModel> homeList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        homeList = new ArrayList<>();
        homeList.add(new HomeModel());

        adapter = new HomeListAdapter(homeList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeInformationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
