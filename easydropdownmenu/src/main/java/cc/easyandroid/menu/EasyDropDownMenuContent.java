package cc.easyandroid.menu;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

public abstract class EasyDropDownMenuContent extends RelativeLayout implements IMenuHandle {

    protected IMenu menu;

    public EasyDropDownMenuContent(Context context) {
        super(context);
        initView(context);
    }


    private void initView(Context context) {
        View.inflate(context, getResourcesId(), this);
    }

    @Override
    public void bandMenu(IMenu menu) {
        this.menu = menu;
    }

    public void setMenuTitle(CharSequence menuTitle) {
        this.menu.setMenuTitle(menuTitle);

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

    protected abstract int getResourcesId();

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
        super.onDetachedFromWindow();
        onHide();
    }
}
