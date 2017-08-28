package TXNews.customView;

import java.util.List;

import TXNews.Main.R;
import TXNews.bean.NewsModel;
import TXNews.getUrlContent.GetImageLoader;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/***
 * 自定义控件    继承现有控件 拓展
 * 定制  自定义控件  ： 新闻列表控件
 * 
 * */
public class MyNewsListView extends ListView{

	public MyNewsListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyNewsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyNewsListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyNewsListViewAdapter adapter;
	
	//控件初始化
	private void init_view(){
		adapter = new MyNewsListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//更新控件  数据   提供外部调用更新 数据
	public void Update(List<NewsModel> lst_news){
		adapter = new MyNewsListViewAdapter(lst_news);
		this.setAdapter(adapter);
	}
		
	//自定义一个适配器
    public class MyNewsListViewAdapter extends BaseAdapter{
    	
    	public MyNewsListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyNewsListViewAdapter(List<NewsModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}

		List<NewsModel> NewsM_lst = null;
    	class ViewHolder{    //定义一个保存 控件元素 的辅助类
    		ImageView imageView;
    		TextView  title;
    		TextView  author;
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
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			NewsModel newsModel = (NewsModel)getItem(arg0);
			View view;
			ViewHolder viewHolder;
			
			if(arg1==null){
			//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
				view = View.inflate(getContext(), R.layout.i_news_item, null);//两种写法一样
				
				//添加ViewHolder
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);
				viewHolder.title = (TextView)view.findViewById(R.id.title);
				viewHolder.author = (TextView)view.findViewById(R.id.author);
				viewHolder.date = (TextView)view.findViewById(R.id.date);
				view.setTag(viewHolder);
			}else{
				view = arg1;
				viewHolder = (ViewHolder)view.getTag();  //重新获取 ViewHolder
			}
			
			//将 数据适配
			if(newsModel != null){ 
	
				viewHolder.title.setText(newsModel.title);
				viewHolder.author.setText(newsModel.author_name);
				viewHolder.date.setText(newsModel.date);
//				viewHolder.imageView.setImageURI(Uri.parse(newsModel.thumbnail_pic_s) );
				// 调用 图片 加载类 加载图片 
				GetImageLoader.getImageLoader(newsModel.thumbnail_pic_s, 
						viewHolder.imageView, R.drawable.default_image,R.drawable.error_image,
						800, 650);
			}
			
			return view;
		}  	
    }

}
