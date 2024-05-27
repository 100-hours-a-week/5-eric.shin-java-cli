package org.ej31.booking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BookingQueue {
    private static BlockingQueue<Booking> queue = new LinkedBlockingQueue<>();

    public static void addBooking(Booking booking) throws InterruptedException {
        queue.put(booking);
    }

    public static Booking getNextBooking() throws InterruptedException {
        return queue.take();
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }
}

