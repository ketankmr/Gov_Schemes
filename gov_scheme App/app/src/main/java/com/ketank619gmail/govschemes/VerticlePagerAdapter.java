package com.ketank619gmail.govschemes;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Ketan on 21-Aug-17.
 */

public class VerticlePagerAdapter extends PagerAdapter {



    Context mContext;
    ArrayList<String> Scheme,Description,Url;
    LayoutInflater mLayoutInflater;

    public VerticlePagerAdapter(Context context,ArrayList Scheme,ArrayList Description,ArrayList Url) {
        mContext = context;
        this.Scheme=Scheme;
        this.Description= Description;
        this.Url = Url;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Scheme.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.scheme_item, container, false);

        TextView label = (TextView) itemView.findViewById(R.id.textView);
        TextView Schm = (TextView) itemView.findViewById(R.id.title);
        ImageView img = (ImageView) itemView.findViewById(R.id.imageView);
        label.setText(Description.get(position));
        Schm.setText(Scheme.get(position));

        Scheme.get(position);
        Url.get(position);

        Glide
                .with(mContext)
                .load(Url.get(position))
                .placeholder(R.drawable.loading)
                .fitCenter()
                .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                .into(img);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }





}
