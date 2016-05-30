package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.activity.RegistrationActivity;

/**
 * Created by Kim on 2016-04-23.
 */
public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.registration_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}