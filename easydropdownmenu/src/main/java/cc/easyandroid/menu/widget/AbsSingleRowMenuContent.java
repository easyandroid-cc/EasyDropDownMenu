package cc.easyandroid.menu.widget;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.menu.core.EasyDropDownMenuContent;

public class AbsSingleRowMenuContent extends EasyDropDownMenuContent implements EasyFlexibleAdapter.OnItemClickListener {
    public AbsSingleRowMenuContent(Context context) {
        super(context);
    }

    protected final EasyFlexibleAdapter ADAPTER = new EasyFlexibleAdapter(this);
    protected final List<Integer> selectedPositions = new ArrayList<>();


    protected void reset() {
        ADAPTER.clearSelection();
    }

    protected void submit() {
        List<Integer> newSelectedPositions = ADAPTER.getSelectedPositions();
        selectedPositions.clear();
        selectedPositions.addAll(newSelectedPositions);
        List<IFlexible> list = new ArrayList<>();
        for (int index : newSelectedPositions) {
            IFlexible iFlexible = ADAPTER.getItem(index);
            list.add(iFlexible);
        }
        onSelectItems(list);
        hide();
    }

    protected void onSelectItems(List<IFlexible> list) {//自己調用設置titie
    }

    public EasyFlexibleAdapter getAdapter() {
        return ADAPTER;
    }

    @Override
    public void onShow() {
        ADAPTER.clearSelection();
        for (int position : selectedPositions) {
            ADAPTER.addSelection(position);
            ADAPTER.notifyItemChanged(position);
        }
    }

    public void setDefaultSelectedPositions(Integer... positions) {
        selectedPositions.addAll(Arrays.asList(positions));
    }

    @Override
    public boolean isEmpty() {
        return ADAPTER.isEmpty();
    }


    @Override
    public boolean onItemClick(View view, int position) {
        return true;
    }

    @Override
    public void loadData() {//没有数据会走这里

    }

    public void setMenuDatas(ArrayList<IFlexible> items, boolean needShow, Integer... defaultPosition) {
        ADAPTER.addItems(items);
        selectedPositions.addAll(Arrays.asList(defaultPosition));
        ADAPTER.notifyDataSetChanged();
        if (needShow) {
            show();
        }
    }

}
