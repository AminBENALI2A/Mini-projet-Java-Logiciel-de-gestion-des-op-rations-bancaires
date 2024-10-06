public class SavingAccount extends Account{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double rate;
    public SavingAccount(String name, double balance, double rate){
        super(name,balance);
        this.rate = rate;
    }

    @Override
    public void deposit(double amount) throws Exception {
        super.deposit(amount + amount*rate);
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"\t(rate = "+(rate*100)+"%)";
	}
}
