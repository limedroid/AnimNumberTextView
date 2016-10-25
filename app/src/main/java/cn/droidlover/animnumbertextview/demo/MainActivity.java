package cn.droidlover.animnumbertextview.demo;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;

import cn.droidlover.animnumbertextview.AnimNumberTextView;

public class MainActivity extends AppCompatActivity {

    AnimNumberTextView tv_animNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_animNum = (AnimNumberTextView) findViewById(R.id.tv_animNum);

        tv_animNum.withNumber(10, 420);
//        tv_animNum.withNumber(10.5f,1000.5f);
        tv_animNum.duration(1000L).interpolator(new AccelerateInterpolator()).animListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }
}
