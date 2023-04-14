import java.util.ArrayList;
import java.util.List;

class Expense {
    private String category;
    private double amount;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


class ExpenseTracker {
    private List<Expense> expenses;
    private double totalExpenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        totalExpenses = 0;
    }

    // Add an expense to the tracker
    public void addExpense(Expense expense) {
        synchronized (this) {
            expenses.add(expense);
            totalExpenses += expense.getAmount();
        }
    }

    // Get total expenses
    public double getTotalExpenses() {
        synchronized (this) {
            return totalExpenses;
        }
    }

    // Generate reports and visualizations
    public void generateReports() {
        // Code for generating reports and visualizations
        System.out.println("Generating reports and visualizations...");
    }
}

public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();

        // Create multiple threads for adding expenses
        Thread thread1 = new Thread(() -> {
            Expense expense1 = new Expense("Food", 50);
            expenseTracker.addExpense(expense1);
        });

        Thread thread2 = new Thread(() -> {
            Expense expense2 = new Expense("Transportation", 30);
            expenseTracker.addExpense(expense2);
        });

        Thread thread3 = new Thread(() -> {
            Expense expense3 = new Expense("Entertainment", 20);
            expenseTracker.addExpense(expense3);
        });

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Generate reports
        expenseTracker.generateReports();

        // Get total expenses
        System.out.println("Total expenses: $" + expenseTracker.getTotalExpenses());
    }
}
