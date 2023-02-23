import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

class MetalSheeting {
    public static void main(String[] args) {
        // makes the 2d array we'll use to hold all pieces [width,length]
        ArrayList<Metal> metalList = new ArrayList<Metal>();
        ArrayList<Bin> binList = new ArrayList<Bin>();
        ArrayList<Bin> binFinalList = new ArrayList<Bin>();
        Bin binEmpty = new Bin();
        binList.add(binEmpty);
        binFinalList.add(binEmpty);

        // takes inputs for our metal
        metalInput(metalList);

        //sort metal by area DES
        for(int i = 0; i < metalList.size() - 1; i++) {
            if(metalList.get(i).getArea() < metalList.get(i+1).getArea()) {
                Metal temp = metalList.get(i);
                metalList.set(i, metalList.get(i+1));
                metalList.set(i+1, temp);
                i = -1;
            }
        }

//        y height                     (Bin)
//        ┌───────────────────────────┐
//        │                    (Rect) │
//        │                           │
//        ├───────────┐               │
//        │   (Metal) │               │
//        │           │               │
//        │           │               │
//        └───────────┴───────────────┘ x width
//    (0,0)

        // this is where the fun begins
        for(int bin = 0; bin < binList.size(); bin++) {
            for(int i = 0; i < metalList.size(); i++) {
                // locate our current working piece
                Metal metal = metalList.get(i);

                // setup for rectangle scoring
                float scoreArea = 9999;
                boolean rotate = false;
                int bestRect = 0;
                float bestArea = 0;

                // iterate over all free rectangles to find optimal rectangle
                for(int ii = 0; ii < binList.get(bin).freeRect.size(); ii++) {
                    Rect rect = binList.get(bin).getRect(ii);
                    float areaDiff = rect.area - metal.area;
                    if(metal.width <= rect.width || metal.height <= rect.width) {
                        if(###########################################################)
                        } else if(areaDiff >= 0 && areaDiff < scoreArea) {
                            scoreArea = areaDiff;
                            bestRect = ii;
                        } 
                    } else continue;
                }
                // find best rotation that fits

            }
        }
    }

    // Best Short Side Fit algorithm if needed
    static Rect BSSF(Metal metal, Rect rect1, Rect rect2) {
        Rect returned;

        float diff1w = rect1.width-metal.width;
        float diff1h = rect1.height-metal.height;
        
        float diff2w = rect2.width-metal.width;
        float diff2h = rect2.height-metal.height;
        
        float diff1 = Math.min(diff1w, diff1h);
        float diff2 = math.min(diff2w, diff2h);

        if(diff1 = diff2) {
            returned = null;
        } else if(Math.min(diff1, diff2) = diff1) {
            returned = rect1;
        } else {
            returned = rect2;
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

    void AddMetal(Metal inMetal) {
        sorted.add(inMetal);
    }

    void SubRect(int index) {
        freeRect.remove(index);
    }

    ArrayList<Rect> getFreeRect() {
        return freeRect;
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

    int getArea() {
        return Math.round(area*100);
    }
}

// This is all the metal pieces required for jobs to be pressed
// each one has width and height dimensions, and x,y points for top left coord
class Metal {
    float width, height, xCoord, yCoord, area;

    Metal(float inWidth, float inHeight) {
        // sort all dimensions to longest length
        if (inHeight > inWidth) {
            width = inHeight;
            height = inWidth;
        } else {
            width = inWidth;
            height = inHeight;
        }
        area = inWidth*inHeight;
    }

    // return int area*100
    int getArea() {
        return Math.round(area*100);
    }
}

//      │179 ┐191 └192 ─196 ┘217 ┌218 ┤180 ├195 ┼197 ┬194 ┴193