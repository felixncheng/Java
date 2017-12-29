package com.chengmboy.util;

import java.util.concurrent.*;

/**
 * @author cheng_mboy
 */
public class ThreadUtil {

    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();

    public static ThreadPoolExecutor getThreadPoolExecutor(int threadCount, int blockQueueSize) {
        return new ThreadPoolExecutor(threadCount, threadCount,
                0L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(blockQueueSize),
                THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());
    }
}
