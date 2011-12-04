package org.dalinaum.android.gravity;

import android.content.res.Resources;
import android.renderscript.Mesh;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.RenderScriptGL;

public class GravityRS {
    private ScriptC_gravity gravityScript;
    private ScriptC_physics physicsScript;

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

        gravityScript = new ScriptC_gravity(rs, res, R.raw.gravity);
        gravityScript.set_partMesh(ms);
        gravityScript.bind_point(points);
        rs.bindRootScript(gravityScript);
        
        gravityScript.invoke_initParticles();
  
        physicsScript = new ScriptC_physics(rs, res, R.raw.physics);
        
        gravityScript.set_physicsScript(physicsScript);
    }

    public void newTouchPosition(float x, float y, float pressure, int id)
    {
    	physicsScript.set_gTouchX(x);
    	physicsScript.set_gTouchY(y);    	
    }
}
