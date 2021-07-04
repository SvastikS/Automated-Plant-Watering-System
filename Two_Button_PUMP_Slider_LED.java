package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Two_Button_PUMP_Slider_LED extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var sp = SerialPortService.getSerialPort("COM7");
        var outputStream = sp.getOutputStream();

        var pane = new BorderPane();
        var label = new Label();
        var button = new Button();
        var slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(100.0);

        button.setText("Control Pump");

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try{
                outputStream.write(newValue.byteValue());
                label.setText(String.valueOf(newValue));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
        button.setOnMousePressed(value -> {
            try{
                outputStream.write(255);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });

        button.setOnMouseReleased(value -> {
            try{
                outputStream.write(0);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });


        pane.setTop(button);
        pane.setBottom(label);
        pane.setCenter(slider);
        pane.setPadding(new Insets(0, 20, 0, 20));

        var scene = new Scene(pane, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}