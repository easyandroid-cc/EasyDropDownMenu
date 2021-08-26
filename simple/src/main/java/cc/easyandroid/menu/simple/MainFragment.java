package cc.easyandroid.menu.simple;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.core.MenuAdapter;
import cc.easyandroid.menu.simple.pojo.Item1;
import cc.easyandroid.menu.simple.pojo.Item2;
import cc.easyandroid.menu.simple.pojo.Text;
import cc.easyandroid.menu.simple.pojo.Text1;
import cc.easyandroid.menu.simple.pojo.Text2;
import cc.easyandroid.menu.simple.view.ComplexMenuContentLayout2;
import cc.easyandroid.menu.simple.view.MultiSelectSingleRowMenuContentLayout;
import cc.easyandroid.menu.simple.view.MultiSelectTowRowMenuContent1;
import cc.easyandroid.menu.simple.view.SingleListMenuContentLayout;
import cc.easyandroid.menu.widget.EasyDropDownGroup;
import cc.easyandroid.menu.widget.EasyDropDownMenu;
import cc.easyandroid.menu.widget.PopEasyDropDownMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    EasyDropDownMenu easyDropDownMenu1;
    EasyDropDownMenu easyDropDownMenu2;
    EasyDropDownMenu easyDropDownMenu3;
    EasyDropDownMenu easyDropDownMenu4;
    PopEasyDropDownMenu easyDropDownMenu5;
    PopEasyDropDownMenu easyDropDownMenu6;
    ViewGroup container;
    EasyDropDownGroup easyDropDownGroup;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        easyDropDownGroup = view.findViewById(R.id.easyDropDownMenuContainer);
        easyDropDownMenu1 = view.findViewById(R.id.menuFilter1);
        easyDropDownMenu2 = view.findViewById(R.id.menuFilter2);
        easyDropDownMenu3 = view.findViewById(R.id.menuFilter3);
        easyDropDownMenu4 = view.findViewById(R.id.menuFilter4);
        easyDropDownMenu5 = view.findViewById(R.id.menuFilter5);
        easyDropDownMenu6 = view.findViewById(R.id.menuFilter6);

        container = view.findViewById(R.id.container);
        easyDropDownGroup.setMenuContentContainer(container);

        easyDropDownMenu1.setMenuAdapter(new MenuAdapter(new SingleListMenuContentLayout(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
                final ArrayList<Item1> lists4 = dd1();
                Item1 item1 = new Item1();
                item1.setName("不限");
                item1.setMenuItemTag(getDefaultMenuTitle());
                lists4.add(0, item1);
                setMenuData(lists4,true,0);
                return null;
            }
        });
        easyDropDownMenu2.setMenuAdapter(new MenuAdapter(new MultiSelectSingleRowMenuContentLayout(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
                final ArrayList<Item1> lists4 = dd1();
                Item1 item1 = new Item1();
                item1.setName("不限");
                item1.setMenuItemTag(getDefaultMenuTitle());
                lists4.add(0, item1);
                setMenuData(lists4,true,0);
                return null;
            }
        });
        easyDropDownMenu3.setMenuAdapter(new MenuAdapter(new MultiSelectTowRowMenuContent1(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
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
                //nomit.setMenuItemTag(getDefaultMenuTitle());
                lists4.add(0, nomit);
                //getAdapter1().addItems(lists4);
                // getAdapter1().notifyDataSetChanged();
                setMenuData(lists4,true,0);
                return null;
            }
        });
        easyDropDownMenu4.setMenuAdapter(new MenuAdapter(new ComplexMenuContentLayout2(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
                final ArrayList<Item2> lists4 = dd2();
                ArrayList arrayList = new ArrayList();
                for (Item2 item2 : lists4) {
                    arrayList.add(item2);
                    arrayList.addAll(item2.getSubregions());
                }
                // setMenuDatas(arrayList, true);
                setMenuData(lists4,true,0);
                return null;
            }
        });
        easyDropDownMenu5.setMenuAdapter(new MenuAdapter(new ComplexMenuContentLayout2(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
                final ArrayList<Item2> lists4 = dd2();
                ArrayList arrayList = new ArrayList();
                for (Item2 item2 : lists4) {
                    arrayList.add(item2);
                    arrayList.addAll(item2.getSubregions());
                }
                // setMenuDatas(arrayList, true);
                setMenuData(lists4,true,0);
                return null;
            }
        });
        easyDropDownMenu6.setMenuAdapter(new MenuAdapter(new SingleListMenuContentLayout(getContext())) {
            @Override
            public ArrayList loadData(String viewType) {
                final ArrayList<Item1> lists4 = dd1();
                Item1 item1 = new Item1();
                item1.setName("不限");
                // item1.setMenuItemTag(getDefaultMenuTitle());
                lists4.add(0, item1);
                setMenuData(lists4,true,0);
                return null;
            }
        });
    }  public ArrayList<Item1> dd1() {
        Text1 text1 = new Gson().fromJson(Text.text, Text1.class);
        final ArrayList<Item1> lists = text1.getResult();
        return lists;
    }
    public ArrayList<Item2> dd2() {
        Text2 text1 = new Gson().fromJson(Text.text, Text2.class);
        final ArrayList<Item2> lists = text1.getResult();
        return lists;
    }
}
