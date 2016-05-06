package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.adapter.HomeListAdapter;
import mcl.jejunu.ac.homesharing.model.HomeModel;

/**
 * Created by Kim on 2016-04-23.
 */
public class FindFragment extends Fragment {

    private static FindFragment newInstance = null;

    private ArrayList<HomeModel> homeList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static FindFragment getInstance(){
        if(newInstance == null){
            newInstance = new FindFragment();
        }
        return newInstance;
    }

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
        return view;
    }

}
