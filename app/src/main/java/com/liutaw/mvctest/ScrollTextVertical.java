package com.liutaw.mvctest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liutaw.mvctest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liutao on 15/12/25.
 */
public class ScrollTextVertical extends FrameLayout {
    private TextView text_content;
    private TextView text_content_two;
    private List<String> data;
    private long turnning = 2000;
    private int index = 0;
    private boolean isOn = false;
    private Runnable runnable;
    private AdapterView.OnItemClickListener listener;

    private Animation inAnimation;
    private Animation outAnimation;

    public ScrollTextVertical(Context context) {
        super(context);
        init();
    }

    public ScrollTextVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_text, this);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnItem();
            }
        });
        text_content = (TextView) view.findViewById(R.id.text_content);
        text_content_two = (TextView) view.findViewById(R.id.text_content_two);

        //init animation
        inAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_bottom_in);
        outAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_top_out);
        runnable = new Runnable() {
            @Override
            public void run() {
                if (!isOn) return;
                if (currentTextview == null) return;
                currentTextview.startAnimation(outAnimation);
            }
        };
        inAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //等待x秒
                postDelayed(runnable, turnning);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setData(List<String> data) {
        stopTurnning();
        setListener(null);//清空监听器
        if (data != null) {
            this.data = data;
        } else this.data = new ArrayList<>();
        startTurning();
    }

    public void addData(List<String> data) {
        if (data != null) {
            this.data.addAll(data);
            return;
        }
        if (this.data == null)
            this.data = new ArrayList<>();
    }

    private void start() {
        if (!isOn) return;
        setCurrentTextView();
        if (data == null || data.size() == 0 || currentTextview == null) return;
        if (index > data.size() - 1) {
            index = 0;//达到末端，继续循环
        }
        currentTextview.setText(data.get(index++));
        currentTextview.startAnimation(inAnimation);
    }

    private boolean isFirst = true;
    private TextView currentTextview;

    private void setCurrentTextView() {
        //自动变化，为什么需要两个textview，因为要达到一种效果
        if (isFirst) {
            isFirst = false;
            this.text_content.setVisibility(VISIBLE);
            this.text_content_two.setVisibility(GONE);
            currentTextview = this.text_content;
            return;
        }
        isFirst = true;
        this.text_content.setVisibility(GONE);
        this.text_content_two.setVisibility(VISIBLE);
        currentTextview = this.text_content_two;
    }

    public void stopTurnning() {
        if (inAnimation.hasStarted()) {
            inAnimation.cancel();
        }
        if (outAnimation.hasStarted()) {
            outAnimation.cancel();
        }
        //一定要去掉，否则就会出现滚动加快的情况
        if (runnable != null && getHandler() != null) {
            getHandler().removeCallbacks(runnable);
        }
        isOn = false;
        index = 0;
    }

    public void startTurning() {
        if (isOn) return;
        isOn = true;
        start();
    }

    public void pauseTurning() {
        if (inAnimation.hasStarted()) {
            inAnimation.cancel();
        }
        if (outAnimation.hasStarted()) {
            outAnimation.cancel();
        }
        if (runnable != null && getHandler() != null) {
            getHandler().removeCallbacks(runnable);
        }
        isOn = false;
    }

    public void reStartTurning() {
        stopTurnning();
        startTurning();
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void clickOnItem() {
        if (data==null||data.size()==0) return;
        if (index>=0&&index<=data.size()-1) {
            if (listener==null) return;
            //注意index是自增的，需要减1
            listener.onItemClick(null,null,index-1,0);
        }
    }

}
