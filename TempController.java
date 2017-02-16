package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TempController implements Initializable {
	//Convert Buttons
	@FXML
	Button convert1;
	@FXML
	Button convert2;
	//List of Temperature Scales to be used
	ObservableList<TempScale> scales = FXCollections.observableArrayList(TempScale.CELSIUS,TempScale.FAHRENHEIT,TempScale.KELVIN);
	//GUI's lists of temperature scales for user to choose from
	@FXML
	ComboBox<TempScale> box1;
	@FXML
	ComboBox<TempScale> box2;
	//User input fields
	@FXML
	TextField text1;
	@FXML
	TextField text2;
	
	//Required even if not used to initialize variables
	public  TempController() {
		
		
	}
	//Required by JavaFX to load the FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		box1.getItems().addAll(scales);
		box2.getItems().addAll(scales);
	}
	@FXML
	//Activates when convert button is pressed
	private void convertTemp(ActionEvent event) {
		if (event.getSource().equals(convert1)) {
			text2.setText(tempConvert(box1.getValue(), box2.getValue(),text1).toString());
		}
		else {
			text1.setText(tempConvert(box2.getValue(), box1.getValue(), text2).toString());
		}
	}
	//Helper method to be used when converting temperatures
	private Double tempConvert(TempScale source, TempScale destination, TextField sourceText) {
		//Figure out what value the user has put into the textfield
		double val = Double.parseDouble(sourceText.getText());
		//Determine how to convert the value the user entered
		switch (source) {
		//If going from celsius
		case CELSIUS:
			//Determines where to go from celsius
			switch (destination) {
			//Going to fahrenheit
			case FAHRENHEIT:
				val = celsiusToFahrenheit(val);
				break;
			//Going to kelvin for all the science people
			case KELVIN:
				val = celsuisToKelvin(val);
				break;
			//If not fahrenheit or kelvin exit switch statement 
			default:
				break;
			}
			break;
		//If going from fahrenheit
		case FAHRENHEIT:
			//Determines where to go from fahrenheit
			switch (destination) {
			//Going to Celsius
			case CELSIUS:
				val = fahrenheitToCelsius(val);
				break;
			//Going to Kelvin
			case KELVIN:
				val = fahrenheitToKelvin(val);
				break;
			//If not Kelvin or Celsius
			default:
				break;
			}
			break;
		//If going from Kelvin
		case KELVIN:
			switch (destination) {
			//Going to Celsius from Kelvin
			case CELSIUS:
				val = kelvinToCelsius(val);
				break;
			//Going to Fahrenheit from Kelvin
			case FAHRENHEIT:
				val = kelvinToFahrenheit(val);
				break;
			//If not Fahrenheit or Celsius
			default:
				break;
			}
			break;
		default:
			break;
		}
		//END OF SWITCH STATEMENTS
		//Returns converted value
		return val;
	}

	//CONVERSION METHODS
	private double celsiusToFahrenheit(double valToConvert) {
		return ((valToConvert * 9) / 5) + 32;
	}
	private double celsuisToKelvin(double valToConvert) {
		return valToConvert + 273;
	}
	private double fahrenheitToCelsius(double valToConvert) {
		return ((valToConvert - 32) * 5) / 9;
	}
	private double fahrenheitToKelvin(double valToConvert) {
		double convertedValue = 0.0;
		convertedValue = fahrenheitToCelsius(valToConvert);
		convertedValue = celsuisToKelvin(convertedValue);
		return convertedValue;
	}
	private double kelvinToCelsius(double valToConvert) {
		return valToConvert - 273;
	}
	private double kelvinToFahrenheit(double valToConvert) {
		double convertedValue = 0.0;
		convertedValue = kelvinToCelsius(valToConvert);
		convertedValue = celsiusToFahrenheit(convertedValue);
		return convertedValue;
	}

}
