package com.deltastudio.ran.delta.bb;

import android.support.v4.app.Fragment;

import com.deltastudio.ran.delta.intro.TestFragment;
import com.deltastudio.ran.deltalibrary.widget.fragment.DeltaFragmentManagerAdapter;

/**
 * Created by Ran on 7/6/2016.
 */
public class FragmentAdapter implements DeltaFragmentManagerAdapter {

    private static final String TABS[] = {"Chats", "Contacts", "Circle", "Me"};

    @Override
    public Fragment onCreateFragment(int position) {

        return TestFragment.newInstance(String.valueOf(position));
    }

    @Override
    public String getTag(int position) {
        return TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}