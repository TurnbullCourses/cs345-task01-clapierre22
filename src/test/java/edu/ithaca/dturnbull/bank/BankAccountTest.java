package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);
        BankAccount bankAccount1 = new BankAccount("b@a.com", 100);
        assertFalse(bankAccount1.getBalance() == 99);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        // checks function with valid withdraw
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), 0.001);
        // checks function with invalid withdraw, value greater than balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        // checks function with invalid withdraw, negative value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-2));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid("a@b.com")); // valid email address
        assertFalse(BankAccount.isEmailValid("")); // empty string (no email address provided)
        assertTrue(BankAccount.isEmailValid("lerlichsondeliz-2@ithaca.edu")); // valid email with a hyphen in the local part
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz--2@ithaca.edu")); // invalid: double hyphen in the local part
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz2-@ithaca.edu")); // invalid: hyphen at the end of the local part
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca..edu")); // invalid: consecutive periods in the domain
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca.e")); // invalid: domain extension is too short
        assertTrue(BankAccount.isEmailValid("lerlichsondeliz@ithaca.eduuuuuuuuuuuuuuu")); // valid: domain extension is long but allowed
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca")); // invalid: missing top-level domain
        assertFalse(BankAccount.isEmailValid("lerlihsondelizithaca.edu")); // invalid: missing '@' symbol
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@@ithaca.edu")); // invalid: multiple '@' symbols
        assertFalse(BankAccount.isEmailValid("@ithaca.edu")); // invalid: missing local part before '@'
        assertFalse(BankAccount.isEmailValid(".l@ithaca.edu")); // invalid: local part starts with a period
        assertFalse(BankAccount.isEmailValid("lerichsondeliz@ith#ca.edu")); // invalid: special character '#' in domain
        assertFalse(BankAccount.isEmailValid("lukas@nonexistentdomain1234.com")); // invalid: not real domain     
        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}