package mcl.jejunu.ac.homesharing.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcl.jejunu.ac.homesharing.R;

/**
 * Created by Kim on 2016-04-23.
 */
public class FindFragment extends Fragment {

    private static FindFragment newInstance = null;

    public static FindFragment getInstance(){
        if(newInstance == null){
            newInstance = new FindFragment();
        }
        return newInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }

}
