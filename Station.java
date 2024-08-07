import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Station {
    private static final int MAX_BUFFER_SIZE = 5; // Μέγιστο μέγεθος buffer
    private String name;
    private int id;
    private Queue<Packet> buffer;  // Ουρά για την κράτηση πακέτων
    private Random random;  // Γεννήτρια τυχαίων αριθμών για την πιθανότητα μετάδοσης
    private String wavelength; // Το μήκος κύματος που χρησιμοποιεί αυτός ο σταθμός
    private int totalDelay; // Συνολική καθυστέρηση όλων των μεταδιδόμενων πακέτων
    private int packetsSent; // Συνολικός αριθμός πακέτων που εστάλησαν με επιτυχία
    private int packetsLost; // Συνολικός αριθμός απορριφθέντων πακέτων

    public Station(String name, int id, String wavelength) {
        this.name = name;
        this.id = id;
        this.wavelength = wavelength;
        this.buffer = new LinkedList<>();
        this.random = new Random();
        this.totalDelay = 0;
        this.packetsSent = 0;
        this.packetsLost=0;
    }

    /**
     * Προσομοιώνει τη μετάδοση πακέτων από ένα buffer με πιθανότητα 50%.
     * Για κάθε κλήση, η μέθοδος ελέγχει αν ο buffer δεν είναι άδειος. Εάν υπάρχει ένα πακέτο,
     * έχει 50% πιθανότητα να μεταδοθεί στην τρέχουσα time slot. Εάν το πακέτο
     * μεταδίδεται, ο χρόνος μετάδοσής του ορίζεται στην τρέχουσα χρονοθυρίδα και η παράδοσή του
     * ορίζεται στην επόμενη χρονοθυρίδα. Στη συνέχεια, το πακέτο αφαιρείται από το buffer. Ο
     * συνολικός αριθμός των πακέτων που έχουν αποσταλεί και η συνολική καθυστέρηση ενημερώνονται αναλόγως. Εάν το
     * πακέτο δε μεταδοθεί, παραμένει στο buffer για πιθανή μετάδοση σε μια
     * µελλοντική χρονοθυρίδα.
     *
     * @param currentTimeSlot Η τρέχουσα χρονοθυρίδα στην οποία επιχειρείται η μετάδοση.
     */
    public void transmitPackets(int currentTimeSlot) {
        if (!buffer.isEmpty()) {
            Packet packet = buffer.peek();
            if (random.nextDouble() < 0.5) {
                packet.setTransmissionTime(currentTimeSlot);
                packet.setDeliveryTime(currentTimeSlot + 1);
                //System.out.println("Transmitting packet: " + packet.getName());
                buffer.poll();  // Αφαίρεση του πακέτου από την ουρά μετά την επιτυχή μετάδοση
                packetsSent++;
                totalDelay += (currentTimeSlot - packet.getArrivalTime()); // Αύξηση της συνολικής καθυστέρησης
            } else {
                //System.out.println("Failed to transmit packet: " + packet.getName());
                //System.out.println("Transmission collision for packet: " + buffer.peek().getName());
            }
        }
    }

    /**
     * Προσθέτει ένα πακέτο στο buffer αν υπάρχει διαθέσιμος χώρος.
     * Όταν φτάνει ένα πακέτο, αυτή η μέθοδος ελέγχει αν ο buffer δεν έχει φτάσει στο μέγιστο όριο
     * χωρητικότητας. Αν υπάρχει διαθέσιμος χώρος, ορίζεται ο χρόνος άφιξης του πακέτου και το
     * πακέτο προστίθεται στο buffer. Εάν ο buffer είναι γεμάτος, το πακέτο δεν προστίθεται και
     * θεωρείται χαμένο. Ο αριθμός των χαμένων πακέτων αυξάνεται σε αυτή την περίπτωση.
     *
     * @param packet Το πακέτο που πρέπει να προστεθεί στο buffer.
     * @param arrivalTime Ο χρόνος άφιξης του πακέτου.
     */
    public void addPacket(Packet packet, int arrivalTime) {
        if (buffer.size() < MAX_BUFFER_SIZE) {
            packet.setArrivalTime(arrivalTime);
            buffer.offer(packet);
            //System.out.println("Added packet to buffer: " + packet.getName());
        } else {
            packetsLost++;
            //System.out.println("Buffer is full. Dropping packet: " + packet.getName());
        }
    }

    // Getters and Setters
    public int getTotalDelay() {
        return totalDelay;
    }

    public int getPacketsSent() {
        return packetsSent;
    }

    public int getPacketsLost() {
        return packetsLost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWavelength() {
        return wavelength;
    }

    public void setWavelength(String wavelength) {
        this.wavelength=wavelength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
