package zhuyekeji.zhengzhou.jxlifecircle.widget;

import android.app.Activity;

import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.base.Request;

import java.io.File;

/**
 * Created by Administrator on 2018/5/7.
 */

public abstract class FileDiialogCallback extends FileCallback
{
    private CustomProgressDialog dialog;

    public FileDiialogCallback(Activity activity, String content) {
//        dialog = new ProgressDialog(activity);

        dialog = new CustomProgressDialog(activity, content);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.layout_progress);
        dialog.setCanceledOnTouchOutside(false);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage(content);
    }

    @Override
    public void onStart(Request<File, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
