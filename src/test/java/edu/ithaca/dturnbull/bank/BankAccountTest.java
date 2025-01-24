package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertTrue(BankAccount.isEmailValid("lerlichsondeliz-2@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz--2@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz2-@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca..edu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca.e"));
        assertTrue(BankAccount.isEmailValid("lerlichsondeliz@ithaca.eduuuuuuuuuuuuuuu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@ithaca"));
        assertFalse(BankAccount.isEmailValid("lerlihsondelizithaca.edu"));
        assertFalse(BankAccount.isEmailValid("lerlichsondeliz@@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid("@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid(".l@ithaca.edu"));
        assertFalse(BankAccount.isEmailValid("lerichsondeliz@ith#ca.edu"));
        
        
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