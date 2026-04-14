class BankAccount {
    private int balance = 1000;

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println("Mungu hiilee: " + amount + ", Үлдэгдэл: " + balance);
    }
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Mungu avlaa: " + amount + ", Үлдэгдэл: " + balance);
        } else {
            System.out.println("Mungu hureltsehgui baina: " + amount);
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class BankSimulation {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread[] customers = new Thread[3];

        customers[0] = new Thread(new Customer(account, "deposit", 500));
        customers[1] = new Thread(new Customer(account, "withdraw", 700));
        customers[2] = new Thread(new Customer(account, "withdraw", 600));
        for (Thread t : customers) {
            t.start();
        }
        for (Thread t : customers) {
            t.join();
        }

        System.out.println("Etssiin uldegdel: " + account.getBalance()); 
    }
}