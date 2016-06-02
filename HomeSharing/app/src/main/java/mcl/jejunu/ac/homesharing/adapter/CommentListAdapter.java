package mcl.jejunu.ac.homesharing.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.writerText.setText(getComment(position).getUser().getNickname());
        holder.contentText.setText(getComment(position).getContent());
    }

    public void replaceWith(Collection<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }

    public Comment getComment(int position) {
        return comments.get(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView writerText;
        public TextView contentText;

        public ViewHolder(View v) {
            super(v);
            writerText = (TextView) v.findViewById(R.id.writer_text);
            contentText = (TextView) v.findViewById(R.id.content_text);
        }
    }
}