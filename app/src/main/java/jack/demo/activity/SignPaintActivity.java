package jack.demo.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import jack.demo.R;
import jack.demo.utils.SPUtils;
import jack.demo.widget.PaletteView;

import static jack.demo.utils.CommonUtils.saveImage;

/**
 * ================================================
 * description:绘画板，保存
 * ================================================
 * package_name: jack.demo.activity
 * author: PayneJay
 * date: 2017/5/27.
 */

public class SignPaintActivity extends JackBaseActivity implements PaletteView.Callback, Handler.Callback {
    @BindView(R.id.palette)
    PaletteView mPalette;
    @BindView(R.id.image_cache)
    ImageView imageCache;
    @BindView(R.id.undo)
    ImageView undo;
    @BindView(R.id.redo)
    ImageView redo;
    @BindView(R.id.pen)
    ImageView pen;
    @BindView(R.id.eraser)
    ImageView eraser;
    @BindView(R.id.clear)
    ImageView clear;

    private ProgressDialog mSaveProgressDlg;
    private static final int MSG_SAVE_SUCCESS = 1;
    private static final int MSG_SAVE_FAILED = 2;
    private Handler mHandler;

    @Override
    protected void init() {
        mPalette.setCallback(this);
        mHandler = new Handler(this);
        ivTopSave.setVisibility(View.VISIBLE);
        pen.setSelected(true);

        initImageCache();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_sign_paint;
    }

    @OnClick({R.id.undo, R.id.redo, R.id.pen, R.id.eraser, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.undo:
                mPalette.undo();
                break;
            case R.id.redo:
                mPalette.redo();
                break;
            case R.id.pen:
                view.setSelected(true);
                eraser.setSelected(false);
                mPalette.setMode(PaletteView.Mode.DRAW);
                break;
            case R.id.eraser:
                view.setSelected(true);
                eraser.setSelected(false);
                mPalette.setMode(PaletteView.Mode.ERASER);
                break;
            case R.id.clear:
                mPalette.clear();
                imageCache.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onUndoRedoStatusChanged() {
        undo.setEnabled(mPalette.canUndo());
        redo.setEnabled(mPalette.canRedo());
    }

    @Override
    protected void topRightSave() {
        if (mSaveProgressDlg == null) {
            initSaveProgressDlg();
        }
        mSaveProgressDlg.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bm = mPalette.buildBitmap();
                String savedFile = saveImage(bm, 100);
                if (savedFile != null) {
                    scanFile(SignPaintActivity.this, savedFile);
                    mHandler.obtainMessage(MSG_SAVE_SUCCESS).sendToTarget();
                    SPUtils.put(SignPaintActivity.this, SPUtils.IMAGE_PATH, savedFile);
                } else {
                    mHandler.obtainMessage(MSG_SAVE_FAILED).sendToTarget();
                }
            }
        }).start();
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SAVE_FAILED:
                mSaveProgressDlg.dismiss();
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                break;
            case MSG_SAVE_SUCCESS:
                mSaveProgressDlg.dismiss();
                Toast.makeText(this, "画板已保存", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_SAVE_FAILED);
        mHandler.removeMessages(MSG_SAVE_SUCCESS);
    }

    private static void scanFile(Context context, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }

    private void initSaveProgressDlg() {
        mSaveProgressDlg = new ProgressDialog(this);
        mSaveProgressDlg.setMessage("正在保存,请稍候...");
        mSaveProgressDlg.setCancelable(false);
    }

    private void initImageCache() {
        String imagePath = (String) SPUtils.get(this, SPUtils.IMAGE_PATH, "");
        if (!TextUtils.isEmpty(imagePath)) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;           /*不进行图片抖动处理*/
            options.inPreferredConfig = null;  /*设置让解码器以最佳方式解码*/
            options.inSampleSize = 1;          /*图片长宽方向缩小倍数*/
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            imageCache.setImageBitmap(bitmap);
            imageCache.setVisibility(View.VISIBLE);
        }
    }

}
