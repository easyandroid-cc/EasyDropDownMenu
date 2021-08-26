package cc.easyandroid.menu.simple.view;

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
import cc.easyandroid.menu.simple.pojo.Item2;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text2;

@Deprecated
public class ComplexMenuContentLayout extends RelativeLayout {
    RecyclerView recyclerView;
    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter();
    final Model mModel = new Model();
    public ComplexMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    public ComplexMenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ComplexMenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    View reset;
    View submit;
    Toast toast;
    public void initView(Context context) {
        View.inflate(context, R.layout.complexmultiselectlist_layout, this);
        recyclerView = findViewById(R.id.recyclerview);
        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
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
                if (toast == null) {
                    toast = Toast.makeText(getContext(), TextUtils.join(",", adapter.getSelectedPositions()), Toast.LENGTH_SHORT);
                }
                toast.setText(TextUtils.join(",", adapter.getSelectedPositions()));
                toast.show();
            }
        });
        final ArrayList<Item2> lists4 = dd2();
        adapter.addItems(lists4);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
        return lists;
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
