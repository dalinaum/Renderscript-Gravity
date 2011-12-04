typedef struct __attribute__((packed, aligned(4))) Point {
    float2 delta;
    float2 position;
    uchar4 color;
} Point_t;

typedef struct ScreenInfo {
    float width;
    float height;
} ScreenInfo_t;

