package org.dgutstu.dgutshop.core.task;

import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:25 2020/12/19
 */
@Component
public class TaskService {
    private TaskService taskService;
    private DelayQueue<Task> delayQueue = new DelayQueue<>();

    private void init(){
        taskService = this;

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Task task = delayQueue.take();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void addTask(Task task){
        if (delayQueue.contains(task)){
            return;
        }
        delayQueue.add(task);
    }

    public void removeTask(Task task){
        delayQueue.remove(task);
    }
}
