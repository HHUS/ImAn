package com.csii.db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunhao on 2017/3/20.
 */

public class ZipInfoBean implements Parcelable {
    
    private String ZipName;
    private String ZipLength ;
    private String ZipVersion ;
    private String ZipPassWord;
    private String ZipMd5;

    public String getZipLength() {
        return ZipLength;
    }

    public void setZipLength(String zipLength) {
        ZipLength = zipLength;
    }

    public String getZipVersion() {
        return ZipVersion;
    }

    public void setZipVersion(String zipVersion) {
        ZipVersion = zipVersion;
    }

    public String getZipPassWord() {
        return ZipPassWord;
    }

    public void setZipPassWord(String zipPassWord) {
        ZipPassWord = zipPassWord;
    }

    public String getZipMd5() {
        return ZipMd5;
    }

    public void setZipMd5(String zipMd5) {
        ZipMd5 = zipMd5;
    }

    public String getZipName() {
        return ZipName;
    }

    public void setZipName(String zipName) {
        ZipName = zipName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ZipName);
        dest.writeString(this.ZipLength);
        dest.writeString(this.ZipVersion);
        dest.writeString(this.ZipPassWord);
        dest.writeString(this.ZipMd5);
    }

    public ZipInfoBean() {
    }

    protected ZipInfoBean(Parcel in) {
        this.ZipName = in.readString();
        this.ZipLength = in.readString();
        this.ZipVersion = in.readString();
        this.ZipPassWord = in.readString();
        this.ZipMd5 = in.readString();
    }

    public static final Parcelable.Creator<ZipInfoBean> CREATOR = new Parcelable.Creator<ZipInfoBean>() {
        public ZipInfoBean createFromParcel(Parcel source) {
            return new ZipInfoBean(source);
        }

        public ZipInfoBean[] newArray(int size) {
            return new ZipInfoBean[size];
        }
    };
}
