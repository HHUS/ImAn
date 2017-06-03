package com.csii.db;

/**
 * Created by sunhao on 2017/3/20.
 */

public class MySql {

    public static final String DATABASE_NAME = "vxzip.dp";
    public static final int DATABASE_VERSION = 1;
    public static final String ZIPINFO_TABLE = "zipinfo";

    /**
     * 创建Zip信息保存表
     */
    public static final String createZipTable = "create table if not exists " + ZIPINFO_TABLE +
            "(id integer primary key autoincrement," +
            "ZipName text," +
            "ZipLength text," +
            "ZipPassWord float," +
            "ZipVersion integer,)";

}
