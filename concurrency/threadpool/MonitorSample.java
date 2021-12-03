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

/*
output:
timestamp[1638544231088]T: pool-1-thread-1, active count: 5, pool size: 5, max pool size: 10, task count: 8
timestamp[1638544231088]T: pool-1-thread-3, active count: 5, pool size: 5, max pool size: 10, task count: 8
timestamp[1638544231088]T: pool-1-thread-4, active count: 6, pool size: 6, max pool size: 10, task count: 9
timestamp[1638544231088]T: pool-1-thread-5, active count: 6, pool size: 6, max pool size: 10, task count: 9
timestamp[1638544231089]T: pool-1-thread-6, active count: 7, pool size: 7, max pool size: 10, task count: 10
timestamp[1638544231088]T: pool-1-thread-2, active count: 5, pool size: 5, max pool size: 10, task count: 8
timestamp[1638544231089]T: pool-1-thread-7, active count: 7, pool size: 7, max pool size: 10, task count: 10

timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]T: pool-1-thread-6, active count: 7, pool size: 7, max pool size: 10, task count: 10
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 2, largest pool size: 7
timestamp[1638544236127]T: pool-1-thread-3, active count: 7, pool size: 7, max pool size: 10, task count: 10
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]active count: 7, pool size: 7, max pool size: 10, task count: 10, completed task count: 0, largest pool size: 7
timestamp[1638544236127]T: pool-1-thread-7, active count: 7, pool size: 7, max pool size: 10, task count: 10

timestamp[1638544241132]active count: 3, pool size: 3, max pool size: 10, task count: 10, completed task count: 7, largest pool size: 7
timestamp[1638544241132]active count: 3, pool size: 3, max pool size: 10, task count: 10, completed task count: 7, largest pool size: 7
timestamp[1638544241132]active count: 3, pool size: 3, max pool size: 10, task count: 10, completed task count: 7, largest pool size: 7
*/
