package com.utils;

import com.network.RetrofitClient;
import com.network.bean.NewsBean;
import com.network.config.Constants;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by SJD
 * time: 2018/5/28
 */

public class HttpUtils {
    private static final String TAG = "NET HttpUtils";

    public static Observable<List<NewsBean>> getNewsByType(int type, int page) {
        return RetrofitClient.getApi().getNewsByType(type, page);
    }

    public static Observable<String> login(String username, String password) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.Login_username, username);
        hashMap.put(Constants.Login_password, password);
        return RetrofitClient.getApi().login(hashMap);
    }

    public static Observable<String> chatRobot(String msg) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Constants.chatContants, msg);
        return RetrofitClient.getApiChat().chatRobot(hashMap);
    }

}

