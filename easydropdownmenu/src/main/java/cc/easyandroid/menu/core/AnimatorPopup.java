package cc.easyandroid.menu.core;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;


public class AnimatorPopup extends PopupWindow {
    public AnimatorPopup(int width, int height) {
        super(width, height);
        setClippingEnabled(false);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            Resources resources = anchor.getResources();
            int height = resources.getDisplayMetrics().heightPixels;//最大可以显示的区域，他不包含通知栏
            height -= visibleFrame.bottom;//anchor 底部坐标位置，这里他剪掉了通知栏高度，使用下面要把通知栏高度加上，不然底部会显示一个空白
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                height += resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
            }
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
