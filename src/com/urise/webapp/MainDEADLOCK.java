package com.urise.webapp;

public class MainDEADLOCK {
    public static final Object objectForLocking = new Object();
    public static final Object objectForLocking2 = new Object();
    private static int fistCount = 0;
    private static int secondCount = 0;

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        myThread.start();
        myThread1.start();

        System.out.println(fistCount);
        System.out.println(secondCount);
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            method1();
            method2();
        }
    }

    public static void method1() {
        synchronized (objectForLocking) {
            sleeper(100L);
            synchronized (objectForLocking2) {
                counting();
            }
        }
    }

    public static void method2() {
        synchronized (objectForLocking2) {
            sleeper(110L);
            synchronized (objectForLocking) {
                counting();
            }
        }
    }

    private static void counting() {
        fistCount++;
        secondCount++;
    }

    private static void sleeper(Long millis)  {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
