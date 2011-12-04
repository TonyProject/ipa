package edu.tony.ipa;

import greendroid.app.GDApplication;
import android.content.Intent;
import android.net.Uri;

public class IPAApplication extends GDApplication {
 
    @Override
    public Class<?> getHomeActivityClass() {
        return Home.class;
    }
 
    @Override
    public Intent getMainApplicationIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_url)));
    }
 
}
