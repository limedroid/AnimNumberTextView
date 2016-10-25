package cn.droidlover.animnumbertextview;


import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AnimNumberTextView extends TextView {

    static int TYPE_INT = 0;    //int类型
    static int TYPE_FLOAT = 1;    //float类型

    float fromValue;
    float toValue;
    Animator.AnimatorListener animListener;
    TimeInterpolator interpolator;

    int type = TYPE_INT;

    long duration = 300L;    //动画时间

    DecimalFormat dformat;


    public AnimNumberTextView(Context context) {
        super(context);
    }

    public AnimNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimNumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimNumberTextView withNumber(int from, int to) {
        type = TYPE_INT;
        fromValue = from;
        toValue = to;

        return this;
    }

    public AnimNumberTextView withNumber(float from, float to) {
        type = TYPE_FLOAT;
        fromValue = from;
        toValue = to;

        return this;
    }

    public AnimNumberTextView animListener(Animator.AnimatorListener listener) {
        animListener = listener;
        return this;
    }

    public AnimNumberTextView interpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    /**
     * 动画时间
     *
     * @param duration
     * @return
     */
    public AnimNumberTextView duration(long duration) {
        this.duration = duration;
        return this;
    }

    public void start() {
        if (type == TYPE_INT) {
            animInt();
        } else if (type == TYPE_FLOAT) {
            animFloat();
        }
    }

    private void animInt() {
        ValueAnimator animator = ValueAnimator.ofInt((int) fromValue, (int) toValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(animation.getAnimatedValue().toString());
            }
        });
        animator.setDuration(duration);
        if (interpolator != null) animator.setInterpolator(interpolator);
        if (animListener != null) animator.addListener(animListener);

        animator.start();
    }

    private void animFloat() {
        ValueAnimator animator = ValueAnimator.ofFloat(fromValue, toValue);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(getFormat(".00").format(Double.parseDouble(animation.getAnimatedValue().toString())));
            }
        });
        animator.setDuration(duration);
        if (interpolator != null) animator.setInterpolator(interpolator);
        if (animListener != null) animator.addListener(animListener);

        animator.start();
    }


    private DecimalFormat getFormat(String pattern) {
        if (dformat == null) {
            dformat = new DecimalFormat();
        }
        dformat.setRoundingMode(RoundingMode.FLOOR);
        dformat.applyPattern(pattern);
        return dformat;
    }
}
