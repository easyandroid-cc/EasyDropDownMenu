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
import cc.easyandroid.listfiltermenu.simple.R;

public class MultiSelectTowRowMenuContentLayout extends RelativeLayout {
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    EasyFlexibleAdapter adapter1 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            Item1 item1 = (Item1) adapter1.getItem(position);
            List<Item1> subList = item1.getSubregions();
            if (subList != null && subList.size() > 0) {
                adapter2.setItems(subList);
                adapter2.notifyDataSetChanged();
                recyclerView2.setVisibility(View.VISIBLE);
            } else {
                recyclerView2.setVisibility(View.GONE);
            }
            return false;
        }
    });
    EasyFlexibleAdapter adapter2 = new EasyFlexibleAdapter(new EasyFlexibleAdapter.OnItemClickListener() {
        @Override
        public boolean onItemClick(View view, int position) {
            return false;
        }
    });

    final Model mModel = new Model();

    public MultiSelectTowRowMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    public MultiSelectTowRowMenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MultiSelectTowRowMenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    View reset;
    View submit;
    Toast toast;

    public void initView(Context context) {
        adapter1.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        adapter2.setMode(EasyFlexibleAdapter.MODE_MULTI);
        View.inflate(context, R.layout.multiselect_towrowlist_layout, this);
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
                mModel.setChildSelectIndex(adapter2.getSelectedPositions());
                if (toast == null) {
                    toast = Toast.makeText(getContext(), TextUtils.join(",", adapter1.getSelectedPositions()), Toast.LENGTH_SHORT);
                }
                toast.setText(TextUtils.join(",", adapter1.getSelectedPositions()));
                toast.show();
            }
        });


        final ArrayList<Item1> lists4 = dd1();
        adapter1.addItems(lists4);
        adapter1.notifyDataSetChanged();
    }


    protected void reset() {
        adapter1.clearSelection();
        adapter1.addSelection(0);
        adapter1.notifyItemChanged(0);
        adapter2.clearSelection();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        adapter1.clearSelection();
        adapter2.clearSelection();
        adapter1.addSelection(mModel.fristSelectPosition);
        adapter1.notifyItemChanged(mModel.fristSelectPosition);

        for (int position : mModel.towSelectPosition) {
            adapter2.addSelection(position);
            adapter2.notifyItemChanged(position);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }

    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
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
            // this.towSelectPosition.add(0);
        }
    }
}
