public class Packet {
    private String name;
    private int id;
    private int arrivalTime;         // Χρονική σχισμή κατά την οποία το πακέτο έφτασε στο σταθμό
    private int transmissionTime;    // Time slot when the packet started transmitting
    private int deliveryTime;        // Χρονική σχισμή κατά την οποία το πακέτο παραδόθηκε στον προορισμό
    private String sourceStation;
    private String destination;

    public Packet(String name, int id, int arrivalTime, String sourceStation, String destination) {
        this.name = name;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.sourceStation = sourceStation;
        this.destination = destination;
        this.transmissionTime = -1;
        this.deliveryTime = -1;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTransmissionTime() {
        return transmissionTime;
    }

    public void setTransmissionTime(int transmissionTime) {
        this.transmissionTime = transmissionTime;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}
