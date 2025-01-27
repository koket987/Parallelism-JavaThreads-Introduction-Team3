package edu.eci.arsw.math;

public class PiThread extends Thread {
    private int start;
    private int count;
    private byte[] result;

    public PiThread(int start, int count) {
        this.start = start;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            result = PiDigits.getDigits(start, count,1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getResult() {
        return result;
    }
}
