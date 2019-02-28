package cc.easyandroid.menu.simple;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.easyandroid.listfiltermenu.simple.R;
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

        easyDropDownMenu1.setMenuContentView(new SingleListMenuContentLayout(getActivity()));
        easyDropDownMenu2.setMenuContentView(new MultiSelectSingleRowMenuContentLayout(getActivity()));
//       easyDropDownMenu3.setMenuContentView(new MultiSelectTowRowMenuContentLayout(this));
        easyDropDownMenu3.setMenuContentView(new MultiSelectTowRowMenuContent1(getActivity()));
        easyDropDownMenu4.setMenuContentView(new ComplexMenuContentLayout2(getActivity()));
        easyDropDownMenu5.setMenuContentView(new ComplexMenuContentLayout2(getActivity()));
        easyDropDownMenu6.setMenuContentView(new SingleListMenuContentLayout(getActivity()));
    }
}
