package edu.eci.arsw.math;

public class PiDigitThread extends Thread {
    private int start;
    private int count;
    private byte[] digits;

    public PiDigitThread(int start, int count) {
        this.start = start;
        this.count = count;
        this.digits = new byte[count];
    }

    @Override
    public void run() {
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % PiDigits.DigitsPerSum == 0) {
                sum = 4 * PiDigits.sum(1, start)
                        - 2 * PiDigits.sum(4, start)
                        - PiDigits.sum(5, start)
                        - PiDigits.sum(6, start);

                start += PiDigits.DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }
    }

    public byte[] getDigits() {
        return digits;
    }
}