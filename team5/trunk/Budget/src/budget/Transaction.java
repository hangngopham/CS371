package budget;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Simple class that holds the value assigned to a transaction.
 * Objects of this type belong to a specific row.
 **/public class Transaction {
	 /**
	  * This variable holds the value of the transaction.  In addition to this, there will also be a transaction name in the future.
	  */
	 private BigDecimal value;

	 private String user_name;


	 /**
	  * Default constructor for a Transaction object.
	  */
	 public Transaction() {
		 setValue(0.0);
	 }//End default constructor.

	 /**
	  * Value added constructor.
	  * @param v the double value that BigDecimal should be set to.
	  */
	 public Transaction(double v) {
		 setValue(v);
	 }//End value added constructor.
	 
	 //Will be replacing public Transaction(double v)
	 public Transaction(String whoDid, double amount) {
		 setValue(amount);
		 user_name = whoDid;
		 System.out.println(user_name);
	 }

	 /**
	  * The user will be needing to see transaction values, therefore, return the BigDecimal to avoid accuracy loss.
	  * @return returns the value of the transaction.
	  */
	 public BigDecimal getValue() {
		 return value;
	 }//End accessor.

	 /**
	  * Set the value of the transaction.
	  * @param toSet a double value that the value will be set to.
	  */
	 public void setValue(double toSet) {
		 value = new BigDecimal(toSet);
	 }//End setValue.

	 public void setValue(String toSet) {
		 value = new BigDecimal(toSet);
	 }

	 /**
	  * Simple toString method that will simply print the value back in a currency format.
	  */
	 public String toString() {
		 return NumberFormat.getCurrencyInstance().format(getValue());
	 }//End toString
 }//End class.
