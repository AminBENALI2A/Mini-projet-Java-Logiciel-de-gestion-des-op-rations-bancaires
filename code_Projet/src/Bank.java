import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Bank {
	private String Name;
	private String FILENAME="bank.dta";
	public static ArrayList<Account> accounts = new ArrayList<>();
	Random rand = new Random(System.currentTimeMillis()); 
	private Authorization authorization;
	public Bank(Authorization authorization) {
		this.authorization = authorization;
	}
	
	public Account lookFor(String number) {
		for(Account acc : accounts) {
			if(acc.getNumber().equals(number)) {
				return acc;
			}
		}
		return new Account();
	}
	public boolean start(String name,String pwd){
		if(authorization.authorization(name,pwd)){
			return true;
		}else{
			return false;
		}
	}
	public void simulate() {
		accounts.add(new SavingAccount("Ahmed",rand.nextInt(100000),Math.round(rand.nextDouble()*10) / 100.0));
		accounts.add(new CurrentAccount("Zohayr",rand.nextInt(100000),5000));
		accounts.add(new SavingAccount("khalid",rand.nextInt(100000),Math.round(rand.nextDouble()*10)/ 100.0));
		accounts.add(new CurrentAccount("Houssam",rand.nextInt(100000),6000));
		accounts.add(new SavingAccount("Hanan",rand.nextInt(100000),Math.round(rand.nextDouble()*10)/ 100.0));
		for(Account acc : accounts) {
			long randomDay = LocalDate.of(2000, 01, 01).toEpochDay();
			for(int i=0;i<10;i++) {
				long minDay = randomDay;
			    long maxDay =LocalDate.of(2022, 12, 31).toEpochDay();
			    randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
			    LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
			    int choice = rand.nextInt(3);
			    String type="";
			    if(choice==0) {type = "DEPOSIT";}
			    else if(choice==1) {type = "WITHDRAW";}
			    else {type = "TRANSFERT";}
				acc.operations.add(new Operation(type, Math.round(rand.nextDouble()*100000 * 100.0) / 100.0, randomDate));
		    }
		}
	}
	private static Bank getInstance() {
		return new Bank(simpleAuth);
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getName() {
		return this.Name;
	}
	static Authorization simpleAuth = new Authorization() {
		@Override
		public boolean authorization(String username, String pwd) {
			return (username.equals("admin") && pwd.equals("123"));
		}
	};
	
	private void save (String fileName) throws IOException{
		OutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName));
		try (var oos = new ObjectOutputStream (fos)) {
			for(Account acc : accounts) {
			     oos.writeObject(acc);
		         oos.flush();
			}
			oos.close();
			System.out.println("Object written to file successfully");
		} catch (IOException e) {
            e.printStackTrace();
        }
		}
	
	private ArrayList<Account> load(String fileName) throws IOException, ClassNotFoundException {
		 ArrayList<Account> LoadedAccounts = new ArrayList<>();
		 FileInputStream fis = new FileInputStream(fileName);
         ObjectInputStream ois = new ObjectInputStream(fis);

         while (fis.available() > 0) {
             Account acc = (Account) ois.readObject();
             LoadedAccounts.add(acc);
         }
         ois.close();
         return LoadedAccounts;
		}


	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Bank bank = Bank.getInstance();
		//Creates accounts and make some bank operations
		bank.simulate();
		//SAVE ACCOUNTS
		bank.save(bank.FILENAME);//we overwrite the entire file
		//LOAD ACCOUNTS
		List<Account> accountsLoaded = bank.load(bank.FILENAME);
		accountsLoaded.forEach(
		(c) -> System.out.println(c.bankStatement (LocalDate.MIN))
		);

		AuthFrame frame = new AuthFrame(bank); //Authorization
		frame.setVisible(true);
	}

}
