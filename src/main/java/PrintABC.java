public class PrintABC {
    private final Object monitor = new Object();
    private volatile char curChr = 'A';

    public static void main(String[] args) {
        final PrintABC print = new PrintABC();

        Thread prA = new Thread(new Runnable() {
            public void run() {
                print.printA();
            }
        });

        Thread prB = new Thread(new Runnable() {
            public void run() {
                print.printB();
            }
        });

        Thread prC = new Thread(new Runnable() {
            public void run() {
                print.printC();
            }
        });

        prA.start();
        prB.start();
        prC.start();
    }

    public void printA(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (curChr != 'A') {
                        monitor.wait();
                    }
                    System.out.print("A");
                    curChr ='B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (curChr != 'B') {
                        monitor.wait();
                    }
                    System.out.print("B");
                    curChr ='C';
                    monitor.notifyAll();;
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized (monitor){
            try {
                for (int i = 0; i < 5; i++) {
                    while (curChr != 'C') {
                        monitor.wait();
                    }
                    System.out.println("C");
                    curChr ='A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}