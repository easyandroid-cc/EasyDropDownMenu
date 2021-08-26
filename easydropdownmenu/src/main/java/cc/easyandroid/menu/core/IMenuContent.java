package cc.easyandroid.menu.core;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.items.IFlexible;

public interface IMenuContent {
//    void bandMenu(IMenu menu);
//
//    void loadData();
//
//    boolean isEmpty();

    ViewGroup getContentView();

    void setMenuDatas(ArrayList<IFlexible> items, boolean needShow, Integer... defaultPosition);

    void setListener(Listener listener);

    interface Listener {
        void onSelectItems(List<IFlexible>... list1);
    }

}
