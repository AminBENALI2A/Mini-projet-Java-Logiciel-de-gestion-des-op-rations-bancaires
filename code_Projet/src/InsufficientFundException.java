

/*Exception thrown if the current account balance is insufficient
*/
public class InsufficientFundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientFundException(String message) {
		super(message);
	}
}
