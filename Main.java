package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //Values that will be used in distance section of code 
    private final ObservableList<Units> metricDistances;
    private final ObservableList<Units> usaDistances;
    private final DoubleProperty meters = new SimpleDoubleProperty(1);
    private final ObservableList<Units> metricWeights;
    private final ObservableList<Units> usaWeights;

    public Main() {
        //Create Units objects for metric distances
        metricDistances = FXCollections.observableArrayList(
                new Units("Centimeters", 0.01),
                new Units("Meters", 1.0),
                new Units("Kilometers", 1000.0));
        
        //Create Units objects for U.S. distances to be used in distance
        usaDistances = FXCollections.observableArrayList(
                new Units("Inches", 0.0254),
                new Units("Feet", 0.305),
                new Units("Yards", 0.914),
                new Units("Miles", 1613.0));
        //Create Units objects for Metric weights to be used in weight section
        metricWeights = FXCollections.observableArrayList(
        		new Units("Kilograms", 1000.0),
        		new Units("Grams", 1.0)
        		);
        //Create Units objects for U.S. weights to be used in weight section
        usaWeights = FXCollections.observableArrayList(
        		new Units("Pounds", 453.592),
        		new Units("Ounces", 28.3495)
        		);
        
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        VBox distance = new VBox(
                new Panel(
                        "Metric System", metricDistances, meters),
                new Panel(
                        "U.S. System", usaDistances, meters));
        VBox weight = new VBox(
                new Panel(
                        "Metric System", metricWeights, meters),
                new Panel(
                        "U.S. System", usaWeights, meters));
        //Main GUI Screen
        Accordion accordion = new Accordion();
        //Used to add distance section to acordion
        TitledPane distancePane = new TitledPane("Distance", distance);
      //Used to add weight section to acordion
        TitledPane weightPane = new TitledPane("Weight",weight );
        //Will be used to add temperature pane to acordion
        //Note that tempPane was created in FXML for easier creation
        BorderPane tempPane = FXMLLoader.load(Main.class.getResource("TempPanel.fxml"));
        //Temperature pane that will be added to acordion since acordion MUST have titledPanes ONLY
        TitledPane tempPanelToAdd= new TitledPane("Temperature", tempPane); 
        //Adds all panes to acordion
        accordion.getPanes().add(distancePane);
        accordion.getPanes().add(weightPane);
        accordion.getPanes().add(tempPanelToAdd);
        //Creates main screen
        Scene scene = new Scene(accordion);
        //Gives name to application
        stage.setTitle("Converter");
        //Sets main screen to previously created screen
        stage.setScene(scene);
        stage.show();
    }
}
