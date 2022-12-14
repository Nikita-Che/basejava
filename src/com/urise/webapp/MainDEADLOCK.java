package com.urise.webapp;

public class MainDEADLOCK {
    public static final Object objectForLocking = new Object();
    public static final Object objectForLocking2 = new Object();
    private static int fistCount = 0;
    private static int secondCount = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                method1();
                method2();
            });
            thread.start();
            Thread thread1 = new Thread(() -> {
                method2();
                method1();
            });
            thread1.start();

            Thread thread2 = new Thread(() -> {
                method2();
                method1();
            });
            thread2.start();
        }

        System.out.println(fistCount);
        System.out.println(secondCount);
    }

    public static void method1() {
        synchronized (objectForLocking) {
            fistCount++;
            synchronized (objectForLocking2) {
                secondCount++;
            }
        }
    }

    public static void method2() {
        synchronized (objectForLocking2) {
            fistCount++;
            synchronized (objectForLocking) {
                secondCount++;
            }
        }
    }
}
