package edu.ithaca.dturnbull.bank;

import java.net.InetAddress;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email) && isAmountValid(startingBalance)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " or Starting Balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post increases/decreases the balance of two accounts by the input amount
     * @throws IllegalArgumentException if amount is negative or has more than two decimal points
     */
    public void transfer(double amount, BankAccount account){
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount is invalid");
        }
        else{
            balance -= amount;
            account.balance += amount;
        }
    }

    /**
     * @post increases the balance by input amount
     * @throws IllegalArgumentException if amount is negative or has more than two decimal points
     */
    public void deposit(double amount){
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount is invalid");
        }
        else{
            balance += amount;
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if amount is greater than balance
     * @throws IllegalArgumentException if amount is negative
     */
    public void withdraw(double amount) throws InsufficientFundsException{
        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount is invalid");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Insufficient funds");
        }
        else{
            balance -= amount;
        }
    }

    /**
     * @return true if the amount is positive and has two decimal points or less, and false otherwise
     */

    public static boolean isAmountValid(double amount){
        if (amount < 0){
            return false;
        }
        String amountString = Double.toString(amount);
        if (amountString.indexOf('.') == -1){
            return true;
        }
        if ((amountString.length() - amountString.indexOf('.')) > 3){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * @post returns true if email is valid, false otherwise
     */
    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.indexOf('.') == -1 || email.charAt(0) == '.' || email.length() - email.substring(email.indexOf('@')).length() < 1){
            return false;
        }
        else {
            // RegEx from NLP
            String emailRegex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})+$";
            if (email.matches(emailRegex) == false) {
                return false;
            }
            String domain = email.substring(email.indexOf('@') + 1);

            // Perform DNS lookup via InetAddress to check if the domain exists
            try{
                InetAddress.getByName(domain);
                return true;
            } 
            catch (Exception e){
                return false;
            }

            // for (int i = 0; i < email.indexOf('@'); i++){
            //     if (email.charAt(i) == '#' || email.charAt(i) == '$' || email.charAt(i) == '%'){
            //         return false;
            //     }
            //     if (email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '-'){
            //         if (email.charAt(i) == email.charAt(i+1) || email.charAt(i+1) == '@'){
            //             return false;
            //         }
            //     }
            // }

            // for (int i = email.indexOf('@') + 1; i < email.length(); i++){
            //     if (email.charAt(i) == '@'){
            //         return false;
            //     }
            //     if (email.charAt(i) == '#' || email.charAt(i) == '$' || email.charAt(i) == '%'){
            //         return false;
            //     }
            //     if (email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '-'){
            //         if (email.charAt(i) == email.charAt(i+1)){
            //             return false;
            //         }
            //         if (email.length() - (i+1) < 2){
            //             return false;
            //         }
            //     }
            // }
            // return true;
        }
    }
}