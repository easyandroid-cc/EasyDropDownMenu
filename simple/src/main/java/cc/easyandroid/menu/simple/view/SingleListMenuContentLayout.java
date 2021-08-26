package cc.easyandroid.menu.simple.view;

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
import cc.easyandroid.menu.simple.pojo.Item1;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text1;
import cc.easyandroid.menu.widget.AbsSingleRowMenuContent;

/**
 * 单列单选
 */
public class SingleListMenuContentLayout extends AbsSingleRowMenuContent {
    RecyclerView recyclerView;

    public SingleListMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    View edittext;

    public void initView(Context context) {
        setContentView(R.layout.singlelist_layout);
        getAdapter().setMode(EasyFlexibleAdapter.MODE_SINGLE);
        View view = findViewById(R.id.content);
        edittext = findViewById(R.id.edittext);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(getAdapter());
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();//
        layoutParams.height = (int) (heightPixels * 0.6);
        view.requestLayout();
    }

    @Override
    protected void onHide() {
        super.onHide();
    }


    @Override
    public void onItemClick(int position) {
        submit();
    }

}
