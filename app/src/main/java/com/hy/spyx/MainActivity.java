package com.hy.spyx;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.net.NetworkInterface;
import java.util.Enumeration;

/*import com.miui.zeus.mimo.sdk.MimoSdk;
import com.miui.zeus.mimo.sdk.ad.AdWorkerFactory;
import com.miui.zeus.mimo.sdk.ad.IAdWorker;
import com.miui.zeus.mimo.sdk.listener.MimoAdListener;
import com.xiaomi.ad.common.pojo.AdType;*/

public class MainActivity extends AppCompatActivity {

    private static boolean isExit=false;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            isExit=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE}     , 0);
            }
        }
        MimoSdk.init(this, "2882303761518010855", "5891801092855", "ow5+PRhL/tPnahMpp+GRvg==");
        try {
            IAdWorker myAdWorker = AdWorkerFactory.getAdWorker(this, (ViewGroup) getWindow().getDecorView(), new MimoAdListener() {
                @Override
                public void onAdPresent() {
                    Log.e(NationApp.NT_LOG_TAG, "onAdPresent");
                }

                @Override
                public void onAdClick() {
                    Log.e(NationApp.NT_LOG_TAG, "onAdClick");
                }

                @Override
                public void onAdDismissed() {
                    Log.e(NationApp.NT_LOG_TAG, "onAdDismissed");
                }

                @Override
                public void onAdFailed(String s) {
                    Log.e(NationApp.NT_LOG_TAG, String.format("onAdFailed[%s]",s));
                }

                @Override
                public void onAdLoaded(int size) {
                    Log.e(NationApp.NT_LOG_TAG, "ad loaded");
                }

                @Override
                public void onStimulateSuccess() {
                }
            }, AdType.AD_SPLASH);
            myAdWorker.loadAndShow("b373ee903da0c6fc9c9da202df95a500");
            Log.i(NationApp.NT_LOG_TAG, "myAdWorker");
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i(NationApp.NT_LOG_TAG, "exception");
        }*/
        setContentView(R.layout.activity_main);

        //AppConnect.getInstance("10d654a87ae9ba9eaccb322acb079eff","default",this);
        //AppConnect.getInstance(this).showOffers(this);


        NationManager.getInstance().init(MainActivity.this);

        TextView startBtn = (TextView) findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        String id = getId();
        if (id==null){
            id = Long.toString(System.currentTimeMillis());
        }
        updateBase();
        updateCover();
        Log.i(NationApp.NT_LOG_TAG,"NationApp onCreate....");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(NationApp.NT_LOG_TAG,"MainActivity onStart enter...");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        updateBase();
        updateCover();
        /*AppConnect.getInstance(this).setPopAdNoDataListener(new AppListener(){ @Override
            public void onPopNoData() {
                Log.e("debug", "插屏广告已经没有数据");
            }
        });
        AppConnect.getInstance(this).showPopAd(this, new AppListener(){ @Override
            public void onPopClose() {
                super.onPopClose();
            }
        });*/
        //Dialog popAdDialog = AppConnect.getInstance(this).getPopAdDialog();
        Log.i(NationApp.NT_LOG_TAG,"MainActivity onResume enter...");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(NationApp.NT_LOG_TAG,"MainActivity onPause enter...");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //AppConnect.getInstance(this).close();
        Log.i(NationApp.NT_LOG_TAG,"MainActivity onDestroy enter...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.game_help:
                showHelp();
                break;
            default:
                break;
        }

        return true;
    }

    private void showHelp(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("游戏规则");
        normalDialog.setMessage("嫌疑犯带着机密信息出逃，警方追踪其踪迹到达机场，但只找到最近6次离开的记录，根据已知线索，你能推断出嫌疑人的秘密目的地吗？通过收集秘密目的地的线索，最终尝试逮捕嫌疑人。游戏开始时点击线索菜单，使用一条线索，然后点击国旗排除一个你认为不是秘密目的地的国家，如果正好排除了秘密目的地，则本回合结束，否则继续；或者发起逮捕行动，无论是否逮捕成功，本回合都将结束。游戏最多进行5个回合，赢得其中3个回合视为胜利，否则失败。游戏一共有16个关于秘密目的地的线索，最多5个可以选择使用，当所有线索使用完时，可以最后发起一次逮捕行动。\n关于数据的注释：游戏中有4500多个数据点，毫无疑问，会有部分不准确或可能产生分歧之处。如有疑问请发邮件到nevershaka@163.com");
        normalDialog.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // 显示
        normalDialog.show();
    }

    /*@Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        else {
            return super.dispatchKeyEvent(event);
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if(!isExit){
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0,2000);
        }
        else{
            finish();
            System.exit(0);
        }
    }

    private void updateBase(){
        SharedPreferences preferences = getSharedPreferences(NationApp.APP_INFO,Context.MODE_PRIVATE);
        int all = preferences.getInt(NationApp.ALLCOUNT,-1);
        int exp = 0;
        int win = 0;
        long tim = 0;
        double rate = 0.0;
        int used = 0;
        if (all==-1){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(NationApp.ALLCOUNT,0);
            editor.putInt(NationApp.WINCOUNT,0);
            editor.putLong(NationApp.TOTALTIME,0);
            editor.commit();
            exp = 0;
            rate = 0.0;
        }
        else{
            win = preferences.getInt(NationApp.WINCOUNT,0);
            tim = preferences.getLong(NationApp.TOTALTIME,0);
            if (all!=0){
                exp = all;
                rate = 100*((double)win/(double)all);
                used = (int)((double)tim/exp)/1000;
            }
        }
        NationApp app = (NationApp) NationApp.getAppContext();
        app.setUserInfo(exp,win,tim);
        TextView tv = (TextView)findViewById(R.id.exp);
        String str = String.format("经验【%d】",all);
        tv.setText(str);
        tv = (TextView)findViewById(R.id.win);
        str = String.format("胜率【%.2f%%】",rate);
        tv.setText(str);
        tv = (TextView)findViewById(R.id.time);
        str = String.format("平均用时【%ds】",used);
        tv.setText(str);
        Log.i(NationApp.NT_LOG_TAG,String.format("MainActivity updateBase exp[%d] win[%d]...",exp,win));
    }

    private String getId(){
        String mac = null;
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface nif = interfaces.nextElement();

                byte[] addr = nif.getHardwareAddress();
                if (addr == null || addr.length == 0) {
                    continue;
                }

                StringBuilder buf = new StringBuilder();
                for (byte b : addr) {
                    buf.append(String.format("%02X", b));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                mac = buf.toString();
                Log.d("---mac", "interfaceName="+nif.getName()+", mac="+mac);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mac;
    }

    private void updateCover(){
        Object[] set = NationManager.getInstance().getandom(42).toArray();
        int[] ids = new int[]{R.id.n1,R.id.n2,R.id.n3,R.id.n4,R.id.n5,R.id.n6,R.id.n7,R.id.n8,R.id.n9,R.id.n10,R.id.n11,R.id.n12,R.id.n13,R.id.n14,R.id.n15,R.id.n16,R.id.n17,R.id.n18,R.id.n19,R.id.n20,R.id.n21,R.id.n22,R.id.n23,R.id.n24,R.id.n25,R.id.n26,R.id.n27,R.id.n28,R.id.n29,R.id.n30,R.id.n31,R.id.n32,R.id.n33,R.id.n34,R.id.n35,R.id.n36,R.id.n37,R.id.n38,R.id.n39,R.id.n40,R.id.n41,R.id.n42};
        for (int i=0;i<ids.length;i++){
            ImageView iv = (ImageView)findViewById(ids[i]);
            int res = NationManager.getInstance().getNation((String)set[i]).getResId();
            final GameNation nation = NationManager.getInstance().getNation((String)set[i]);
            iv.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, res));
            iv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(MainActivity.this);
                    normalDialog.setIcon(nation.getResId());
                    normalDialog.setTitle(nation.getName());
                    String info = String.format("首都：%s",nation.getCapital());
                    if (nation.isCapitaiMax()){
                        info = String.format("%s*",info);
                    }
                    info = String.format("%s\n行驶方向：%s",info,nation.getTravel());
                    info = String.format("%s\n政体：%s",info,nation.getRegime());
                    info = String.format("%s\n面积：%s",info,nation.getArea());
                    info = String.format("%s\n人口：%s",info,nation.getPopulation());
                    info = String.format("%s\n官方货币：%s",info,nation.getCurrency());
                    info = String.format("%s\n人均GDP：%d美元",info,nation.getGdp());
                    info = String.format("%s\n宗教：%s",info,nation.getReligion());
                    info = String.format("%s\n最高峰：海拔%d米",info,nation.getPeek());
                    info = String.format("%s\n农产品：%s",info,nation.getAgriculture());
                    info = String.format("%s\n出口：%s",info,nation.getExport());
                    info = String.format("%s\n工业：%s",info,nation.getIndustry());
                    if(nation.getCoast().length()==0){
                        info = String.format("%s\n海岸线：无",info);
                    }
                    else{
                        info = String.format("%s\n海岸线：%s",info,nation.getCoast());
                    }
                    info = String.format("%s\n历史信息：%s",info,nation.getHistory());
                    info = String.format("%s\n官方语言：%s",info,nation.getLanguage());
                    if(nation.getNeighbor().length()==0){
                        info = String.format("%s\n邻国：无",info);
                    }
                    else{
                        info = String.format("%s\n邻国：%s",info,nation.getNeighbor());
                    }
                    info = String.format("%s\n\n注释：首都后面带*表示首都是国内最大的城市",info);
                    normalDialog.setMessage(info);
                    normalDialog.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    // 显示
                    normalDialog.show();
                }
            });
        }
    }
}
