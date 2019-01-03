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

import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.widget.AbsTowListsMenuContent;

public class MultiSelectTowRowMenuContent1 extends AbsTowListsMenuContent {
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;


    public MultiSelectTowRowMenuContent1(Context context) {
        super(context);
        initView(context);
    }

    @Override
    protected void onSelectItems(List<IFlexible> list1, List<IFlexible> list2) {
        if (list2.size() > 0) {
            setMenuTitle(TextUtils.join(",", list2));
        }else{
            setMenuTitle(TextUtils.join(",", list1));
        }
    }

    @Override
    protected void hideList2() {
        recyclerView2.setVisibility(View.GONE);
    }

    @Override
    protected void showList2() {

        recyclerView2.setVisibility(View.VISIBLE);
    }

    View reset;
    View submit;

    public void initView(Context context) {
        setContentView(R.layout.multiselect_towrowlist_layout);
        View content = findViewById(R.id.content);
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView1.setAdapter(getAdapter1());

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(getAdapter2());

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
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = content.getLayoutParams();//
        layoutParams.height = (int) (heightPixels * 0.6);
        content.requestLayout();

    }

    @Override
    public void loadData() {
        final ArrayList<Item1> lists4 = dd1();
        for (Item1 item1 : lists4) {
            ArrayList<Item1> subList = item1.getSubregions();
            if (subList != null && subList.size() > 0) {
                Item1 i = new Item1();
                i.setName("全" + item1.getName());
                subList.add(0, i);
            }
        }
        Item1 nomit = new Item1();
        nomit.setName("不限");
        nomit.setMenuItemTag(getDefaultMenuTitle());
        lists4.add(0, nomit);
        getAdapter1().addItems(lists4);
        getAdapter1().notifyDataSetChanged();
        show();
    }


    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }


}
