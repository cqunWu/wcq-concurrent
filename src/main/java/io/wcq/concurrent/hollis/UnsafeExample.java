package io.wcq.concurrent.Hollis;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeExample {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 使用放射获取Unsafe实例
        Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafeField.get(null);

        // 分配堆外内存，返回内存地址
        long size = 1024; // 内存大小
        long address = unsafe.allocateMemory(size);

        // 写入数据到堆外内存
        String dataToWrite = "Hello, this is Hollis testing direct memory!";
        byte[] dateBytes = dataToWrite.getBytes();
        for (int i = 0; i < dateBytes.length; i++) {
            unsafe.putByte(address + i, dateBytes[i]);
        }

        // 从堆外内存读取数据
        byte[] dateToRead = new byte[dateBytes.length];
        for (int i = 0; i < dateBytes.length; i++) {
            dateToRead[i] = unsafe.getByte(address + i);
        }
        System.out.println(new String(dateBytes));
        System.out.println(new String(dateToRead));

        System.out.println(unsafe.addressSize());
        // 释放堆外内存
        unsafe.freeMemory(address);
        System.out.println(new String(dateBytes));
        System.out.println(new String(dateToRead));
    }
}
