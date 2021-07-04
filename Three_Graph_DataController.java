package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Three_Graph_DataController implements SerialPortMessageListenerWithExceptions {
    private static final byte[] DELIMITER = new byte[]{'\n'};
    private final ObservableList<XYChart.Data<Number, Number>> dataPoints;
    private Object ObservableList;

    public Three_Graph_DataController() {

        this.dataPoints = FXCollections.observableArrayList();
    }

    public ObservableList<XYChart.Data<Number, Number>> getDataPoints() {

        return dataPoints;
    }

    @Override
    public int getListeningEvents() {

        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_RECEIVED){
            return ;
        }
        var rawData = serialPortEvent.getReceivedData();
        var convertedData = ByteBuffer.wrap(rawData).getInt();
        var currentTime = System.currentTimeMillis();
        XYChart.Data<Number, Number> dataPoint;
        dataPoint = new XYChart.Data<>(currentTime, convertedData);
        Platform.runLater(() -> this.dataPoints.add(dataPoint));
    }

    @Override
    public void catchException(Exception e) {
        e.printStackTrace();
    }

    @Override
    public byte[] getMessageDelimiter() {
        return DELIMITER;
    }

    @Override
    public boolean delimiterIndicatesEndOfMessage() {
        return true;
    }
}