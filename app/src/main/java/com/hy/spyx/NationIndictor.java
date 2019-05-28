package com.hy.spyx;

public class NationIndictor {
    public NationIndictor(int index,int imageId,int textId,NationMenu menu){
        mIndex = index;
        mimageViewId = imageId;
        mTextViewId = textId;
        mMenu = menu;
    }

    public int getIndex(){
        return mIndex;
    }
    public int getImageId(){
        return mimageViewId;
    }
    public int getTextId(){
        return mTextViewId;
    }
    public NationMenu getMenu(){
        return mMenu;
    }

    private int mIndex;
    private int mimageViewId;
    private int mTextViewId;
    private NationMenu mMenu;
}
