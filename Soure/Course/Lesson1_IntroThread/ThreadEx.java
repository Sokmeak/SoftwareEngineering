class RunnableDemo3 implements Runnable {

    private Thread thread;
    private String threadName;

    // Creating a constructor

    RunnableDemo3(String name) {
        this.threadName = name;

        System.out.println("Creating thread: " + name);
    }

    @Override
    public void run() {
        System.out.println("Running thread: " + threadName);
        try {

            for (int i = 5; i > 0; i--) {
                System.out.println("Thread : " + threadName + ", " + i);
                // delay for 1s
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            System.out.println("Thread : " + threadName + " Interrupted");

        }

        System.out.println("Thread " + threadName + " has finished.");

    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

}

public class ThreadEx {
    static int var = 0;
    public static void main(String[] args) throws Exception {
        // RunnableDemo3 task1 = new RunnableDemo3("Task1");
        // task1.start();
// Creating thread using lamda Expression
        Thread th1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
               // System.out.println(i);
               var++;

            }
        });

        th1.start();
        
        Thread th2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    // System.out.println(i);

                    var++;
                }
            }
        });

        th2.start();

        // try {
        //     th2.join();
        // } catch (Exception e) {
        //     // TODO: handle exception
        // }
        // th1.wait(1000); // pause execution until got notified
        // th1.notify();

        th1.join();
        th2.join();

        System.out.println("Var = "+var);
        System.out.println("All threads have finished");

    }

}
