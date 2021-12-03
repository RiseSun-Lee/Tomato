package com.risesun.tomato.concurrency.threadpool;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * tips: 监控线程池的执行示例
 *
 * @author risesun-lee
 */
public class MonitorSample {
    /**
     * 定义线程池
     */
    private static final MonitorableThreadPoolExecutor executor = new MonitorableThreadPoolExecutor(5, 10, new LinkedBlockingQueue<>(3));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
