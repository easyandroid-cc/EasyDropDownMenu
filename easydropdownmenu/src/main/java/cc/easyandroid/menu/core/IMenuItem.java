package cc.easyandroid.menu.core;

import java.util.List;

public interface IMenuItem {
    List<? extends IMenuItem> getChildItems();//如果有下一级
//    CharSequence getMenuItemTag();//显示的名称
//
//    HashMap<String, String> getParameter();//参数的封装
}
