package com.risesun.tomato.concurrency.threadpool;

import java.util.concurrent.*;

/**
 * tips: 带监控的线程池
 *
 * @author risesun-lee
 */
public class MonitorableThreadPoolExecutor extends ThreadPoolExecutor {

    public MonitorableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, 5L, TimeUnit.SECONDS, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        log(String.format("timestamp[" + System.currentTimeMillis() + "]T: " + t.getName() + ", active count: %d, pool size: %d, max pool size: %d, task count: %d",
                super.getActiveCount(),         // 当前线程池正在执行任务的活跃线程
                super.getPoolSize(),            // 当前线程池中的线程数目
                super.getMaximumPoolSize(),     // 当前线程池中支持的最大并发执行的线程数目
                super.getTaskCount()           // 当前线程池中: 已完成执行的任务总数 + 正在执行的任务总数 + 等待执行的任务总数(等待队列中)
        ));
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        log(String.format("timestamp[" + System.currentTimeMillis() + "]active count: %d, pool size: %d, max pool size: %d, task count: %d, completed task count: %d, largest pool size: %d",
                super.getActiveCount(),         // 当前线程池正在执行任务的活跃线程
                super.getPoolSize(),            // 当前线程池中的线程数目
                super.getMaximumPoolSize(),     // 当前线程池中支持的最大并发执行的线程数目
                super.getTaskCount(),           // 当前线程池中: 已完成执行的任务总数 + 正在执行的任务总数 + 等待执行的任务总数(等待队列中)
                super.getCompletedTaskCount(),  // 当前线程池完成执行的任务总数
                super.getLargestPoolSize()      // 线程池曾经到达过的最大工作线程数量
        ));
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
