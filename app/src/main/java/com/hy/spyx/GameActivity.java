package com.hy.spyx;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class GameActivity extends AppCompatActivity {

    public static final String GAME_NATION_1="NATION_1";
    public static final String GAME_NATION_2="NATION_2";
    public static final String GAME_NATION_3="NATION_3";
    public static final String GAME_NATION_4="NATION_4";
    public static final String GAME_NATION_5="NATION_5";
    public static final String GAME_NATION_6="NATION_6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.init();
        Log.i(NationApp.NT_LOG_TAG,"GameActivity onCreate enter...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(NationApp.NT_LOG_TAG,"GameActivity onStart enter...");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i(NationApp.NT_LOG_TAG,"GameActivity onResume enter...");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i(NationApp.NT_LOG_TAG,"GameActivity onPause enter...");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(NationApp.NT_LOG_TAG,"GameActivity onDestroy enter...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.end_game:
                if(NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_WIN||NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_LOSE){
                    finish();
                }
                else{
                    showNormalDialog();
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        }else {
            return super.dispatchKeyEvent(event);
        }
    }

    public void updateGame(){
        int[] tids = new int[]{R.id.rtxt1,R.id.rtxt2,R.id.rtxt3,R.id.rtxt4,R.id.rtxt5};
        int rnd = NationGame.getInstance().getRound();
        for (int i=0;i<tids.length;i++){
            TextView tv = (TextView)findViewById(tids[i]);
            if(i+1==rnd){
                tv.setTextColor(Color.parseColor("#FFFF37"));
            }
            else{
                tv.setTextColor(Color.parseColor("#000000"));
            }
        }
        Iterator iter = mNationIndicators.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            NationIndictor indictor = (NationIndictor)entry.getValue();
            ImageView iv = (ImageView)this.findViewById(indictor.getImageId());
            //NationGame.getInstance().getExclude();
            if (!NationGame.getInstance().isEnable(indictor.getIndex())){
                iv.setEnabled(false);
            }
            else {
                iv.setEnabled(true);
            }
            int index = indictor.getIndex();
            int flagId = NationGame.getInstance().getImageId(index);
            iv.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, flagId));
            TextView tv = (TextView)findViewById(indictor.getTextId());
            tv.setText(NationGame.getInstance().getNationName(index));
        }
        NationGame game = NationGame.getInstance();
        setRound();
        ListView lv = (ListView)findViewById(R.id.process);
        lv.setAdapter(new ArrayAdapter<String>(GameActivity.this, R.layout.layout_process,R.id.processinfo,NationGame.getInstance().getProcess()));
        lv.setSelection(lv.getBottom());
        Menu menu = mClueMenu.getMenu();
        MenuItem res = menu.findItem(R.id.restart);
        HashSet<Integer> pile = NationGame.getInstance().inSupply();
        for (int sid:pile) {
            MenuItem item = menu.findItem(sid);
            item.setVisible(false);
        }
        pile = NationGame.getInstance().inDrop();
        for (int sid:pile) {
            MenuItem item = menu.findItem(sid);
            item.setVisible(false);
        }
        TextView clv=(TextView) findViewById(R.id.clues);
        if (NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_LOSE||NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_WIN){
            res.setVisible(true);
            pile = NationGame.getInstance().inHands();
            for (int sid:pile) {
                MenuItem item = menu.findItem(sid);
                item.setVisible(false);
            }
            clv.setText("再来一局");
            clv.setOnClickListener(new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    init();
                }
            });
            SharedPreferences preferences = getSharedPreferences(NationApp.APP_INFO,Context.MODE_PRIVATE);
            int all = preferences.getInt(NationApp.ALLCOUNT,-1);
            int win = preferences.getInt(NationApp.WINCOUNT,-1);
            long tim = preferences.getLong(NationApp.TOTALTIME,-1);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(NationApp.ALLCOUNT,all+1);
            if(NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_WIN){
                editor.putInt(NationApp.WINCOUNT,win+1);
            }
            editor.putLong(NationApp.TOTALTIME,tim+(System.currentTimeMillis()-mStart));
            editor.commit();
            NationApp app = (NationApp) NationApp.getAppContext();
            app.setUserInfo(preferences.getInt(NationApp.ALLCOUNT,0),preferences.getInt(NationApp.WINCOUNT,0),preferences.getLong(NationApp.TOTALTIME,0));
            Log.i(NationApp.NT_LOG_TAG,String.format("GameActivity updateGame all[%d] win[%d]....",all,win));
            final AlertDialog.Builder normalDialog =
                    new AlertDialog.Builder(GameActivity.this);
            //normalDialog.setIcon(R.drawable.icon_dialog);
            normalDialog.setTitle("游戏结束");
            if(NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_WIN){
                normalDialog.setMessage("恭喜你获得胜利");
            }
            else{
                normalDialog.setMessage("很遗憾，游戏失败");
            }
            normalDialog.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            // 显示
            normalDialog.show();
        }
        else{
            res.setVisible(false);
            pile = NationGame.getInstance().inHands();
            for (int sid:pile) {
                MenuItem item = menu.findItem(sid);
                item.setVisible(true);
            }
            if(NationGame.getInstance().cluesLeft()==0){
                clv.setText("请实施最后一次抓捕");
                clv.setOnClickListener(null);
            }
            else{
                clv.setText(String.format("线索[%d]", NationGame.getInstance().cluesLeft()));
            }
        }
    }

    private void setRound(){
        ArrayList<Boolean> reuts = NationGame.getInstance().getRoundResult();
        if(reuts.size()!=0){
            for (int i=0;i<reuts.size();i++){
                ImageView iv = getRoundImageView(i+1);
                if(iv!=null){
                    if (reuts.get(i)){
                        iv.setImageLevel(25);
                    }
                    else{
                        iv.setImageLevel(45);
                    }
                }
            }
        }
        else{
            for (int i=0;i<5;i++){
                ImageView iv = getRoundImageView(i+1);
                if(iv!=null){
                    iv.setImageLevel(5);
                }
            }
        }
    }

    private ImageView getRoundImageView(int index){
        if (index==1){
            return (ImageView)findViewById(R.id.rimg1);
        }
        else if(index==2){
            return (ImageView)findViewById(R.id.rimg2);
        }
        else if(index==3){
            return (ImageView)findViewById(R.id.rimg3);
        }
        else if(index==4){
            return (ImageView)findViewById(R.id.rimg4);
        }
        else if(index==5){
            return (ImageView)findViewById(R.id.rimg5);
        }
        return null;
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(GameActivity.this);
        //normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("关闭");
        normalDialog.setMessage("游戏正在进行，确定退出吗？");
        normalDialog.setPositiveButton("确定退出",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        normalDialog.setNegativeButton("继续游戏",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void init(){
        /*WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);*/
        NationGame.getInstance().start(GameActivity.this);
        ListView lv = (ListView)findViewById(R.id.process);
        lv.setDivider(null);
        lv.setAdapter(new ArrayAdapter<String>(GameActivity.this, R.layout.layout_process,R.id.processinfo,NationGame.getInstance().getProcess()));
        initNationMenu();
        initClueMenu();
        updateGame();
        mStart = System.currentTimeMillis();
    }

    private void initClueMenu(){
        TextView tv = (TextView)findViewById(R.id.clues);
        mClueMenu = new ClueMenu(GameActivity.this, tv);//第二个参数是绑定的那个view
        MenuInflater inflater = mClueMenu.getMenuInflater(); //填充菜单
        inflater.inflate(R.menu.clue_menu, mClueMenu.getMenu());
        mClueMenu.setOnMenuItemClickListener(mClueMenu);
        Menu menu = mClueMenu.getMenu();
        MenuItem res = menu.findItem(R.id.restart);
        res.setVisible(false);
        HashSet<Integer> pile = NationGame.getInstance().inSupply();
        for (int sid:pile) {
            MenuItem item = menu.findItem(sid);
            item.setVisible(false);
        }
        tv.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (NationGame.getInstance().getStatus()!=NationGame.GAME_STATUS_ACTION){
                    mClueMenu.show();
                }
            }
        });
    }

    private void createNationIndicator(String key,int index,int imageId,int textId){
        ImageView im = (ImageView)findViewById(imageId);
        NationMenu popup = new NationMenu(GameActivity.this, im,index);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.nation_menu, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(popup);
        final String target = key;
        im.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                NationMenu menu = mNationIndicators.get(target).getMenu();
                if(menu.isEnable()&&NationGame.getInstance().getStatus()==NationGame.GAME_STATUS_ACTION){
                    //显示(这一行代码不要忘记了
                    menu.show();
                }
            }
        });
        NationIndictor ind = new NationIndictor(index,imageId,textId,popup);
        mNationIndicators.put(key,ind);
    }

    private void initNationMenu(){
        mNationIndicators = new HashMap<String,NationIndictor>();
        createNationIndicator(GAME_NATION_1,1,R.id.nation1,R.id.name1);
        createNationIndicator(GAME_NATION_2,2,R.id.nation2,R.id.name2);
        createNationIndicator(GAME_NATION_3,3,R.id.nation3,R.id.name3);
        createNationIndicator(GAME_NATION_4,4,R.id.nation4,R.id.name4);
        createNationIndicator(GAME_NATION_5,5,R.id.nation5,R.id.name5);
        createNationIndicator(GAME_NATION_6,6,R.id.nation6,R.id.name6);
    }

    private HashMap<String,NationIndictor> mNationIndicators;
    private ClueMenu mClueMenu;
    private long mStart;
}
