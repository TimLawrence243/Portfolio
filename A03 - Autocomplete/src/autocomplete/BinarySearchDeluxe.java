package autocomplete;

import java.util.Comparator;

import edu.princeton.cs.algs4.BinarySearch;

public class BinarySearchDeluxe {
	// Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		int hi = a.length;
		int low = 0;
		
		while(low <= hi) {
			int mid = low + (hi - low)/2;
			int compy = comparator.compare(a[mid], key);
			
			if (compy >= 1) 										{hi = mid - 1;}
			else if (compy <= -1) 									{low = mid + 1;}
			else if (comparator.compare(a[mid - 1], a[mid]) == 0) 	{hi = mid - 1;}
			else return mid;
		}

    	return -1;
    	
    }

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		int hi = a.length -1;
		int low = 0;
		
		while(low <= hi) {
			int mid = low + (hi - low)/2;
			int compy = comparator.compare(a[mid], key);
			
			if (compy >= 1) 										{hi = mid - 1;}
			else if (compy <= -1) 									{low = mid + 1;}
			else if (comparator.compare(a[mid + 1], a[mid]) == 0) 	{low = mid + 1;}
			else return mid;
		}


    	return -1;
    	
    	
    }
}
