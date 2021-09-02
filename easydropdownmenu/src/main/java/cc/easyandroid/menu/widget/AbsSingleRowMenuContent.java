package cc.easyandroid.menu.widget;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.SelectableAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.menu.core.EasyDropDownMenuContent;

public class AbsSingleRowMenuContent extends EasyDropDownMenuContent {
    public AbsSingleRowMenuContent(Context context) {
        super(context);
    }

    protected final EasyFlexibleAdapter ADAPTER = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, final int position) {
            if (ADAPTER.getMode() == SelectableAdapter.MODE_MULTI && INIT_SELECT_OPTION[0] >= 0) {//多选，单选不处理
                if (position == INIT_SELECT_OPTION[0]) {
                    ADAPTER.clearSelection();//toggleActivation  会在onItemClick 返回后执行
                } else {
                    if (ADAPTER.isSelected(INIT_SELECT_OPTION[0])) {
                        ADAPTER.setItemChecked(INIT_SELECT_OPTION[0], false);
                    } else if (ADAPTER.isSelected(position)) {
                        if (ADAPTER.getSelectedPositions().size() == 1) {// 最后一个选择项将要被取消选中
                            ADAPTER.setItemChecked(INIT_SELECT_OPTION[0], true);
                            ADAPTER.notifyItemChanged(INIT_SELECT_OPTION[0]);
                        }
                    }
                }
            }
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    AbsSingleRowMenuContent.this.onItemClick(position);
                }
            }, 100);
            return true;
        }
    });

    @CallSuper
    protected void onItemClick(int position) {//这里要检查是否没有选中项目

    }

    protected final List<Integer> selectedPositions = new ArrayList<>();

    protected void reset() {
        ADAPTER.clearSelection();
        ADAPTER.setItemChecked(INIT_SELECT_OPTION[0], true);
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
        //hide();
    }

    void onSelectItems(List<IFlexible> list) {//自己調用設置titie
        mListener.onSelectItems(list);
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


    public void setMenuDatas(ArrayList<IFlexible> items, boolean needShow, Integer... defaultPosition) {
        ADAPTER.setItems(items);
        selectedPositions.addAll(Arrays.asList(defaultPosition));
        for (int position : selectedPositions) {
            ADAPTER.addSelection(position);
        }
        ADAPTER.notifyDataSetChanged();
    }


}
