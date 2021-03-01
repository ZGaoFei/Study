package com.zgf.javalib.singleinstance;

public class SingleInstanceMain {

    public static void main(String[] args) {
        SingleInstance01 instance01 = SingleInstance01.getInstance();

        SingleInstance02 instance02 = SingleInstance02.getInstance();

        SingleInstance03 instance03 = SingleInstance03.getInstance();

        SingleInstance04 instance04 = SingleInstance04.INSTANCE;
    }
}

// 懒汉式，时间换空间
class SingleInstance01 {
    private volatile static SingleInstance01 instance = null;

    private SingleInstance01() {
    }

    public static SingleInstance01 getInstance() {
        if (instance == null) {
            synchronized (SingleInstance01.class) {
                if (instance == null) {
                    instance = new SingleInstance01();
                }
            }
        }
        return instance;
    }
}

// 饿汉试，空间换时间
class SingleInstance02 {
    private static SingleInstance02 instance = new SingleInstance02();

    private SingleInstance02() {
    }

    public static SingleInstance02 getInstance() {
        return instance;
    }
}

/**
 * 静态内部类实现
 *
 * 当getInstance方法第一次被调用的时候,它第一次读取SingletonHolder.instance，
 * 导致SingletonHolder类得到初始化；
 * 而这个类在装载并被初始化的时候，会初始化它的静态域，
 * 从而创建Singleton的实例，由于是静态的域，
 * 因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
 */
class SingleInstance03 {

    private static class SingleHolder {
        // 静态初始化器，由JVM来保证线程安全
        private static SingleInstance03 instance = new SingleInstance03();
    }

    private SingleInstance03() {
    }

    public static SingleInstance03 getInstance() {
        return SingleHolder.instance;
    }
}

// 使用枚举类来实现
enum SingleInstance04 {
    INSTANCE;
}
