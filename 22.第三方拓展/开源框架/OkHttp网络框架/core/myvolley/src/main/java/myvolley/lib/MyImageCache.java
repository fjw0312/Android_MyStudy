package myvolley.lib;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;


/***
 * �Զ��� ����ͼƬ������
 * author:fjw0312@163.com
 * date:2017.7.29
 * 
 * */
public class MyImageCache implements ImageCache{
	//LruCacheͼƬ���洦����
	private LruCache<String,Bitmap> myCache;
	int maxMemory = (int) (Runtime.getRuntime().maxMemory() ); // ��ȡ�������ڴ�����ֵ��ʹ���ڴ泬�����ֵ������OutOfMemory�쳣����λ��K
    int cacheSize = maxMemory / 8;// ʹ���������ڴ�ֵ��1/8��Ϊ����Ĵ�С�� 
	
	//����
	public MyImageCache() {
		// TODO Auto-generated constructor stub
		myCache = new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub   // ��д�˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ������
				//return bitmap.getByteCount()/1024;
				return bitmap.getRowBytes()*bitmap.getHeight();
			}			
		};
	}
	
	//���� ����ģʽ
	public static MyImageCache bitmapCache; //��ʾ��������� �� �����ʹ�õ� �û��� ��ʹ�øþ�̬ ����ͼƬ�б�
	public static synchronized MyImageCache instance(){
		if(bitmapCache==null){
			bitmapCache = new MyImageCache();
		}
		return bitmapCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		// TODO Auto-generated method stub
		return myCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		// TODO Auto-generated method stub
		myCache.put(url, bitmap);
	}

}
