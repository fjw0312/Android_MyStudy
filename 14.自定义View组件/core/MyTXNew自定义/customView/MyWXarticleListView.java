package TXNews.customView;

import java.util.List;

import TXNews.Main.R;
import TXNews.bean.WXArticleModel;
import TXNews.getUrlContent.GetImageLoader;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/***
 * 自定义控件    继承现有控件 拓展
 * 定制  自定义控件  ：微信精选列表控件
 * 
 * */
public class MyWXarticleListView extends ListView{

	public MyWXarticleListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyWXarticleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyWXarticleListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyWXarticleListViewAdapter adapter;
	
	//控件初始化
	private void init_view(){
		adapter = new MyWXarticleListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	public void Update(List<WXArticleModel> lst_news){
		adapter = new MyWXarticleListViewAdapter(lst_news);
		this.setAdapter(adapter);
	}
		
	//自定义一个适配器
    public class MyWXarticleListViewAdapter extends BaseAdapter{
    	
    	public MyWXarticleListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyWXarticleListViewAdapter(List<WXArticleModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}

		List<WXArticleModel> NewsM_lst = null;
    	class ViewHolder1{    //定义一个保存 控件元素 的辅助类
    		ImageView imageView;
    		TextView  title;
    		TextView  subTitle;
    		TextView  hitcount;
    		TextView  date;
    	}
    	class ViewHolder2{    //定义一个保存 控件元素 的辅助类
    		
    		TextView  title;
    		TextView  subTitle;
    		TextView  hitcount;
    		TextView  date;
    	}
    	 
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if(NewsM_lst==null) return 0;
			return NewsM_lst.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			if(NewsM_lst==null) return null;
			return NewsM_lst.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			WXArticleModel wxArticleModel = (WXArticleModel)getItem(arg0);
//			View view;
			ViewHolder1 viewHolder1;
			ViewHolder2 viewHolder2;
			
			
			switch(wxArticleModel.type){
			case WXArticleModel.TYPE_A:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_wxarticle_item1, null);
					viewHolder1 = new ViewHolder1();
					viewHolder1.imageView = (ImageView)view.findViewById(R.id.imageView);//添加ViewHolder
					viewHolder1.title = (TextView)view.findViewById(R.id.title);
					viewHolder1.subTitle = (TextView)view.findViewById(R.id.subTitle);
					viewHolder1.hitcount = (TextView)view.findViewById(R.id.hitcount);
					viewHolder1.date = (TextView)view.findViewById(R.id.date);
					view.setTag(viewHolder1);
				}else{
	//				view = arg1;
					viewHolder1 = (ViewHolder1)view.getTag();  //重新获取 ViewHolder 
				}
				//将 数据适配
				if(wxArticleModel != null){
					viewHolder1.title.setText(wxArticleModel.title);
					viewHolder1.subTitle.setText(wxArticleModel.subTitle);
					viewHolder1.hitcount.setText("阅读："+wxArticleModel.hitCount);
					viewHolder1.date.setText(wxArticleModel.pubTime);
					GetImageLoader.getImageLoader(wxArticleModel.thumbnails, 
							viewHolder1.imageView, R.drawable.default_image,R.drawable.error_image,
							800, 650);
				}
				break;
			case WXArticleModel.TYPE_B:
				if(view==null){
					view = View.inflate(getContext(), R.layout.i_wxarticle_item2, null);
					viewHolder2 = new ViewHolder2();
					viewHolder2.title = (TextView)view.findViewById(R.id.title);
					viewHolder2.subTitle = (TextView)view.findViewById(R.id.subTitle);
					viewHolder2.hitcount = (TextView)view.findViewById(R.id.hitcount);
					viewHolder2.date = (TextView)view.findViewById(R.id.date);
					view.setTag(viewHolder2);
				}else{
		//			view = arg1;
					viewHolder2 = (ViewHolder2)view.getTag();  //重新获取 ViewHolder 
				}
				//将 数据适配
				if(wxArticleModel != null){
					viewHolder2.title.setText(wxArticleModel.title);
					viewHolder2.subTitle.setText(wxArticleModel.subTitle);
					viewHolder2.hitcount.setText(wxArticleModel.hitCount);
					viewHolder2.date.setText(wxArticleModel.pubTime);
				}
				break; 
			default:break;
			}
			
			return view;
		}  	
    }

}
