# Automated Plant Watering System
### University in-course project

## INTRODUCTION

This project serves the purpose of automatically watering plants when the soil moisture drops below a certain level. The system uses a moisture sensor to detect when the soil moisture value is below a certain threshold and initiates the water pump in order to successfully water the plant.

### COMPONENTS LIST

- **Grove board** - Used to connect a processor such as Arduino to a computer for commands.
- **MOSFET** - an amplifier and is used in order to run the pump.
- **Battery Pack** - Power supply for the MOSFET.
- **Moisture sensor** - Takes air/soil moisture readings.
- **Pump** - Moves water from the water supply to the plant.

# Functionality

This system is capable of watering a plant either manually or autonomously. It is done manually with the help and usage of event-driven programming such as a JavaFx button where a button was created and could be used to initiate the water pump if clicked upon with a cursor. Furthermore, the requirement of watering the plant autonomously was achieved using specific libraries such as jSerialComm. The jSerialComm library establishes communication between Java and Arduino by providing access to a certain serial port through which the Arduino connection was established. This is how the Java program informs the grove board that the soil was dry and as a reaction, the water pump is switched on.

## The Setup
![IMG_6663](https://user-images.githubusercontent.com/84158891/149266572-fcf033c1-925b-4384-aae4-93e9c9f8843c.jpg)
