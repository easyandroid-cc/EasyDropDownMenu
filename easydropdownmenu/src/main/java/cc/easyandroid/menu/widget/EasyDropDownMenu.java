package cc.easyandroid.menu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cc.easyandroid.menu.R;
import cc.easyandroid.menu.core.AnimView;
import cc.easyandroid.menu.core.IMenu;
import cc.easyandroid.menu.core.MenuAdapter;

public class EasyDropDownMenu extends AnimView implements IMenu {
    protected CharSequence mDefaultMenuTitle;
    protected TextView mTitleTextView;//顯示标题的textview控件
    protected ViewGroup mMenuContentContainer;//容器這個容器一般是公用的(這個需要傳進來)
    protected ViewGroup mMenuContentView;//

    MenuAdapter menuAdapter;

    public void setMenuAdapter(MenuAdapter menuAdapter) {
        this.menuAdapter = menuAdapter;
        menuAdapter.onAttachedToMenu(this);
        this.mMenuContentView = menuAdapter.getMenuContentView();
    }

    public MenuAdapter getMenuAdapter() {
        return menuAdapter;
    }

    protected void setMenuContentContainer(ViewGroup menuContentContainer) {
        this.mMenuContentContainer = menuContentContainer;
        this.mMenuContentContainer.setVisibility(GONE);
    }

   // public void setMenuContentView(ViewGroup menuContentView) {
     //   this.mMenuContentView = menuContentView;
//        if (mMenuContentView instanceof IMenuContent) {
//            ((EasyDropDownMenuContent) mMenuContentView).bandMenu(this);
//        }
   // }

    public ViewGroup getMenuContentView() {
        return mMenuContentView;
    }


    public EasyDropDownMenu(Context context) {
        this(context, null);
    }

    public EasyDropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.EasyDropDownMenuStyle);
    }

    public EasyDropDownMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyDropDownMenu, defStyle, 0);

        int menuTitleLayoutResourceId = a.getResourceId(R.styleable.EasyDropDownMenu_menuTitleView, R.layout.menu_title_view);
        CharSequence menuTitle = a.getText(R.styleable.EasyDropDownMenu_menuTitle);
      //  int menuContentViewResourceId = a.getResourceId(R.styleable.EasyDropDownMenu_menuContentView, 0);

        a.recycle();
        if (menuTitleLayoutResourceId > 0) {
            setMenuTitleLayoutResourceId(menuTitleLayoutResourceId);
        }
       // if (menuContentViewResourceId > 0) {
          //  setMenuContentViewResourceId(menuContentViewResourceId);
       // }
        if (!TextUtils.isEmpty(menuTitle)) {
            setDefaultMenuTitle(menuTitle);
            setMenuTitle(menuTitle);
        }
    }

    @Override
    public void addView(View childView, int index, ViewGroup.LayoutParams params) {
        super.addView(childView, index, params);
    }

    public void setMenuTitle(CharSequence menuTitle) {
        setMenuTitle(menuTitle, false);
    }

    public void setMenuTitle(CharSequence menuTitle, boolean forceHighlight) {
        if (TextUtils.isEmpty(menuTitle)) {
            mTitleTextView.setText(mDefaultMenuTitle);
            mTitleTextView.setSelected(false);
            return;
        }
        mTitleTextView.setText(menuTitle);
        if (!mDefaultMenuTitle.equals(menuTitle) || forceHighlight) {
            mTitleTextView.setSelected(true);
        } else {//title 内容 相同
            mTitleTextView.setSelected(false);
        }
    }

    public void setDefaultMenuTitle(CharSequence menuTitle) {
        mDefaultMenuTitle = menuTitle;
    }

    /**
     * init menuTitle
     *
     * @param menuTitleLayoutResourceId menuTitleLayoutResourceId
     */
    public void setMenuTitleLayoutResourceId(int menuTitleLayoutResourceId) {
        View menuTitleView = View.inflate(getContext(), menuTitleLayoutResourceId, this);
        mTitleTextView = menuTitleView.findViewById(R.id.easyDropDownMenu_Title);
        mTitleTextView.setActivated(false);
        mTitleTextView.setSaveEnabled(false);
        mTitleTextView.setFreezesText(false);
        menuTitleView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMenuClick();

            }
        });
        onMenuTitleViewCreated(menuTitleView, this);
    }

    /**
     * 设置弹出的view
     */
//    public void setMenuContentViewResourceId(int menuContentViewResourceId) {
//        ViewGroup menuContentView = (ViewGroup) View.inflate(getContext(), menuContentViewResourceId, null);
//        setMenuContentView(menuContentView);
//    }

    protected void onMenuTitleViewCreated(View menuTitleView, EasyDropDownMenu easyDropDownMenu) {
        //no use
    }

    protected void onMenuClick() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        if(menuAdapter==null){
            Log.e("EasyDropDownMenu","menuAdapter is null");
            return;
        }
        if (menuAdapter.isEmpty(menuAdapter.getViewType())) {//检查数据是否是null，
            ArrayList data=menuAdapter.loadData(menuAdapter.getViewType());//请求的限制按钮点击
            if(data!=null&&!data.isEmpty()){
                menuAdapter.setMenuData(data,false);
            }else{
                return;
            }
        }
        if (!isShowing() && showInAnim(mMenuContentView)) {
            if (showlistener != null) {
                showlistener.onShow(this);
            }
            //fix : The specified child already has a parent. You must call removeView() on the child's parent first
            mMenuContentContainer.removeView(mMenuContentView);
            mMenuContentContainer.addView(mMenuContentView);
            onShow();
            mMenuContentContainer.setVisibility(VISIBLE);
        }
    }

    protected void onShow() {

    }

    public void hide() {
        if (dismissListener != null) {
            dismissListener.onDismiss(this);
        }
        showExitAnim(mMenuContentView);
    }

    @Override
    public CharSequence getDefaultMenuTitle() {
        return mDefaultMenuTitle;
    }


    //顯示動畫執行完畢
    @Override
    protected void showView(View animView) {
        setFocusableInTouchMode(false);
        mTitleTextView.setActivated(true);
        setActivated(true);//当前layout 设置setSelected 位true
    }

    /**
     * 这里是重点：動畫執行完畢后再調用removeView，不然不會有動畫效果
     */
    @Override
    protected void dismissView(View animView) {
        super.dismissView(animView);

        mMenuContentContainer.removeView(mMenuContentView);
        if (mMenuContentContainer.getChildCount() == 0) {
            mMenuContentContainer.setVisibility(GONE);
        }
        mTitleTextView.setActivated(false);
        setFocusableInTouchMode(false);
        setFocusable(false);//当前layout
    }

    public boolean isShowing() {
        return mTitleTextView.isActivated() || showAnimIsRunning();
    }


    OnMenuDismissListener dismissListener;
    OnMenuShowlistener showlistener;

    public void setOnMenuDismissListener(OnMenuDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setOnMenuShowlistener(OnMenuShowlistener showlistener) {
        this.showlistener = showlistener;
    }

}
