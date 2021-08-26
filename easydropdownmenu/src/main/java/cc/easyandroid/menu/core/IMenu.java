package cc.easyandroid.menu.core;


public interface IMenu {

    void setMenuTitle(CharSequence menuTitle);

    void setMenuTitle(CharSequence menuTitle, boolean forceHighlight);

    void setDefaultMenuTitle(CharSequence menuTitle);

    void show();

    void hide();

    boolean isShowing();

    CharSequence getDefaultMenuTitle();


    void setOnMenuShowlistener(OnMenuShowlistener listener);

    void setOnMenuDismissListener(OnMenuDismissListener listener);

    interface OnMenuDismissListener {
        void onDismiss(IMenu v);
    }

    interface OnMenuShowlistener {
        void onShow(IMenu v);
    }
}
