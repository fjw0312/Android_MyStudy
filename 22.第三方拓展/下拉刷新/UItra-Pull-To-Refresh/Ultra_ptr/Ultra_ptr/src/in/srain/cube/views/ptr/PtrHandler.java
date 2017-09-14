package in.srain.cube.views.ptr;

import android.view.View;

/**
 *  下拉刷新的功能接口
 *  	-- 1，是否能够刷新的回调（checkCanDoRefresh（）） 
 *  			--》true表示可以刷新，false表示不可以刷新  （ListView默认实现类：PtrDefaultHandler）
 *  	-- 2,刷新开始的回调（onRefreshBegin()）
 *  			-->>该方法中主要请求网络，刷新数据
 *  
 * 备注人： Alex_MaHao
 * @date 创建时间：2016年1月21日 下午5:45:46
 */
public interface PtrHandler {

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     * <p/>
     * {@link in.srain.cube.views.ptr.PtrDefaultHandler#checkContentCanBePulledDown}
     */
    public boolean checkCanDoRefresh(final PtrFrameLayout frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    public void onRefreshBegin(final PtrFrameLayout frame);
}