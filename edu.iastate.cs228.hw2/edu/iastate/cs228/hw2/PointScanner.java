package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Alec Frey
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	private Point[] points;

	private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of
											// the x coordinates and y coordinates of those points in the array
											// points[].
	private Algorithm sortingAlgorithm;

	protected long scanTime; // execution time in nanoseconds.

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
		if (pts.length == 0 || pts == null) {
			throw new IllegalArgumentException("PointScanner pts is null or length 0.");
		}

		points = new Point[pts.length];
		sortingAlgorithm = algo;
		scanTime = 0;

		for (int i = 0; i < pts.length; i++) {
			Point p = pts[i];
			if (p != null) {
				points[i] = new Point(p);
			}
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
		sortingAlgorithm = algo;
		scanTime = 0;

		File f = new File(inputFileName);

		if (!f.exists()) {
			throw new FileNotFoundException();
		}

		Scanner s = new Scanner(f);
		int numPoints = 0;

		while (s.hasNextInt()) {
			numPoints++;
			s.nextInt();
		}
		s.close();
		s = new Scanner(f);

		if (numPoints % 2 != 0) {
			s.close();
			throw new InputMismatchException("File doesnt contain even amount of integers.");
		}
		
		points = new Point[numPoints/2];

		int i = 0;
		while (s.hasNextInt()) {
			int x = s.nextInt();
			int y = s.nextInt();
			Point p = new Point(x, y);
			if (p != null) {
				points[i] = p;
				i++;
			}
		}
		s.close();
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;
		int medX = 0;
		int medY = 0;
		long before = 0;
		long after = 0;

		if (this.sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);

			// sort by x coordinates
			aSorter.setComparator(0);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime = after - before;
			medX = aSorter.getMedian().getX();
			//System.out.println("InsertionSorter MedX: " + medX);


			// sort by y coordinates
			aSorter.setComparator(1);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime += after - before;
			medY = aSorter.getMedian().getY();
			//System.out.println("InsertionSorter MedY: " + medY);


			medianCoordinatePoint = new Point(medX, medY);

		} else if (this.sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
			
			// sort by x coordinates
			aSorter.setComparator(0);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime = after - before;
			medX = aSorter.getMedian().getX();
			//System.out.println("Selection Sort MedX: " + medX);

			// sort by y coordinates
			aSorter.setComparator(1);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime += after - before;
			medY = aSorter.getMedian().getY();
			//System.out.println("Selection Sort MedY: " + medY);


			medianCoordinatePoint = new Point(medX, medY);
			
			
		} else if (this.sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);

			// sort by x coordinates
			aSorter.setComparator(0);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime = after - before;
			medX = aSorter.getMedian().getX();
			//System.out.println("MergeSort MedX: " + medX);


			// sort by y coordinates
			aSorter.setComparator(1);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime += after - before;
			medY = aSorter.getMedian().getY();
			//System.out.println("MergeSort MedY: " + medY);
 
			
			medianCoordinatePoint = new Point(medX, medY);
			
		} else if (this.sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
			
			// sort by x coordinates
			aSorter.setComparator(0);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime = after - before;
			medX = aSorter.getMedian().getX();
			//System.out.println("QuickSort MedX: " + medX);


			// sort by y coordinates
			aSorter.setComparator(1);
			before = System.nanoTime();
			aSorter.sort();
			after = System.nanoTime();
			scanTime += after - before;
			medY = aSorter.getMedian().getY();
			//System.out.println("QuickSort MedY: " + medY);


			medianCoordinatePoint = new Point(medX, medY);
		}

		// create an object to be referenced by aSorter according to sortingAlgorithm.
		// for each of the two
		// rounds of sorting, have aSorter do the following:
		//
		// a) call setComparator() with an argument 0 or 1.
		//
		// b) call sort().
		//
		// c) use a new Point object to store the coordinates of the
		// medianCoordinatePoint
		//
		// d) set the medianCoordinatePoint reference to the object with the correct
		// coordinates.
		//
		// e) sum up the times spent on the two sorting rounds and set the instance
		// variable scanTime.
	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {
		return sortingAlgorithm + "";
		// outputs in main
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {
		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {
		File f = new File("outputFileName.txt");
		if (!f.exists()) {
			throw new FileNotFoundException();
		}

		PrintWriter out = new PrintWriter("outputFileName.txt");

		out.println(toString());
		out.close();
	}
}
