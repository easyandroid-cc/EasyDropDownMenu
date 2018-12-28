package cc.easyandroid.menu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cc.easyandroid.menu.core.IMenu;
import cc.easyandroid.menu.widget.CommonEasyDropDownMenu;
import cc.easyandroid.menu.widget.EasyDropDownMenu;


/**
 * 一组menu控制点击后每次显示一个
 */
public class EasyDropDownGroup extends LinearLayout implements EasyDropDownMenu.OnMenuShowlistener, EasyDropDownMenu.OnMenuDismissListener {
    private final ArrayList<IMenu> menus = new ArrayList<>();

    public EasyDropDownGroup(Context context) {
        super(context);

    }

    public EasyDropDownGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyDropDownGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void addView(View childView, int index, ViewGroup.LayoutParams params) {
        super.addView(childView, index, params);
        if (childView != null && childView instanceof IMenu) {
            initializeMenu((IMenu) childView);
        }
    }

    ViewGroup mMenuContentContainer;

    public void setMenuContentContainer(ViewGroup menuContentContainer) {
        this.mMenuContentContainer = menuContentContainer;
        this.mMenuContentContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideAll();
            }
        });
        if (menus.size() > 0) {
            for (IMenu menu : menus) {
                if (menu instanceof CommonEasyDropDownMenu) {
                    ((CommonEasyDropDownMenu) menu).setMenuContentContainer(mMenuContentContainer);
                }
            }
        }
    }

    private void initializeMenu(IMenu menu) {
        menu.setOnMenuShowlistener(this);
        menu.setOnMenuDismissListener(this);
        menus.add(menu);
        if (mMenuContentContainer != null && menu instanceof CommonEasyDropDownMenu) {
            ((CommonEasyDropDownMenu) menu).setMenuContentContainer(mMenuContentContainer);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void hideAll() {
        for (IMenu menu : menus) {
            if (menu.isShowing()) {
                menu.hide();
            }
        }
    }

    @Override
    public void onShow(IMenu v) {//显示之前先隐藏其他的
        hideAll();
    }

    @Override
    public void onDismiss(IMenu v) {//contentView 隐藏之前调用
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);//强制隐藏键盘 
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }
}
