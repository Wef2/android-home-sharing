package mcl.jejunu.ac.homesharing.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.model.Home;

/**
 * Created by Kim on 2016-05-27.
 */
public class MyRoomListAdapter extends RecyclerView.Adapter<MyRoomListAdapter.ViewHolder> {
    private ArrayList<Home> homes;
    private View.OnClickListener listener;

    public MyRoomListAdapter(Collection<Home> homeModels, View.OnClickListener listener) {
        homes = new ArrayList<>();
        homes.addAll(homeModels);
        this.listener = listener;
    }

    @Override
    public MyRoomListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_home_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardView.setTag(getHome(position));
        holder.cardView.setOnClickListener(listener);
    }

    public Home getHome(int position) {
        return homes.get(position);
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
        }
    }
}

