package study.com.feedbackdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import study.com.feedbackdemo.R;
import study.com.feedbackdemo.helper.IdHelper;




public class DialogCreator {
    public static Dialog mLoadingDialog;

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(IdHelper.getLayout(context, "jmui_loading_view"), null);
        RelativeLayout layout = (RelativeLayout) v.findViewById(IdHelper.getViewID(context, "jmui_dialog_view"));
        ImageView mLoadImg = (ImageView) v.findViewById(IdHelper.getViewID(context, "jmui_loading_img"));
        TextView mLoadText = (TextView) v.findViewById(IdHelper.getViewID(context, "jmui_loading_txt"));
        AnimationDrawable mDrawable = (AnimationDrawable) mLoadImg.getDrawable();
        mDrawable.start();
        mLoadText.setText(msg);
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return loadingDialog;
    }

    public static Dialog createBaseCustomDialog(Context context, String title, String text,
                                                View.OnClickListener onClickListener) {
        Dialog baseDialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(IdHelper.getLayout(context, "jmui_dialog_base"), null);
        baseDialog.setContentView(v);
        TextView titleTv = (TextView) v.findViewById(IdHelper.getViewID(context, "jmui_dialog_base_title_tv"));
        TextView textTv = (TextView) v.findViewById(IdHelper.getViewID(context, "jmui_dialog_base_text_tv"));
        Button confirmBtn = (Button) v.findViewById(IdHelper.getViewID(context, "jmui_dialog_base_confirm_btn"));
        titleTv.setText(title);
        textTv.setText(text);
        confirmBtn.setOnClickListener(onClickListener);
        baseDialog.setCancelable(false);
        return baseDialog;
    }

    public static Dialog createBaseDialogWithTitle(Context context, String title, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View view = LayoutInflater.from(context).inflate(IdHelper.getLayout(context,
                "jmui_dialog_base_with_button"), null);
        dialog.setContentView(view);
        TextView titleTv = (TextView) view.findViewById(IdHelper.getViewID(context, "jmui_title"));
        titleTv.setText(title);
        final Button cancel = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        commit.setText(IdHelper.getString(context, "jmui_confirm"));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createDelConversationDialog(Context context,
                                                     View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View v = LayoutInflater.from(context).inflate(
                IdHelper.getLayout(context, "jmui_dialog_delete_conv"), null);
        dialog.setContentView(v);
        final LinearLayout deleteLl = (LinearLayout) v.findViewById(IdHelper
                .getViewID(context, "jmui_delete_conv_ll"));
        final LinearLayout top = (LinearLayout) v.findViewById(IdHelper
                .getViewID(context, "jmui_top_conv_ll"));
        TextView isTop = (TextView) v.findViewById(IdHelper.getViewID(context, "tv_conv_top"));

        deleteLl.setOnClickListener(listener);
//        top.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }
    public static Dialog createSavePictureDialog(Context context,
                                                 View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View v = LayoutInflater.from(context).inflate(
                IdHelper.getLayout(context, "jmui_dialog_delete_conv"), null);
        dialog.setContentView(v);
        final LinearLayout deleteLl = (LinearLayout) v.findViewById(IdHelper
                .getViewID(context, "jmui_delete_conv_ll"));
        TextView textView = (TextView) v.findViewById(IdHelper.getViewID(context, "tv_dialogText"));
        textView.setText("保存到手机");

        deleteLl.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createDelRecommendDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View v = LayoutInflater.from(context).inflate(
                IdHelper.getLayout(context, "jmui_dialog_del_recommend"), null);
        dialog.setContentView(v);
        final LinearLayout deleteLl = (LinearLayout) v.findViewById(IdHelper
                .getViewID(context, "jmui_del_recommend_ll"));
        deleteLl.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createLongPressMessageDialog(Context context, String title, boolean hide,
                                                      View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View view = LayoutInflater.from(context).inflate(IdHelper.getLayout(context, "jmui_dialog_msg_alert"), null);
        dialog.setContentView(view);
        Button copyBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_copy_msg_btn"));
        Button deleteBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_delete_msg_btn"));
        if (hide) {
            copyBtn.setVisibility(View.GONE);
        }
        copyBtn.setOnClickListener(listener);
        deleteBtn.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createResendDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View view = LayoutInflater.from(context).inflate(
                IdHelper.getLayout(context, "jmui_dialog_base_with_button"), null);
        dialog.setContentView(view);
        Button cancelBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        Button resendBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        cancelBtn.setOnClickListener(listener);
        resendBtn.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createDeleteMessageDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(IdHelper.getLayout(context, "jmui_dialog_base_with_button"), null);
        dialog.setContentView(v);
        TextView title = (TextView) v.findViewById(IdHelper.getViewID(context, "jmui_title"));
        title.setText(IdHelper.getString(context, "jmui_clear_history_confirm_title"));
        final Button cancel = (Button) v.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) v.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        commit.setText(IdHelper.getString(context, "jmui_confirm"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createExitGroupDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(IdHelper.getLayout(context, "jmui_dialog_base_with_button"), null);
        dialog.setContentView(v);
        TextView title = (TextView) v.findViewById(IdHelper.getViewID(context, "jmui_title"));
        title.setText(IdHelper.getString(context, "jmui_delete_group_confirm_title"));
        final Button cancel = (Button) v.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) v.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        commit.setText(IdHelper.getString(context, "jmui_confirm"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createSetAvatarDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(IdHelper.getLayout(context, "jmui_dialog_set_avatar"), null);
        dialog.setContentView(view);
        Button takePhotoBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_take_photo_btn"));
        Button pickPictureBtn = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_pick_picture_btn"));
        takePhotoBtn.setOnClickListener(listener);
        pickPictureBtn.setOnClickListener(listener);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createLogoutDialog(Context context, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View view = LayoutInflater.from(context).inflate(IdHelper.getLayout(context,
                "jmui_dialog_base_with_button"), null);
        dialog.setContentView(view);
        TextView title = (TextView) view.findViewById(IdHelper.getViewID(context, "jmui_title"));
        title.setText(IdHelper.getString(context, "jmui_logout_confirm"));
        final Button cancel = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        commit.setText(IdHelper.getString(context, "jmui_confirm"));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    public static Dialog createLogoutStatusDialog(Context context, String title, View.OnClickListener listener) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        View view = LayoutInflater.from(context).inflate(IdHelper.getLayout(context,
                "jmui_dialog_base_with_button"), null);
        dialog.setContentView(view);
        TextView titleTv = (TextView) view.findViewById(IdHelper.getViewID(context, "jmui_title"));
        titleTv.setText(title);
        final Button cancel = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        cancel.setText("退出");
        commit.setText("重新登录");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }


    public static Dialog createDeleteMemberDialog(Context context, View.OnClickListener listener,
                                                  boolean isSingle) {
        Dialog dialog = new Dialog(context, IdHelper.getStyle(context, "jmui_default_dialog_style"));
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(IdHelper.getLayout(context, "jmui_dialog_base_with_button"), null);
        dialog.setContentView(view);
        TextView title = (TextView) view.findViewById(IdHelper.getViewID(context, "jmui_title"));
        if (isSingle) {
            title.setText(IdHelper.getString(context, "jmui_delete_member_confirm_hint"));
        } else {
            title.setText(IdHelper.getString(context, "jmui_delete_confirm_hint"));
        }
        final Button cancel = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_cancel_btn"));
        final Button commit = (Button) view.findViewById(IdHelper.getViewID(context, "jmui_commit_btn"));
        cancel.setOnClickListener(listener);
        commit.setOnClickListener(listener);
        commit.setText(IdHelper.getString(context, "jmui_confirm"));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }



}
