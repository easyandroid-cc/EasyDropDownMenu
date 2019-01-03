package cc.easyandroid.menu.simple;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.widget.AbsSingleRowMenuContent;

public class SingleListMenuContentLayout extends AbsSingleRowMenuContent {
    RecyclerView recyclerView;

    public SingleListMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    @Override
    protected void onSelectItems(List<IFlexible> list) {
        setMenuTitle(TextUtils.join(",", list));
    }

    View edittext;

    public void initView(Context context) {
        setContentView(R.layout.singlelist_layout);
        getAdapter().setMode(EasyFlexibleAdapter.MODE_SINGLE);
        View view = findViewById(R.id.content);
        edittext = findViewById(R.id.edittext);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(getAdapter());
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();//
        layoutParams.height = (int) (heightPixels * 0.6);
        view.requestLayout();
    }

    @Override
    protected void onHide() {
        super.onHide();
    }

    @Override
    public void loadData() {
        final ArrayList<Item1> lists4 = dd1();
        Item1 item1 = new Item1();
        item1.setName("不限");
        item1.setMenuItemTag(getDefaultMenuTitle());
        lists4.add(0, item1);
        getAdapter().addItems(lists4);
        getAdapter().notifyDataSetChanged();
        show();
    }

    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    @Override
    public boolean onItemClick(View view, int position) {
        getAdapter().setItemChecked(position,true);
        submit();
        return true;
    }

}
