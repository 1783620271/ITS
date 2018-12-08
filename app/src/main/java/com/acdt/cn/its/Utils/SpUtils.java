package com.acdt.cn.its.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SpUtils extends Activity {
    private static SharedPreferences sp;

    //写
    /*
    Context context 上下文, String key 储存节点的名称, boolean value 节点的值
     */
    public static void putBoolean(Context context, String key, boolean value){
        //储存节点名称，读写方式
        if(sp==null){
            sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }
    //读 defvalue 没有此节点的时候的默认值 return ：默认值或此节点读取到的值
    public static boolean getBoolean(Context context, String key, boolean defvalue){
        //储存节点名称，读写方式
        if(sp==null){
            sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defvalue);
    }
    public static void putString(Context context, String key, String value){
        //储存节点名称，读写方式
        if(sp==null){
            sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).commit();
    }
    //读 defvalue 没有此节点的时候的默认值 return ：默认值或此节点读取到的值
    public static String getString(Context context, String key, String defvalue){
        //储存节点名称，读写方式
        if(sp==null){
            sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        return sp.getString(key,defvalue);
    }

    public static void remove(Context context, String key) {
        if(sp==null){
            sp =context.getSharedPreferences("config",Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }
}
