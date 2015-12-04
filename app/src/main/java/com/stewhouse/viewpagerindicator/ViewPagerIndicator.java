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
    private LayoutParams    _indicatorBGParams;

    // Variables of indicator view.
    private RelativeLayout  _indicatorView;
    private int             _indicatorColor;
    private LayoutParams    _indicatorParams;

    // Count current page which indicator is pointing at.
    private int             _currentPage;

    // Variable of indicator's animation.
    private Animation       _indicatorAnimation;

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
        _indicatorPage = -1;

        _indicatorBGView = null;
        _indicatorBGColor = -1;
        _indicatorBGParams = null;

        _indicatorView = null;
        _indicatorColor = -1;
        _indicatorParams = null;

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
        initViewsAndParams();

        // Setting indicator background view's parameters.
        _indicatorBGParams.setMargins(0 - ((getMeasuredWidth() / _indicatorPage) * (_indicatorPage - current)), 0, 0, 0);
        _indicatorBGView.setBackgroundColor(_indicatorBGColor);
        _indicatorBGView.setLayoutParams(_indicatorBGParams);

        // Setting indicator view's parameters.
        _indicatorView.setBackgroundColor(_indicatorColor);
        _indicatorView.setLayoutParams(_indicatorParams);

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

    private void initViewsAndParams() {
        if (_indicatorBGView == null) {
            _indicatorBGView = new RelativeLayout(getContext());
            addView(_indicatorBGView);
        }
        if (_indicatorView == null) {
            _indicatorView = new RelativeLayout(getContext());
            _indicatorBGView.addView(_indicatorView);
        }
        if (_indicatorBGParams == null) {
            _indicatorBGParams = new LayoutParams(getMeasuredWidth(), LayoutParams.MATCH_PARENT);
            _indicatorBGParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        }
        if (_indicatorParams == null) {
            _indicatorParams = new LayoutParams(getMeasuredWidth() / _indicatorPage, LayoutParams.MATCH_PARENT);
            _indicatorParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        }
    }
}
