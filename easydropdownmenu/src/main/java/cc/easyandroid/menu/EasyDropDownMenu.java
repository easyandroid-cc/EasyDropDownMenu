package cc.easyandroid.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EasyDropDownMenu extends AnimView {
    protected CharSequence mDefultMenuTitle;
    protected TextView mTitleTextView;//顯示标题的textview控件
    protected ViewGroup mMenuContentContainer;//容器這個容器一般是公用的(這個需要傳進來)
    protected ViewGroup mMenuContentView;//


    public void setMenuContentContainer(ViewGroup menuContentContainer) {
        this.mMenuContentContainer = menuContentContainer;
    }

    public void setMenuContentView(ViewGroup menuContentView) {
        this.mMenuContentView = menuContentView;
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
        int menuContentViewResourceId = a.getResourceId(R.styleable.EasyDropDownMenu_menuContentView, R.layout.menu_content_view);

        a.recycle();
        if (menuTitleLayoutResourceId > 0) {
            setMenuTitleLayoutResourceId(menuTitleLayoutResourceId);
        }
        if (menuContentViewResourceId > 0) {
            setMenuContentViewResourceId(menuContentViewResourceId);
        }
        if (!TextUtils.isEmpty(menuTitle)) {
            setDefultMenuTitle(menuTitle);
            setMenuTitle(menuTitle);
        }
    }


    public void setMenuTitle(CharSequence menuTitle) {
        setMenuTitle(menuTitle, false);
    }

    public void setMenuTitle(CharSequence menuTitle, boolean forceHighlight) {
        mTitleTextView.setText(menuTitle);
        if (!mDefultMenuTitle.equals(menuTitle) || forceHighlight) {
            mTitleTextView.setEnabled(true);
        } else {//title 内容 相同
            mTitleTextView.setEnabled(false);
        }
    }

    public void setDefultMenuTitle(CharSequence menuTitle) {
        mDefultMenuTitle = menuTitle;
    }

    /**
     * init menuTitle
     *
     * @param menuTitleLayoutResourceId menuTitleLayoutResourceId
     */
    public void setMenuTitleLayoutResourceId(int menuTitleLayoutResourceId) {
        View menuTitleView = View.inflate(getContext(), menuTitleLayoutResourceId, this);
        mTitleTextView = menuTitleView.findViewById(R.id.easyDropDownMenu_Title);
        mTitleTextView.setEnabled(false);
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
    protected void showView(View animView) {
        setFocusableInTouchMode(false);
        mTitleTextView.setSelected(true);
        setSelected(true);//当前layout 设置setSelected 位true
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
        mTitleTextView.setSelected(false);
        setFocusableInTouchMode(false);
        setFocusable(false);//当前layout
    }

    public boolean isShow() {
        return mTitleTextView.isSelected() || showAnimIsRunning();
    }


    public interface OnMenuDismissListener {
        void onDismiss(EasyDropDownMenu v);
    }

    public interface OnMenuShowlistener {
        void onShow(EasyDropDownMenu v);
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
