package com.cola.http.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cola.http.OkHttp;
import com.cola.http.callback.impl.JsonListResultCallback;
import com.cola.http.callback.impl.JsonResultCallback;
import com.cola.http.callback.impl.StringResultCallback;
import com.cola.http.sample.json.FastJsonConvert;
import com.cola.http.sample.models.User;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * OkHttp Demo : http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0106/2275.html
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "okhttp-manager";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private final OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        OkHttp.getInstance().jsonConvert(new FastJsonConvert());
    }

    @OnClick(R.id.get_string)
    public void onGetStringClick() {
        // get String
        OkHttp.get()
                .url("http://publicobject.com/helloworld.txt")
                .execute(new StringResultCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "get string: " + response);
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable) {
                        Log.d(TAG, throwable.getMessage());
                    }
                });

        // get json for object

        OkHttp.get()
                .url("https://raw.githubusercontent.com/wfxphoebus/okhttp-manager/master/json/User.json")
                .execute(new JsonResultCallback<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Log.d(TAG, "get json: " + user.toString());
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable) {
                        Log.d(TAG, throwable.getMessage());
                    }
                });

        // get json for list

        OkHttp.get()
                .url("https://raw.githubusercontent.com/wfxphoebus/okhttp-manager/master/json/Users.json")
                .execute(new JsonListResultCallback<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        for (User u : users) {
                            Log.d(TAG, "get json list: " + u.toString());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable) {
                        Log.d(TAG, throwable.getMessage());
                    }
                });
    }

    @OnClick(R.id.get_json)
    public void onGetJsonClick() {

    }

    @OnClick(R.id.get_headers)
    public void onSyncGetClick() {
        // 同步需要在线程里
        new Thread(new Runnable() {
            @Override
            public void run() {

                final Request request = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();
                Response response = null;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Headers headers = response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        Log.d(TAG, "header name: " + headers.name(i) + " value: " + headers.value(i));
                    }

                    Log.d(TAG, response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
