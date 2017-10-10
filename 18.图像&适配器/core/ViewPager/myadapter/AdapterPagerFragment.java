package myadapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class AdapterPagerFragment extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public AdapterPagerFragment(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return  mFragments.size();
    }


}
