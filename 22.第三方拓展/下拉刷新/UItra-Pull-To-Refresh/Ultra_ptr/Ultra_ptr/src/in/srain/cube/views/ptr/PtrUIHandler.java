package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 * 	下拉刷新的UI状态回调类
 * 		下拉过程中头部UI变化的监听
 * 
 * 备注人： Alex_MaHao
 * @date 创建时间：2016年1月21日 下午5:49:37
 */
public interface PtrUIHandler {

    /**
     * When the content view has reached（达成，到达） top and refresh has been completed, view will be reset.
     *	Content 重新回到顶部， Header 消失，整个下拉刷新过程完全结束以后，重置 View。
     * @param frame
     */
    public void onUIReset(PtrFrameLayout frame);

    /**
     * prepare for loading
     *	准备刷新，Header 将要出现时调用。
     * @param frame
     */
    public void onUIRefreshPrepare(PtrFrameLayout frame);

    /**
     * 开始刷新，Header 进入刷新状态之前调用。
     * perform refreshing UI
     */
    public void onUIRefreshBegin(PtrFrameLayout frame);

    /**
     * 刷新结束，Header 开始向上移动之前调用。
     * perform UI after refresh
     */
    public void onUIRefreshComplete(PtrFrameLayout frame);

    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
