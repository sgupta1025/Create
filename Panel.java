//Modified from http://docs.oracle.com/javafx/2/swing/port-to-javafx.htm tutorial
package application;

import javafx.scene.control.TextField;
import java.text.NumberFormat;
import java.text.ParseException;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class Panel extends TitledPane {
	//List of units to be used
	private ComboBox<Units> listOfStuff;
	//User text field
	private TextField textField;
	//Used for easier division with GUI
	private DoubleProperty meters;
	//Used to format responses
	private NumberFormat numberFormat;
	//Sets user text field going from metric unit
	private InvalidationListener fromMeters = t -> {
        if (!textField.isFocused()) {
            textField.setText(numberFormat.format(meters.get() / getMultiplier()));
        } 
    };

  //Sets user text field going to metric unit
    private InvalidationListener toMeters = t -> {
        if (!textField.isFocused()) {
            return;
        }
        try {
            meters.set(numberFormat.parse(textField.getText()).doubleValue() * getMultiplier());
            } catch (ParseException | Error | RuntimeException ignored) {
            }
    };
    //Constructor
    public Panel(String title, ObservableList<Units> units, DoubleProperty meters) {
    	numberFormat = NumberFormat.getNumberInstance();
    	numberFormat.setMaximumFractionDigits(3);
    	textField = new TextField();
    	listOfStuff = new ComboBox<Units>(units);
        listOfStuff.setConverter(new StringConverter<Units>() {                      
                            
            @Override
            public String toString(Units t) {
                return t.description;
            }

            @Override
            public Units fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        //Used for alignment of objects
        HBox hbox = new HBox(textField, listOfStuff);
        setContent(hbox);
        this.meters = meters;
        
        listOfStuff.getSelectionModel().select(0);
        //Used to determine when value needs to be changed
        meters.addListener(fromMeters);
        //Used to determine when value needs to be changed
        listOfStuff.valueProperty().addListener(fromMeters);
        textField.textProperty().addListener(toMeters);
        fromMeters.invalidated(null);
    }
	private double getMultiplier() {
		return 	listOfStuff.getValue().value;
	}
}
