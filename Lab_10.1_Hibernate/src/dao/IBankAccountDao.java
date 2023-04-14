package dao;

import pojos.BankAccount;

public interface IBankAccountDao {

	String openBankAccount(BankAccount bankAccount, int vendorId);

	String closeAccount(int vendorId, String accountType);

}
