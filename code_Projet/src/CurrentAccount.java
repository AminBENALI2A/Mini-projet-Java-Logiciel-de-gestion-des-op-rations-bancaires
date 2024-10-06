public class CurrentAccount extends Account{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double creditLimit;
    public CurrentAccount(String name, double balance, double creditLimit){
        super(name, balance);
        this.creditLimit = creditLimit;
    }
    @Override
    public void withdraw(double amount) throws InsufficientFundException {
        if(amount > this.creditLimit) {
            throw new InsufficientFundException("Transaction denied. exceded limit credit.");
        }
        super.withdraw(amount);
    }
	@Override
	public String toString() {
		return super.toString()+ "\t( "+creditLimit+ " authzed)";
	}
}