package pa2;

import java.util.ArrayList;

/**
 * This class contains the width and stitch cut methods. 
 * 
 * @author Jeremy Galang and Ethan McGill
 *
 */
public class MatrixCuts {

	/**
	 * Width cut method
	 * @param M - the 2D array of numbers
	 * @return An arrayList of Tuples that contain the Tuples we want to remove from M
	 */
	public static ArrayList<Tuple> widthCut(int[][] M) {

		//error checking
		if(M == null) {
			return null;
		}
	
		ArrayList<Tuple> ret = new ArrayList<Tuple>(); //the arraylist we want to return
		int[][] calculated = new int[M.length][M[0].length]; //the 2D array M with the new calculations

		
		//start from the bottom right and make our way to the top left
		for (int i = M.length - 1; i >= 0; i--) {
			for (int j = M[i].length - 1; j >= 0; j--) {

				if (i == M.length - 1) { //if we're on the last row, just fill it in
					calculated[i][j] = M[i][j];
				}

				else if (j == 0) { // if we're on the left edge

					calculated[i][j] = M[i][j] + Math.min(calculated[i + 1][j], calculated[i + 1][j + 1]);

				}

				else if (j == M[i].length - 1) { // if we're on the right edge

					calculated[i][j] = M[i][j] + Math.min(calculated[i + 1][j], calculated[i + 1][j - 1]);

				}

				else { // both ways, find minimum of all 3 possible locations
					calculated[i][j] = M[i][j] + Math.min(calculated[i + 1][j - 1],
							Math.min(calculated[i + 1][j], calculated[i + 1][j + 1]));

				}

			}
		}

		int smallest = Integer.MAX_VALUE; // this is basically infinitiy, just a placeholder
		int smallestIdx = 0;

		for (int i = 0; i < calculated[0].length; i++) { // find the smallest in the first row
			if (calculated[0][i] < smallest) {
				smallest = calculated[0][i];
				smallestIdx = i;
			}

		}

		ret.add(new Tuple(smallest, -1)); // shortest path length

		ret.add(new Tuple(0, smallestIdx)); // then first cell

		//start from element 1
		for (int i = 1; i < M.length; i++) {

			if (smallestIdx == 0) {// on left side, can't check left
				if (calculated[i][smallestIdx] < calculated[i][smallestIdx + 1]) { // right 
					smallestIdx += 1;
					ret.add(new Tuple(i, smallestIdx)); // shortest path lengths

				} else { //otherwise move down to right
					ret.add(new Tuple(i + 1, smallestIdx + 1));
					smallestIdx += 1;
				}
				// otherwise it'll stay the same

			} else if (smallestIdx == calculated[0].length - 1) { // on right side, can't check right
				if (calculated[i][smallestIdx] > calculated[i][smallestIdx - 1]) {
					smallestIdx -= 1;
					ret.add(new Tuple(i, smallestIdx)); // shortest path length

				} else {
					ret.add(new Tuple(i, smallestIdx)); //dont reduce the smallestIdx, we didn't move
				}
			}

			else {

				if (calculated[i][smallestIdx - 1] < calculated[i][smallestIdx]
						&& calculated[i][smallestIdx - 1] < calculated[i][smallestIdx + 1]) { // right
					smallestIdx -= 1;
					ret.add(new Tuple(i, smallestIdx)); // shortest path length
				}

				else if (calculated[i][smallestIdx + 1] < calculated[i][smallestIdx]
						&& calculated[i][smallestIdx + 1] < calculated[i][smallestIdx - 1]) {
					smallestIdx += 1;
					ret.add(new Tuple(i, smallestIdx)); // shortest path length

				} else {
					ret.add(new Tuple(i, smallestIdx)); // shortest path length
				}

			}

		}

		return ret;
	}

	public static ArrayList<Tuple> stitchCut(int[][] M) {

		//error checking
		if(M == null) {
			return null;
		}
		
		
		ArrayList<Tuple> ret = new ArrayList<Tuple>();

		// copied from the width cut
		int[][] calculated = new int[M.length][M[0].length];

		for (int i = M.length - 1; i >= 0; i--) {
			for (int j = M[i].length - 1; j >= 0; j--) {

				if (i == M.length - 1) { // if we're on the first row
					calculated[i][j] = M[i][j];
				}

				else if (j == M[i].length - 1) { // if on the right column
					calculated[i][j] = M[i][j] + calculated[i + 1][j];
				}

				else if (i != M.length - 1 && j != M[i].length - 1) { // find minimum of all 3 possible locations,
																		// right, diagonally right, and bottom
					calculated[i][j] = M[i][j]
							+ Math.min(calculated[i][j + 1], Math.min(calculated[i + 1][j], calculated[i + 1][j + 1]));
				}

			}
		}

		int smallest = Integer.MAX_VALUE;
		int smallestIdxI = 0; // starting row index of smallest path
		int smallestIdxJ = 0; // starting column index of smallest path

		for (int i = 0; i < calculated[0].length; i++) { // find the smallest in the first row
			if (calculated[0][i] < smallest) {
				smallest = calculated[0][i];
				smallestIdxJ = i;
			}

		}

		ret.add(new Tuple(smallest, -1)); // shortest path length

		ret.add(new Tuple(smallestIdxI, smallestIdxJ)); // then first cell

		while (smallestIdxI != M.length - 1) { //while we're not in the last row

			if (smallestIdxJ == M[0].length) {
				smallestIdxI += 1;
				ret.add(new Tuple(smallestIdxI, smallestIdxJ)); // shortest path length
			} else {
				if (smallestIdxJ == M[0].length - 1) {
					ret.add(new Tuple(smallestIdxI, smallestIdxJ));
					smallestIdxI++;
				}

				else if (calculated[smallestIdxI + 1][smallestIdxJ] <= calculated[smallestIdxI + 1][smallestIdxJ + 1]
						&& calculated[smallestIdxI + 1][smallestIdxJ] <= calculated[smallestIdxI][smallestIdxJ + 1]) {
					smallestIdxI += 1;
					ret.add(new Tuple(smallestIdxI, smallestIdxJ)); // add one down
				} else if (calculated[smallestIdxI + 1][smallestIdxJ + 1] < calculated[smallestIdxI + 1][smallestIdxJ]
						&& calculated[smallestIdxI + 1][smallestIdxJ + 1] <= calculated[smallestIdxI][smallestIdxJ
								+ 1]) {
					smallestIdxI += 1;
					smallestIdxJ += 1;
					ret.add(new Tuple(smallestIdxI, smallestIdxJ)); // add one diagnol down and to the right
				} else {
					smallestIdxJ += 1;
					ret.add(new Tuple(smallestIdxI, smallestIdxJ)); // add one to the right

				}
			}

		}

		return ret;
	}

}
