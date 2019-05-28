package com.hy.spyx;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


public class NationMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener{
    public NationMenu(Context context, View view){
        super(context,view);
        mView = (ImageView) view;
        mActivity = (GameActivity)context;
    }
    public NationMenu(Context context, View view,int index){
        super(context,view);
        mView = (ImageView) view;
        mActivity = (GameActivity)context;
        mIndex = index;
    }

    public boolean isEnable(){
        return mView.isEnabled();
    }


    public boolean onMenuItemClick(MenuItem item) {
        Log.d("nation", "onMenuItemClick");
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.arrest:
                Log.d("nation", "onMenuItemClick trade");
                return arrest();
            case R.id.exclude:
                Log.d("nation", "onMenuItemClick openbuy");
                return exclude();
            default:
                return true;
        }
    }

    private boolean arrest(){
        String nationId = NationGame.getInstance().getNationId(mIndex);
        NationGame.getInstance().arrest(nationId);
        mActivity.updateGame();
        return true;
    }

    private boolean exclude(){
        String nationId = NationGame.getInstance().getNationId(mIndex);
        if(NationGame.getInstance().exclude(nationId)){
            mActivity.updateGame();
        }
        else{
            Toast.makeText(mActivity, "最后一次机会只能执行逮捕操作", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    private int mIndex;
    private ImageView mView;
    private GameActivity mActivity;
}
