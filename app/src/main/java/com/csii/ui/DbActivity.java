package com.csii.ui;

import android.app.DownloadManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.csii.greendao.DaoMaster;
import com.csii.greendao.DaoSession;
import com.csii.greendao.NewsChannelTable;
import com.csii.greendao.NewsChannelTableDao;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by sunhao on 2017/3/31.
 */

public class DbActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setUpDatabase();


    }

    private void setUpDatabase() {

        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = helper.getWritableDatabase();

        daoMaster = new DaoMaster(db);

        daoSession = daoMaster.newSession();
    }

    private NewsChannelTableDao getNewDao(){


        return daoSession.getNewsChannelTableDao();
    }


    private void instert(){
        NewsChannelTable ta = new NewsChannelTable();

        //插入操作，插入对象
        getNewDao().insert(ta);
    }


    private void query(){
        //Query类代表了一个可以重复执行的查询

        Query query = getNewDao().queryBuilder().
                where(NewsChannelTableDao.Properties.NewsChannelId.eq("Text")).
                orderAsc(NewsChannelTableDao.Properties.NewsChannelFixed).build();

        //查询结果以List返回
        //List news = query.List();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }

    private void delete(){
        getNewDao().deleteByKey("id");
    }

}
