package mcl.jejunu.ac.homesharing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import mcl.jejunu.ac.homesharing.R;

/**
 * Created by neo-202 on 2016-05-26.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.viewpager_childview, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.viewpager_childimage);
        img.setImageResource(R.drawable.home);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


}