package cc.easyandroid.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;


/**
 *
 */
public class EasyDropDownMenuContainer extends LinearLayout implements EasyDropDownMenu.OnMenuShowlistener, EasyDropDownMenu.OnMenuDismissListener {
    private final ArrayList<EasyDropDownMenu> menus = new ArrayList<>();

    public EasyDropDownMenuContainer(Context context) {
        super(context);
        init();

    }

    public EasyDropDownMenuContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EasyDropDownMenuContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    public void addView(View childView, int index, ViewGroup.LayoutParams params) {
        super.addView(childView, index, params);
        if (childView != null && childView instanceof EasyDropDownMenu) {
            initializeMenu((EasyDropDownMenu) childView);
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
            for (EasyDropDownMenu menu : menus) {
                menu.setMenuContentContainer(mMenuContentContainer);
            }
        }
    }

    private void initializeMenu(EasyDropDownMenu menu) {
        menu.setOnMenuShowlistener(this);
        menu.setOnMenuDismissListener(this);
        menus.add(menu);
        if (mMenuContentContainer != null) {
            menu.setMenuContentContainer(mMenuContentContainer);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void hideAll() {
        for (EasyDropDownMenu menu : menus) {
            if (menu.isShow()) {
                menu.hide();
            }
        }
    }

    @Override
    public void onShow(EasyDropDownMenu v) {//显示之前先隐藏其他的
        hideAll();
    }

    @Override
    public void onDismiss(EasyDropDownMenu v) {

    }

}
