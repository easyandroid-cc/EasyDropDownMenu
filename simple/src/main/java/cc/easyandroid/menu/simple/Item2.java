package cc.easyandroid.menu.simple;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;

public class Item2 extends Text2.ResultEntity implements IFlexible<Item2.ListViewHolder>  {
    HashMap<String, String> para = new HashMap<>();



    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setEnabled(boolean b) {
    }

    @Override
    public boolean isSelectable() {
        return false;
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
        return R.layout.list_item_2;
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
        GridView gridview;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = (TextView) header_ad.findViewById(R.id.easyListFilter_ItemDisplayName);
            gridview = (GridView) header_ad.findViewById(R.id.gridview);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
//            Toast.makeText(view.getContext(), "po" + position, Toast.LENGTH_SHORT).show();
        }

        public void setData(final Item2 entity) {
            textView.setText(entity.getName());
            GridViewAdapter gridViewAdapter = new GridViewAdapter(getContentView().getContext());
            gridViewAdapter.addAll(entity.getSubregions());
            gridview.setAdapter(gridViewAdapter);
          // gridview.setItemChecked(entity.getEasyItemManager().getChildSelectTempPosition(), true);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SparseBooleanArray sparseBooleanArray = gridview.getCheckedItemPositions();
                    boolean currentPositionSelected = sparseBooleanArray.get(position, false);
                   // entity.getEasyItemManager().setChildSelectTempPosition(currentPositionSelected ? position : -1);
                }
            });
        }
    }

    public class GridViewAdapter extends BaseAdapter {
        Context mContext;

        LayoutInflater mInflater;

        List<IFlexible> items;

        public GridViewAdapter(Context context) {
            items = new ArrayList<>();
            this.mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        public void addAll(List<? extends IFlexible> easyitems) {
            if (easyitems != null && easyitems.size() > 0) {
                items.addAll(easyitems);
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public IFlexible getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridViewAdapterHolder holder;
            if (convertView == null || convertView.getTag() == null) {
                convertView = mInflater.inflate(R.layout.item_menu_gridview, parent, false);
                holder = new GridViewAdapterHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (GridViewAdapterHolder) convertView.getTag();
            }
            IFlexible easyItem = getItem(position);
           // holder.item_title_tv.setText(easyItem.getEasyItemTag());
            return convertView;
        }

        class GridViewAdapterHolder {
            TextView item_title_tv;

            public GridViewAdapterHolder(View view) {
                item_title_tv = (TextView) view.findViewById(R.id.item_title_tv);
            }
        }
    }


}
