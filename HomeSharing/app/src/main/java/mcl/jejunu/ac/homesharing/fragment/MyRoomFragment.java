package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.activity.RegistrationActivity;
import mcl.jejunu.ac.homesharing.activity.ReservationListActivity;
import mcl.jejunu.ac.homesharing.adapter.MyRoomListAdapter;
import mcl.jejunu.ac.homesharing.model.Home;

/**
 * Created by Kim on 2016-04-23.
 */
public class MyRoomFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Home> myHomeList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_home, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.registration_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        myHomeList = new ArrayList<>();
        myHomeList.add(new Home());
        adapter = new MyRoomListAdapter(myHomeList, this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        Home homeModel = (Home) v.getTag();
        Intent intent = new Intent(getActivity(), ReservationListActivity.class);
        intent.putExtra("id", homeModel.getId());
        startActivity(intent);

    }
}