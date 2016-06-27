package com.example.wangwei.lockscreendemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

public class SlideLayout extends FrameLayout{
	private View upView,mainView,downView;
	private int upWidth = 0;
	private Scroller scroller;
	public SlideLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SlideLayout(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		scroller = new Scroller(getContext());
		ScrollView scroller = new ScrollView(getContext());
    }
	
	/**
	 * 当1级的子view全部加载完调用，可以用初始化子view的引用
	 * 注意，这里无法获取子view的宽高
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		upView = getChildAt(0);
		mainView = getChildAt(1);
		downView = getChildAt(2);
		upWidth = upView.getLayoutParams().width;
	}

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("dispatch");
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println("intercept");
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = (int) ( ev.getY()- downY);

			if(Math.abs(deltaY)>8){
				return true;
			}
			break;
		}
		return true;
//		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * l: 当前子view的左边在父view的坐标系中的x坐标
	 * t: 当前子view的顶边在父view的坐标系中的y坐标
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
//		Log.e("MAIN", "L: "+l+"   t: "+t  +"  r: "+r  + "   b: "+b);
		upView.layout(0, -upView.getMeasuredHeight(), upWidth, 0);
		mainView.layout(0, 0, r, b);
		downView.layout(0,200,r,b+mainView.getMeasuredHeight());
	}
	
	private int downY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("touch");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveY = (int) event.getY();
			int deltaY = (int) ( moveY- downY);
			this.layout(this.getLeft(),this.getTop() + deltaY, this.getRight(), this.getBottom() + deltaY);
			downY = moveY;
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return true;
	}
	

}
