class RunnableDemo implements Runnable {

    // There are two ways to run thread
    // 1. implement Runnable class
    // 2. extends Thread class

    // In Java, business logic is the part of a program that implements the rules
    // that govern how data is created, modified, and presented to users
    private Thread t;
    private String threadName;

    // Constructor
    RunnableDemo(String name) {
        this.threadName = name;

        System.out.println("Creating thread: " + threadName);

    }

    @Override
    public void run() {

        System.out.println("Running thread: " + threadName);

        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");

        }

        System.out.println("Thread " + threadName + " has finished");

    }

    public void start() {
        System.out.println("Starting thread " + threadName);

        // check if thread is null
        if (t == null) {
            t = new Thread(this, threadName);
            // call itsetf back.
            t.start();
        }

    }

}

// Creating Thread by extend class Thread

class RunnableDemo2 extends Thread {

    private Thread t;
    private String threadName;

    RunnableDemo2(String name) {
        this.threadName = name;

        System.out.println("Creating thread: " + threadName);

    }

    @Override
    public void run() {

        System.out.println("Running thread: " + threadName);

        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");

        }

        System.out.println("Thread " + threadName + " has finished");

    }

    public void start() {
        System.out.println("Starting thread " + threadName);

        // check if thread is null
        if (t == null) {
            t = new Thread(this, threadName);
            // call itsetf back.
            t.start();
        }

    }   

}

public class TestThread {

    public static void main(String[] args) {

        // RunnableDemo task1 = new RunnableDemo("Task-1");

        // task1.start();

        // RunnableDemo task2 = new RunnableDemo("Task-2");
        // task2.start();
        RunnableDemo2 task1 = new RunnableDemo2("Task-1");

        task1.start();

        RunnableDemo2 task2 = new RunnableDemo2("Task-2");
        task2.start();
    }

}
