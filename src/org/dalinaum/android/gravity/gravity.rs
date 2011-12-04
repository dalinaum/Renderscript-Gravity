#pragma version(1)
#pragma rs java_package_name(org.dalinaum.android.gravity)
#pragma stateFragment(parent)

#include "rs_graphics.rsh"
#include "gravity.rsh"

float gTouchX = 50.f;
float gTouchY = 50.f;

Point_t *point;
rs_script physicsScript;
rs_mesh partMesh;

void initParticles() {
    int size = rsAllocationGetDimX(rsGetAllocation(point));
    float width = rsgGetWidth();
    float height = rsgGetHeight();
    uchar4 c = rsPackColorTo8888(0.0f, 0.9f, 0.9f);
    Point_t *p = point;

    for (int i = 0; i < size; i++) {
        p->position.x = rsRand(width);
        p->position.y = rsRand(height);
        p->delta.x = 0;
        p->delta.y = 0;
        p->color = c;
        p++;
    }
}

int root() {
    float width = rsgGetWidth();
    float height = rsgGetHeight();
    rsgClearColor(0.f, 0.f, 0.f, 1.f);

    int size = rsAllocationGetDimX(rsGetAllocation(point));
    Point_t *p = point;

    rs_allocation aIn, aOut;
    aIn = rsGetAllocation(point);
    aOut = rsGetAllocation(point);
    ScreenInfo_t info = { .width = width, .height = height };
    rsForEach(physicsScript, aIn, aOut, &info);
    rsClearObject(&aIn);
    rsClearObject(&aOut);

    rsgDrawMesh(partMesh);

    return 1;
}
