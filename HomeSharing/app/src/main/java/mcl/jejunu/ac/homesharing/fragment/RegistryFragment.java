package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mcl.jejunu.ac.homesharing.R;

/**
 * Created by Kim on 2016-04-23.
 */
public class RegistryFragment extends Fragment {

    private Button registrationButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registry, container, false);
        registrationButton = (Button) view.findViewById(R.id.registration_button);
        return view;
    }

}