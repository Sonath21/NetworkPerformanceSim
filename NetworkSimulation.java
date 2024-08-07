import java.util.*;

public class NetworkSimulation {

    public static void main(String[] args) {
        // Αρχικοποίηση σταθμών
        Station[] stations = new Station[8];
        stations[0] = new Station("PC1", 1, "λ1");
        stations[1] = new Station("PC2", 2, "λ1");
        stations[2] = new Station("PC3", 3, "λ2");
        stations[3] = new Station("PC4", 4, "λ2");
        stations[4] = new Station("PC5", 5, "λ3");
        stations[5] = new Station("PC6", 6, "λ3");
        stations[6] = new Station("PC7", 7, "λ4");
        stations[7] = new Station("PC8", 8, "λ4");

        int totalSlots = 500000; // Συνολικός αριθμός χρονοθυρίδων προς προσομοίωση

        // Εισαγωγή χρήστη για την πιθανότητα p
        Scanner scanner = new Scanner(System.in);
        double p;
        while (true) {
            System.out.print("Enter packet arrival probability (0.1 to 1): ");
            p = scanner.nextDouble(); // Read user input

            if (p >= 0.1 && p <= 1 && ((p * 10) % 1 == 0)) {
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
        scanner.close();

            int packetId = 0;
            int totalPacketsCreated = 0;

            for (int slot = 0; slot < totalSlots; slot++) {
                // Για κάθε σταθμό, αν φτάσει ένα νέο πακέτο (με βάση την πιθανότητα p), προσθέστε το στην ουρά.
                for (Station station : stations) {
                    if (Math.random() < p) {
                        totalPacketsCreated++;
                        String packetName = "Packet_" + packetId;
                        int id = packetId++;
                        int arrivalTime = slot; // Η τρέχουσα χρονοθυρίδα (slot) είναι ο χρόνος άφιξης
                        String sourceStation = station.getName(); // Χρησιμοποιήστε το όνομα του σταθμού ως πηγή
                        String destination = "Server"; // Υποθέτοντας ότι όλα τα πακέτα αποστέλλονται σε έναν "Server"
                        Packet newPacket = new Packet(packetName, id, arrivalTime, sourceStation, destination);
                        station.addPacket(newPacket, slot);
                    }

                    // Προσπάθεια μετάδοσης πακέτου αν η ουρά δεν είναι άδεια
                    station.transmitPackets(slot);
                }

            }

        //--------START-A--------------------
        /*for (Station station : stations) {
            int stationPacketsSent = station.getPacketsSent();
            if (stationPacketsSent > 0) {
                double averageDelay = (double) station.getTotalDelay() / stationPacketsSent;
                System.out.println("Average delay for " + station.getName() + ": " + averageDelay);
            } else {
                System.out.println("No packets were sent from " + station.getName());
            }
        }*/

        int grandTotalDelay = 0;
        int grandTotalPacketsSent = 0;
        for (Station station : stations) {
            grandTotalDelay += station.getTotalDelay();
            grandTotalPacketsSent += station.getPacketsSent();
        }
        if (grandTotalPacketsSent > 0) {
            double overallAverageDelay = (double) grandTotalDelay / grandTotalPacketsSent;
            System.out.println("Overall average delay: " + overallAverageDelay);
        } else {
            System.out.println("No packets were sent from any station.");
        }

        //--------END-A--------------------

        //--------START-B----------------

        for (Station station : stations) {
            grandTotalPacketsSent += station.getPacketsSent();
        }

        double throughput = (double) grandTotalPacketsSent / totalSlots;
        System.out.println("Network Throughput: " + throughput + " packets per time slot");

        //---------END-B-------------

        //--------START-C------

        int grandTotalPacketsLost = 0;
        for (Station station : stations) {
            grandTotalPacketsLost += station.getPacketsLost();
        }

        double packetLossRate = (double) grandTotalPacketsLost / totalPacketsCreated;
        System.out.println("Packet Loss Rate: " + packetLossRate);

    }



    }


