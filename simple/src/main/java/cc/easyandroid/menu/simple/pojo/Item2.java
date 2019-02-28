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
import cc.easyandroid.easyrecyclerview.items.IHeaderSpanFill;
import cc.easyandroid.listfiltermenu.simple.R;

public class Item2 extends Text2.ResultEntity implements IFlexible<Item2.ListViewHolder> ,IHeaderSpanFill {
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
        return 1;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_2;
    }

    @Override
    public String toString() {
        return getName();
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
      //  RecyclerView gridview;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = header_ad.findViewById(R.id.easyListFilter_ItemDisplayName);
           // gridview = header_ad.findViewById(R.id.gridview);
           // gridview.setLayoutManager(new GridLayoutManager(header_ad.getContext(),3));
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
//            Toast.makeText(view.getContext(), "po" + position, Toast.LENGTH_SHORT).show();
        }

        public void setData(final Item2 entity) {
            textView.setText(entity.getName());
//            EasyFlexibleAdapter gridViewAdapter = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
//                @Override
//                public boolean onItemClick(View view, int position) {
//                    return false;
//                }
//            });
//            ArrayList<Item2_Child> item2s=entity.getSubregions();
//            gridViewAdapter.setMode(EasyFlexibleAdapter.MODE_MULTI);
//              gridview.setAdapter(gridViewAdapter);
//            if(item2s!=null){
//                gridViewAdapter.setItems(item2s);
//                gridViewAdapter.notifyDataSetChanged();
//            }
            // gridview.setItemChecked(entity.getEasyItemManager().getChildSelectTempPosition(), true);

        }
    }


}
