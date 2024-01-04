package com.yhdyy.ybserver.utils;

import java.util.concurrent.atomic.AtomicLong;

public class SerialGenerator {
    private static final AtomicLong counter = new AtomicLong(0);

    /**
     * 递增序列号生成，固定六位，超过从0重新计数
     * 需和时间拼接保证唯一性
     */
    public static String getSerialNum() {
        if (counter.get() >= 999999) {
            counter.set(0);
        }
        return String.format("%06d", counter.incrementAndGet());
    }
}
