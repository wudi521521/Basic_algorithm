package com.thread.advance.chapter1;

import java.util.Random;

/**
 * @author Dillon Wu
 * @Description: 写线程
 * @date 2021/8/23 11:37
 */
public class WriteWork extends Thread {
    private static final Random random = new Random(System.currentTimeMillis());
    /**
     * 共享数据
     */
    private final ShareData data;

    /**
     * 文件
     */
    private final String filer;

    private int index = 0;

    public WriteWork(ShareData data, String filer) {
        this.data = data;
        this.filer = filer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据
     * @return
     */
    private char nextChar() {
        char c = filer.charAt(index);
        index++;
        if (index >= filer.length()) {
            index = 0;
        }
        return c;
    }
}
