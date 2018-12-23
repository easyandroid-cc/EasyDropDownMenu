package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.EasyDropDownMenuContent;
import cc.easyandroid.menu.IMenu;
import cc.easyandroid.menu.IMenuHandle;

public class ComplexMenuContentLayout2 extends EasyDropDownMenuContent implements EasyFlexibleAdapter.OnItemClickListener {
    public ComplexMenuContentLayout2(Context context) {
        super(context);
        onViewCreated(this);

    }

    View reset;
    View submit;
    RecyclerView recyclerView;
    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter(this);
    final Model mModel = new Model();

    public void onViewCreated(View context) {
        recyclerView = findViewById(R.id.recyclerview);
        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
        adapter.setMode(EasyFlexibleAdapter.MODE_MULTI);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearSelection();
            }
        });
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.setChildSelectIndex(adapter.getSelectedPositions());
                setMenuTitle(TextUtils.join(",", adapter.getSelectedPositions()));
                hide();
            }
        });

    }

    @Override
    protected int getResourcesId() {
        return R.layout.complexmultiselectlist_layout;
    }


    @Override
    public void onShow() {
        adapter.clearSelection();
        for (int position : mModel.selectPosition) {
            adapter.addSelection(position);
            adapter.notifyItemChanged(position);
        }
    }

    @Override
    public boolean isEmpty() {
        return adapter.isEmpty();
    }

    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
        return lists;
    }

    @Override
    public boolean onItemClick(View view, int position) {
        return adapter.isEmpty();
    }

    @Override
    public void loadData() {
        final ArrayList<Item2> lists4 = dd2();
        for (Item2 item2 : lists4) {
            adapter.addItem(item2);
            adapter.addItems(item2.getSubregions());
        }
        adapter.notifyDataSetChanged();
        show();
    }

    public class Model {
        public List<Integer> selectPosition = new ArrayList<>();

        public void setChildSelectIndex(List<Integer> child) {
            this.selectPosition.clear();
            this.selectPosition.addAll(child);
        }
    }
}
