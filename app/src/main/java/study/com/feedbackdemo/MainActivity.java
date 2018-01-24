package study.com.feedbackdemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import study.com.feedbackdemo.activity.BaseActivity;
import study.com.feedbackdemo.app.JGApplication;
import study.com.feedbackdemo.bean.ImageItem;
import study.com.feedbackdemo.common.CommonUtils;
import study.com.feedbackdemo.dialog.SelectDialog;
import study.com.feedbackdemo.imagepicker.ImagePicker;
import study.com.feedbackdemo.imagepicker.activity.ImageGridActivity;
import study.com.feedbackdemo.imagepicker.activity.ImagePreviewDelActivity;
import study.com.feedbackdemo.imagepicker.adapter.ImagePickerAdapter;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private static final int MAX_COUNT = 300;
    private static final String JIGUANG_IM_ACCOUNT = "feedback_Android";
    private EditText mEd_feedback;
    private Button mBtn_sure;
    private TextView mTv_count;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private TextView mFeedback_text;
    int maxImgCount = 4;
    private Dialog mLoadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initWidget();
        initListener();

    }

    /**
     * 初始化控件和标题栏
     */
    private void initView() {
        initTitle(true, true, "意见反馈", "", false, "");
        mEd_feedback = (EditText) findViewById(R.id.ed_feedback);
        mTv_count = (TextView) findViewById(R.id.tv_count);
        mFeedback_text = (TextView) findViewById(R.id.feedback_text);
        mBtn_sure = (Button) findViewById(R.id.btn_sure);
    }

    /**
     * 初始化RecycleView及其相关配置
     */
    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        JGApplication.maxImgCount = 4;
        //初始化RecycleView的适配器
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //在已知RecycleView显示的条目下，添加该属性可以最大程度上优化RecycleView的性能，
        // 因为此属性为true时可以在RecycleView内容改变的时候避免重复计算RecycleView的Item高度
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 添加“确认”按钮的点击事件以及“输入框”的内容改变事件
     */
    private void initListener() {
        mBtn_sure.setOnClickListener(this);
        mEd_feedback.addTextChangedListener(new TextChange());
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
//                mLoadingDialog = DialogCreator.createLoadingDialog(MainActivity.this,
//                        getString(R.string.creating_msg));
//                mLoadingDialog.show();
//                //发送文字
//                String feedback = mEd_feedback.getText().toString().trim();
                Toast.makeText(this, "暂时无法发送", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View view, final int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(MainActivity.this, ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);

                break;
            default:
                //打开预览
                final Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    private class TextChange implements TextWatcher {

        @Override
        public void afterTextChanged(Editable arg0) {
            int num = MAX_COUNT - arg0.length();
            mTv_count.setText(num + "");
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before,
                                  int count) {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CommonUtils.hideKeyboard(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (selImageList.size() > 0) {
            mFeedback_text.setVisibility(View.GONE);
        } else {
            mFeedback_text.setVisibility(View.VISIBLE);
        }
    }
}
