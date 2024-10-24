class Main implements Runnable {
    public static void main(String[] args) {

        Runnable r1 = new Main();
        Runnable r2 = new Main();

        Thread t1 = new Thread(r1, "task1");
        Thread t2 = new Thread(r2, "task2");

        t1.start();

        t2.start();

        System.out.println("This code is outside of the thread ");

    }

    @Override
    public void run() {
        System.out.println("This code is running in a thread");

    }

}
