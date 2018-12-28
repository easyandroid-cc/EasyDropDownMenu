package cc.easyandroid.menu.core;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import cc.easyandroid.menu.core.IMenu;
import cc.easyandroid.menu.core.IMenuContent;

public abstract class EasyDropDownMenuContent extends RelativeLayout implements IMenuContent {

    protected IMenu menu;

    public EasyDropDownMenuContent(Context context) {
        super(context);
    }

    @Override
    public void bandMenu(IMenu menu) {
        this.menu = menu;
    }

    public void setMenuTitle(CharSequence menuTitle) {
        this.menu.setMenuTitle(menuTitle);
    }

    public void setContentView(int layoutResID) {
        View.inflate(getContext(), layoutResID, this);
    }

    public void hide() {
        menu.hide();
    }

    public void show() {
        menu.show();
    }

    public CharSequence getDefaultMenuTitle() {
        return menu.getDefaultMenuTitle();
    }

    protected void onHide() {

    }

    protected void onShow() {
    }

    public abstract boolean isEmpty();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onShow();
    }

    @Override
    protected void onDetachedFromWindow() {
        onHide();
        super.onDetachedFromWindow();
    }
}
