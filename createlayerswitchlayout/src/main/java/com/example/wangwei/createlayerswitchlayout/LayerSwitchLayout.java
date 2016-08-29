package com.example.wangwei.createlayerswitchlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by wangwei on 16/8/25.
 */
public class LayerSwitchLayout extends FrameLayout{

    private ViewGroup scrollDownShowLayer, defaultShowLayer;
    private boolean isShowDefault = true;
    private static final int LAYER_VERTICAL_OFFSET = 100;
    private SwitchListener switchListener;
    private int downY = -1;
    private String TAG = "DL";
    private Scroller scroller;

    public LayerSwitchLayout(Context context) {
        this(context,null);
    }

    public LayerSwitchLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LayerSwitchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount() >= 2 && getChildAt(0) instanceof ViewGroup && getChildAt(1) instanceof ViewGroup) {
            defaultShowLayer = (ViewGroup) getChildAt(1);
            scrollDownShowLayer = (ViewGroup) getChildAt(0);
        }else {
            throw new InflateException();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        scrollDownShowLayer.layout(left, top - LAYER_VERTICAL_OFFSET, right, bottom - LAYER_VERTICAL_OFFSET);
        defaultShowLayer.layout(left,top,right,bottom);
        setLayerVisibility(isShowDefault);
    }

    private int interceptDownX = 0;
    private int interceptDownY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean result = false;

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                interceptDownX = (int) ev.getRawX();
                interceptDownY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int) ev.getRawX();
                int y = (int) ev.getRawY();
                if(Math.abs(x - interceptDownX) < Math.abs(y - interceptDownY)){
                    result = true;
                }else {
                    result = false;
                }
                interceptDownX = x;
                interceptDownY = y;
                break;
            case MotionEvent.ACTION_UP:
                interceptDownX = 0;
                interceptDownY = 0;
                break;

        }

        if(result){
            return result;
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if(scrollDownShowLayer.getVisibility() != VISIBLE){
                    scrollDownShowLayer.setVisibility(VISIBLE);
                }

                if(defaultShowLayer.getVisibility() != VISIBLE){
                    defaultShowLayer.setVisibility(VISIBLE);
                }

                if(downY == -1){
                    downY = interceptDownY;
                }else {
                    interceptDownY = downY;
                }
                int deltaY = (int) (event.getY() - downY) / 2;
                downY = (int) event.getY();
                int newScrollY = getScrollY() - deltaY;

//                System.out.println("Fraction:"+newScrollY / (float)LAYER_VERTICAL_OFFSET);


                if(newScrollY < -LAYER_VERTICAL_OFFSET)
                    newScrollY = -LAYER_VERTICAL_OFFSET;
                if(newScrollY > 0)
                    newScrollY = 0;
                scrollTo(0,newScrollY);

                float fraction = Math.abs(newScrollY / (float)LAYER_VERTICAL_OFFSET);
                setViewsAlpha(fraction);

                break;
            case MotionEvent.ACTION_UP:
                System.out.println(getScrollY());
                if(-getScrollY()<(LAYER_VERTICAL_OFFSET /4))
                {
                    showDefaultView();
                }else{
                    showScrollDownView();
                }
                downY = -1;

                break;
        }
        return true;
    }

    public void showDefaultView()
    {
        if(switchListener != null){
            switchListener.onShowDefaultLayer(isShowDefault);
        }
        isShowDefault = true;
        scroller.startScroll(0,getScrollY(),0,-getScrollY(),400);
        invalidate();
    }

    public void showScrollDownView()
    {
        if(switchListener != null){
            switchListener.onShowScrollDownLayer(isShowDefault);
        }
        isShowDefault = false;
        scroller.startScroll(0,getScrollY(),0,-(LAYER_VERTICAL_OFFSET+getScrollY()),400);
        invalidate();
    }

    public SwitchListener getSwitchListener() {
        return switchListener;
    }

    public void setSwitchListener(SwitchListener switchListener) {
        this.switchListener = switchListener;
    }

    public interface SwitchListener{
        void onShowScrollDownLayer(boolean isShowDefault);
        void onShowDefaultLayer(boolean isShowDefault);
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset())
        {
            scrollTo(0,scroller.getCurrY());
            invalidate();
            float fraction = Math.abs(getScrollY() / (float)LAYER_VERTICAL_OFFSET);
            setViewsAlpha(fraction);
            if(scroller.isFinished()){
                setLayerVisibility(isShowDefault);
            }
        }
    }

    private void setViewsAlpha(float fraction){
        scrollDownShowLayer.setAlpha(fraction);
        defaultShowLayer.setAlpha(1 - fraction);
    }

    private void setLayerVisibility(boolean isShowDefault){
        if(isShowDefault){
            defaultShowLayer.setVisibility(VISIBLE);
            scrollDownShowLayer.setVisibility(GONE);
        }else {
            defaultShowLayer.setVisibility(GONE);
            scrollDownShowLayer.setVisibility(VISIBLE);
        }
    }

}
