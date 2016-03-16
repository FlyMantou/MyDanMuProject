package demo.com.myapplication;

import android.app.Application;

import com.wilddog.client.Wilddog;

/**
 * Created by huanghai on 2016/3/15.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Wilddog.setAndroidContext(this);
    }
}
