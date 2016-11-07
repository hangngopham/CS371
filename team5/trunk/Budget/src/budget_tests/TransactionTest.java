package budget_tests;
import budget.Transaction;

public class TransactionTest {
	public static void main(String args[]){
		//Utilize all constructors.
		Transaction tester = new Transaction();
		Transaction tester2 = new Transaction(50.55);
		Transaction tester3 = new Transaction("User1", 45.61);
		
		//Print all the values.
		System.out.println("Default: " + tester.getValue());
		System.out.println("Value added: " + tester2.getValue());
		System.out.println("Name+Value added: " + tester3.getValue());
		
		//Use the two value setters.
		tester.setValue("100.89");
		tester2.setValue(1000.50);
		
		//Use the to-string
		System.out.println(tester);
		System.out.println(tester2);
	}
}
