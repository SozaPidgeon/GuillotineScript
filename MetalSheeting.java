import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

class MetalSheeting {
    public static void main(String[] args) {
        // makes the lists of all bins we'll need
        
        // all newly input metal > all finalised metal
        ArrayList<Metal> metalList = new ArrayList<Metal>();

        // list of all bins we'll need
        ArrayList<Bin> binList = new ArrayList<Bin>();

        Bin startingBin = new Bin();
        binList.add(startingBin);

        // takes inputs for our metal
        metalInput(metalList);

        //sort metal by area DES
        for(int i = 0; i < metalList.size() - 1; i++) {
            if(metalList.get(i).area < metalList.get(i+1).area) {
                Metal temp = metalList.get(i);
                metalList.set(i, metalList.get(i+1));
                metalList.set(i+1, temp);
                i = -1;
            }
        }

        // this is where the fun begins
        // MAXRECTS-BSSF-GLOBAL-BBF
        // (loop over everything at every instance and score the best of everything)

        // iterate over every metal piece, no man left behind
        for (int metal = 0; metal < metalList.size(); metal++) {
            int bestMetal = null;
            int bestBin = null;
            for (int bin = 0; bin < binList; bin++) {

            }

        }


//        y height                     (Bin)
//        ┌───────────────────────────┐
//        │                    (Rect) │
//        │                           │
//        ├─────────────┐             │
//        │     (Metal) │             │
//        │             │             │
//        │             │             │
//        └─────────────┴─────────────┘ x width
//    (0,0)

        //     for(int binIndex = 0; binIndex < binList.size(); binIndex++) {
        //         for(int i = 0; i < metalList.size(); i++) {
        //             // locate our current working piece
        //             Metal metal = metalList.get(i);

        //             // setup for rectangle scoring
        //             boolean rotate = false;
        //             Rect bestRect = null;
        //             float bestArea = 0;

        //             // iterate over all free rectangles to find optimal rectangle
        //             for(int rectIndex = 0; rectIndex < binList.get(binIndex).freeRect.size(); rectIndex++) {
        //                 // make rectangle object
        //                 Rect rect = binList.get(binIndex).getRect(rectIndex);
        //                 // calculate area difference
        //                 float areaDiff = rect.area - metal.area;
        //                 // check that at least one side of the metal fits in the free rectangle
        //                 if(metal.width <= rect.width || metal.height <= rect.width) {
        //                     // check metal fits in rectangle
        //                     if(areaDiff >= 0) {
        //                         // check that this rectangle doesnt share the same area as the current best option
        //                         // otherwise use the best short side fit to select the better option
        //                         if(bestArea == rect.area) {
        //                             Rect BSSFCheck = BSSF(metal, bestRect, rect);
        //                             bestRect = BSSFCheck;
        //                             bestArea = BSSFCheck.area;
        //                         } else if(rect.area < bestArea) {
        //                             bestRect = rect;
        //                             bestArea = rect.area;
        //                         }
        //                     }
        //                 }
        //             }
        //             // check there is a suitable rectangle to use
        //             if(bestRect != null) {
        //                 // find best rotation that fits
        //                 if(bestRect.width - metal.width < 0) {
        //                     float temp = metal.width;
        //                     metal.width = metal.height;
        //                     metal.height = temp;
        //                 }

        //             } else {
        //                 nextBinList.add(metal);
        //             }
        //         }
        //         // move all unused metal into the metal list and iterate to next bin
        //         metalList = nextBinList;
        //         nextBinList = null;
        //     }
        // }

    // Best Short Side Fit algorithm for tied rectangles rect1, rect2
    static Rect BSSFC(Metal metal, Rect rect1, Rect rect2) {
        Rect returned = null;

        float diff1w = rect1.width-metal.width;
        float diff1h = rect1.height-metal.height;
        
        float diff2w = rect2.width-metal.width;
        float diff2h = rect2.height-metal.height;
        
        float diff1 = Math.min(diff1w, diff1h);
        float diff2 = Math.min(diff2w, diff2h);

        if(diff1 != diff2) {
            if(Math.min(diff1, diff2) == diff1) {
                returned = rect1;
            } else {
                returned = rect2;
            }
        }
        return returned;
    }

    // Used to create an array of metal pieces to place
    static void metalInput(ArrayList<Metal> metalList) {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        // keep taking inputs until nothing remains
        while(loop) {
            System.out.print("Enter metal size: ");
            String inputMetal = input.nextLine();
            // throw an exit here boss
            if(inputMetal.equals("")) {
                loop = false;
            // input some dimension width*height
            } else {
                String[] inputMetalStrings = inputMetal.split("x", 2);
                //make Metal object from width,height values
                float width = Float.valueOf(inputMetalStrings[0]);
                float height = Float.valueOf(inputMetalStrings[1]);
                Metal metal = new Metal(width, height);
                metalList.add(metal);
            }
        }
    }
}

// This is the big metal sheets that must be cut up
// internal sheets make up the spaces available to be used on the sheet
class Bin {
    static int width = 97;
    static int height = 49;
    ArrayList<Rect> freeRect = new ArrayList<Rect>();
    ArrayList<Metal> sorted = new ArrayList<Metal>();

    Bin() {
        Rect rect = new Rect(width, height, 0, 0);
        freeRect.add(rect);
    }

    void AddRect(float inWidth, float inHeight, float inX, float inY) {
        Rect rect = new Rect(inWidth, inHeight, inX, inY);
        freeRect.add(rect);
    }

    void SubRect(int index) {
        freeRect.remove(index);
    }

    ArrayList<Rect> getFreeRect() {
        return freeRect;
    }
    
    void AddSorted(Metal inMetal) {
        sorted.add(inMetal);
    }

    ArrayList<Metal> GetSorted() {
        return sorted;
    }

    Rect getRect(int index) {
        return freeRect.get(index);
    }

    Metal GetMetal(int index) {
        return sorted.get(index);
    }
}

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
}

//      │179 ┐191 └192 ─196 ┘217 ┌218 ┤180 ├195 ┼197 ┬194 ┴193