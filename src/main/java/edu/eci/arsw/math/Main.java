package edu.eci.arsw.math;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1 Hilo:");
        System.out.println(bytesToHex(PiDigits.getDigits(0, 100, 1)));

        System.out.println("\n2 Hilos:");
        System.out.println(bytesToHex(PiDigits.getDigits(0, 100, 2)));

        System.out.println("\n4 Hilos:");
        System.out.println(bytesToHex(PiDigits.getDigits(0, 100, 4)));

        // Prueba de largo cálculo
        System.out.println("\nCalculando 10,000 dígitos con 4 hilos...");
        byte[] result = PiDigits.getDigits(0, 10000, 4);
        System.out.println("Cálculo terminado.");
        System.out.println(bytesToHex(result));
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
