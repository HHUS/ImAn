package com.csii.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.csii.greendao.DaoMaster;
import com.csii.greendao.DaoSession;
import com.csii.iman.R;
import com.google.gson.Gson;

import java.security.PublicKey;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sunhao on 2017/3/29.
 */

public class ImanApplication extends Application{

    public static ImanApplication mApplication;
    private static Gson sGson = new Gson();
    public DaoSession dapSession;
    public SQLiteDatabase baseDb;
    public DaoMaster.DevOpenHelper helper;
    public DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("").setFontAttrId(R.attr.fontPath).build());

        setUpDatabase();
    }

    /**
     * 设置基本数据库
     */
    private void setUpDatabase() {

        //通过DaoMaster的内部类DevOpenHelper，可以得到一个SQLiteOpenHelper对象
        //：默认的DaoMaster.DevOpenHelper会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        helper = new DaoMaster.DevOpenHelper(this,"",null);
        baseDb = helper.getWritableDatabase();
        //注意：该数据库连接属于DaoMaster，所以多个Session指的是相同的数据库连接
        daoMaster = new DaoMaster(baseDb);
        dapSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        return dapSession;
    }

    public SQLiteDatabase getDataBase(){
        return baseDb;
    }

    public static Application getContext(){
        return  mApplication;
    }

    public static Gson getGson() {
        return sGson;
    }



}
