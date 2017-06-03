package com.csii.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by sunhao on 2017/3/20.
 */

public class DBManager {


    private static DBManager singleton;
    private DBHelper helper;
    private SQLiteDatabase db;
    private Context mContext;

    /**
     * 私有构造器
     *
     * @param context
     */
    private DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBManager getManager(Context context) {
        if (singleton == null) {
            synchronized (DBManager.class) {
                singleton = new DBManager(context);
            }
        }
        return singleton;
    }

    /**
     * 删除所有的数据
     */
    public void removeAllZipInfo() {

        db.beginTransaction();
        db.delete(MySql.ZIPINFO_TABLE, null, null);
//        db.execSQL("delete from " + MySql.ZIPINFO_TABLE + "");
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 添加Zip信息
     */
    public void addAllZipInfos(List<ZipInfoBean> listDatas) {
        db.beginTransaction();
        for (ZipInfoBean data : listDatas) {
            addZipInfo(data);
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    private void addZipInfo(ZipInfoBean data) {
        insert(data);
    }

    /**
     * 插入一条压缩包的信息
     *
     * @param info
     */
    public void insert(ZipInfoBean info) {
        db.beginTransaction();
        ContentValues values = getFileValues(info);
        db.insert(MySql.ZIPINFO_TABLE, (String) null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 更新数据
     *
     * @param values
     * @param name
     * @param value
     */
    public void Update(ContentValues values, String name, String value) {
        db.beginTransaction();
        this.db.update(MySql.ZIPINFO_TABLE, values, name + "=?", new String[]{value});
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 更新数据
     *
     * @param info
     * @param zipName
     */
    public void update(ZipInfoBean info, String zipName) {
        ZipInfoBean fileInfo = this.query(zipName);
        if (fileInfo != null) {
            this.delete(zipName);
        }

        this.insert(info);
    }

    /**
     * 删除数据
     *
     * @param zipName
     */
    public void delete(String zipName) {
        db.beginTransaction();
        delete("ZipName", zipName);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**
     * 删除数据
     *
     * @param name
     * @param value
     */
    public void delete(String name, String value) {
        if (this.db == null) {
            this.db = helper.getWritableDatabase();
        }

        this.db.delete(MySql.ZIPINFO_TABLE, name + "=?", new String[]{value});
    }

    /**
     * @param info
     * @return
     */
    public ContentValues getFileValues(ZipInfoBean info) {
        ContentValues value = new ContentValues();
        value.put("ZipLength", info.getZipLength());
        value.put("ZipName", info.getZipName());
        value.put("ZipMD5", info.getZipMd5());
        value.put("ZipPassword", info.getZipPassWord());
        value.put("ZipVersion", info.getZipVersion());
        return value;
    }

    /**
     * @return
     */
    public Cursor query() {
        db.beginTransaction();
        Cursor c = db.query(MySql.ZIPINFO_TABLE, null, null, null, null, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return c;

    }


    /**
     * @param c
     * @param key
     * @return
     */
    private String getString(Cursor c, String key) {
        return c.getColumnIndex(key) != -1 ? c.getString(c.getColumnIndex(key)) : "";
    }


    /**
     * 返回某一个压缩包的信息
     *
     * @param fileName
     * @return
     */
    public synchronized ZipInfoBean query(String fileName) {
        ZipInfoBean info = null;
        Cursor c = query();

        if (c != null) {
            while (c.moveToNext()) {
                String FileLength = this.getString(c, "ZipLength");
                String FileMD5 = this.getString(c, "ZipMD5");
                String FileName = this.getString(c, "ZipName");
                String FilePassword = this.getString(c, "ZipPassword");
                String FileVersion = this.getString(c, "ZipVersion");
                if (fileName.equals(FileName)) {
                    info = new ZipInfoBean();
                    info.setZipLength(FileLength);
                    info.setZipMd5(FileMD5);
                    info.setZipName(FileName);
//                  FilePassword = DESUtils.decrypt(FilePassword);
                    info.setZipPassWord(FilePassword);
                    info.setZipVersion(FileVersion);
                }
            }
        }
        return info;
    }

}
