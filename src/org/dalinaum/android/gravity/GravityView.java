package org.dalinaum.android.gravity;

import android.content.Context;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class GravityView extends RSSurfaceView {
    private RenderScriptGL mRS;
    private GravityRS mRender;

    public GravityView(Context context) {
        super(context);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w,
            int h)
    {
        super.surfaceChanged(holder, format, w, h);
        if (mRS == null) {
            RenderScriptGL.SurfaceConfig sc =
                    new RenderScriptGL.SurfaceConfig();
            mRS = createRenderScriptGL(sc);
            mRS.setSurface(holder, w, h);
            mRender = new GravityRS();
            mRender.init(mRS, getResources(), w, h);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mRS == null) {
            mRS = null;
            destroyRenderScriptGL();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mRender.newTouchPosition(ev.getX(0), ev.getY(0),
                ev.getPressure(0), ev.getPointerId(0));
        return true;
    }
}
