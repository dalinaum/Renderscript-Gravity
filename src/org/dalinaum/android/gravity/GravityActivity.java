package org.dalinaum.android.gravity;

import android.app.Activity;
import android.os.Bundle;

public class GravityActivity extends Activity {
    private GravityView mView;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GravityView(this);
        setContentView(mView);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mView.resume();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        mView.pause();
    }
}