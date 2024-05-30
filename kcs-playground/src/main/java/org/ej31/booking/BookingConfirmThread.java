package org.ej31.booking;

public class BookingConfirmThread extends Thread{
    private boolean running = true;

    public void run() {
        while (running) {
            try {
                if (!BookingQueue.isEmpty()) {
                    Booking booking = BookingQueue.getNextCancellation();
                    System.out.println("Processing cancellation for dog: " + booking.getDog().getName());
                    booking.getGroomer().setBooked(false); // Reset groomer booking status
                    System.out.println(booking.getDog().getName() + " has been cancelled.");
                } else {
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
