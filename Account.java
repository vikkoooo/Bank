package lunvik8;

/**
 * Account class that defines a Account object that is held by a Customer. Part
 * of D0018D, assignment 1.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @version 1.1 (2021-03-05)
 */

public class Account
{
	/**
	 * Class variable. We want an unique accountId for the whole bank, not just for
	 * the customer.
	 */
	private static int nextAccountId = 1000;

	/**
	 * Instance variables
	 */
	private int accountId;
	private double balance;
	private double rate;
	private String type;

	/**
	 * Constructor
	 */
	public Account()
	{
		nextAccountId++; // Increment class variable nextAccountId
		accountId = nextAccountId; // Then set our new account to that Id
		balance = 0;
		rate = 1;
		type = "Sparkonto";
	}

	/**
	 * Deposit into account
	 * 
	 * @param amount to deposit as double
	 */
	public void deposit(double amount)
	{
		balance += amount;
	}

	/**
	 * Withdrawal from account
	 * 
	 * @param amount to withdrawal as double
	 */
	public void withdrawal(double amount)
	{
		balance -= amount;
	}

	/**
	 * getAccountId
	 * 
	 * @return the accountId
	 */
	public int getAccountId()
	{
		return accountId;
	}

	/**
	 * Check balance
	 * 
	 * @param accountId for the account we want to check
	 * @return the balance
	 */
	public double getBalance(int accountId)
	{
		return balance;
	}

	/**
	 * Calculate interest (SEK)
	 * 
	 * @return the interest as double
	 */
	public double calculateInterest()
	{
		double interest = balance * rate / 100;
		return interest;
	}

	/**
	 * A toString representation of the account including rate as %.
	 * 
	 * @return accountId + balance + type + rate
	 */
	public String toStringWithRate()
	{
		return (accountId + " " + balance + " kr " + type + " " + rate + " %");
	}

	/**
	 * A toString representation of the account without rate.
	 * 
	 * @return accountId + balance + type
	 */
	public String toStringWithoutRate()
	{
		return (accountId + " " + balance + " kr " + type + " ");
	}

}