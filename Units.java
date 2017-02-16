//Created by following tutorial on http://docs.oracle.com/javafx/2/swing/port-to-javafx.htm
package application;

public class Units {
	String description;
	double value;
	public Units(String description, double val) {
		this.description = description;
		this.value = val;
	}
	@Override
	public String toString() {
		return this.description;
	}
}
