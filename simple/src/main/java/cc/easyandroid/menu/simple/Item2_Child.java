package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;

public class Item2_Child extends Text2.ResultEntity implements IFlexible<Item2_Child.ListViewHolder> {


    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {
    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void setSelectable(boolean b) {

    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return spanCount;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_menu_gridview;
    }


    @Override
    public ListViewHolder createViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, LayoutInflater inflater, ViewGroup viewGroup) {
        return new ListViewHolder(inflater.inflate(getLayoutRes(), viewGroup, false), easyFlexibleListViewAdapter);
    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, ListViewHolder viewHolder, int i, List list) {
        viewHolder.setData(this);
    }

    public class ListViewHolder extends FlexibleViewHolder {
        TextView textView;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = header_ad.findViewById(R.id.item_title_tv);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
        }

        public void setData(final Item2_Child entity) {
            textView.setText(entity.getName());

        }
    }

}
