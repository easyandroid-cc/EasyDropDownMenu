package cc.easyandroid.menu.core;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;


public class AnimatorPopup extends PopupWindow {
    public AnimatorPopup(int width, int height) {
        super(width, height);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void dismiss() {
        //這裡不調用super,讓動畫執行完畢有調用realDismiss
    }

    public void realDismiss() {
        super.dismiss();
    }
}
