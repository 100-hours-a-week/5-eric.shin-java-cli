package org.ej31.booking;

public class BookingManagerThread extends Thread {
    private boolean running = true;
    private boolean printedNoBookingsMessage = false;

    public void run() {
        while (running) {
            try {
                if (!BookingQueue.isEmpty()) {
                    Booking booking = BookingQueue.getNextBooking();
                    System.out.println("Processing booking for dog: " + booking.getDog().getName());
                    // Simulate time taken for grooming process
                    Thread.sleep(2000); // 2 seconds per booking for demo purposes
                    System.out.println("Finished booking for dog: " + booking.getDog().getName());
                    printedNoBookingsMessage = false; // Reset the flag after processing a booking
                } else {
                    if (!printedNoBookingsMessage) {
                        System.out.println("No bookings in queue. Waiting for new bookings...");
                        printedNoBookingsMessage = true; // Set the flag to true after printing the message
                    }
                    Thread.sleep(1000); // Wait before checking the queue again
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRunning() {
        running = false;
    }
}


