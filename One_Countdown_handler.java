package svastiku.ca;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class One_Countdown_handler extends TimerTask implements SerialPortDataListener {
    private byte n;
    private final byte timerDuration;
    private final OutputStream outputStream;

    public One_Basic_Inititation(byte timerDuration, OutputStream outputStream) {
        this.n = timerDuration;
        this.timerDuration = timerDuration;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {

        try {
            if (this.n > 0) {
                this.outputStream.write(this.n);
                this.n -= 1;
            } else {
                this.outputStream.write(-1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getListeningEvents() {
        System.out.println("rx!!");
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

        if (serialPortEvent.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {

            System.out.println("Water the plant!");
            this.n = timerDuration;
        }

    }
}
