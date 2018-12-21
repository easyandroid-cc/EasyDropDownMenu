package cc.easyandroid.menu.simple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.listfiltermenu.simple.R;

public class SingleListMenuContentLayout extends RelativeLayout implements EasyFlexibleAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter(this);

    public SingleListMenuContentLayout(Context context) {
        super(context);
        initView(context);
    }

    public SingleListMenuContentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SingleListMenuContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        adapter.setMode(EasyFlexibleAdapter.MODE_SINGLE);
        View.inflate(context, R.layout.singlelist_layout, this);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        final ArrayList<Item1> lists4 = dd1();
        adapter.addItems(lists4);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

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

    Toast toast;

    @Override
    public boolean onItemClick(View view, int position) {
        System.out.println("cgp size =" + adapter.getSelectedPositions().size());
        Item1 item1 = (Item1) adapter.getItem(position);
        if (toast == null) {
            toast = Toast.makeText(view.getContext(), item1.getName(), Toast.LENGTH_SHORT);
        }
        toast.setText(item1.getName());
        toast.show();
        return false;
    }
}
