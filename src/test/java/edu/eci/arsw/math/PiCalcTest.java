package edu.eci.arsw.math;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PiCalcTest {

    @Test
    public void testSingleThread() throws InterruptedException {
        byte[] result = PiDigits.getDigits(0, 10, 1);
        String resultHex = Main.bytesToHex(result);
        System.out.println("Resultado (1 hilo): " + resultHex);
        assertEquals("243F6A8885", resultHex); // Verificar con el valor esperado
    }

    @Test
    public void testTwoThreads() throws InterruptedException {
        byte[] result = PiDigits.getDigits(0, 10, 2);
        String resultHex = Main.bytesToHex(result);
        System.out.println("Resultado (2 hilos): " + resultHex);
        assertEquals("243F6A8885", resultHex); // Verificar con el valor esperado
    }

    @Test
    public void testThreeThreads() throws InterruptedException {
        byte[] result = PiDigits.getDigits(0, 10, 3);
        String resultHex = Main.bytesToHex(result);
        System.out.println("Resultado (3 hilos): " + resultHex);
        assertEquals("243F6A8885", resultHex); // Verificar con el valor esperado
    }


}
