package cc.easyandroid.menu.simple.pojo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.core.IMenuItem;

/**
 * Created by cgpllx on 2016/10/17.
 */
public class Item1 extends Text1.ResultEntity implements IFlexible<Item1.ListViewHolder>, IMenuItem {

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
        return R.layout.list_item;
    }


    @Override
    public ListViewHolder createViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, LayoutInflater inflater, ViewGroup viewGroup) {
        return new ListViewHolder(inflater.inflate(getLayoutRes(), viewGroup, false), easyFlexibleListViewAdapter);
    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, ListViewHolder viewHolder, int i, List list) {
        viewHolder.setData(this);
    }

    CharSequence menuItemTag;

    public void setMenuItemTag(CharSequence menuItemTag) {
        this.menuItemTag = menuItemTag;
    }

    public CharSequence getMenuItemTag() {
        if (menuItemTag == null) {
            return getName();
        } else {
            return menuItemTag;
        }

    }

    @Override
    public List<Item1> getChildItems() {
        return getSubregions();
    }

    @Override
    public String toString() {
        return getMenuItemTag().toString();
    }

    public HashMap<String, String> getParameter() {
        return null;
    }

    public class ListViewHolder extends FlexibleViewHolder {
        TextView textView;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = (TextView) header_ad.findViewById(R.id.easyListFilter_ItemDisplayName);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
            //Toast.makeText(view.getContext(), "po" + position, Toast.LENGTH_SHORT).show();

        }

        public void setData(Item1 entity) {
            textView.setText(entity.getName());
//            if (entity.getSubregions().size() > 0) {
//            //    textView.setTextColor(getContentView().getResources().getColor(R.color.q_ff0000));
//            } else {
//               // textView.setTextColor(getContentView().getResources().getColor(R.color.colorPrimary));
//            }
        }

    }

    public Item1() {
    }

}
