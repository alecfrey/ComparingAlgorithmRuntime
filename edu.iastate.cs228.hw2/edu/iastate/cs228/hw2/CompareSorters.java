package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * @author Alec Frey
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		// Conducts multiple rounds of comparison of four sorting algorithms. Within
		// each round,
		// set up scanning as follows:
		//
		// a) If asked to scan random points, calls generateRandomPoints() to initialize
		// an array
		// of random points.
		//
		// b) Reassigns to the array scanners[] (declared below) the references to four
		// new
		// PointScanner objects, which are created using four different values
		// of the Algorithm type: SelectionSort, InsertionSort, MergeSort and QuickSort.
		// --------------------------------------------------------------------------------------
		// For each input of points, do the following.
		//
		// a) Initialize the array scanners[].
		//
		// b) Iterate through the array scanners[], and have every scanner call the
		// scan()
		// method in the PointScanner class.
		//
		// c) After all four scans are done for the input, print out the statistics
		// table from
		// section 2.
		//
		// A sample scenario is given in Section 2 of the project description.

		int trial = 1;
		int pick = 0;
		Scanner s = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		Scanner s3 = new Scanner(System.in);
		System.out.println("Performance of Four Sorting Algorithms in Point Scanning");

		while (pick != 3) {
			System.out.println("keys : 1 (random integers),  2 (file input),  3 (exit)");
			pick = s.nextInt();
			PointScanner[] scanners = new PointScanner[4];
			
			if (pick == 3) { 
				System.out.println("Exited.");
				s.close();
				System.exit(0);
			}
			
			System.out.println("Trial " + trial + ":" + trial);
			trial++;
			
			if (pick == 1) {
				System.out.println("Enter number of points to be created:");
				int numPoints = s.nextInt();
				Random rand = new Random();

				Point[] array = generateRandomPoints(numPoints, rand);

				scanners[0] = new PointScanner(array, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(array, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(array, Algorithm.MergeSort);
				scanners[3] = new PointScanner(array, Algorithm.QuickSort);

				System.out.println("algorithm   size  time (ns)");
				System.out.println("----------------------------------");

				for (int i = 0; i < scanners.length; i++) {
					scanners[i].scan();
					String stats = scanners[i].stats();

					if (i < 2) {
						System.out.println(stats + "     " + numPoints + "  " + scanners[i].scanTime);
					} else {
						System.out.println(stats + "         " + numPoints + "  " + scanners[i].scanTime);
					}
				}
				System.out.println("----------------------------------" + '\n');
				
			} else if (pick == 2) {
				s2 = new Scanner(System.in);
				System.out.println("Points from a file");
				System.out.println("File name:");
				String fileName = s2.nextLine();
				File f = new File(fileName);
				s3 = new Scanner(f);
				int numPoints = 0;

				while (s3.hasNextInt()) {
					numPoints++;
					s3.nextInt();
				}
				
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);

				System.out.println("algorithm   size  time (ns)");
				System.out.println("----------------------------------");

				for (int i = 0; i < scanners.length; i++) {
					scanners[i].scan();
					String stats = scanners[i].stats();

					if (i < 2) {
						System.out.println(stats + "     " + numPoints + "  " + scanners[i].scanTime);
					} else {
						System.out.println(stats + "         " + numPoints + "  " + scanners[i].scanTime);
					}
				}
				System.out.println("----------------------------------" + '\n');
				
			} else {
				System.out.println("Invalid Entry.");
				trial--;
			}
		}
		s.close();
		s2.close();
		s3.close();
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] × [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		if (numPts < 1) {
			throw new IllegalArgumentException("NumPoints less than 1");
		}

		Point[] ptArray = new Point[numPts];

		for (int i = 0; i < numPts; i++) {
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;

			ptArray[i] = new Point(x, y);
		}
		return ptArray;
	}
}