package mcl.jejunu.ac.homesharing.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.model.HomeModel;

/**
 * Created by Kim on 2016-05-06.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private ArrayList<HomeModel> homes;

    public HomeListAdapter(Collection<HomeModel> homeModels) {
        homes = new ArrayList<>();
        homes.addAll(homeModels);
    }

    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView locationText;
        public TextView priceText;
        public TextView sizeText;
        public TextView ratingText;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            locationText = (TextView) v.findViewById(R.id.location_text);
            priceText  = (TextView) v.findViewById(R.id.price_text);
            sizeText  = (TextView) v.findViewById(R.id.size_text);
            ratingText = (TextView) v.findViewById(R.id.rating_text);
        }
    }
}
