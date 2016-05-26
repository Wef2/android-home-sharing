package mcl.jejunu.ac.homesharing.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by neo-202 on 2016-05-26.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private int[] imageResources = {android.support.design.R.drawable.abc_ab_share_pack_mtrl_alpha, android.support.design.R.drawable.abc_action_bar_item_background_material};

    private Context context;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        return new Object();
    }


}