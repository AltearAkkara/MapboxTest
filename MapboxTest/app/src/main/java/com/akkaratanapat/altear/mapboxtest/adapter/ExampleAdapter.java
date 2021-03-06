package com.akkaratanapat.altear.mapboxtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akkaratanapat.altear.mapboxtest.MainActivity;
import com.akkaratanapat.altear.mapboxtest.R;
import com.akkaratanapat.altear.mapboxtest.model.ExampleItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ExampleItemModel> dataSource;
    private Context mContext;

    public ExampleAdapter(Context context, List<ExampleItemModel> dataSource) {
        this.dataSource = dataSource;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_description_item, parent, false);

        switch (viewType) {
            case 0: return new ViewHolder(view);
            case 1: return new ViewHolderDescription(view1);
        }

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(((MainActivity)mContext).getCurrentCategory() == R.id.nav_lab && position == 0){
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ExampleItemModel detailItem = dataSource.get(position);
                ViewHolder viewHolder = (ViewHolder) holder;

                String imageUrl = mContext.getString(detailItem.getImageUrl());

                if (!imageUrl.isEmpty()) {
                    Picasso.with(mContext)
                            .load(imageUrl)
                            .into(viewHolder.imageView);
                } else {
                    viewHolder.imageView.setImageDrawable(null);
                }

                viewHolder.titleTextView.setText(mContext.getString(detailItem.getTitle()));
                viewHolder.descriptionTextView.setText(mContext.getString(detailItem.getDescription()));
        }
    }

    @Override
    public int getItemCount() {
        return (null != dataSource ? dataSource.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.example_image);
            titleTextView = (TextView) itemView.findViewById(R.id.example_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.example_description);
        }
    }

    public static class ViewHolderDescription extends RecyclerView.ViewHolder {

        public ViewHolderDescription(final View itemView) {
            super(itemView);
        }
    }
}