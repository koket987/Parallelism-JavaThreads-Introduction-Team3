/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;


/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{
    private int A,B;

    public CountThread (int A, int B){
        this.A = A;
        this.B = B;
    }

    @Override
    public void run(){
        for (int i = A; i <=B;i++ ){
            System.out.println(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                System.out.println("Hilo interrumpido: " + e.getMessage());
            }
        }
        System.out.println("Hilo terminado.");
    }

    public static void main (String[] args){
        CountThread thread = new CountThread(1,10);
        CountThread thread1 = new CountThread(10,20);
        thread.start();
        thread1.start();
    }
}
