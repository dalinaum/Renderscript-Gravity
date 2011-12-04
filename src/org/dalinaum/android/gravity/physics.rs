#pragma version(1)
#pragma rs java_package_name(org.dalinaum.android.gravity)

#include "gravity.rsh"

float gTouchX = 50.f;
float gTouchY = 50.f;

void root(const Point_t *pointIn, Point_t *pointOut, const void *info) {
    ScreenInfo_t screenInfo = *(ScreenInfo_t *) info;
    float width = screenInfo.width;
    float height = screenInfo.height;
    float diff_x = gTouchX - pointIn->position.x;
    float diff_y = gTouchY - pointIn->position.y;
    float acc = 50.f / (diff_x * diff_x + diff_y * diff_y);
    float acc_x = acc * diff_x;
    float acc_y = acc * diff_y;
    pointOut->delta.x = pointIn->delta.x + acc_x;
    pointOut->delta.y = pointIn->delta.y + acc_y;
    pointOut->position.x = pointIn->position.x + pointOut->delta.x;
    pointOut->position.y = pointIn->position.y + pointOut->delta.y;
    pointOut->delta.x = pointIn->delta.x * 0.96;
    pointOut->delta.y = pointIn->delta.y * 0.96;

    if (pointOut->position.x > width) {
        pointOut->position.x = 0;
    } else if (pointOut->position.x < 0) {
        pointOut->position.x = width;
    }

    if (pointOut->position.y > height) {
        pointOut->position.y = 0;
    } else if (pointOut->position.y < 0) {
        pointOut->position.y = height;
    }
}
