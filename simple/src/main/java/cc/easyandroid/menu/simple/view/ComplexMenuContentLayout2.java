package cc.easyandroid.menu.simple.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.items.IFlexible;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.simple.pojo.Item2;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text2;
import cc.easyandroid.menu.widget.AbsSingleRowMenuContent;

public class ComplexMenuContentLayout2 extends AbsSingleRowMenuContent  {
    public  ComplexMenuContentLayout2(Context context) {
        super(context);
        onViewCreated(this);
    }

    View resetView;
    View submitView;
    RecyclerView recyclerView;

    public void onViewCreated(View context) {
        setContentView(R.layout.complexmultiselectlist_layout);
        recyclerView = findViewById(R.id.recyclerview);
        resetView = findViewById(R.id.reset);
        submitView = findViewById(R.id.submit);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(getAdapter());
        getAdapter().setMode(EasyFlexibleAdapter.MODE_MULTI);
        resetView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        submitView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }
}
