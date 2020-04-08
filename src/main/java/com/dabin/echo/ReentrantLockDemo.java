package com.dabin.echo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:ReentrantLockDemo
 * @author: dabin
 * @date: 2020/4/88:53
 */
public class ReentrantLockDemo {
    ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }
}
