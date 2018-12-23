package cc.easyandroid.menu;

public interface IMenu {

    void setMenuTitle(CharSequence menuTitle);

    void setMenuTitle(CharSequence menuTitle, boolean forceHighlight);

    void setDefaultMenuTitle(CharSequence menuTitle);

    void show();

    void hide();

    CharSequence getDefaultMenuTitle();


}
