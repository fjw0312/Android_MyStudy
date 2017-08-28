package TXNews.customView;

import java.util.List;

import TXNews.Main.R;
import TXNews.bean.NewsModel;
import TXNews.bean.VideoItemModel;
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
import android.widget.VideoView;


/***
 * �Զ���ؼ�    �̳����пؼ� ��չ
 * ����  �Զ���ؼ�  �� ��Ƶ�б�ؼ�
 * 
 * */
public class MyVideoListView extends ListView{

	public MyVideoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init_view(); 
	}

	public MyVideoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init_view();
	}

	public MyVideoListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init_view();
	}
	MyVideoListViewAdapter adapter;
	
	//�ؼ���ʼ��
	private void init_view(){
		adapter = new MyVideoListViewAdapter();
		this.setAdapter(adapter);
	}
	
	//���¿ؼ�  ����   �ṩ�ⲿ���ø��� ����
	public void Update(List<VideoItemModel> lst_news){
		adapter = new MyVideoListViewAdapter(lst_news);
		this.setAdapter(adapter);
	}
		
	//�Զ���һ��������
    public class MyVideoListViewAdapter extends BaseAdapter{
    	
    	public MyVideoListViewAdapter() {
			super();
			// TODO Auto-generated constructor stub
		}
    	public MyVideoListViewAdapter(List<VideoItemModel> lst_news) {
			super();
			// TODO Auto-generated constructor stub
			NewsM_lst = lst_news;
		}

		List<VideoItemModel> NewsM_lst = null;
    	class ViewHolder{    //����һ������ �ؼ�Ԫ�� �ĸ�����
    		ImageView imageView;
    		TextView  title;
    		TextView  time;
    		VideoView videoView;
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
			VideoItemModel videoItemModel = (VideoItemModel)getItem(arg0);
			View view;
			ViewHolder viewHolder;
			
			if(arg1==null){
			//	view = LayoutInflater.from(getContext()).inflate(item_layout_id, null); 
				view = View.inflate(getContext(), R.layout.i_videolist_item, null);//����д��һ��
				
				//���ViewHolder
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView)view.findViewById(R.id.img_id);
				viewHolder.title = (TextView)view.findViewById(R.id.Tx_title);
				viewHolder.time = (TextView)view.findViewById(R.id.Tx_time);
				viewHolder.videoView = (VideoView)view.findViewById(R.id.Vd_videoId);
				view.setTag(viewHolder);
			}else{
				view = arg1;
				viewHolder = (ViewHolder)view.getTag();  //���»�ȡ ViewHolder
			}
			
			//�� ��������
			if(videoItemModel != null){ 
				String str_min = "";
				String str_sec = "";
				String str_time = videoItemModel.sizeHD;
				 if("".equals(str_time)==false){
					 int time_i = ( Integer.parseInt( str_time ) )/10;
					 str_min = String.valueOf(time_i/60);
					 str_sec = String.valueOf(time_i%60);
				 }
				viewHolder.title.setText(videoItemModel.title);
				if("0".equals(str_time)==false){
					if("0".equals(str_sec)) str_sec = "00";
					
					viewHolder.time.setText(str_min+":"+str_sec);
				}
				

				// ���� ͼƬ ������ ����ͼƬ 
				GetImageLoader.getImageLoader(videoItemModel.cover, 
						viewHolder.imageView, R.drawable.default_image,R.drawable.error_image,
						800, 600);
				//׼�� ����video
			}
			
			return view;
		}  	
    }

}
