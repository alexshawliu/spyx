package com.hy.spyx;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameNation {
    public GameNation(){
        mSimilars = new ArrayList<String>();
    }
    public void setResId(int rid){
        mResId = rid;
    }
    public String getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
    public int getResId(){
        return mResId;
    }
    public String getTravel(){
        return mTravel;
    }
    public String getCapital(){
        return mCapital;
    }
    public boolean isCapitaiMax(){
        return mCapitalMax;
    }
    public String getArea(){
        return mArea;
    }
    public String getPopulation(){
        return mPopulation;
    }
    public String getRegime(){
        return mRegime;
    }

    public boolean isMonarchy(){
        if (mRegime.indexOf("君主")==-1){
            return false;
        }
        return true;
    }

    public boolean isRepublic(){
        if (mRegime.indexOf("共和")==-1){
            return false;
        }
        return true;
    }

    public boolean isChristianity(){
        if ((mReligion.indexOf("基督")==-1)&&(mReligion.indexOf("天主")==-1)&&(mReligion.indexOf("东正")==-1)){
            return false;
        }
        return true;
    }

    public boolean isIslamism(){
        if (mReligion.indexOf("穆斯林")==-1){
            return false;
        }
        return true;
    }

    public boolean isBuddhism(){
        if (mReligion.indexOf("佛教")==-1){
            return false;
        }
        return true;
    }

    public boolean isCoast(){
        if (mCoast.length()!=0){
            return true;
        }
        return false;
    }

    public boolean isEnglish(){
        if (mLanguage.indexOf("英语")==-1){
            return false;
        }
        return true;
    }

    public boolean isFrench(){
        if (mLanguage.indexOf("法语")==-1){
            return false;
        }
        return true;
    }

    public boolean isSpanish(){
        if (mLanguage.indexOf("西班牙语")==-1){
            return false;
        }
        return true;
    }

    public boolean isDollar() {
        if (mCurrency.indexOf("美元") == -1) {
            return false;
        }
        return true;
    }

    public boolean isEuro() {
        if (mCurrency.indexOf("欧元") == -1) {
            return false;
        }
        return true;
    }

    public boolean isFranc(){
        if (mCurrency.indexOf("法郎")==-1){
            return false;
        }
        return true;
    }

    public int neighborCount(){
        return mNeighbor.split(",").length;
    }

    public int getGdp(){
        return mGdp;
    }

    public int getPeek(){
        return mPeek;
    }

    public String getIndustry(){
        return mIndustry;
    }

    public String getExport(){
        return mExport;
    }

    public String getAgriculture(){
        return mAgriculture;
    }
    public String getReligion(){
        return mReligion;
    }

    public String getHistory(){
        return mHistory;
    }
    public String getLanguage(){
        return mLanguage;
    }
    public String getCurrency(){
        return mCurrency;
    }
    public String getCoast(){
        return mCoast;
    }
    public String getNeighbor(){
        return mNeighbor;
    }


    public ArrayList<String> pickSimilar(HashSet<String> exclude){
        ArrayList<String> group = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();
        for (int i=0;i<mSimilars.size();i++){
            if(!exclude.contains(mSimilars.get(i))){
                all.add(mSimilars.get(i));
            }
        }
        if (all.size()>=5){
            exclude.add(mId);
            group.add(mId);
            Random ra =new Random();
            while(group.size()<6){
                String k = all.get(ra.nextInt(all.size()));
                if(!exclude.contains(k)){
                    group.add(k);
                    exclude.add(k);
                }
            }
        }
        return group;
    }

    public ArrayList<String> pickUnsimilar(HashSet<String> exclude){
        ArrayList<String> group = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();
        ArrayList<String> poll = NationManager.getInstance().allNations();
        for (int i=0;i<poll.size();i++){
            if(!exclude.contains(poll.get(i))){
                all.add(poll.get(i));
            }
        }
        if (all.size()>=5){
            exclude.add(mId);
            group.add(mId);
            Random ra =new Random();
            while(group.size()<6){
                String k = all.get(ra.nextInt(all.size()));
                if(!exclude.contains(k)){
                    group.add(k);
                    exclude.add(k);
                }
            }
        }
        return group;
    }

    private String mId;
    private String mName;
    private int mResId;
    private String mCapital;
    private boolean mCapitalMax;
    private String mRegime;
    private String mTravel;
    private int mGdp;
    private int mPeek;
    private String mArea;
    private String mPopulation;
    private String mIndustry;
    private String mExport;
    private String mAgriculture;
    private String mReligion;
    private String mHistory;
    private String mLanguage;
    private String mCurrency;
    private String mCoast;
    private String mNeighbor;
    private ArrayList<String> mSimilars;
}
