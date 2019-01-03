package cc.easyandroid.menu.core;

import java.util.HashMap;
import java.util.List;

public interface IMenuItem {
    List<? extends IMenuItem> getChildItems();
    CharSequence getMenuItemTag();//显示的名称

    HashMap<String, String> getParameter();//参数的封装
}
