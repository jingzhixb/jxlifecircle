package zhuyekeji.zhengzhou.jxlifecircle.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * desc:
 * time: 2018/4/12
 *
 * @author wl
 */
public class NewLinearLayout extends LinearLayout
{

    /*将触摸点坐标传递出去*/
    private IGetX iGetX;
    /*弹性滑动*/
    private Scroller mScroller;

    public NewLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public NewLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /*initial*/
    public void init(Context context){
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll(){
        super.computeScroll();
        /*判断是否完成滑动*/
        if(mScroller.computeScrollOffset()){
            scrollTo(0 , mScroller.getCurrY());
            /*循环调用该方法*/
            postInvalidate();
        }
    }

    /*开启弹性滑动*/
    public void beginScroll(int dx ){
       // mScroller.startScroll(0 , mScroller.getCurrY() , 0 , dx , 1000);
        /*调用computeScroll()方法*/
      //  invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        float dx = event.getX()-100;
        float dy = event.getY()-30;
        if(event.getAction() == MotionEvent.ACTION_MOVE)
            iGetX.point(dx , dy);

        if (event.getAction()== MotionEvent.ACTION_UP)
            iGetX.up();
        return super.dispatchTouchEvent(event);
    }

    public void set(IGetX iGetX){
        this.iGetX = iGetX;
    }

    public interface IGetX{
        void point(float dx, float dy);
        void up();
    }
}
