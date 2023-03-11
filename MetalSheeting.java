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
            int bestMetal = 0;
            int bestBin = 0;

            // iterate over every bin, allowing to grow as binList grows
            for (int bin = 0; bin < binList.size(); bin++) {
                int bestRect = 0;

                // iterate over every rect, allowing to grow as rectList grows
                for (int rect = 0; rect < binList.get(bin).getRectList().size(); rect++) {

                }
            }

            binList.get(bestBin).sorted.add(metalList.get(bestMetal));
            metalList.remove(bestMetal);
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

//      │179 ┐191 └192 ─196 ┘217 ┌218 ┤180 ├195 ┼197 ┬194 ┴193