package com.example.dell.picassodemo.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.dell.picassodemo.ConnectionManager;
import com.example.dell.picassodemo.R;

import java.util.List;

/**
 * Created by Dell on 4/15/2017.
 */
public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.MyViewHolder> {

    List<Post> mPostList;
    Context mContext;
    MyListItemClickListener myListItemClickListener;

    public RedditAdapter(List<Post> PostList, Context context, MyListItemClickListener listener) {
        mPostList = PostList;
        mContext = context;
        myListItemClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Post post = mPostList.get(position);

        holder.textView.setText(post.getTitle());

        if (!TextUtils.isEmpty(post.getThumbnail())) {
            ((View) holder.networkImageView.getParent()).setVisibility(View.VISIBLE);
            holder.networkImageView.setImageUrl(post.getThumbnail(), ConnectionManager.getImageLoader(mContext));
        } else {
            ((View) holder.networkImageView.getParent()).setVisibility(View.GONE);

        }


    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public static interface MyListItemClickListener {

        void OnItemClick(Post itemClicked);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        NetworkImageView networkImageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.rowTextview);

            networkImageView = (NetworkImageView) itemView.findViewById(R.id.networkimageview);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myListItemClickListener.OnItemClick(mPostList.get(getPosition()));
        }
    }


}
