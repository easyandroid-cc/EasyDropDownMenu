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

import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.simple.pojo.Item1;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text1;
import cc.easyandroid.menu.widget.AbsTowListsMenuContent;

/**
 * 两列多选
 */
public class MultiSelectTowRowMenuContent1 extends AbsTowListsMenuContent {
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;


    public MultiSelectTowRowMenuContent1(Context context) {
        super(context);
        initView(context);
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
       // content.requestLayout();

    }
}
