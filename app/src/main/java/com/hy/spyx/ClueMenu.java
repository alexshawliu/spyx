package com.hy.spyx;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;


public class ClueMenu extends PopupMenu implements PopupMenu.OnMenuItemClickListener{
    public ClueMenu(Context context, View view){
        super(context,view);
        mView = (TextView) view;
        mActivity = (GameActivity)context;
    }

    public boolean onMenuItemClick(MenuItem item) {
        Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick");
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.q1:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q1:Capital");
                return answer_1();
            case R.id.q2:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q2:Drive");
                return answer_2();
            case R.id.q3:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q3:Area");
                return answer_3();
            case R.id.q4:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q4:Population");
                return answer_4();
            case R.id.q5:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q5:Monarchy");
                return answer_5();
            case R.id.q6:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q6:Republic");
                return answer_6();
            case R.id.q7:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q7:Peek");
                return answer_7();
            case R.id.q8:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q8:Industry");
                return answer_8();
            case R.id.q9:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q9:Agriculture");
                return answer_9();
            case R.id.q10:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q10:Export");
                return answer_10();
            case R.id.q11:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q11:Christianity");
                return answer_11();
            case R.id.q12:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q12:Islamism");
                return answer_12();
            case R.id.q13:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q13:Buddhism");
                return answer_13();
            case R.id.q14:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q14:Coast");
                return answer_14();
            case R.id.q15:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q15:istory");
                return answer_15();
            case R.id.q16:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q16:English");
                return answer_16();
            case R.id.q17:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q17:French");
                return answer_17();
            case R.id.q18:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q18:Spanish");
                return answer_18();
            case R.id.q19:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q19:ollar");
                return answer_19();
            case R.id.q20:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q20:Euro");
                return answer_20();
            case R.id.q21:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q21:ranc");
                return answer_21();
            case R.id.q22:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q22:Neighbor");
                return answer_22();
            case R.id.q23:
                Log.d(NationApp.NT_LOG_TAG, "onMenuItemClick Q23:GDP");
                return answer_23();
            default:
                return true;
        }
    }

    private boolean answer_1(){
        NationGame.getInstance().clueUsed(R.id.capital,1);
        mActivity.updateGame();
        return true;
    }

    private boolean answer_2(){
        NationGame.getInstance().clueUsed(R.id.drive,1);
        mActivity.updateGame();
        return true;
    }

    private boolean answer_3(){
        NationGame.getInstance().clueUsed(R.id.area,1);
        mActivity.updateGame();
        return true;
    }

    private boolean answer_4(){
        NationGame.getInstance().clueUsed(R.id.population,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_5(){
        NationGame.getInstance().clueUsed(R.id.regime,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_6(){
        NationGame.getInstance().clueUsed(R.id.regime,2);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_7(){
        NationGame.getInstance().clueUsed(R.id.peek,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_8(){
        NationGame.getInstance().clueUsed(R.id.industry,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_9(){
        NationGame.getInstance().clueUsed(R.id.agriculture,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_10(){
        NationGame.getInstance().clueUsed(R.id.export,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_11(){
        NationGame.getInstance().clueUsed(R.id.religion,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_12(){
        NationGame.getInstance().clueUsed(R.id.religion,2);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_13(){
        NationGame.getInstance().clueUsed(R.id.religion,3);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_14(){
        NationGame.getInstance().clueUsed(R.id.coast,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_15(){
        NationGame.getInstance().clueUsed(R.id.history,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_16(){
        NationGame.getInstance().clueUsed(R.id.language,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_17(){
        NationGame.getInstance().clueUsed(R.id.language,2);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_18(){
        NationGame.getInstance().clueUsed(R.id.language,3);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_19(){
        NationGame.getInstance().clueUsed(R.id.currency,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_20(){
        NationGame.getInstance().clueUsed(R.id.currency,2);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_21(){
        NationGame.getInstance().clueUsed(R.id.currency,3);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_22(){
        NationGame.getInstance().clueUsed(R.id.neighbor,1);
        mActivity.updateGame();
        return true;
    }
    private boolean answer_23(){
        NationGame.getInstance().clueUsed(R.id.gdp,1);
        mActivity.updateGame();
        return true;
    }

    private TextView mView;
    private GameActivity mActivity;
}
