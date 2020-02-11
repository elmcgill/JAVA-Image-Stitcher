package pa2;

/**
 * REMOVE BEFORE SUBMTTING!
 *
 */
public class TestClass {
	
	public static void main(String[] args) {
		
		
		int[][] temp = { {5, 7, 9, 4, 5}, {2, 3, 1, 1, 2}, {2, 0, 49, 46, 8}, {3, 1, 1, 1,1}, {50, 51, 25, 26, 1}};
	
		MatrixCuts x = new MatrixCuts();

		//ArrayList<Tuple> lol2 = x.widthCut(temp);
		
//		Picture pic = new Picture("39020588482_3301f3d6d1_q.jpg");
//		
//		int[][] importance = new int[pic.height()][pic.width()];
//
//		for (int i = 0; i < pic.height(); i++) {
//
//			for (int j = 0; j < pic.width(); j++) {
//				if (j == 0) {
//					int r1 = pic.get(j, i).getRed();
//					int g1 = pic.get(j, i).getGreen();
//					int b1 = pic.get(j, i).getBlue();
//					int r2 = pic.get(j + 1, i).getRed();
//					int g2 = pic.get(j + 1, i).getGreen();
//					int b2 = pic.get(j + 1, i).getBlue();
//					int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
//
//					importance[i][j] = dist;
//				} else if (j == pic.width() - 1) {
//					int r1 = pic.get(j, i).getRed();
//					int g1 = pic.get(j, i).getGreen();
//					int b1 = pic.get(j, i).getBlue();
//					int r2 = pic.get(j - 1, i).getRed();
//					int g2 = pic.get(j - 1, i).getGreen();
//					int b2 = pic.get(j - 1, i).getBlue();
//					int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
//
//					importance[i][j] = dist;
//				} else {
//					int r1 = pic.get(j - 1, i).getRed();
//					int g1 = pic.get(j - 1, i).getGreen();
//					int b1 = pic.get(j - 1, i).getBlue();
//					int r2 = pic.get(j + 1, i).getRed();
//					int g2 = pic.get(j + 1, i).getGreen();
//					int b2 = pic.get(j + 1, i).getBlue();
//					int dist = (int) (Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
//					importance[i][j] = dist;
//				}
//
//			}
//		}
//		
//		ArrayList<Tuple> lol2 = x.widthCut(importance);
	
		ImageProcessor img = new ImageProcessor();
		
		ImageProcessor.reduceWidth(1000, "3d-cube-background-4k-yo.jpg");
		
//		ImageStitch stitch = new ImageStitch();
//		
//		Picture stitch1 = new Picture("testscreen-large.jpg");
//		Picture stitch2 = new Picture("Iceland_Landscape_Photography_4.jpg");
//		Picture monstrosity = stitch.stitch(stitch1,stitch2);
//		monstrosity.show();
		

		
	}

}
