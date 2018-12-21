package cc.easyandroid.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

public class AnimView extends LinearLayout {
    private Animator animator_Show;
    private Animator animator_Dismiss;

    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected boolean showInAnim(final ViewGroup animView) {
        animView.setVisibility(View.INVISIBLE);
        if (animator_Show != null && animator_Show.isRunning()) {
            return false;
        }
        animView.post(new Runnable() {
            @Override
            public void run() {
                if (animator_Show == null) {
                    animator_Show = createShowAnimator(animView, -animView.getHeight());
                    animator_Show.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            if (animator_Dismiss != null) {
                                animator_Dismiss.cancel();
                            }
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            showView(animView);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            showView(animView);
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                            showView(animView);
                        }
                    });
                }
                animView.setVisibility(View.VISIBLE);
                animator_Show.start();
            }
        });
        return true;
    }

    protected boolean showAnimIsRunning() {
        return animator_Show != null && animator_Show.isRunning();

    }

    protected boolean dismissAnimIsRunning() {
        return animator_Dismiss != null && animator_Dismiss.isRunning();
    }

    protected void showExitAnim(final ViewGroup animView) {
        if (animator_Dismiss != null && animator_Dismiss.isRunning()) {
            return;
        }
        animView.post(new Runnable() {
            @Override
            public void run() {
                animator_Dismiss = createDismissAnimator(animView, -animView.getHeight());
                animator_Dismiss.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (animator_Show != null && animator_Show.isRunning()) {
                            animator_Show.cancel();
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dismissView(animView);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        dismissView(animView);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        dismissView(animView);
                    }
                });
                animator_Dismiss.start();
            }
        });
    }

    protected void dismissView(View animView) {

    }

    protected void showView(View animView) {

    }

    private Animator createShowAnimator(final View together, int translationValue) {
        int duration = together.getResources().getInteger(android.R.integer.config_shortAnimTime);
        ObjectAnimator animShow = ObjectAnimator.ofFloat(together, View.TRANSLATION_Y, translationValue, 0).setDuration(duration * 1);
        animShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int h = (int) (EASYALPHA * animatedFraction);
                ((ViewGroup) together.getParent()).setBackgroundColor(Color.argb(h, 0, 0, 0));//遮罩层
            }
        });
        animShow.setInterpolator(new LinearInterpolator());
        return animShow;
    }

    private static final int EASYALPHA = 100;

    private ObjectAnimator createDismissAnimator(final View together, int translationValue) {
        int duration = together.getResources().getInteger(android.R.integer.config_shortAnimTime);
        ObjectAnimator animShow = ObjectAnimator.ofFloat(together, View.TRANSLATION_Y, 0, translationValue).setDuration(duration * 1);
        animShow.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                int h = (int) (EASYALPHA - EASYALPHA * animatedFraction);
                ((ViewGroup) together.getParent()).setBackgroundColor(Color.argb(h, 0, 0, 0));//遮罩层
            }
        });
        animShow.setInterpolator(new LinearInterpolator());
        return animShow;
    }
}
