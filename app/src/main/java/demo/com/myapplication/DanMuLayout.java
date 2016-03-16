package demo.com.myapplication;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by huanghai on 2016/3/15.
 */
public class DanMuLayout extends RelativeLayout {
    public DanMuLayout(Context context) {
        super(context);
    }

    public DanMuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DanMuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DanMuLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private List<TextView> textViews = new ArrayList<>();
    public void addText(String text,Context context){

        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setTextColor(getColor(context));
        if(textViews.size()!=0){
            textView.setY(new Random().nextInt(100));
        }
        this.addView(textView);
        textViews.add(textView);
        startAnim(textView,context);
    }

    public void startAnim(final TextView textView, Context context){
        System.out.println("-------------:"+context.getApplicationContext().getResources().getDisplayMetrics().widthPixels);
        textView.measure(0,0);
        System.out.println("-------------:"+textView.getMeasuredWidth());
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView,"translationX",context.getApplicationContext().getResources().getDisplayMetrics().widthPixels,0-textView.getMeasuredWidth());
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(animation.getAnimatedFraction()==1){
                    DanMuLayout.this.removeView(textView);
                    System.out.println("移除控件");
                    textViews.remove(textView);
                }
            }
        });
        animator.start();

    }

    private int getColor(Context context){
        int[] colors = {context.getResources().getColor(R.color.colorAccent),context.getResources().getColor(R.color.possible_result_points)
                ,context.getResources().getColor(R.color.category_subscribe_text),context.getResources().getColor(R.color.greenyellow)
                ,context.getResources().getColor(R.color.aquamarine),context.getResources().getColor(R.color.top_bar_normal_bg)};

        int color = colors[new Random().nextInt(6)];

        return color;
    }

    /*private String getRandomColor(){

        int x = new Random().nextInt()*(16777216-1)+1;
        System.out.println("x:"+x);
        String hex = Integer.toHexString(-x);
        System.out.println("hex:"+hex);
        return "#"+hex;
    }*/
}
