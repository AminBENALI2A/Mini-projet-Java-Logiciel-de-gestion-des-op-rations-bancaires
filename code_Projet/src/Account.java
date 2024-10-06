import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Account implements java.io.Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number;
    private String name;
    private double balance;
    ArrayList<Operation> operations = new ArrayList<Operation>(); // Create an ArrayList object

    public boolean add(Operation operation) {
        return operations.add(operation);
    }

    public boolean remove(Object o) {
        return operations.remove(o);
    }

    public String bankStatement (LocalDate fromADate){

        String releveBancaire;
        releveBancaire=this.toString()+" \nOperations :\n ";
        for (Operation operation : operations) {
            if(operation.getDate().isAfter(fromADate)) {
                releveBancaire =releveBancaire + "\n" + operation.toString();
            }
        }
        return releveBancaire;
    }


    /**
     * Constructor
     * @param name
     * @param balance
     */
    public Account(String name, double balance) {
        if(name == null) {
            System.out.println("Name :");
            Scanner scanner = new Scanner(System.in);
            this.name  = scanner.nextLine();
            scanner.close();
        }else this.name = name;
        this.balance = balance;
        //Java Unique Number Generator
        //Generates 16 bytes that contain hexadecimal values
        String [] st = java.util.UUID.randomUUID().toString().split("-");
        this.number = st[0].substring(4);
    }
    public Account() {
        this(null,0.0);
    }
    public Account(Account original) {
        //this(original.name, original.balance);
        if(original == null ) {
            System.out.println("Erreur fatale.");
            System.exit(0);
        }
        this.name = new String(original.name);
        this.balance = original.balance;
        //String [] st = java.util.UUID.randomUUID().toString().split("-");
        //this.number = st[0];
    }
    /**
     *
     * @return the unique number of the account
     */
    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance)throws Exception {
        if(balance <0) throw new Exception("The balance must be positive");
        this.balance = balance;
    }

    @Override
    public String toString() {
//		return "IBAN= "+number+"\tName= "+name+"\tBalance= "+
//				new DecimalFormat("0.00").format(balance)+"\n";
        return String.format("\n %s\t %s\tBalance= %.2f ",number,name,balance);
    }
    /**
     * Deposit an amount of money
     * @param amount to  deposit
     */
    public void deposit (final double amount) throws Exception{
        if(amount <0) throw new Exception("The amount to deposit must be positive !");
        this.balance = this.balance + amount;
    }
    /**
     * Withdraw an amount of money
     * @param amount to  withdraw
     */
    public void  withdraw(final double amount) throws InsufficientFundException{
        if(balance < amount) {
            throw new InsufficientFundException("Transaction denied. Insufficient funds.");
        }
        //if(balance < amount) throw new InsufficientFundException(this, amount);
        this.balance -= amount;

    }
    /**
     * Transfer an amount to a destination account
     * @param amount to  transfer
     * @throws InsufficientFundException
     */
    public void transfer(double amount, Account other) {
        try {
            this.withdraw(amount);
        } catch (InsufficientFundException e) {
            System.out.println(e);
        }
        try {
            other.deposit(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof Account ) {
            Account account = (Account)obj;
            return (this.number == account.number) && (this.name.equals(account.name));
        }
        else return false;
    }

}
