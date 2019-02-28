package cc.easyandroid.menu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * PopEasyDropDownMenu 不需要setMenuContentContainer，单个menu 的时候建议使用PopEasyDropDownMenu
 */
public class CommonEasyDropDownMenu extends EasyDropDownMenu {

    public CommonEasyDropDownMenu(Context context) {
        super(context);
    }

    public CommonEasyDropDownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonEasyDropDownMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setMenuContentContainer(ViewGroup menuContentContainer) {
        super.setMenuContentContainer(menuContentContainer);
    }
}
