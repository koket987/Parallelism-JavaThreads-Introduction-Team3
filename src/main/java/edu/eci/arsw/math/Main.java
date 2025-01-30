package edu.eci.arsw.math;

/**
 *
 * @author hcadavid
 */
public class Main {
    /*
        *En este metodo se realizaran diferentes experimentos con el fin de analizar consumo de CPU y memoria
     */
    public static void main(String a[]) {
        Runtime runtime = Runtime.getRuntime();
        //int numProcessors = runtime.availableProcessors();
        try {
            //bytesToHex(PiDigits.getDigits(0, 1000000, 1));   //Un solo hilo
            //bytesToHex(PiDigits.getDigits(0,1000000,numProcessors)); //Tantos hilos como núcleos de procesamiento
            //bytesToHex(PiDigits.getDigits(0,1000000,2 * numProcessors)); //Tantos hilos como el doble de núcleos de procesamiento
            //bytesToHex(PiDigits.getDigits(0,1000000,200)); // 200 hilos
            bytesToHex(PiDigits.getDigits(0,1000000,500)); // 500 hilos
            //System.exit(0);
        } catch (InterruptedException e) {
            System.err.println("Error during computation: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexChars.length; i += 2) {
            sb.append(hexChars[i + 1]);
        }
        return sb.toString();
    }
}
