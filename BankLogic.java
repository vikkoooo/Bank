package lunvik8;

import java.util.ArrayList;

/**
 * Bank utility methods. Part of D0018D, assignment 1.
 * 
 * @author Viktor Lundberg, lunvik-8
 * @since 2021-05-xx
 */

public class BankLogic {

	/**
	 * ArrayList of type Customer to keep track of customers. Customers are created
	 * through createCustomer method and stored here.
	 */
	private ArrayList<Customer> myCustomerArrayList = new ArrayList<Customer>();

	/**
	 * Creates a Customer object and stores in myCustomerArrayList. Checks whether
	 * the customer is already active.
	 * 
	 * @param name    (First name)
	 * @param surname (Last name)
	 * @param pNo     (Personal number, yymmddxxxx)
	 * @return true if customer was created. false if customer was already active
	 */
	public boolean createCustomer(String name, String surname, String pNo) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// If it does, customer already exists. Return false end exit.
				return false;
			}
		}
		// If we reach this point, customer does not exist in our list yet.
		// Create a new Customer of type Customer with information from method input.
		Customer newCustomer = new Customer(name, surname, pNo);
		// Add to the list of Customers at next position.
		myCustomerArrayList.add(newCustomer);
		// Customer successfully added. Return true and exit.
		return true;
	}

	/**
	 * It gets information about all our current customers in our bank.
	 * 
	 * @return ArrayList of type String with one customer per element.
	 */
	public ArrayList<String> getAllCustomers() {
		// Create temporary list that we later return.
		ArrayList<String> tmpCustomerArr = new ArrayList<String>();
		// Loops through all elements in myCustomerArrayList (i.e, all customers).
		for (Customer var : myCustomerArrayList) {
			// For every customer, use toString method on them and store in our temporary
			// list.
			tmpCustomerArr.add(var.toStringCustomer());
		}
		// Return the list.
		return tmpCustomerArr;
	}

	/**
	 * Searches for a customer in list and returns information about the customer
	 * including it's accounts. This is reached through the list of accounts
	 * associated with the customer.
	 * 
	 * @param pNo of the person we want to retrieve information about.
	 * @return ArrayList of String type or null if no customer is found.
	 */
	public ArrayList<String> getCustomer(String pNo) {
		// Create a temporary list.
		ArrayList<String> tmpCustomerArr = new ArrayList<String>();
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person. Use toString method
				// on the person and add to our temporary list that we return.
				// Add information about customer on first slot.
				tmpCustomerArr.add(tmpCustomer.toStringCustomer());
				// Continue with information about the accounts on following slots.
				// We must loop through the account list to fetch information about each slot
				for (int j = 0; j < tmpCustomer.accountsArrayList.size(); j++) {
					// Add to the list that we return later
					tmpCustomerArr.add(tmpCustomer.accountsArrayList.get(j).toStringWithRate());
				}
				// Return the list.
				return tmpCustomerArr;
			}
		}
		// If no customer is found, return null.
		return null;
	}

	/**
	 * Searches for a customer personal number, pNo. Changes name of the customer if
	 * the customer is found. Input cannot be empty string.
	 * 
	 * @param name    (First name)
	 * @param surname (Last name)
	 * @param pNo     (Personal number, yymmddxxxx)
	 * @return true, if name was successfully changed. false if customer not found.
	 */
	public boolean changeCustomerName(String name, String surname, String pNo) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Changes name of the person, as long as the name input was not empty.
				if (!name.isEmpty()) {
					tmpCustomer.setName(name);
				}
				// Changes the surname of the person, as long as the name input was not empty.
				if (!surname.isEmpty()) {
					tmpCustomer.setSurname(surname);
				}
				// We found the person, so return true.
				return true;
			}
		}
		// We didn't find the person, return false.
		return false;
	}

	/**
	 * Creates a new instance of Account (i.e, creates an account for the person).
	 * We are using pNo to find the correct person to create the account for. The
	 * account is added to the customers own personal accountsArrayList.
	 * 
	 * @param pNo (Personal number)
	 * @return AccountId of the new account that was created, or -1 if no account
	 *         was created.
	 */
	public int createSavingsAccount(String pNo) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Create new instance of Account.
				Account tmpAcc = new Account();
				// Add our freshly created account to customers own personal accountsArrayList.
				tmpCustomer.accountsArrayList.add(tmpAcc);
				// Get account number for the newly created account and return it.
				return (tmpAcc.getAccountId());
			}
		}
		// If we didn't find the customer and no account was created, return -1.
		return -1;
	}

	/**
	 * Searches for a specific account for a specific customer and returns
	 * information about the account.
	 * 
	 * @param pNo       (Personal number)
	 * @param accountId (Account Id)
	 * @return String with information about account if found. Returns null if no
	 *         account was found
	 */
	public String getAccount(String pNo, int accountId) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Loop through all accounts in the array associated with the current person.
				for (int j = 0; j < tmpCustomer.accountsArrayList.size(); j++) {
					// Check whether the current accountId equals the one we are searching for.
					int currentAccountId = tmpCustomer.accountsArrayList.get(j).getAccountId();
					if (currentAccountId == accountId) {
						// If it does, return a toString version of the account.
						return tmpCustomer.accountsArrayList.get(j).toStringWithRate();
					}
				}
			}
		}
		// If we didn't find the customer or account, return null.
		return null;
	}

	/**
	 * Deposits an amount into a customers account
	 * 
	 * @param pNo       (Personal number)
	 * @param accountId (Account id we want to deposit into)
	 * @param amount    (Amount to deposit)
	 * @return True if successful, false if account not found.
	 */
	public boolean deposit(String pNo, int accountId, double amount) {
		if (amount > 0) {
			// Loop through all elements in myCustomerArrayList (i.e, check all customers).
			for (int i = 0; i < myCustomerArrayList.size(); i++) {
				// Store every customer in temporary variable of Customer type.
				Customer tmpCustomer = myCustomerArrayList.get(i);
				// Check whether pNo from method input equals pNo in Customer object.
				if (tmpCustomer.getpNo().equals(pNo)) {
					// Now we have found the correct person.
					// Loop through all accounts in the array associated with the current person.
					for (int j = 0; j < tmpCustomer.accountsArrayList.size(); j++) {
						// Check whether the current accountId equals the one we are searching for.
						int currentAccountId = tmpCustomer.accountsArrayList.get(j).getAccountId();
						if (currentAccountId == accountId) {
							// If it does, we found the correct account.
							// Deposit into the account and return true.
							tmpCustomer.accountsArrayList.get(j).deposit(amount);
							return true;
						}
					}
				}
			}
		}
		// If we didn't find it, return false
		return false;
	}

	/**
	 * Makes a withdrawal from an account, if there is enough money on the account.
	 * 
	 * @param pNo       (Personal number)
	 * @param accountId (Account Id of the account to withdrawal from)
	 * @param amount    (Amount to withdrawal)
	 * @return True if successful, false if account not found or not enough money on
	 *         the account.
	 */
	public boolean withdraw(String pNo, int accountId, double amount) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Loop through all accounts in the array associated with the current person.
				for (int j = 0; j < tmpCustomer.accountsArrayList.size(); j++) {
					// Check whether the current accountId equals the one we are searching for.
					int currentAccountId = tmpCustomer.accountsArrayList.get(j).getAccountId();
					if (currentAccountId == accountId) {
						// If it does, we found the correct account.
						// Withdrawal from the account. But only if there is enough money.
						if (tmpCustomer.accountsArrayList.get(j).getBalance(currentAccountId) >= amount
								&& amount >= 0) {
							tmpCustomer.accountsArrayList.get(j).withdrawal(amount);
							// Withdrawal was successful, return true.
							return true;
						}
					}
				}
			}
		}
		// If we didn't find it, return false.
		return false;
	}

	/**
	 * Closes a specific account owned by the customer. It calculates and shows rate
	 * when closing the account.
	 * 
	 * @param pNo       (Personal number)
	 * @param accountId (Account Id we want to close)
	 * @return Information about the account including calculated rate.
	 */
	public String closeAccount(String pNo, int accountId) {
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Loop through all accounts in the array associated with the current person.
				for (int j = 0; j < tmpCustomer.accountsArrayList.size(); j++) {
					// Check whether the current accountId equals the one we are searching for.
					int currentAccountId = tmpCustomer.accountsArrayList.get(j).getAccountId();
					if (currentAccountId == accountId) {
						// If it does, we found the correct account.
						// Add information about the account + the calculated interest as String
						String s = tmpCustomer.accountsArrayList.get(j).toStringWithoutRate()
								+ tmpCustomer.accountsArrayList.get(j).calculateInterest() + " kr";
						// Permanently close the account.
						tmpCustomer.accountsArrayList.remove(j);
						// Return the String.
						return (s);
					}
				}
			}
		}
		// Returns null if no account was found.
		return null;
	}

	/**
	 * Closes all accounts of a customer and removes the customer from the bank.
	 * 
	 * @param pNo (Personal number)
	 * @return Information about the customer and the closed accounts, including
	 *         rate.
	 */
	public ArrayList<String> deleteCustomer(String pNo) {
		// Create temporary list that we later return.
		ArrayList<String> tmpCustomerArr = new ArrayList<String>();
		// Loop through all elements in myCustomerArrayList (i.e, check all customers).
		for (int i = 0; i < myCustomerArrayList.size(); i++) {
			// Store every customer in temporary variable of Customer type.
			Customer tmpCustomer = myCustomerArrayList.get(i);
			// Check whether pNo from method input equals pNo in Customer object.
			if (tmpCustomer.getpNo().equals(pNo)) {
				// Now we have found the correct person.
				// Add information about the customer to the list.
				tmpCustomerArr.add(tmpCustomer.toStringCustomer());
				// Loop through all the accounts.
				// No incrementer on j since we delete the account each time in the loop.
				// So index shiftes every time.
				for (int j = 0; j < tmpCustomer.accountsArrayList.size();) {
					// Add information about each account including interest to the list.
					tmpCustomerArr.add(tmpCustomer.accountsArrayList.get(j).toStringWithoutRate()
							+ tmpCustomer.accountsArrayList.get(j).calculateInterest() + " kr");
					// Remove the account.
					tmpCustomer.accountsArrayList.remove(j);
				}
				// Remove the customer.
				myCustomerArrayList.remove(i);
				// Return the information.
				return tmpCustomerArr;
			}
		}
		// If nothing was done, return null.
		return null;
	}

}