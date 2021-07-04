package sample;

import sample.Three_Graph_DataController;
import sample.SerialPortService;
import javafx.application.Application;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Three_Graph extends Application {
    private final static int MAX_POTENTIOMETER_VALUE = 1 << 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var controller = new Three_Graph_DataController();
        var serialPort = SerialPortService.getSerialPort("COM7");
        serialPort.addDataListener(controller);

        stage.setTitle("Graph of current moisture values");

        var now = System.currentTimeMillis();


        var xAxis = new NumberAxis("Time", now, now + 50000, 10000);
        var yAxis = new NumberAxis("Current Moisture Value", 0, MAX_POTENTIOMETER_VALUE, 10);

        var series = new XYChart.Series<>(controller.getDataPoints());
        var lineChart = new LineChart<>(xAxis, yAxis, FXCollections.singletonObservableList(series));
        lineChart.setTitle("Moisture Sensor");

        Scene scene = new Scene(lineChart,800,600);

        stage.setScene(scene);
        stage.show();
    }
}
