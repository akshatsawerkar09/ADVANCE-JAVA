package dao;

import pojos.BankAccount;

public interface IBankAccount {

	String openAccount(int vendorId, BankAccount account);

	String closeAccount(int vendorId, int acctId);

}
