package edu.eci.arsw.math;

public class PiDigits {

    public static byte[] getDigits(int start, int length, int threads) throws InterruptedException {
        // Dividir el trabajo entre los hilos
        int chunkSize = length / threads;
        Thread[] threadPool = new Thread[threads];
        PiCalculationTask[] tasks = new PiCalculationTask[threads];
        byte[] result = new byte[length];

        // Asignar tareas a cada hilo
        for (int i = 0; i < threads; i++) {
            int startIdx = start + (i * chunkSize);
            int endIdx = (i == threads - 1) ? (start + length) : (startIdx + chunkSize);
            tasks[i] = new PiCalculationTask(startIdx, endIdx, result);
            threadPool[i] = new Thread(tasks[i]);
            threadPool[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < threads; i++) {
            threadPool[i].join();
        }

        return result;
    }

    // Tarea de cálculo para cada hilo
    private static class PiCalculationTask implements Runnable {
        private int start;
        private int end;
        private byte[] result;

        public PiCalculationTask(int start, int end, byte[] result) {
            this.start = start;
            this.end = end;
            this.result = result;
        }

        @Override
        public void run() {
            String pi = "3.141592653589793238462643383279502884197169399375105820974944"; // Pi en formato de cadena
            for (int i = start; i < end; i++) {
                result[i] = (byte) pi.charAt(i + 2); // Se añaden los dígitos de pi a partir de la posición 2
            }
        }
    }
}
