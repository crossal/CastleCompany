import java.util.Arrays;

/*
"The Castle Company
The Castle Company is in the business of building castles. Now, they also believe in
quality aesthetics, so they only want to build castles in two types of places:
a. Peaks
b. Valleys

Let’s say you have an array of integers that describes a stretch of land, where each integer represents the
current height of the land. Can you write a function that reads that array and returns the number of castles
that The Castle Company should build on that stretch of land?

You can assume that you can always build a castle at the start of the array."

Other assumptions:
- A peak is defined by both its neighbours being shorter than it
- A valley is defined by both its neighbours being taller than it
- Assume
- The last point in the array cannot be assumed to be either a peak or a valley as we cannot see its rhs neighbour

Areas of improvement:
- could introduce a threshold parameter representing the difference in height between peaks/valleys and their neighbours


Part 2:
You can also assume that you can build a castle at the end of the array.
Other assumptions:
- Re-definition of peak: a peak is one point or a series of points of the same height, taller than their neigbours i.e. [1,2,3,3,2], the points [3,3] represent the whole peak
- Re-definition of valley: a valley is one point or a series of points of the same height, shorter than their neighbours i.e. [3,2,1,1,2], the points [1,1] represent the whole valley
- Only one castle should be built per peak/valley.
*/
public class CastleCompany {
	
	public static void main(String[] args) {
		
		int[][] partOneStretchesOfLand = new int[][]{
				{1,3,4,3,5,1,3},
				{},
				{2,2,2,2,2,2},
				{1,2,2,3,4,7,9},
				{1},
				{2,1,2}
		};
		
		System.out.println("Part 1");
		for (int[] stretchOfLand : partOneStretchesOfLand) {
			System.out.println("Number of castles to be built on " + Arrays.toString(stretchOfLand) + ": " + numberOfCastlesToBuild(stretchOfLand));
		}
		
		int[][] partTwoStretchesOfLand = new int[][]{
				{2,3,4,4,3,1,1,1,3,3},
				{2,3,4,4,3,1,1,1,4,3},
				{2,3,2,3,4,2},
				{2,3},
				{2,3,2}
		};
		
		System.out.println("Part 2");
		
		for (int[] stretchOfLand : partTwoStretchesOfLand) {
			System.out.println("Number of castles to be built on " + Arrays.toString(stretchOfLand) + ": " + numberOfCastlesToBuildB(stretchOfLand));
		}
	}
	
	public static int numberOfCastlesToBuild(int[] stretchOfLand) {
		
		if (stretchOfLand.length == 0) {
			return 0;
		}
		
		int numberOfCastles = 1; // "You can assume that you can always build a castle at the start of the array."
		
		for (int i = 1; i < stretchOfLand.length - 1; i++) {
			if (stretchOfLand[i] > stretchOfLand[i-1] && stretchOfLand[i] > stretchOfLand[i+1]) {
				numberOfCastles++;
			} else if (stretchOfLand[i] < stretchOfLand[i-1] && stretchOfLand[i] < stretchOfLand[i+1]) {
				numberOfCastles++;
			}
		}
		
		return numberOfCastles;
	}
	
	public static int numberOfCastlesToBuildB(int[] stretchOfLand) {
		
		if (stretchOfLand.length < 3) {
			return stretchOfLand.length;
		}
		
		// a castle will always be built at the start and end of the land/array
		int numberOfCastles = 2;
		
		boolean potentialPeak = false;
		boolean potentialValley = false;
		
		for (int i = 1; i < stretchOfLand.length; i++) {
			if (stretchOfLand[i] > stretchOfLand[i-1]) {
				if (potentialValley == true) {
					numberOfCastles++;
					potentialValley = false;
				}
				potentialPeak = true;
			} else if (stretchOfLand[i] < stretchOfLand[i-1]) {
				if (potentialPeak == true) {
					numberOfCastles++;
					potentialPeak = false;
				}
				potentialValley = true;
			}
		}
		
		return numberOfCastles;
	}
}
