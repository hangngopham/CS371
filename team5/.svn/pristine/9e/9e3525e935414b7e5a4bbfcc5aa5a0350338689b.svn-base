/**
 * This class will hold the meat of the values.  Rows belong to a category.
**/
/*
 *During refactor*
 *Make a child of BigDecimal that auto prints as currency to reduce print complexity.
 */
package budget;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Vector;

public class Row {
	private String title;
	private BigDecimal estimate;
	private ArrayDeque<Transaction> actual;
	private BigDecimal diff;
	
	//Default constructor.
	public Row() {
		//!!The order here is important. diff and actual must be created before
		//calling setEstimate!!
		setTitle("Insert Row Title");
		actual = new ArrayDeque<Transaction>();
		setEstimate(0.0);
	}//End default constructor.
	
	public Row(String t) {
		setTitle(t);
		actual = new ArrayDeque<Transaction>();
		setEstimate(0.0);
	}
	
	//Value added constructor
	public Row(String t, double e) {
		setTitle(t);
		actual = new ArrayDeque<Transaction>();
		setEstimate(e);
	}//End value added constructor.
	
	//Accessors
	//For title
	public String getTitle() {
		return title;
	}//End getTitle.
	
	//For estimate
	public BigDecimal getEstimate() {
		return estimate;
	}//End getEstimate
	
	//For the actual, nobody really needs the deque, therefore, we'll just return
	//the value as a big decimal.
	public BigDecimal getActual() {
		BigDecimal sum = new BigDecimal(0.0);
		Transaction array[] = new Transaction[actual.size()];
		actual.toArray(array);
		for(int i = 0; i < actual.size(); i++)
			sum = sum.add(array[i].getValue());
		return sum;
	}//End getActual
	
	//get the difference
	public BigDecimal getDiff() {
		setDiff();
		return diff;
	}//End getDiff
	//End accessors.
	
	//Mutators
	//to change title
	public void setTitle(String t) {
		title = t;
	}//End setTitle
	
	//to change the estimate
	public void setEstimate(double e) {
		estimate = new BigDecimal(e);
		setDiff();
	}//End setEstimate
	
	//Overload
	public void setEstimate(String toDec) {
		estimate = new BigDecimal(toDec);
		setDiff();
	}
	
	//To add to the transaction list
	public void addTransaction(double toAdd) {
		actual.addLast(new Transaction(toAdd));
		setDiff();
	}//End addTransaction
	
	public void addTransaction(String toAdd) {
		Transaction toLoad = new Transaction();
		toLoad.setValue(toAdd);
		actual.addLast(toLoad);
	}
	
	//to change the diff.
	private void setDiff() {
		diff = new BigDecimal(getActual().doubleValue() - estimate.doubleValue());
	}//End setDiff
	//End mutators
	
	//toString function - just simplifies testing + printing.
	public String toString() {
		return getTitle() 
				+ " " + NumberFormat.getCurrencyInstance().format(getEstimate())
				+ " " + NumberFormat.getCurrencyInstance().format(getActual())
				+ " " + NumberFormat.getCurrencyInstance().format(getDiff());
	}//End toString
	
	//toVector to print into a table format
	public Vector<Object> getVector() {
		Vector<Object> data = new Vector<Object>();
		data.add(getTitle());
		data.add(NumberFormat.getCurrencyInstance().format(getEstimate()));
		data.add(NumberFormat.getCurrencyInstance().format(getActual()));
		data.add(NumberFormat.getCurrencyInstance().format(getDiff()));
		return data;
	}//End toVector.
	
	public String [] getTransactionVals() {
		if(actual.isEmpty())
			return new String[] {};
		String toReturn [] = new String[actual.toArray().length];
		for(int i = 0; i < actual.toArray().length; i++) {
			toReturn[i] = ((Transaction)actual.toArray()[i]).getValue().toString();
		}
		return toReturn;
	}
	
}//End class.
