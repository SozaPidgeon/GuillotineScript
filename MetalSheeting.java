import java.util.Scanner;
import java.util.ArrayList;

class MetalSheeting {
    public static void main(String[] args) {
        // makes the 2d array we'll use to hold all pieces [width,length]
        ArrayList<Metal> metalList = new ArrayList<Metal>();
        // takes inputs for our metal
        metalInput(metalList);
        for(int i = 0; i < metalList.size(); i++) {
            // locate our current working piece
            Metal metal = metalList.get(i);
            // do shit here

            System.out.println("Area for metal[" + i + "] = " + metal.area);
        }
    }

    // function used to sort metal by longest length and then cross check against Area
    static ArrayList<Metal> metalSorting(ArrayList<Metal> unsorted) {
        ArrayList<Metal> sorted = new ArrayList<Metal>();

        return sorted;
    }

    // Used to create an array of metal pieces to place
    static void metalInput(ArrayList<Metal> metalList) {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        // keep taking inputs until nothing remains
        while(loop) {
            System.out.print("Enter new metal size: ");
            String inputMetal = input.nextLine();
            // throw an exit here boss
            if(inputMetal.equals("")) {
                loop = false;
            // input some dimension x*y
            } else {
                String[] inputMetalStrings = inputMetal.split("x", 2);
                float x = Float.valueOf(inputMetalStrings[0]);
                float y = Float.valueOf(inputMetalStrings[1]);
                Metal metal = new Metal(x, y);
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
    int freeRects = 1;
    ArrayList<Rect> freeRect = new ArrayList<Rect>();
}

// an object to represent the free space created after each piece of metal is
// placed into a bin
class Rect {
    float length, width, xCoord, yCoord;
}

// This is all the metal pieces required for jobs to be pressed
// each one has width and height dimensions, and x,y points for top left coord
class Metal {
    float ll, sl, xCoord, yCoord, area;

    Metal(float inX, float inY) {
        if (inX > inY) {
            ll = inX;
            sl = inY;
        } else {
            ll = inY;
            sl = inX;
        }
        area = inX*inY;
    }
}
