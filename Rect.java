// an object to represent the free space created after each piece of metal is
// placed into a bin
class Rect {
    float width, height, xCoord, yCoord, area;

    Rect(float inWidth, float inHeight, float inX, float inY) {
        width = inWidth;
        height = inHeight;
        xCoord = inX;
        yCoord = inY;
        area = inWidth*inHeight;
    }
}