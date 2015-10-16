package com.stewhouse.viewpagerindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Gomguk on 15. 10. 14..
 */

// TODO: ViewPager 연동.
// TODO: 위아래 기능 추가.
public class ViewPagerIndicator extends RelativeLayout {

    // Count of indicator max page.
    private int             _indicatorPage;

    // Variables of indicator background view.
    private RelativeLayout  _indicatorBGView;
    private int             _indicatorBGColor;

    // Variables of indicator view.
    private RelativeLayout  _indicatorView;
    private int             _indicatorColor;

    // Count current page which indicator is pointing at.
    private int             _currentPage;

    // Variable of indicator's animation.
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

        // If setIndicatorIndex() is called first time, Views related with indicator should be initialized and add to ViewPagerIndicator.
        if (this._indicatorBGView == null) {
            _indicatorBGView = new RelativeLayout(getContext());
            addView(_indicatorBGView);
        }
        if (this._indicatorView == null) {
            _indicatorView = new RelativeLayout(getContext());
            _indicatorBGView.addView(_indicatorView);
        }

        // Setting indicator background view's parameters.
        LayoutParams indicatorBGParams = new LayoutParams((getMeasuredWidth() / _indicatorPage) * current, LayoutParams.MATCH_PARENT);
        indicatorBGParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        _indicatorBGView.setBackgroundColor(_indicatorBGColor);
        _indicatorBGView.setLayoutParams(indicatorBGParams);

        // Setting indicator view's parameters.
        LayoutParams indicatorParams = new LayoutParams(getMeasuredWidth() / _indicatorPage, LayoutParams.MATCH_PARENT);
        indicatorParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        _indicatorView.setBackgroundColor(_indicatorColor);
        _indicatorView.setLayoutParams(indicatorParams);

        // Setting indicator's animating.
        // If setIndicatorIndex() is called first time, current page should be initialized.
        if (_currentPage < 0 ) {
            _currentPage = current;
        } else {
            if (_currentPage > current) {

                // When the page is smaller than ViewPagerIndicator's current page, Indicator will be animated to left side.
                _indicatorAnimation = new TranslateAnimation(((getMeasuredWidth() / _indicatorPage) * (_currentPage - current)), 0, 0, 0);
            } else if (_currentPage < current) {

                // When the page is bigger than ViewPagerIndicator's current page, Indicator will be animated to right side.
                _indicatorAnimation = new TranslateAnimation(0 - ((getMeasuredWidth() / _indicatorPage) * (current - _currentPage)), 0, 0, 0);
            } else {

                // When the page is same with ViewPagerIndicator's current page, Indicator will not be animated.
                _indicatorAnimation = new TranslateAnimation(0, 0, 0, 0);
            }
            _currentPage = current;
            _indicatorAnimation.setDuration(500);
            _indicatorAnimation.setFillAfter(true);
            _indicatorBGView.startAnimation(_indicatorAnimation);
        }

        // Refresh all views of ViewPagerIndicator.
        invalidate();
    }
}
