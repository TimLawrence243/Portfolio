package autocomplete;

import java.util.Comparator;

public final class Term implements Comparable<Term> {
	
	private String q;
	private double w;
	
	//public static final Comparator<Term> BY_QUERY = new CompareByQuery();
	public static final Comparator<Term> BY_WEIGHT = new CompareByWeight();
	// Initialize a term with the given query string and weight.
    public Term(String query, double weight) {
    	q = query;
    	w = weight;
    	
    	if(q == null) {throw new NullPointerException("Query is empty");}
    	if(w < 0) {throw new IllegalArgumentException("Weight is < 0");}
    	
    }

    public String getQ() {
		return q;
	}

	public double getW() {
		return w;
	}

	// Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
		return BY_WEIGHT;
    	
    }

    // Compare the terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
    	if(r < 0) {throw new IllegalArgumentException("Character < 0");}
    	return new CompareByQuery(r);
    	
    }

    // Compare the terms in lexicographic order by query.
    public int compareTo(Term that) {
    	return (this.getQ().compareTo(that.getQ()));
    }

    // Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
    	return getW() + "\t" + getQ();
    }
    
    private static class CompareByQuery implements Comparator<Term>{
    	private final int r;
    	
    	public CompareByQuery(int r) {
    		this.r = r;
    	}
    	
		@Override
		public int compare(Term a, Term b) {
			
			String strA = a.getQ().substring(0, r);
			String strB = b.getQ().substring(0, r);
			//System.out.println(r);
			
			return (strA.compareTo(strB));
			
		}
    	
    }
    
    private static class CompareByWeight implements Comparator<Term>{

		@Override
		public int compare(Term a, Term b) {
			return (int)(b.getW() - (a.getW()));
		}
    	
    }
}
