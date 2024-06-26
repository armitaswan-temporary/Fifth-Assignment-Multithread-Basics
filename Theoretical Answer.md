## Question 1
What will be printed after interrupting the thread?
```java
public static class SleepThread extends Thread {
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted!");
        } finally {
            System.out.println("Thread will be finished here!!!");
        }
    }
}

public static void main(String[] args) {
    SleepThread thread = new SleepThread();
    thread.start();
    thread.interrupt();
}
```
### Answer:
```java
Thread was interrupted!
Thread will be finished here!!!
```
first the try part happens then it's intrupted and we have the first sentence and then the last one which happens anyway cause it's in finally part.

## Question 2
In Java, what would be the outcome if the run() method of a Runnable object is invoked directly, without initiating it inside a Thread object?
```java
public class DirectRunnable implements Runnable {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        DirectRunnable runnable = new DirectRunnable();
        runnable.run();
    }
}
```
### Answer:
```java
Running in: Main
```
there is no thread object created so our current thread would be main which is the root thread of all threads and that's the name that'll be printed.

## Question 3
Elaborate on the sequence of events that occur when the join() method of a thread (let's call it Thread_0) is invoked within the Main() method of a Java program.
```java
public class JoinThread extends Thread {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        JoinThread thread = new JoinThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Back to: " + Thread.currentThread().getName());
    }
}
```
### Answer
```java
Running in: Thread_0
Running in: Main
```
thread_0 is created and is running and after it's finished it'll join to the root thread which is main and then our current thread would be main so it'll print back to main.