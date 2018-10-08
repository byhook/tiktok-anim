package com.onzhou.tiktok;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @anchor: Andy
 * @date: 2018-10-08
 * @description:
 */
public class SwitchAnimBean implements Parcelable {

    public int width;

    public int height;

    public int pivotX;

    public int pivotY;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.pivotX);
        dest.writeInt(this.pivotY);
    }

    public SwitchAnimBean() {
    }

    protected SwitchAnimBean(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.pivotX = in.readInt();
        this.pivotY = in.readInt();
    }

    public static final Parcelable.Creator<SwitchAnimBean> CREATOR = new Parcelable.Creator<SwitchAnimBean>() {
        @Override
        public SwitchAnimBean createFromParcel(Parcel source) {
            return new SwitchAnimBean(source);
        }

        @Override
        public SwitchAnimBean[] newArray(int size) {
            return new SwitchAnimBean[size];
        }
    };
}
