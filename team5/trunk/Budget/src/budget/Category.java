package budget;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Vector;

public class Category {
	private String categoryName;
	private ArrayDeque<Row> rows;
	private CategoryTableModel categoryModel;
	
	//Default constructor
	public Category() {
		//!!Should never be called.
		categoryName = "Place Name Here";
		rows = new ArrayDeque<Row>();
		setupModel();
	}//End default constructor
	
	//Name only constructor
	public Category(String n) {
		categoryName = n;
		rows = new ArrayDeque<Row>();
		setupModel();
	}//End name only constructor.
	
	//Copy style constructor
	public Category(Category toCopy) {
		this.categoryName = toCopy.getCategoryName();
		Row rowsToCopy[] = toCopy.returnRows();
		for(int i = 0; i < rowsToCopy.length; i++)
			this.rows.addLast(rowsToCopy[i]);
		setupModel();
	}//End copy style constructor.
	
	//accessors for variables.
	public String getCategoryName() {
		return categoryName;
	}//End accessor for category name.
	
	//getEstimate returns the sum of the row estimates.
	public BigDecimal getEstimate() {
		BigDecimal sum = new BigDecimal(0.0);
		if(rows.isEmpty() == true) {
			System.out.println("No rows!");
			return new BigDecimal(0.0);
		}
		for(int i = 0; i < rows.size(); i++)
			sum = sum.add(((Row) rows.toArray()[i]).getEstimate());
		return sum;
	}//End accessor for estimate.
	
	//getActual returns the sum of the row actuals.
	public BigDecimal getActual() {
		BigDecimal sum = new BigDecimal(0.0);
		if(rows.isEmpty() == true) {
			System.out.println("No rows!");
			return new BigDecimal(0.0);
		}
		for(int i = 0; i < rows.size(); i++)
			sum = sum.add(((Row) rows.toArray()[i]).getActual());
		return sum;
	}//End getActual.
	
	//getDiff returns the sum of the row differences.
	public BigDecimal getDiff() {
		BigDecimal sum = new BigDecimal(0.0);
		if(rows.isEmpty() == true) {
			System.out.println("No rows!");
			return new BigDecimal(0.0);
		}
		for(int i = 0; i < rows.size(); i++)
			sum = sum.add(((Row) rows.toArray()[i]).getDiff());
		return sum;
	}//End getDiff.
	
	

	//End accessor for estimate.
	
	//Basic mutators, starting out with no data validation
	public void setName(String n) {
		categoryName = n;
	}//End mutator for categoryName.
	
	//getVector returns a vector to go into a table model.
	public Vector<Object> getVector() {
		Vector<Object> data = new Vector<Object>();
		data.add(getCategoryName());
		data.add(NumberFormat.getCurrencyInstance().format(getEstimate()));
		data.add(NumberFormat.getCurrencyInstance().format(getActual()));
		data.add(NumberFormat.getCurrencyInstance().format(getDiff()));
		return data;
	}//end getVector.
	
	//toString will print the name of the Category instead of the memory location.
	public String toString() {
		return getCategoryName();
	}//End toString.
	
	//getModel simply returns the current model for the Category
	public CategoryTableModel getModel() {
		return categoryModel;
	}//End getModel
	
	public void changeRowEstimate(int i, double e)
	{
		getRowIndex(i).setEstimate(e);
	}
	
	public void addRowTransaction(int i, String e) {
		getRowIndex(i).addTransaction(e);
	}
	
	public void addRow(String n)
	{
		rows.addLast(new Row(n,0.0));
		categoryModel.addRow(rows.peekLast().getVector());
		categoryModel.fireTableDataChanged();
	}
	
	public void addRow(Row toLoad) {
		rows.addLast(toLoad);
		categoryModel.addRow(rows.peekLast().getVector());
		categoryModel.fireTableDataChanged();
	}
	
	//setupModel sets up the table model for a category.
	public void setupModel() {
		categoryModel = new CategoryTableModel();
		categoryModel.addColumn("Name");
		categoryModel.addColumn("Estimate");
		categoryModel.addColumn("Actual");
		categoryModel.addColumn("Difference");
		categoryModel.addRow(getVector());
	}//End setupModel
	
	//Find Row
	private Row getRowIndex(int target) {
		return (Row) rows.toArray()[target - 1];
	}//End getCategoryNamed

	//changedData
	public void changedData() {
		setupModel();
		for(int i = 0; i < rows.size(); i++)
			categoryModel.addRow(((Row)rows.toArray()[i]).getVector());
		categoryModel.fireTableDataChanged();
	}//End changedData
	
	public Row[] returnRows() {
		Row toReturn[] = new Row[rows.toArray().length];
		for(int i = 0; i < toReturn.length; i++)
			toReturn[i] = ((Row)rows.toArray()[i]);
		return toReturn;
	}
	
}//End class.
