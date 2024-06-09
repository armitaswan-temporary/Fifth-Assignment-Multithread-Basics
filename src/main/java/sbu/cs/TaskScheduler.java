package sbu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskScheduler {
    public static class Task implements Runnable {
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */
        String taskName;
        int processingTime;

        public Task(String taskName, int processingTime) {
            this.taskName = taskName;
            this.processingTime = processingTime;
        }
        /*
            ------------------------- You don't need to modify this part of the code -------------------------
         */

        @Override
        public void run() {
            /*
                Simulate utilizing CPU by sleeping the thread for the specified processingTime
             */
            try {
                System.out.println("Task " + Thread.currentThread().getName() + " started.");
                Thread.sleep((processingTime));
                System.out.println("Task " + Thread.currentThread().getName() + " done.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<String> doTasks(ArrayList<Task> tasks) {
        ArrayList<String> finishedTasks = new ArrayList<>();

        /*
            Create a thread for each given task, And then start them based on which task has the highest priority
            (highest priority belongs to the tasks that take more time to be completed).
            You have to wait for each task to get done and then start the next task.
            Don't forget to add each task's name to the finishedTasks after it's completely finished.
         */
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                /*
                    reverse task 1 and task 2 to get a reverse order
                 */
                return Integer.compare(t2.processingTime, t1.processingTime);
            }
        });

        for (Task task: tasks){
            Thread thread = new Thread(task , task.taskName);
            thread.start();
            finishedTasks.add(task.taskName);
            try {
                thread.join();
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        return finishedTasks;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
