package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.EasyDropDownMenuContent;

public class MultiSelectSingleRowMenuContentLayout extends EasyDropDownMenuContent implements EasyFlexibleAdapter.OnItemClickListener {
    public MultiSelectSingleRowMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    private View reset;
    private View submit;
    private RecyclerView recyclerView;
    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter(this);
    final Model mModel = new Model();

    public void initView(Context context) {
        adapter.setMode(EasyFlexibleAdapter.MODE_MULTI);
        recyclerView = findViewById(R.id.recyclerview);
        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        mModel.setDefaultSelect(0);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clearSelection();
            }
        });
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> selectedPositions = adapter.getSelectedPositions();
                mModel.setChildSelectIndex(selectedPositions);

                if (selectedPositions.size() == 1) {
                    setMenuTitle(adapter.getItem(selectedPositions.get(0)).toString());
                } else if (selectedPositions.size() > 1) {
                    List<IFlexible> list = new ArrayList<>();
                    for (int index : selectedPositions) {
                        IFlexible iFlexible = adapter.getItem(index);
                        list.add(iFlexible);
                    }
                    setMenuTitle(TextUtils.join(",", list));
                }
                hide();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected int getResourcesId() {
        return R.layout.multiselectlist_layout;
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

    @Override
    public void onShow() {
        super.onShow();
        adapter.clearSelection();
        for (int position : mModel.selectPosition) {
            adapter.addSelection(position);
            adapter.notifyItemChanged(position);
        }
    }

    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }


    @Override
    public boolean onItemClick(View view, int position) {
        System.out.println("cgp size =" + adapter.getSelectedPositions().size());
        if (position == 0) {
            adapter.clearSelection();
            adapter.setItemChecked(position, true);
        } else {
            if (adapter.isSelectable(0)) {
                adapter.setItemChecked(0, false);
            }
        }
        return false;
    }

    public class Model {
        public List<Integer> selectPosition = new ArrayList<>();

        public Model() {
        }

        public void setChildSelectIndex(List<Integer> child) {
            this.selectPosition.clear();
            this.selectPosition.addAll(child);
        }

        public void setDefaultSelect(int position) {
            selectPosition.add(position);
        }
    }
}
