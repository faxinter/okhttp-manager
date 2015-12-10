package com.cola.http.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cola.http.OkHttp;
import com.cola.http.callback.impl.StringResultCallBack;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
    }

    @OnClick(R.id.new_get)
    public void onNewGetClick(){

        OkHttp.get().url("http://www.baidu.com").get(new StringResultCallBack(){
            @Override
            public void onSuccess(Object response) {
                super.onSuccess(response);
            }
        });
    }

    @OnClick(R.id.sync_get)
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

    @OnClick(R.id.async_get)
    public void onAsyncGetClick() {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG, "e: " + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, "header name: " + headers.name(i) + " value: " + headers.value(i));
                }

                Log.d(TAG, response.body().string());
            }
        });
    }

    @OnClick(R.id.get_headers)
    public void onGetHdeadersClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("https://api.github.com/repos/square/okhttp/issues")
                            .header("User-Agent", "OkHttp Headers.java")
                            .addHeader("Accept", "application/json; q=0.5")
                            .addHeader("Accept", "application/vnd.github.v3+json")
                            .build();

                    Response response = mOkHttpClient.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers headers = response.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        Log.d(TAG, "header name: " + headers.name(i) + " value: " + headers.value(i));
                    }

                    System.out.println("Server: " + response.header("Server"));
                    System.out.println("Date: " + response.header("Date"));

                    System.out.println("Vary: " + response.headers("Vary"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.post_string)
    public void onPostStringClick() {
        try {
            String postBody = ""
                    + "Releases\n"
                    + "--------\n"
                    + "\n"
                    + " * _1.0_ May 6, 2013\n"
                    + " * _1.1_ June 15, 2013\n"
                    + " * _1.2_ August 11, 2013\n";

            Request request = new Request.Builder()
                    .url("https://api.github.com/markdown/raw")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                    .build();

            Response response = mOkHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @OnClick(R.id.okhttp_post)
//    public void onOKHttpPostClick() {
//        try{
//
//        }catch (IOException e){
//
//        }
//    }
}
