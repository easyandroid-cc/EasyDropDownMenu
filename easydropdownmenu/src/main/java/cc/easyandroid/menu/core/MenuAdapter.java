package cc.easyandroid.menu.core;

import android.text.TextUtils;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cc.easyandroid.easyrecyclerview.items.IFlexible;

public abstract class MenuAdapter<MVH extends IMenuContent> implements IMenuContent.Listener {
    private static final String NO_TYPE = "NO_TYPE";
    final MVH mvh;

    public MenuAdapter(MVH mvh) {
        this.mvh = mvh;
        this.mvh.setListener(this);
    }

    public final ViewGroup getMenuContentView() {
        return mvh.getContentView();
    }

    IMenu menu;

    public void onAttachedToMenu(IMenu menu) {
        this.menu=menu;
    }

    /**
     * 没有数据会触发loadData 调用
     *
     * @param viewType
     * @return
     */
    public abstract ArrayList loadData(String viewType);

    public String getViewType() {
        return NO_TYPE;
    }

    public boolean isEmpty(String viewType) {
        boolean empty = mItems == null || mItems.isEmpty();
        return empty;
    }


    public void onSelectItems(List<IFlexible>... list) {
        if(list!=null&&list.length>0){
            menu.setMenuTitle(TextUtils.join(",", list[list.length-1]));
        }
        menu.hide();
    }
    public CharSequence getDefaultMenuTitle(){
        return menu.getDefaultMenuTitle();
    }

    public void setMenuTitle(CharSequence menuTitle,boolean forceHighlight){
        menu.setMenuTitle(menuTitle,forceHighlight);
    }

    public IMenu getMenu() {
        return menu;
    }

    ArrayList<IFlexible> mItems = new ArrayList<>();

    public void setMenuData(ArrayList<IFlexible> items, boolean needShow, Integer... defaultPosition) {
        mItems.clear();
        mItems.addAll(items);
        mvh.setMenuDatas(mItems, needShow, defaultPosition);
        if(needShow){
            menu.show();
        }
    }
    public void setMenuTitle(CharSequence title){
        menu.setMenuTitle(title);
    }

}
