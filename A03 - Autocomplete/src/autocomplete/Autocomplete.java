package autocomplete;

import java.util.Arrays;

public final class Autocomplete {
	Term[] tArray;
	BinarySearchDeluxe bsd;
	int j = 0;

	// Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
    	this.tArray = terms;
    	Arrays.sort(tArray);
    	System.out.println("Sorted!");
    }

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	Term t = new Term(prefix, 0);
    	System.out.println("Created temp term with prefix");
    	int first = bsd.firstIndexOf(tArray, t, Term.byPrefixOrder(prefix.length()));
    	System.out.println("First index of: " + first);
    	int last = bsd.lastIndexOf(tArray, t, Term.byPrefixOrder(prefix.length()));
    	System.out.println("Last index of: " + last);
    	int n = last - first;
    	Term[] matches = new Term[n];
    	for(int i = first; i < last; i++) {
    		matches[j] = tArray[i];
    		j++;
    	}
    	Arrays.sort(matches, Term.byReverseWeightOrder());
    	
		return matches;
    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
		return j;
    	
    }
}
