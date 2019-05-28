package com.hy.spyx;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class NationGame {
    public static final int GAME_STATUS_ASK=1;
    public static final int GAME_STATUS_ACTION=2;
    public static final int GAME_STATUS_WIN=3;
    public static final int GAME_STATUS_LOSE=4;

    private static class NationGameHolder{
        /**
         * * 静态初始化器，由JVM来保证线程安全
         */
        private static NationGame instance = new NationGame();
    }

    /**
     * 私有化构造方法
     * */
    private NationGame(){
        mCurRound = 1;
        mCurStatus = NationGame.GAME_STATUS_ASK;
        mRoundNation = new ArrayList<ArrayList<String>>();
        mRoundExclue = new ArrayList<HashSet<String>>();
        for (int i=0;i<5;i++){
            mRoundExclue.add(new HashSet<String>());
        }
        mDest = new ArrayList<String>();
        mRound = new ArrayList<Boolean>();
        mDrop = new HashSet<Integer>();
        mHand = new HashSet<Integer>();
        mSupply = new HashSet<Integer>();
        mClues = new int[]{R.id.capital,R.id.area,R.id.drive,R.id.population,R.id.regime,R.id.peek,R.id.gdp,R.id.industry,R.id.agriculture,R.id.export,R.id.religion,R.id.coast,R.id.history,R.id.language,R.id.currency,R.id.neighbor};
        mProcess = new ArrayList<String>();
    }
    public static  NationGame getInstance(){
        return NationGame.NationGameHolder.instance;
    }

    public void start(GameActivity activity){
        mCurRound = 1;
        mCurStatus = NationGame.GAME_STATUS_ASK;
        mRoundNation.clear();
        for(int i=0;i<mRoundExclue.size();i++){
            mRoundExclue.get(i).clear();
        }
        mDest.clear();
        mRound.clear();
        mDrop.clear();
        mHand.clear();
        mSupply.clear();
        mProcess.clear();
        mProcess.add("【1】游戏开始，先点击线索菜单，获得目的地信息，再点击国旗排除选项或实施逮捕,然后依次重来");
        HashSet<String> tooks = new HashSet<String>();
        Random ra =new Random();
        ArrayList<String> keys = NationManager.getInstance().allNations();
        NationApp app = (NationApp) NationApp.getAppContext();
        double rate =app.getWinRate();
        double exp =app.getExp();
        while(mRoundNation.size()!=5){
            String k = keys.get(ra.nextInt(keys.size()));
            if(!tooks.contains(k)){
                ArrayList<String> group = null;
                if(exp>10&&rate>50){
                    group = NationManager.getInstance().getNation(k).pickSimilar(tooks);
                }
                else{
                    group = NationManager.getInstance().getNation(k).pickUnsimilar(tooks);
                }
                if (group.size()>0){
                    for(int j=0;j<group.size();j++){
                        int p = ra.nextInt(group.size());
                        String tmp = group.get(p);
                        group.set(p,group.get(j));
                        group.set(j,tmp);
                    }
                    mDest.add(k);
                    mRoundNation.add(group);
                }
            }
        }
        for(int i=0;i<mClues.length;i++){
            int p = ra.nextInt(mClues.length);
            int tmp = mClues[i];
            mClues[i] = mClues[p];
            mClues[p] = tmp;
        }
        for (int i=0;i<mClues.length;i++){
            if(i<5){
                mHand.add(mClues[i]);
            }
            else{
                mSupply.add(mClues[i]);
            }
        }
    }

    public void end(){

    }

    public void clueUsed(int clue,int question){
        mHand.remove(clue);
        mDrop.add(clue);
        if(mSupply.size()!=0){
            Integer newitem = (Integer) mSupply.toArray()[0];
            mHand.add(newitem);
            mSupply.remove(newitem);
        }
        String dest = mDest.get(mCurRound-1);
        if(clue==R.id.capital){
            if(NationManager.getInstance().getNation(dest).isCapitaiMax()){
                mProcess.add(String.format("【%d】线索:目的地首都是国内最大城市",getRound()));
            }
            else{
                mProcess.add(String.format("【%d】线索:目的地首都不是国内最大城市",getRound()));
            }
        }
        else if(clue == R.id.drive){
            mProcess.add(String.format("【%d】线索:目的地行驶方向为【%s】",getRound(),NationManager.getInstance().getNation(dest).getTravel()));
        }
        else if(clue == R.id.area){
            mProcess.add(String.format("【%d】线索:目的地国土面积为【%s】",getRound(),NationManager.getInstance().getNation(dest).getArea()));
        }
        else if(clue == R.id.population){
            mProcess.add(String.format("【%d】线索:目的地国家人口为【%s】",getRound(),NationManager.getInstance().getNation(dest).getPopulation()));
        }
        else if(clue == R.id.regime){
            if(question==1){
                if(NationManager.getInstance().getNation(dest).isMonarchy()){
                    mProcess.add(String.format("【%d】线索:目的地是君主制政体国家",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:目的地不是君主制政体国家",getRound()));
                }
            }
            else{
                if(NationManager.getInstance().getNation(dest).isRepublic()){
                    mProcess.add(String.format("【%d】线索:目的地是共和制政体国家",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:目的地不是共和制政体国家",getRound()));
                }
            }
        }
        else if(clue == R.id.peek){
            int peek = NationManager.getInstance().getNation(dest).getPeek();
            mProcess.add(String.format("【%d】线索:目的地国家的最高峰在海拔【%d】米",getRound(),peek));
            /*if (peek>3750){
                mProcess.add(String.format("【%d】线索:目的地国家的最高峰在海拔3750米之上",getRound()));
            }
            else if(peek>=2500){
                mProcess.add(String.format("【%d】线索:目的地国家的最高峰在海拔2500-3750之间",getRound()));
            }
            else if(peek>=1200){
                mProcess.add(String.format("【%d】线索:目的地国家的最高峰在海拔1200-2500之间",getRound()));
            }
            else{
                mProcess.add(String.format("【%d】线索:目的地国家的最高峰在海拔1200米之下",getRound()));
            }*/
        }
        else if(clue == R.id.industry){
            mProcess.add(String.format("【%d】线索:目的地国家最大的工业是【%s】",getRound(),NationManager.getInstance().getNation(dest).getIndustry()));
        }
        else if(clue == R.id.agriculture){
            mProcess.add(String.format("【%d】线索:目的地国家最主要的农业是【%s】",getRound(),NationManager.getInstance().getNation(dest).getAgriculture()));
        }
        else if(clue == R.id.export){
            mProcess.add(String.format("【%d】线索:目的地国家最主要的出口商品是【%s】",getRound(),NationManager.getInstance().getNation(dest).getExport()));
        }
        else if(clue == R.id.religion){
            if(question==1){
                if(NationManager.getInstance().getNation(dest).isChristianity()){
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教是基督教",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教不是基督教",getRound()));
                }
            }
            else if(question==2){
                if(NationManager.getInstance().getNation(dest).isIslamism()){
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教是伊斯兰教",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教不是伊斯兰教",getRound()));
                }
            }
            else{
                if(NationManager.getInstance().getNation(dest).isBuddhism()){
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教是佛教",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:目的地国家最大的宗教不是佛教",getRound()));
                }
            }
        }
        else if(clue == R.id.coast){
            if(NationManager.getInstance().getNation(dest).isCoast()){
                mProcess.add(String.format("【%d】线索:目的地国家有海岸线",getRound()));
            }
            else{
                mProcess.add(String.format("【%d】线索:目的地国家没有海岸线",getRound()));
            }
        }
        else if(clue == R.id.history){
            mProcess.add(String.format("【%d】线索:目的地国家的历史【%s】",getRound(),NationManager.getInstance().getNation(dest).getHistory()));
        }
        else if(clue == R.id.language){
            if(question==1){
                if(NationManager.getInstance().getNation(dest).isEnglish()){
                    mProcess.add(String.format("【%d】线索:英语是目的地国家官方语言",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:英语不是目的地国家官方语言",getRound()));
                }
            }
            else if(question==2){
                if(NationManager.getInstance().getNation(dest).isFrench()){
                    mProcess.add(String.format("【%d】线索:法语是目的地国家官方语言",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:法语不是目的地国家官方语言",getRound()));
                }
            }
            else{
                if(NationManager.getInstance().getNation(dest).isSpanish()){
                    mProcess.add(String.format("【%d】线索:西班牙语是目的地国家官方语言",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:西班牙语不是目的地国家官方语言",getRound()));
                }
            }
        }
        else if(clue == R.id.currency){
            if(question==1){
                if(NationManager.getInstance().getNation(dest).isDollar()){
                    mProcess.add(String.format("【%d】线索:美元是目的地国家官方货币",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:美元不是目的地国家官方货币",getRound()));
                }
            }
            else if(question==2){
                if(NationManager.getInstance().getNation(dest).isEuro()){
                    mProcess.add(String.format("【%d】线索:欧元是目的地国家官方货币",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:欧元是目的地国家官方货币",getRound()));
                }
            }
            else{
                if(NationManager.getInstance().getNation(dest).isFranc()){
                    mProcess.add(String.format("【%d】线索:法郎是目的地国家官方货币",getRound()));
                }
                else{
                    mProcess.add(String.format("【%d】线索:法郎不是目的地国家官方货币",getRound()));
                }
            }
        }
        else if(clue == R.id.neighbor){
            mProcess.add(String.format("【%d】线索:目的地国家邻国数量为【%s】",getRound(),NationManager.getInstance().getNation(dest).neighborCount()));
        }
        else if(clue == R.id.gdp){
            mProcess.add(String.format("【%d】线索:目的地国家国内国内生产总值【%s】美元",getRound(),NationManager.getInstance().getNation(dest).getGdp()));
        }

        mCurStatus = NationGame.GAME_STATUS_ACTION;
    }

    public void arrest(String nationId){
        if(nationId==mDest.get(mCurRound-1)){
            mProcess.add(String.format("【%d】逮捕成功,目的地【%s】",getRound(),getDestinationName()));
            mRound.add(true);
        }
        else{
            mProcess.add(String.format("【%d】逮捕【%s】失败,目的地【%s】",getRound(),NationManager.getInstance().getNation(nationId).getName(),getDestinationName()));
            mRound.add(false);
        }
        resetStatus();
    }

    public boolean exclude(String nationId){
        if (mHand.size()==0){
            return false;
        }
        if(nationId==mDest.get(mCurRound-1)){
            mRound.add(false);
            mProcess.add(String.format("【%d】目的地【%s】被排除，本轮失败",getRound(),getDestinationName()));
            resetStatus();
        }
        else{
            mProcess.add(String.format("【%d】排除嫌疑【%s】成功,请使用下一条线索",getRound(),NationManager.getInstance().getNation(nationId).getName()));
            HashSet<String> ex = mRoundExclue.get(mCurRound-1);
            ex.add(nationId);
            mCurStatus = NationGame.GAME_STATUS_ASK;
        }
        return true;
    }

    public boolean isEnable(int index){
        String nid = mRoundNation.get(mCurRound-1).get(index-1);
        if(mRoundExclue.size()!=0){
            HashSet<String> exclues = mRoundExclue.get(mCurRound-1);
            if(exclues.contains(nid)){
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> getProcess(){
        return mProcess;
    }

    public ArrayList<String> getRoundNations(){
        return mRoundNation.get(mCurRound-1);
    }

    public int getImageId(int index){
        return NationManager.getInstance().getNation(mRoundNation.get(mCurRound-1).get(index-1)).getResId();
    }

    public String getNationName(int index){
        return NationManager.getInstance().getNation(mRoundNation.get(mCurRound-1).get(index-1)).getName();
    }

    public HashSet<String> getExclude(){
        return mRoundExclue.get(mCurRound-1);
    }

    public String getNationId(int index){
        return mRoundNation.get(mCurRound-1).get(index-1);
    }

    public  int cluesLeft(){
        return mSupply.size()+mHand.size();
    }
    public  HashSet<Integer> inHands(){
        return mHand;
    }
    public  HashSet<Integer> inSupply(){
        return mSupply;
    }
    public  HashSet<Integer> inDrop(){
        return mDrop;
    }
    public  int getRound(){
        return mCurRound;
    }
    public  void setStatus(int status){
        mCurStatus = status;
    }
    public int getStatus(){
        return mCurStatus;
    }
    public String getDestinationName(){
        return NationManager.getInstance().getNation(mDest.get(mCurRound-1)).getName();
    }
    public ArrayList<Boolean> getRoundResult(){
        return mRound;
    }

    private void resetStatus(){
        int tcnt = 0;
        int fcnt = 0;
        for(int i=0;i<mRound.size();i++){
            if (mRound.get(i)){
                tcnt++;
            }
            else{
                fcnt++;
            }
        }
        if(tcnt>=3){
            mCurStatus = GAME_STATUS_WIN;
            mProcess.add(String.format("【%d】游戏胜利",NationGame.getInstance().getRound()));

        }
        else if(fcnt>=3){
            mCurStatus = GAME_STATUS_LOSE;
            mProcess.add(String.format("【%d】游戏失败",NationGame.getInstance().getRound()));
        }
        else{
            mCurStatus = GAME_STATUS_ASK;
            mCurRound++;
            mProcess.add(String.format("【%d】请选择一项线索使用",NationGame.getInstance().getRound()));
        }
    }

    private int mCurRound;
    private int mCurStatus;
    private ArrayList<ArrayList<String>> mRoundNation;
    private ArrayList<HashSet<String>> mRoundExclue;
    private HashSet<Integer> mDrop;
    private HashSet<Integer> mHand;
    private HashSet<Integer> mSupply;
    private ArrayList<Boolean> mRound;
    private ArrayList<String> mDest;
    private int[] mClues;
    private ArrayList<String> mProcess;

}
