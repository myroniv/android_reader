package com.example.asus.android_reader.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.asus.android_reader.domain.entities.Page;
import com.example.asus.android_reader.ui.PageFragment;

import java.util.List;

/**
 * Created by Asus on 09.10.2017.
 */

public class PageFragmentAdapter extends FragmentStatePagerAdapter {


    private List<Page> pages;

    public PageFragmentAdapter(FragmentManager fm,List<Page> pages) {
        super(fm);
        this.pages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(pages.get(position));
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }
}
