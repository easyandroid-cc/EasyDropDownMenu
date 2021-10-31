package cc.easyandroid.menu.simple.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.core.EasyDropDownMenuContent;
import cc.easyandroid.menu.simple.pojo.Item1;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text1;

@Deprecated
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
        setContentView(R.layout.multiselect_towrowlist_layout);
        adapter1.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        adapter2.setMode(EasyFlexibleAdapter.MODE_MULTI);
        View content = findViewById(R.id.content);
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
                       // setMenuTitle(adapter1.getItem(list1.get(0)).toString());
                    }
                } else if (selectedPositions.size() == 1) {
                    //setMenuTitle(adapter2.getItem(selectedPositions.get(0)).toString());
                } else if (selectedPositions.size() > 1) {
                    List<IFlexible> list = new ArrayList<>();
                    for (int index : selectedPositions) {
                        IFlexible iFlexible = adapter2.getItem(index);
                        list.add(iFlexible);
                    }
                   // setMenuTitle(TextUtils.join(",", list));
                }
                //hide();
            }
        });
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = content.getLayoutParams();//
        layoutParams.height = (int) (heightPixels * 0.6);
        content.requestLayout();

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

    @Override
    public ViewGroup getContentView() {
        return null;
    }

    @Override
    public void setMenuDatas(ArrayList<IFlexible> items, boolean needShow, Integer... defaultPosition) {

    }

    @Override
    public void setListener(Listener listener) {

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
