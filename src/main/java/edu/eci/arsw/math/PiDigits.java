package edu.eci.arsw.math;

import java.util.ArrayList;
import java.util.List;

public class PiDigits {

    public static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;


    public static byte[] getDigits(int start, int count, int N) throws InterruptedException {
        if (start < 0 || count < 0) {
            throw new RuntimeException("Invalid Interval");
        }

        PiDigitThread[] threads = new PiDigitThread[N];
        int chunkSize = count / N;
        int remainder = count % N;

        for (int i = 0; i < N; i++) {
            int threadStart = start + i * chunkSize;
            int threadCount = chunkSize + (i == N - 1 ? remainder : 0);
            threads[i] = new PiDigitThread(threadStart, threadCount);
            threads[i].start();
        }

        byte[] digits = new byte[count];
        int index = 0;

        for (PiDigitThread thread : threads) {
            thread.join();
            byte[] threadDigits = thread.getDigits();
            System.arraycopy(threadDigits, 0, digits, index, threadDigits.length);
            index += threadDigits.length;
        }

        return digits;
    }

    public static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

    public static class PiDigitsThread extends Thread {
        private final int start;
        private final int count;
        private byte[] result;

        public PiDigitsThread(int start, int count) {
            this.start = start;
            this.count = count;
        }
        public static byte[] getDigits(int start, int count, int N) throws InterruptedException {
            if (start < 0 || count < 0) {
                throw new RuntimeException("Invalid Interval");
            }

            PiDigitThread[] threads = new PiDigitThread[N];
            int chunkSize = count / N;
            int remainder = count % N;

            for (int i = 0; i < N; i++) {
                int threadStart = start + i * chunkSize;
                int threadCount = chunkSize + (i == N - 1 ? remainder : 0);
                threads[i] = new PiDigitThread(threadStart, threadCount);
                threads[i].start();
            }

            byte[] digits = new byte[count];
            int index = 0;

            for (PiDigitThread thread : threads) {
                thread.join();
                byte[] threadDigits = thread.getDigits();
                System.arraycopy(threadDigits, 0, digits, index, threadDigits.length);
                index += threadDigits.length;
            }

            return digits;
        }

        @Override
        public void run() {
            result = new byte[count];
            double sum = 0;

            for (int i = 0; i < count; i++) {
                if ((i + start) % DigitsPerSum == 0) {
                    sum = 4 * sum(1, start + i)
                            - 2 * sum(4, start + i)
                            - sum(5, start + i)
                            - sum(6, start + i);
                }

                sum = 16 * (sum - Math.floor(sum));
                result[i] = (byte) Math.floor(sum); // Redondea correctamente
            }
        }


        public byte[] getResult() {
            return result;
        }
    }
}
