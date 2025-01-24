package junzi.xposed.hook;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import android.net.Uri;
import android.graphics.Color;
import android.content.DialogInterface;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        final ImageView iv = findViewById(R.id.menu);
        iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.menu, null);
                    LinearLayout parentLayout = new LinearLayout(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    parentLayout.setLayoutParams(layoutParams);
                    parentLayout.setGravity(Gravity.END);
                    parentLayout.setPadding(0, 0, 8 * (int)Math.ceil(getResources().getDisplayMetrics().scaledDensity), 0);
                    parentLayout.addView(popupView);
                    final PopupWindow popupWindow = new PopupWindow(parentLayout,
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT);
                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.setFocusable(true);
                    ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(iv, "rotation", 0, 45);
                    rotateAnimator.setDuration(300);
                    rotateAnimator.start();
                    popupView.findViewById(R.id.menu_item_1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                                try {
                                    String url = "mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=3350859854";
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                    startActivity(intent);
                                } catch (Exception e) {
                                    Toast.makeText(MainActivity.this, "你似乎没有安装QQ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    popupView.findViewById(R.id.menu_item_2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("关于软件")
                                    .setMessage("软件作者：荔枝的君子\n版本：" + BuildConfig.VERSION_NAME + "\n感谢您的使用")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .create();
                                dialog.getWindow().setBackgroundDrawableResource(R.drawable.background);
                                dialog.show();
                            }
                        });
                    popupView.findViewById(R.id.menu_item_3).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                System.exit(0);
                            }
                        });
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                ObjectAnimator rotateBackAnimator = ObjectAnimator.ofFloat(iv, "rotation", iv.getRotation(), 0f);
                                rotateBackAnimator.setDuration(300);
                                rotateBackAnimator.start();
                            }
                        });
                    popupWindow.showAsDropDown(v);
                }
            });
        setApp(R.id.layout1, "滚动的天空", "4.0.0+ (免内购)", "com.turbochilli.rollingsky", "跳转到微信取消支付即可获得奖励\n绕过实名认证\n绕过网络环境验证\n时间验证(未测试)");
        setApp(R.id.layout2, "囧次元", "理论全版本 (去广告)", "com.llmm.huiyuanuxiang", "去除广告，登录账号即可观看动漫");
        setApp(R.id.layout3, "CiliCli动漫", "理论全版本 (去广告)", "com.llmm.huiyuanuxiang.p3", "去除广告");
        setApp(R.id.layout4, "跳舞的线", "理论全版本 (无限会员，免内购)", "com.cmplay.dancingline", "跳转到微信支付取消即可获得奖励(部分免root框架不可用)\n免费会员权益(需联网)");
        setApp(R.id.layout5, "P站助手Lite", "理论全版本 (解锁会员，精简布局)", "com.bravedefault.pixivlite_android.lite", "解锁会员\n精简无用布局");
        setApp(R.id.layout6, "饥饿鲨进化", "理论全版本 (免内购)", "com.fgol", "跳转到支付页点叉即可获取奖励");
        setApp(R.id.layout7, "饥饿鲨世界", "理论全版本 (免内购)", "com.fgol.hsw.zq", "跳转到支付页点叉即可获得奖励");
        setApp(R.id.layout9, "汽水音乐", "支持版本自测 (解锁SVIP)", "com.luna.music", "解锁SVIP(不确定是否长期有效)");
        setApp(R.id.layout8, "看漫", "支持版本自测 (仅解除阅读限制)", "com.wbxm.icartoon", "可以观看被锁住的章节\n由于官方APP使用了360加固，因此免root框架可能无法使用");
        setApp(R.id.layout10, "绿豆免费小说", "理论全版本 (解锁VIP)", "com.ldxs.reader", "解锁VIP");
        setApp(R.id.layout11, "重生细胞", "理论全版本 (解锁全部内容)", "com.bilibili.deadcells.mobile", "解锁本体以及DLC");
        setApp(R.id.layout12, "锦书", "理论全版本 (解锁VIP)", "com.wifi.reader.jinshu", "解锁VIP7");
        setApp(R.id.layout13, "星芽免费短剧", "理论全版本 (解锁VIP)", "com.jz.xydj", "解锁VIP\n由于软件使用了360加固，免root框架可能无法使用");
        if (!isHook()) {
            TextView title = findViewById(R.id.title);
            title.setText("模块未激活");
            title.setTextColor(Color.RED);
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("模块未激活\n功能可能未生效\n使用免Root框架请忽略")
                .setPositiveButton("我知道了", null)
                .create();
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.background);
            dialog.show();
        }
    }
    private void setApp(int Resid, final String name, String ver, final String packname, final String massage) {
        TextView AppName = findViewById(Resid).findViewById(R.id.AppName);
        TextView AppVer = findViewById(Resid).findViewById(R.id.AppVer);
        TextView PackName = findViewById(Resid).findViewById(R.id.PackName);
        ImageView icon = findViewById(Resid).findViewById(R.id.img);
        AppName.setText(name);
        AppVer.setText(ver);
        PackName.setText(packname);
        final Drawable b = getAppIconByPackageName(packname);
        if (b != null) {
            icon.setImageDrawable(b);
        }
        findViewById(Resid).findViewById(R.id.app_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ok;
                    String NoInstall = "";
                    if (b == null){
                        NoInstall = "(未安装)";
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    if (isHook()) {
                        builder.setNegativeButton("取消", null);
                        ok = "打开";
                    } else {
                        ok = "确定";
                    }
                    builder.setTitle(name + NoInstall).setMessage(massage).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dia, int which) {
                                if (isHook()){
                                    startActivity(getPackageManager().getLaunchIntentForPackage(packname));
                                }
                            }
                        });
                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.background);
                    dialog.show();
                }
            });
    }
    private Drawable getAppIconByPackageName(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            Drawable icon = ai.loadIcon(pm);
            return icon;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }
    private Boolean isHook() {
        return false;
    }
}
