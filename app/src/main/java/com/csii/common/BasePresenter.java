package com.csii.common;

import android.content.Context;

import com.csii.application.ImanApplication;
import com.silencedut.router.Router;

/**
 * Created by sunhao on 2017/3/29.
 */

public abstract class BasePresenter<T extends BaseView>{

    protected T mPresenterView;
    private Context mContext = ImanApplication.getContext();

    public BasePresenter(T mPresenterView) {
        this.mPresenterView = mPresenterView;
    }

    public void attachView(T mPresenterView){
        this.mPresenterView = mPresenterView;
        Router.instance().register(this);
    }

    public void onDectchView(){
        mPresenterView = null;
    }
}
