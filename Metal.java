// This is all the metal pieces required for jobs to be pressed
// each one has width and height dimensions, and x,y points for top left coord
class Metal {
    float width, height, xCoord, yCoord, area;

    Metal(float inWidth, float inHeight) {
        // sort all dimensions to make width longest length
        if (inHeight > inWidth) {
            width = inHeight;
            height = inWidth;
        } else {
            width = inWidth;
            height = inHeight;
        }
        area = inWidth*inHeight;
    }

    void Rotate() {
        float tempWidth = width;
        width = height;
        height = tempWidth;
    }
}