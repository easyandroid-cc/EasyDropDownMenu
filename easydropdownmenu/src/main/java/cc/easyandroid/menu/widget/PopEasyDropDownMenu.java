package cc.easyandroid.menu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import cc.easyandroid.menu.core.AnimatorPopup;


public class PopEasyDropDownMenu extends EasyDropDownMenu {
    protected AnimatorPopup pupupWindow;

    public PopEasyDropDownMenu(Context context) {
        super(context);
        initView(context);
    }

    public PopEasyDropDownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PopEasyDropDownMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    @Override
    protected void setMenuContentContainer(ViewGroup menuContentContainer) {
        super.setMenuContentContainer(menuContentContainer);
        menuContentContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
            }
        });
        pupupWindow.setContentView(menuContentContainer);
    }

    private void initView(Context context) {
        pupupWindow = new AnimatorPopup(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT) {
            @Override
            public void dismiss() {
                super.dismiss();//父類沒執行dismiss
                hide();
            }
        };

        ViewGroup popMenuContentContainer = buildContentContainer(context);
        setMenuContentContainer(popMenuContentContainer);
        setupPupupWindow(pupupWindow);
    }

    protected ViewGroup buildContentContainer(Context context) {
        RelativeLayout frameLayout = new RelativeLayout(context);
        frameLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return frameLayout;
    }

    protected void setupPupupWindow(final PopupWindow pupupWindow) {
        //pupupWindow.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        pupupWindow.setOutsideTouchable(true);
        pupupWindow.setFocusable(false);
        pupupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pupupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        pupupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // not user
            }
        });
    }


    private View anchorView;

    public void setAnchorView(View anchorView) {
        this.anchorView = anchorView;
    }

    @Override
    protected void onShow() {
        super.onShow();
        pupupWindow.showAsDropDown(anchorView == null ? this : anchorView, 0, 0);
    }

    @Override
    protected void dismissView(View animView) {
        super.dismissView(animView);
        dismissPupupWindow();
    }

    @Override
    public boolean isShowing() {
        return pupupWindow != null && pupupWindow.isShowing();
    }

    public void dismissPupupWindow() {
        if (pupupWindow != null && pupupWindow.isShowing()) {
            pupupWindow.realDismiss();//真正的dismiss
        }
    }
}
