package cc.easyandroid.menu;

import java.util.HashMap;


public interface IMenuItem {
    CharSequence getMenuItemTag();//显示的名称

    HashMap<String, String> getParameter();//参数的封装
}
