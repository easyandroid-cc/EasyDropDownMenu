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

public class MultiSelectTowRowMenuContentLayout extends EasyDropDownMenuContent {
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    EasyFlexibleAdapter adapter1 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            if (recyclerView2.getTag() != null && recyclerView2.getTag().toString().equals(String.valueOf(position))) {
                return true;//前面点击过了
            }
            Item1 item1 = (Item1) adapter1.getItem(position);
            List<Item1> subList = item1.getSubregions();
            if (subList != null && subList.size() > 0) {
                adapter2.clearSelection();
                adapter2.setItems(subList);
                adapter2.addSelection(0);
                adapter2.notifyDataSetChanged();
                recyclerView2.setVisibility(View.VISIBLE);
                recyclerView2.setTag(position);
            } else {
                adapter2.clearSelection();
                adapter2.clearItems();
                recyclerView2.setVisibility(View.GONE);
                recyclerView2.setTag(null);
            }
            return false;
        }
    });
    EasyFlexibleAdapter adapter2 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            if (position == 0) {
                adapter2.clearSelection();
                adapter2.setItemChecked(0, true);
            } else {
                if (adapter2.isSelectable(0)) {
                    adapter2.setItemChecked(0, false);
                }
            }
            return false;
        }
    });

    final Model mModel = new Model();

    public MultiSelectTowRowMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    View reset;
    View submit;

    public void initView(Context context) {
        adapter1.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        adapter2.setMode(EasyFlexibleAdapter.MODE_MULTI);
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView1.setAdapter(adapter1);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(adapter2);

        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        mModel.setFristSelectPosition(0);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

            }
        });
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter1.getSelectedPositions().size() > 0) {
                    mModel.setFristSelectPosition(adapter1.getSelectedPositions().get(0));
                }
                List<Integer> selectedPositions = adapter2.getSelectedPositions();
                mModel.setChildSelectIndex(selectedPositions);
                if (selectedPositions.size() == 0) {
                    List<Integer> list1 = adapter1.getSelectedPositions();
                    if (list1.size() >= 1) {
                        setMenuTitle(adapter1.getItem(list1.get(0)).toString());
                    }
                } else if (selectedPositions.size() == 1) {
                    setMenuTitle(adapter2.getItem(selectedPositions.get(0)).toString());
                } else if (selectedPositions.size() > 1) {
                    List<IFlexible> list = new ArrayList<>();
                    for (int index : selectedPositions) {
                        IFlexible iFlexible = adapter2.getItem(index);
                        list.add(iFlexible);
                    }
                    setMenuTitle(TextUtils.join(",", list));
                }
                hide();
            }
        });


    }

    @Override
    protected int getResourcesId() {
        return R.layout.multiselect_towrowlist_layout;
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
        adapter1.addItems(lists4);
        adapter1.notifyDataSetChanged();
        show();
    }

    @Override
    public boolean isEmpty() {
        return adapter1.isEmpty();
    }


    protected void reset() {
        adapter1.clearSelection();
        adapter1.addSelection(0);
        adapter1.notifyItemChanged(0);
        adapter1.mItemClickListener.onItemClick(null, 0);
        adapter2.clearSelection();
    }

    @Override
    public void onShow() {
        super.onShow();
        adapter1.clearSelection();
        adapter2.clearSelection();
        adapter1.addSelection(mModel.fristSelectPosition);
        adapter1.mItemClickListener.onItemClick(null, mModel.fristSelectPosition);
        adapter1.notifyItemChanged(mModel.fristSelectPosition);

        for (int position : mModel.towSelectPosition) {
            adapter2.addSelection(position);
            adapter2.notifyItemChanged(position);
        }
    }

    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    public class Model {
        public List<Integer> towSelectPosition = new ArrayList<>();
        public int fristSelectPosition = -1;

        public Model() {
        }

        public void setChildSelectIndex(List<Integer> child) {
            this.towSelectPosition.clear();
            this.towSelectPosition.addAll(child);
        }

        public void setFristSelectPosition(int position) {
            this.fristSelectPosition = position;
            this.towSelectPosition.clear();
        }
    }
}
