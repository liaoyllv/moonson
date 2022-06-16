package com.jf.moonson.business.order.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        Future<?> submit = executor.submit(() -> {

        });

    }


}
