package org.ej31.booking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingQueue {
    private static BlockingQueue<Booking> bookingQueue = new LinkedBlockingQueue<>();
    private static BlockingQueue<Booking> cancelQueue = new LinkedBlockingQueue<>();

    public static void addBooking(Booking booking) throws InterruptedException {
        bookingQueue.put(booking);
    }

    public static Booking getNextBooking() throws InterruptedException {
        return bookingQueue.take();
    }

    public static boolean isEmpty() {
        return bookingQueue.isEmpty();
    }

    public static void addCancellation(Booking booking) throws InterruptedException {
        cancelQueue.put(booking);
    }

    public static Booking getNextCancellation() throws InterruptedException {
        return cancelQueue.take();
    }
}