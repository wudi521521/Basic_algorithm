package com.concurrent.phase.thread.advance.chapter6;

import java.io.FileWriter;
import java.io.Writer;

/**
 * @author Dillon Wu
 * @Description:
 * @date 2021/8/24 9:26
 */
public class BalkingData {

    private final String fileName;

    private String content;

    private boolean changed;

    public BalkingData(String fileName,String content){
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    public synchronized void change(String newContent){
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save(){
        if (!changed){
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave(){
        System.out.println(Thread.currentThread().getName()+" calls do save,content");
        try {
            Writer writer = new FileWriter(fileName,true);
            writer.write(content);
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
