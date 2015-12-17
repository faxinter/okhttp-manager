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
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;

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

    @OnClick(R.id.post)
    public void onPostClick() {
        OkHttp.post()
                .url("https://en.wikipedia.org/w/index.php")
                .param("search", "Jurassic Park")
                .execute(new StringResultCallback() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "post string: " + response);
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable) {
                        Log.d(TAG, throwable.getMessage());
                    }
                });
    }


}
