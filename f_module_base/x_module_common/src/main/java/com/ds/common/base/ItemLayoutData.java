package com.ds.common.base;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @Author ljiezhou
 * @date 2023/8/15
 * @Description
 */
public class ItemLayoutData implements Parcelable {
    private int itemId;
    private int titleId;
    private int descId;
    private int icon;
    private boolean isChecked;

    private int value;

    private String titleStr;
    private String descStr;
    private long size, time;


    protected ItemLayoutData(Parcel in) {
        itemId = in.readInt();
        titleId = in.readInt();
        descId = in.readInt();
        icon = in.readInt();
        isChecked = in.readByte() != 0;
        value = in.readInt();
        titleStr = in.readString();
        descStr = in.readString();
        size = in.readLong();
        time = in.readLong();
    }

//
//    public static final Creator<ItemLayoutData> CREATOR = new Creator() {
//        @Override
//        public ItemLayoutData createFromParcel(Parcel in) {
//            return new ItemLayoutData(in);
//        }
//
//        @Override
//        public ItemLayoutData[] newArray(int size) {
//            return new ItemLayoutData[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {
//
//        dest.writeInt(titldId);
//        dest.writeInt(descId);
//        dest.writeInt(icon);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
////            dest.writeBoolean(isChecked);
////        } else
//        dest.writeByte((byte) (isChecked ? 1 : 0));
//        dest.writeInt(value);
//        dest.writeString(titleStr);
//        dest.writeString(descStr);
//        dest.writeLong(size);
//        dest.writeLong(time);
//    }


    public static final Creator<ItemLayoutData> CREATOR = new Creator<ItemLayoutData>() {
        @Override
        public ItemLayoutData createFromParcel(Parcel in) {
            return new ItemLayoutData(in);
        }

        @Override
        public ItemLayoutData[] newArray(int size) {
            return new ItemLayoutData[size];
        }
    };

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getDescId() {
        return descId;
    }

    public void setDescId(int descId) {
        this.descId = descId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ItemLayoutData(Builder builder) {
        this.itemId = builder.itemId;
        this.titleId = builder.titldId;
        this.descId = builder.descId;
        this.icon = builder.icon;
        this.isChecked = builder.isChecked;
        this.value = builder.value;
        this.titleStr = builder.titleStr;
        this.descStr = builder.descStr;
        this.size = builder.size;
        this.time = builder.time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(itemId);
        dest.writeInt(titleId);
        dest.writeInt(descId);
        dest.writeInt(icon);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeInt(value);
        dest.writeString(titleStr);
        dest.writeString(descStr);
        dest.writeLong(size);
        dest.writeLong(time);
    }


//    public static final Creator<ItemLayoutData> CREATOR = new Creator<ItemLayoutData>() {
//        @Override
//        public ItemLayoutData createFromParcel(Parcel in) {
//            return new ItemLayoutData(in);
//        }
//
//        @Override
//        public ItemLayoutData[] newArray(int size) {
//            return new ItemLayoutData[size];
//        }
//    };

    public static class Builder {
        private int itemId;
        private int titldId;
        private int descId;
        private int icon;
        private boolean isChecked;
        private int value;

        private String titleStr;
        private String descStr;
        private long size, time;

        public Builder setItemId(int itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder setTitldId(int titldId) {
            this.titldId = titldId;
            return this;
        }

        public Builder setDescId(int descId) {
            this.descId = descId;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder isChecked(boolean isChecked) {
            this.isChecked = isChecked;
            return this;
        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Builder setTitleStr(String titleStr) {
            this.titleStr = titleStr;
            return this;
        }

        public Builder setDescStr(String descStr) {
            this.descStr = descStr;
            return this;
        }

        public Builder setSize(long size) {
            this.size = size;
            return this;
        }

        public Builder setTime(long time) {
            this.time = time;
            return this;
        }

        public ItemLayoutData builder() {
            return new ItemLayoutData(this);
        }
    }
}
