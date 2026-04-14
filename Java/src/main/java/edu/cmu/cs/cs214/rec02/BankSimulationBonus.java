import java.util.ArrayList;

class BankAccount {

    private int balance = 1000;

    private ArrayList<String> log = new ArrayList<String>();

    public synchronized void deposit(int amount) {
        balance += amount;
        String record = "Deposited " + amount + " => Balance: " + balance;
        log.add(record);      
        System.out.println(record);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            String record = "Withdrawn " + amount + " => Balance: " + balance;
            log.add(record);   
            System.out.println(record);
        } else {
            String record = "FAILED: Insufficient funds for " + amount + " (Balance: " + balance + ")";
            log.add(record);
            System.out.println(record);
        }
    }

    public int getBalance() {
        return balance;
    }
    public void printLog() {
        System.out.println("\n=== Guilgeenii burtgel ===");
        for (int i = 0; i < log.size(); i++) {
            System.out.println((i + 1) + ". " + log.get(i));
        }
        System.out.println("Нийт гүйлгээ: " + log.size());
    }
}
class Customer implements Runnable {

    private BankAccount account;
    private String action;
    private int amount;

    public Customer(BankAccount account, String action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    public void run() {
        if (action.equals("deposit")) {
            account.deposit(amount);
        } else if (action.equals("withdraw")) {
            account.withdraw(amount);
        }
    }
}
public class BankSimulationBonus {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== Nemelt: Priority + Guilgeenii temdeglel ===");
        System.out.println("Ehleh balance: 1000");
        System.out.println("------------------------------------------");

        BankAccount account = new BankAccount();

        Thread[] customers = new Thread[3];
        customers[0] = new Thread(new Customer(account, "deposit", 500));
        customers[1] = new Thread(new Customer(account, "withdraw", 700));
        customers[2] = new Thread(new Customer(account, "withdraw", 600));
        customers[0].setPriority(Thread.MAX_PRIORITY);  
        customers[1].setPriority(Thread.NORM_PRIORITY);
        customers[2].setPriority(Thread.MIN_PRIORITY);

        System.out.println("Thread 0 (deposit) priority: " + customers[0].getPriority());
        System.out.println("Thread 1 (withdraw 700) priority: " + customers[1].getPriority());
        System.out.println("Thread 2 (withdraw 600) priority: " + customers[2].getPriority());
        System.out.println("------------------------------------------");

        for (Thread t : customers) {
            t.start();
        }

        for (Thread t : customers) {
            t.join();
        }

        System.out.println("------------------------------------------");
        System.out.println("Final Balance: " + account.getBalance());
        account.printLog();
    }
}
