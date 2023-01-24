package com.urise.webapp.util;

public class LazySingleton {
    private static volatile LazySingleton INSTANCE;
//    int i;
//    double sin = Math.sin(12.2);

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
}
