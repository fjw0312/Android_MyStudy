package com.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/18.
 * 图片加载  处理类   该类Api 不建议运行在 UI Thread
 * 如果 网络图片 加载建议 使用Glide Picasso框架
 */

public class GetFitBitmap {
    public GetFitBitmap() {
    }

    //处理图片缩放比例
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if ( ((float)height / (float)reqHeight) > ((float)width / (float)reqWidth)) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }
        return inSampleSize;
    }


    //加载 资源文件图片
    public static Bitmap getResourcesImage(Resources res, int rsc_id,int reqWidth, int reqHeight){

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(res, rsc_id, opt);
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return BitmapFactory.decodeResource(res, rsc_id, opt);
    }
    //加载 本地文件图片
    public static Bitmap getFileImage(String strFile,int reqWidth, int reqHeight){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(strFile,opt);
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return  BitmapFactory.decodeFile(strFile,opt);
    }
    //加载 网络图片
    public static Bitmap getNetImage(InputStream in, int reqWidth, int reqHeight){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        BitmapFactory.decodeStream(in, null, opt);
        return null;
    }

    //加载 网络图片
    public static Bitmap getNetImage(String strUrl, int reqWidth, int reqHeight){
        InputStream in = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();   //与服务端连接   接下可以直接收发数据了
            connection.setRequestMethod("GET"); //设置为请求获取数据     发送“GET”
            //connection.setRequestProperty("encoding", "uft-8");  指定编码
            connection.setConnectTimeout(5000); //设置网络连接超时   可以不设置
            connection.setReadTimeout(5000);    //设置网络读取超时   可以不设置
            if (connection.getResponseCode() == 200) {  //数据接收成功
                in = connection.getInputStream(); //获取网络字节流
            }
        }catch (Exception e){
            Log.e("Error:","GetBitmapForImageView->getNetImage>>网络请求异常抛出！");
            return null;
        }

        // 处理 图片压缩
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inSampleSize = calculateInSampleSize(opt,reqWidth,reqHeight);
        return  BitmapFactory.decodeStream(in, null, opt);
    }

}
