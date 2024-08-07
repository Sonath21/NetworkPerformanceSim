# Network Simulation

The Network Simulation project is a Java-based application designed to simulate the behavior of a network of stations sending packets to a server. The simulation models packet arrival, transmission, and delivery times, while also tracking key metrics such as packet loss, average delay, and network throughput.

## Features
- **Packet Management**: Creation and management of packets with attributes like arrival time, transmission time, and delivery time.
- **Station Behavior**: Simulate station operations including packet queueing, transmission attempts, and handling of buffer overflow.
- **Network Metrics**: Calculate and display overall average delay, network throughput, and packet loss rate.

## Classes

### Packet
- **Attributes**: 
  - `name`: Name of the packet
  - `id`: Unique identifier
  - `arrivalTime`: Time slot when the packet arrived at the station
  - `transmissionTime`: Time slot when the packet started transmitting
  - `deliveryTime`: Time slot when the packet was delivered to the destination
  - `sourceStation`: Source station of the packet
  - `destination`: Destination of the packet

- **Methods**: 
  - Getters and setters for each attribute

### Station
- **Attributes**: 
  - `name`: Name of the station
  - `id`: Unique identifier
  - `buffer`: Queue to hold packets
  - `wavelength`: Wavelength used by the station
  - `totalDelay`: Total delay of all transmitted packets
  - `packetsSent`: Total number of successfully sent packets
  - `packetsLost`: Total number of lost packets due to buffer overflow

- **Methods**:
  - `transmitPackets(int currentTimeSlot)`: Simulates packet transmission with a 50% success probability
  - `addPacket(Packet packet, int arrivalTime)`: Adds a packet to the buffer if there is space, otherwise increments packet loss counter
  - Getters and setters for relevant attributes

### NetworkSimulation
- **Methods**:
  - `main(String[] args)`: Initializes stations, simulates packet arrival and transmission over a specified number of time slots, and calculates network metrics

## Usage
1. Compile the Java files:
   ```bash
   javac NetworkSimulation.java Packet.java Station.java
