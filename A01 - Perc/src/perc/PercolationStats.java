package perc;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double totalOpen;
	private int times;
	double[] numOpen;


	/**
	 * Runs T times on an N by N grid, randomly generating sites to open and recording how many 
	 * sites it took to open before percolating, and generating statistics on those numbers.
	 * @param N number of rows and columns for a square grid
	 * @param T number of times to run tests
	 */
	public PercolationStats(int N, int T) {
		
		times = 0;
		totalOpen = 0;
		numOpen = new double[T]; //Create double[] of size T (number of times we're running)
		
		
		Percolation percolation = new Percolation(N);
		
		for (times = 0; times < T; times++) {
			while (percolation.percolates() != true) {
				int r = StdRandom.uniform(N); //Create two random numbers between 0 - N-1
				int r2 = StdRandom.uniform(N);
				percolation.open(r, r2);

			}
			
			totalOpen = percolation.numberOfOpenSites() * 1.0; //Convert numberOfOpenSites from int to Double and store in totalOpen
			
			numOpen[times] = totalOpen / (N*N); //store percentage of sites opened vs. total sites into double[] numOpen at position 0, 1, 2...
			
			percolation = new Percolation(N); //Remake percolation with a blank slate before looping back

		}
		
	}
		
	/**
	 * Sample mean of percolation
	 * @return average (mean) of number of sites it took to open to percolate
	 */
	public double mean() {
		
		double mean = StdStats.mean(numOpen);
		return mean;
		
	}
	
	
	/**
	 * sample standard deviation of percolation threshold
	 * @return Standard deviation between number of sites it took to open to percolate
	 */
	public double stddev() {
		
		double stdDev = 0.000000000;
		stdDev = StdStats.stddev(numOpen);
		return stdDev;
	} 
	
	
	/**
	 * Low endpoint of 95% confidence interval
	 * 
	 */
	public double confidenceLow() {
		
		double confLow = 0.0;
		
		confLow = (mean() - (1.960 * stddev()) / Math.sqrt(times)); //95% confidence formula
		
		return confLow;
	}
	
	
	/** 
	 * High endpoint of 95% confidence interval
	 * 
	 */
	public double confidenceHigh() {
		
		double confHigh = 0.0;
		
		confHigh = (mean() + (1.960 * stddev()) / Math.sqrt(times));
		
		return confHigh;
	}
	
}
	 


