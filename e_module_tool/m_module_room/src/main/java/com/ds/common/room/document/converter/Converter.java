package com.ds.common.room.document.converter;

import androidx.room.TypeConverter;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    @TypeConverter
    public ArrayList<String> stringToObject(String value) {
        LogUtils.d(value);
        return GsonUtils.fromJson(value, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public String objectToString(ArrayList<String> list) {
        return GsonUtils.toJson(list);
    }

}
