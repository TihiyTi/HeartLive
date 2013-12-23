package com.tihiy.rclint.mvcAbstract;

import java.util.concurrent.BlockingQueue;

public interface DynamicModelInterface<T extends Number> {
    public void setQueue(BlockingQueue<T> queue);
}
