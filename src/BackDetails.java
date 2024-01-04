import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BackDetails
{
    // Class attributes
    public String ID;
    public String name;
    public int accbalance;
    public int transactionhist;
    public List<Integer> transactionsList;

    // Constructor to initialize name, ID, and transactionsList
    public BackDetails(String username, String userID) {
        name = username;
        ID = userID;
        transactionsList = new ArrayList<>();
    }

    // Method to update amount and statement once money is deposited
    public void deposit(int amount) {
        if (amount != 0) {
            accbalance = accbalance + amount;
            transactionhist = amount;
            transactionsList.add(transactionhist);
        }
    }

    // Method to update once money is withdrawn
    public void withdraw(int amount) {
        if (amount != 0) {
            accbalance = accbalance - amount;
            transactionhist = -amount;
            transactionsList.add(transactionhist);
        }
    }

    // Method to check previous transactions (deposited and withdrawn), essentially the bank statement
    public void prevTransaction() {
        if (!transactionsList.isEmpty()) {
            System.out.println("Transaction History:");
            for (int transaction : transactionsList) {
                if (transaction > 0) {
                    System.out.println("Deposited: " + transaction);
                } else if (transaction < 0) {
                    System.out.println("Withdrawn: " + Math.abs(transaction));
                }
            }
        } else {
            System.out.println("No transactions occurred");
        }
    }

    // Method to display the menu to the user
    public void Menu() {
        char choice;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            try {
                System.out.println("**************************************************************************************");
                System.out.println("Please choose one of the options");
                System.out.println("**************************************************************************************");
                choice = scanner.next().charAt(0);

                switch (choice) {
                    case '1':
                        displayBalance();
                        break;

                    case '2':
                        depositAmount();
                        break;

                    case '3':
                        withdrawAmount();
                        break;

                    case '4':
                        viewTransactions();
                        break;

                    case '5':
                        System.out.println("Thank you for using Banky Bank");
                        break;

                    default:
                        System.out.println("INVALID OPTION! PLEASE TRY AGAIN");
                        break;
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
                choice = '\0'; // Reset choice to prompt the user again
            }

        } while (choice != '5');

        // Close the scanner to avoid resource leaks
        scanner.close();
    }

    // Helper method to display balance
    private void displayBalance() {
        System.out.println("\n=================================================================================");
        System.out.println("Your balance is = " + accbalance);
        System.out.println("=================================================================================");
    }

    // Helper method to deposit amount
    private void depositAmount() {
        System.out.println("\n=================================================================================");
        System.out.println("Enter deposit amount");
        System.out.println("=================================================================================");
        Scanner scanner = new Scanner(System.in);

        try {
            int amount = scanner.nextInt();
            deposit(amount);
            System.out.println("Deposit successful!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer amount.");
        } finally {
            scanner.nextLine(); // Consume the newline character
        }
    }

    // Helper method to withdraw amount
    private void withdrawAmount() {
        System.out.println("\n=================================================================================");
        System.out.println("Enter withdrawal amount:");
        System.out.println("=================================================================================");
        Scanner scanner = new Scanner(System.in);

        try {
            int amount = scanner.nextInt();
            withdraw(amount);
            System.out.println("Withdrawal successful!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer amount.");
        } finally {
            scanner.nextLine(); // Consume the newline character
        }
    }

    // Helper method to view transactions
    private void viewTransactions() {
        System.out.println("\n=================================================================================");
        prevTransaction();
        System.out.println("=================================================================================");
    }
}