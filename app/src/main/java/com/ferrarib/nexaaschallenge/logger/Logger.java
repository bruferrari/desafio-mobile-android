package com.ferrarib.nexaaschallenge.logger;

import android.util.Log;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Bruno Ferrari on 24 October 2016.
 */

public class Logger {

    private Logger() {
        // Prevents this class from being instantiated
    }

    public static void httpCodeLogger(String clazz, Response response, Call call) {
        if (response.code() != HttpURLConnection.HTTP_OK) {
            Log.e(clazz, "Something happened when tying to request "
                    + call.request().url() + " with HTTP CODE " + response.code());
        }
    }

}
