package mcl.jejunu.ac.homesharing.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.model.Comment;

/**
 * Created by Kim on 2016-05-27.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {
    private ArrayList<Comment> comments;

    public CommentListAdapter(Collection<Comment> comments) {
        this.comments = new ArrayList<>();
        this.comments.addAll(comments);
    }

    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }


    public Comment getComment(int position) {
        return comments.get(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);

        }
    }
}

