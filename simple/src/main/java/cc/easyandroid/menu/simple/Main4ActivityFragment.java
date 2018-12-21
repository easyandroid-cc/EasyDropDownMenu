package cc.easyandroid.menu.simple;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.listfiltermenu.simple.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class Main4ActivityFragment extends Fragment {
    RecyclerView recyclerView;

    public Main4ActivityFragment() {
    }

    EasyFlexibleAdapter adapter = new EasyFlexibleAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("cgp onCreateView");
        return inflater.inflate(R.layout.fragment_main4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("cgp onViewCreated");
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        final ArrayList<Item2> lists4 = dd2();
        adapter.addItems(lists4);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("cgp onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("cgp onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("cgp onDestroy");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("cgp onStart");
    }

    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
        return lists;
    }
}
