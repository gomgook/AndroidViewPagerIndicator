package com.stewhouse.viewpagerindicator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Allwin-Eva on 15. 10. 14..
 */

public class ViewPagerIndicator extends RelativeLayout {

    private RelativeLayout leftView;
    private RelativeLayout rodView;

    public ViewPagerIndicator(Context context) {
        super(context);

        initialize();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initialize();
    }

    public void initialize() {
        leftView = null;
        rodView = null;
    }

    // TODO: super 함수 세분화할 것.
    public void setLayout(Context context, int bgColor, int rodColor, int page, int current) {
        setBackgroundColor(bgColor);

        if (this.leftView == null) {
            leftView = new RelativeLayout(context);
            addView(leftView);
        }

        LayoutParams leftParams = new LayoutParams((getMeasuredWidth() / page) * current, LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        leftView.setBackgroundColor(Color.BLACK);
        leftView.setLayoutParams(leftParams);

        if (this.rodView == null) {
            rodView = new RelativeLayout(context);
            leftView.addView(rodView);
        }

        LayoutParams rodParams = new LayoutParams(getMeasuredWidth() / page, LayoutParams.MATCH_PARENT);
        rodParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        rodView.setBackgroundColor(rodColor);
        rodView.setLayoutParams(rodParams);

        invalidate();
    }
}
