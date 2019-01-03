package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.widget.AbsSingleRowMenuContent;

public class MultiSelectSingleRowMenuContentLayout extends AbsSingleRowMenuContent {
    public MultiSelectSingleRowMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    @Override
    protected void onSelectItems(List<IFlexible> list) {
        setMenuTitle(TextUtils.join(",", list));
    }

    private View reset;
    private View submit;
    private RecyclerView recyclerView;

    public void initView(Context context) {
        setContentView(R.layout.multiselectlist_layout);
        View view = findViewById(R.id.content);
        getAdapter().setMode(EasyFlexibleAdapter.MODE_MULTI);
        recyclerView = findViewById(R.id.recyclerview);
        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(getAdapter());
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();//
        layoutParams.height = (int) (heightPixels * 0.6);
        view.requestLayout();
    }

    @Override
    public void loadData() {
        final ArrayList lists4 = dd1();
        Item1 item1 = new Item1();
        item1.setName("不限");
        item1.setMenuItemTag(getDefaultMenuTitle());
        lists4.add(0, item1);
        setMenuDatas(lists4, true, 0);
    }

    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    @Override
    public boolean onItemClick(View view, int position) {
        if (position == 0) {
            getAdapter().clearSelection();
            getAdapter().setItemChecked(position, true);
        } else {
            if (getAdapter().isSelectable(0)) {
                getAdapter().setItemChecked(0, false);
            }
        }
        return true;
    }

}
