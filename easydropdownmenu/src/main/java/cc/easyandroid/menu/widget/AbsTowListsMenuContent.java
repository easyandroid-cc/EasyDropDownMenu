package cc.easyandroid.menu.widget;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.menu.core.EasyDropDownMenuContent;
import cc.easyandroid.menu.core.IMenuItem;

public abstract class AbsTowListsMenuContent extends EasyDropDownMenuContent {

    protected final EasyFlexibleAdapter ADAPTER1 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            if (ADAPTER1.isSelected(position)) {
                return true;
            }
            IMenuItem item1 = (IMenuItem) ADAPTER1.getItem(position);
            List subList = item1.getChildItems();
            ADAPTER2.clearSelection();//清空选择项
            if (subList != null && subList.size() > 0) {
                ADAPTER2.setItems(subList);
                ADAPTER2.addSelection(0);
                ADAPTER2.notifyDataSetChanged();
                showList2();
            } else {
                ADAPTER2.clearItems();
                hideList2();
            }
            return true;
        }
    });
    protected final EasyFlexibleAdapter ADAPTER2 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            if (position == 0) {
                ADAPTER2.clearSelection();
                ADAPTER2.setItemChecked(0, true);
                ADAPTER2.notifyItemChanged(0);
                return false;
            } else {
                if (ADAPTER2.isSelected(0)) {
                    ADAPTER2.setItemChecked(0, false);
                    ADAPTER2.setItemChecked(position, true);
                    return false;
                }
            }
            return true;
        }
    });
    protected final List<Integer> mSelectPosition1 = new ArrayList<>();
    protected final List<Integer> mSelectposition2 = new ArrayList<>();

    public EasyFlexibleAdapter getAdapter1() {
        return ADAPTER1;
    }

    public EasyFlexibleAdapter getAdapter2() {
        return ADAPTER2;
    }

    public AbsTowListsMenuContent(Context context) {
        super(context);
        initView(context);
    }

    protected void submit() {
        mSelectPosition1.clear();
        mSelectPosition1.addAll(ADAPTER1.getSelectedPositions());

        mSelectposition2.clear();
        mSelectposition2.addAll(ADAPTER2.getSelectedPositions());

        List<IFlexible> list1 = new ArrayList<>();
        for (int index : mSelectPosition1) {
            IFlexible iFlexible = ADAPTER1.getItem(index);
            list1.add(iFlexible);
        }

        List<IFlexible> list2 = new ArrayList<>();
        for (int index : mSelectposition2) {
            IFlexible iFlexible = ADAPTER2.getItem(index);
            list2.add(iFlexible);
        }
        onSelectItems(list1, list2);
        hide();
    }

    protected abstract void onSelectItems(List<IFlexible> list1, List<IFlexible> list2);

    protected abstract void hideList2();

    protected abstract void showList2();

    private void initView(Context context) {
        ADAPTER1.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        ADAPTER2.setMode(EasyFlexibleAdapter.MODE_MULTI);
        mSelectPosition1.add(0);
    }


    protected void reset() {
        ADAPTER1.clearSelection();
        if (ADAPTER1.mItemClickListener.onItemClick(this, 0)) {
            ADAPTER1.addSelection(0);
            ADAPTER1.notifyItemChanged(0);
        }
    }

    @Override
    public void onShow() {
        super.onShow();
        ADAPTER1.clearSelection();
        ADAPTER2.clearSelection();
        for (int position : mSelectPosition1) {
            if (ADAPTER1.mItemClickListener.onItemClick(this, position)) {
                ADAPTER1.addSelection(position);
                ADAPTER2.notifyItemChanged(position);
            }
        }
        for (int position : mSelectposition2) {
            if (ADAPTER2.mItemClickListener.onItemClick(this, position)) {
                ADAPTER2.addSelection(position);
                ADAPTER2.notifyItemChanged(position);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return ADAPTER1.isEmpty();
    }
}
