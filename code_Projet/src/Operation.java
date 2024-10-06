import java.time.LocalDate;

public class Operation implements java.io.Serializable   {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DEPOSIT = "DEPOSIT";
    public static final String TRANSFERT = "TRANSFERT";
    public static final String WITHDRAW = "WITHDRAW";
    private String type;
    private  double amount;
    private LocalDate date;
    public Operation(String type, double amount, LocalDate date){
        setType(type);
        setAmount(amount);
        setDate(date);
    }


    public String getType(){return this.type;}
    public double getAmount(){return this.amount;}
    public LocalDate getDate(){return this.date;}

    public void setType(String type){
        if(type !=DEPOSIT && type !=WITHDRAW && type != TRANSFERT){
            System.out.println("Operation type invalid");
            return;
        }
        this.type = type;
    }
    public void setAmount(double Amount){
        if(Amount <0){
            System.out.println("Amount is invalid");
            return;
        }
        this.amount = Amount;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public String toString(){
        return this.date +"\t" +this.type+"\t"+this.amount ;
    }
    public boolean equals(Operation operation){
        return this.type == operation.type && this.amount == operation.amount && this.date == operation.date;
    }




}
