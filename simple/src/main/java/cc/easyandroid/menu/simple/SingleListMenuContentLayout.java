package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.EasyDropDownMenuContent;

public class SingleListMenuContentLayout extends EasyDropDownMenuContent implements EasyFlexibleAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter(this);

    public SingleListMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }


    public void initView(Context context) {
        adapter.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected int getResourcesId() {
        return R.layout.singlelist_layout;
    }

    @Override
    public void loadData() {
        final ArrayList<Item1> lists4 = dd1();
        Item1 item1 = new Item1();
        item1.setName("不限");
        item1.setMenuItemTag(getDefaultMenuTitle());
        lists4.add(0, item1);
        adapter.addItems(lists4);
        adapter.notifyDataSetChanged();
        show();
    }

    @Override
    public boolean isEmpty() {
        return adapter.isEmpty();
    }


    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    @Override
    public boolean onItemClick(View view, int position) {
        System.out.println("cgp size =" + adapter.getSelectedPositions().size());
        Item1 item1 = (Item1) adapter.getItem(position);
        setMenuTitle(item1.getMenuItemTag());
        hide();
        return false;
    }
}
