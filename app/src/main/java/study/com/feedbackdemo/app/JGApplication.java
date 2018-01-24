package study.com.feedbackdemo.app;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.facebook.drawee.backends.pipeline.Fresco;

import study.com.feedbackdemo.imagepicker.GlideImageLoader;
import study.com.feedbackdemo.imagepicker.ImagePicker;
import study.com.feedbackdemo.imagepicker.util.SharePreferenceManager;
import study.com.feedbackdemo.imagepicker.util.StorageUtil;
import study.com.feedbackdemo.imagepicker.view.CropImageView;

/**
 * Created by Administrator on 2017/12/5.
 */

public class JGApplication extends com.activeandroid.app.Application {
    public static int maxImgCount;
    public static String PICTURE_DIR = "sdcard/JChatDemo/pictures/";
    public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //完成存储工具的初始化
        StorageUtil.init(context, null);
        //图片加载的开源框架Fresco的初始化
        Fresco.initialize(getApplicationContext());

        //注册图片选择器
        initImagePicker();

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    public static void setPicturePath(String appKey) {
        if (!SharePreferenceManager.getCachedAppKey().equals(appKey)) {
            SharePreferenceManager.setCachedAppKey(appKey);
            PICTURE_DIR = "sdcard/JChatDemo/pictures/" + appKey + "/";
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
