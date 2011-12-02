package org.dalinaum.android.gravity;

import android.content.res.Resources;
import android.renderscript.Mesh;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.RenderScriptGL;

public class GravityRS {
    private ScriptC_gravity mScript;

    public void init(RenderScriptGL rs, Resources res, int width,
            int height)
    {
        ProgramFragmentFixedFunction.Builder pfb =
                new ProgramFragmentFixedFunction.Builder(rs);
        pfb.setVaryingColor(true);
        rs.bindProgramFragment(pfb.create());

        Mesh.AllocationBuilder mb = new Mesh.AllocationBuilder(rs);
        final int PART_COUNT = 50000;
        ScriptField_Point points = new ScriptField_Point(rs, PART_COUNT);
        mb.addVertexAllocation(points.getAllocation());
        mb.addIndexSetType(Mesh.Primitive.POINT);
        Mesh ms = mb.create();

        mScript = new ScriptC_gravity(rs, res, R.raw.gravity);
        mScript.set_partMesh(ms);
        mScript.bind_point(points);
        rs.bindRootScript(mScript);

        mScript.invoke_initParticles();
    }

    public void newTouchPosition(float x, float y, float pressure, int id)
    {
        mScript.set_gTouchX(x);
        mScript.set_gTouchY(y);
    }
}
