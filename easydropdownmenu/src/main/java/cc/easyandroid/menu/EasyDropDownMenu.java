package cc.easyandroid.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EasyDropDownMenu extends AnimView implements IMenu {
    protected CharSequence mDefaultMenuTitle;
    protected TextView mTitleTextView;//顯示标题的textview控件
    protected ViewGroup mMenuContentContainer;//容器這個容器一般是公用的(這個需要傳進來)
    protected ViewGroup mMenuContentView;//


    public void setMenuContentContainer(ViewGroup menuContentContainer) {
        this.mMenuContentContainer = menuContentContainer;
    }

    public void setMenuContentView(ViewGroup menuContentView) {
        this.mMenuContentView = menuContentView;
        if (mMenuContentView instanceof IMenuHandle) {
            ((EasyDropDownMenuContent) mMenuContentView).bandMenu(this);
        }
    }

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
        int menuContentViewResourceId = a.getResourceId(R.styleable.EasyDropDownMenu_menuContentView, 0);

        a.recycle();
        if (menuTitleLayoutResourceId > 0) {
            setMenuTitleLayoutResourceId(menuTitleLayoutResourceId);
        }
        if (menuContentViewResourceId > 0) {
            setMenuContentViewResourceId(menuContentViewResourceId);
        }
        if (!TextUtils.isEmpty(menuTitle)) {
            setDefaultMenuTitle(menuTitle);
            setMenuTitle(menuTitle);
        }
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
    public void setMenuContentViewResourceId(int menuContentViewResourceId) {
        ViewGroup menuContentView = (ViewGroup) View.inflate(getContext(), menuContentViewResourceId, null);
        setMenuContentView(menuContentView);
    }

    protected void onMenuTitleViewCreated(View menuTitleView, EasyDropDownMenu easyDropDownMenu) {
        //no use
    }

    protected void onMenuClick() {
        if (isShow()) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        if (mMenuContentView instanceof IMenuHandle) {
            if (((IMenuHandle) mMenuContentView).isEmpty()) {//检查数据是否是null，
                ((IMenuHandle) mMenuContentView).loadData();
                return;
            }
        }
        if (!isShow() && showInAnim(mMenuContentView)) {
            if (showlistener != null) {
                showlistener.onShow(this);
            }
            mMenuContentContainer.addView(mMenuContentView);
        }
    }

    public void hide() {
        showExitAnim(mMenuContentView);
    }

    @Override
    public CharSequence getDefaultMenuTitle() {
        return mDefaultMenuTitle;
    }

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
        if (dismissListener != null) {
            dismissListener.onDismiss(this);
        }
        mMenuContentContainer.removeView(mMenuContentView);
        mTitleTextView.setActivated(false);
        setFocusableInTouchMode(false);
        setFocusable(false);//当前layout
    }

    public boolean isShow() {
        return mTitleTextView.isActivated() || showAnimIsRunning();
    }


    public interface OnMenuDismissListener {
        void onDismiss(EasyDropDownMenu v);
    }

    public interface OnMenuShowlistener {
        void onShow(EasyDropDownMenu v);
    }

    /**
     * 没有数据时候的回掉接口
     */
    public interface OnMenuWithoutDataClickLinstener {
        void withoutData(EasyDropDownMenu menu);
    }

    OnMenuDismissListener dismissListener;
    OnMenuShowlistener showlistener;

    public void setOnMenuDismissListener(OnMenuDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setOnMenuShowlistener(OnMenuShowlistener showlistener) {
        this.showlistener = showlistener;
    }

    /**
     * 设置没有数据时候的监听回掉
     *
     * @param menuWithoutDataClickLinstener menuWithoutDataClickLinstener
     */
    public void setOnMenuWithoutDataClickLinstener(OnMenuWithoutDataClickLinstener menuWithoutDataClickLinstener) {
        this.menuWithoutDataClickLinstener = menuWithoutDataClickLinstener;
    }

    private OnMenuWithoutDataClickLinstener menuWithoutDataClickLinstener;

}
