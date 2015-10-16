package com.stewhouse.viewpagerindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Allwin-Eva on 15. 10. 14..
 */

// TODO: Animation 연동.
// TODO: ViewPager 연동.
public class ViewPagerIndicator extends RelativeLayout {

    private int             _indicatorPage;

    private RelativeLayout  _indicatorBGView;
    private int             _indicatorBGColor;

    private RelativeLayout  _indicatorView;
    private int             _indicatorColor;

    private int             _currentPage;

    private Animation       _indicatorAnimation;

    public ViewPagerIndicator(Context context) {
        super(context);

        initialize(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initialize(context);
    }

    public void initialize(Context context) {
        _indicatorPage = -1;

        _indicatorBGView = null;
        _indicatorBGColor = -1;

        _indicatorView = null;
        _indicatorColor = -1;

        _currentPage = -1;

        _indicatorAnimation = null;
    }

    public void setIndicatorBGColor(int indicatorBGColor) {
        _indicatorBGColor = indicatorBGColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        _indicatorColor = indicatorColor;
    }

    public void setIndicatorPage(int indicatorPage) {
        _indicatorPage = indicatorPage;
    }

    public void setIndicatorIndex(int current) {
        if (this._indicatorBGView == null) {
            _indicatorBGView = new RelativeLayout(getContext());
            addView(_indicatorBGView);
        }

        LayoutParams indicatorBGParams = new LayoutParams((getMeasuredWidth() / _indicatorPage) * current, LayoutParams.MATCH_PARENT);
        indicatorBGParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        _indicatorBGView.setBackgroundColor(_indicatorBGColor);
        _indicatorBGView.setLayoutParams(indicatorBGParams);

        if (this._indicatorView == null) {
            _indicatorView = new RelativeLayout(getContext());
            _indicatorBGView.addView(_indicatorView);
        }

        LayoutParams indicatorParams = new LayoutParams(getMeasuredWidth() / _indicatorPage, LayoutParams.MATCH_PARENT);
        indicatorParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        _indicatorView.setBackgroundColor(_indicatorColor);
        _indicatorView.setLayoutParams(indicatorParams);

        if (_currentPage < 0 ) {
            _currentPage = current;
        } else {
            if (_currentPage > current) {
                _indicatorAnimation = new TranslateAnimation(((getMeasuredWidth() / _indicatorPage) * (_currentPage - current)), 0, 0, 0);
            } else if (_currentPage < current) {
                _indicatorAnimation = new TranslateAnimation(0 - ((getMeasuredWidth() / _indicatorPage) * (current - _currentPage)), 0, 0, 0);
            } else {
                _indicatorAnimation = new TranslateAnimation(0, 0, 0, 0);
            }
            _currentPage = current;
            _indicatorAnimation.setDuration(500);
            _indicatorAnimation.setFillAfter(true);
            _indicatorBGView.startAnimation(_indicatorAnimation);
        }

        invalidate();
    }
}
