// This is the big metal sheets that must be cut up
// internal sheets make up the spaces available to be used on the sheet
import java.util.ArrayList;

class Bin {
    static int width = 97;
    static int height = 49;

    Rect[] maxRectLil = new Rect[3];
    Rect[] maxRectBig = new Rect[2];

    ArrayList<Rect> rectList = new ArrayList<Rect>();
    ArrayList<Metal> sorted = new ArrayList<Metal>();

    Bin() {
        Rect rect = new Rect(width, height, 0, 0);
        rectList.add(rect);
    }

    void AddRect(float inWidth, float inHeight, float inX, float inY) {
        Rect rect = new Rect(inWidth, inHeight, inX, inY);
        rectList.add(rect);
    }

    void AddRect(Rect inRect) {
        rectList.add(inRect);
    }

    void SubRect(int index) {
        rectList.remove(index);
    }

    ArrayList<Rect> getRectList() {
        return rectList;
    }

    void AddSorted(Metal inMetal) {
        sorted.add(inMetal);
    }

    ArrayList<Metal> GetSorted() {
        return sorted;
    }

    Rect getRect(int index) {
        return rectList.get(index);
    }

    Metal GetMetal(int index) {
        return sorted.get(index);
    }

    void MaxRect(Bin inBin, int inRect, Metal inM) {
        Rect inR = inBin.getRect(inRect);

        float rl1w = inM.width;
        float rl1h = inR.height - inM.height;
        float rl1x = inM.xCoord;
        float rl1y = inM.yCoord + inM.height;
        maxRectLil[0] = new Rect(rl1w, rl1h, rl1x, rl1y);

        float rl2w = inR.width - inM.width;
        float rl2h = inR.height - inM.height;
        float rl2x = inM.xCoord + inM.width;
        float rl2y = inM.yCoord + inM.height;
        maxRectLil[1] = new Rect(rl2w, rl2h, rl2x, rl2y);

        float rl3w = inR.width - inM.width;
        float rl3h = inM.height;
        float rl3x = inM.xCoord + inM.width;
        float rl3y = inM.yCoord;
        maxRectLil[2] = new Rect(rl3w, rl3h, rl3x, rl3y);

        float rb1w = maxRectLil[0].width + maxRectLil[1].width;
        float rb1h = maxRectLil[0].height;
        float rb1x = maxRectLil[0].xCoord;
        float rb1y = maxRectLil[0].yCoord;
        maxRectBig[0] = new Rect(rb1w, rb1h, rb1x, rb1y);

        float rb2w = maxRectLil[2].width;
        float rb2h = maxRectLil[2].height + maxRectLil[1].height;
        float rb2x = maxRectLil[2].xCoord;
        float rb2y = maxRectLil[2].yCoord;
        maxRectBig[1] = new Rect(rb2w, rb2h, rb2x, rb2y);
    }
}