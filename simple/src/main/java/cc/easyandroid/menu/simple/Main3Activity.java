package cc.easyandroid.menu.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import cc.easyandroid.menu.widget.EasyDropDownMenu;
import cc.easyandroid.listfiltermenu.simple.R;
import cc.easyandroid.menu.widget.EasyDropDownGroup;
import cc.easyandroid.menu.widget.PopEasyDropDownMenu;

public class Main3Activity extends AppCompatActivity {
    EasyDropDownMenu easyDropDownMenu1;
    EasyDropDownMenu easyDropDownMenu2;
    EasyDropDownMenu easyDropDownMenu3;
    EasyDropDownMenu easyDropDownMenu4;
    PopEasyDropDownMenu easyDropDownMenu5;
    PopEasyDropDownMenu easyDropDownMenu6;
    ViewGroup container;
    EasyDropDownGroup easyDropDownGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        easyDropDownGroup = findViewById(R.id.easyDropDownMenuContainer);
        easyDropDownMenu1 = findViewById(R.id.menuFilter1);
        easyDropDownMenu2 = findViewById(R.id.menuFilter2);
        easyDropDownMenu3 = findViewById(R.id.menuFilter3);
        easyDropDownMenu4 = findViewById(R.id.menuFilter4);
        easyDropDownMenu5 = findViewById(R.id.menuFilter5);
        easyDropDownMenu6 = findViewById(R.id.menuFilter6);

        container = findViewById(R.id.container);
        easyDropDownGroup.setMenuContentContainer(container);

        easyDropDownMenu1.setMenuContentView(new SingleListMenuContentLayout(this));
        easyDropDownMenu2.setMenuContentView(new MultiSelectSingleRowMenuContentLayout(this));
        easyDropDownMenu3.setMenuContentView(new MultiSelectTowRowMenuContentLayout(this));
        easyDropDownMenu4.setMenuContentView(new ComplexMenuContentLayout2(this));
        easyDropDownMenu5.setMenuContentView(new ComplexMenuContentLayout2(this));
        easyDropDownMenu6.setMenuContentView(new SingleListMenuContentLayout(this));

    }

}
