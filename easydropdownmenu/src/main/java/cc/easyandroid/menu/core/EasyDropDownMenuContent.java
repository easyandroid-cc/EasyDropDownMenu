package cc.easyandroid.menu.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public abstract class EasyDropDownMenuContent extends RelativeLayout implements IMenuContent {

    protected final int[] INIT_SELECT_OPTION=initSelectOption();;//每一列 没有选中的时候默认的选中的位置

    public EasyDropDownMenuContent(Context context) {
        super(context);
    }


    public void setContentView(int layoutResID) {
        View.inflate(getContext(), layoutResID, this);
    }

    protected void onHide() {

    }

    protected void onShow() {
    }

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

    protected Listener mListener;

    @Override
    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewGroup getContentView() {
        return this;
    }

    protected int[] initSelectOption() {
        return new int[]{0, 0};
    }
}
