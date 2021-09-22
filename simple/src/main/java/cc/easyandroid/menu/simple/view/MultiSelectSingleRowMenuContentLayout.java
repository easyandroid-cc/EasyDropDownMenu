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
import cc.easyandroid.menu.simple.pojo.Item1;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text1;
import cc.easyandroid.menu.widget.AbsSingleRowMenuContent;

/**
 * 单列多选
 */
public class MultiSelectSingleRowMenuContentLayout extends AbsSingleRowMenuContent {
    public MultiSelectSingleRowMenuContentLayout(Context context) {
        super(context);
        initView(context);
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

}
