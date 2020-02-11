package pa2;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Image Processing class that contains the reduceWidth method.
 * 
 * @author Jeremy Galang and Ethan Mcgill
 */
public class ImageProcessor {

	public ImageProcessor() {

	}

	/**
	 * This method returns a picture object
	 * 
	 * @param x          - the size to reduce the picture by
	 * @param inputImage - the image as a string
	 * @return a picture object
	 */
	public static Picture reduceWidth(int x, String inputImage) {
		
		//if the image is null or x is less than or equal to zero
		if(inputImage == null || x <= 0) {
			return null;
		}

		Picture pic = new Picture(inputImage); //create a new picture object
		Picture old = new Picture(pic);
		Picture ret = null;
		
		int height = pic.height(); //get the width and height
		int width = pic.width();

		//now create the next few pictures
		for (int k = 1; k <= x; k++) {

			//this array keeps track of which pixels are important
			int[][] importance = new int[height][width];

			for (int i = 0; i < height; i++) { 

				for (int j = 0; j < width; j++) {
					if (j == 0) { //if we're on the first column
						int r1 = old.get(j,i).getRed();
						int g1 = old.get(j,i).getGreen();
						int b1 = old.get(j,i).getBlue();
						int r2 = old.get(j+1,i).getRed();
						int g2 = old.get(j+1,i).getGreen();
						int b2 = old.get(j+1,i).getBlue();
						//distance formula that was provided in the spec
						int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));

						importance[i][j] = dist;
					} else if (j == importance[0].length - 1) { //if we're on the last column
						int r1 = old.get(j,i).getRed();
						int g1 = old.get(j,i).getGreen();
						int b1 = old.get(j,i).getBlue();
						int r2 = old.get(j-1,i).getRed();
						int g2 = old.get(j-1,i).getGreen();
						int b2 = old.get(j-1,i).getBlue();
						//distance formula that was provided in the spec
						int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));

						importance[i][j] = dist;
					} else { //otherwise we're in the middle somewhere
						int r1 = old.get(j-1,i).getRed();
						int g1 = old.get(j-1,i).getGreen();
						int b1 = old.get(j-1,i).getBlue();
						int r2 = old.get(j+1,i).getRed();
						int g2 = old.get(j+1,i).getGreen();
						int b2 = old.get(j+1,i).getBlue();
						//distance formula that was provided in the spec
						int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
						importance[i][j] = dist;
					}

				}
			}

			ArrayList<Tuple> cutList = new ArrayList<>(); //list of tuples we're going to remove

			cutList = MatrixCuts.widthCut(importance); //call widthcut on it
			
			System.out.println(k);
			
			cutList.remove(0); //the first element contains the length of the path, so disregard it

			width--; //reduce the width by one
			
			ret = new Picture(width, height);
			//create the temp array
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					if (j < cutList.get(i).getY()) {
						ret.set(j,i,old.get(j,i)); //creating the new picture
					} else {
						ret.set(j,i,old.get(j+1,i));
					}
				}
			}
			
			old = new Picture(ret); //replace the picture with the newly calculated one

		}

		ret.show(); //show the picture
		return ret;

	}

}
