package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.indexOf('.') == -1 || email.charAt(0) == '.' || email.length() - email.substring(email.indexOf('@')).length() < 1){
            return false;
        }
        else {
            for (int i = 0; i < email.indexOf('@'); i++){
                if (email.charAt(i) == '#' || email.charAt(i) == '$' || email.charAt(i) == '%'){
                    return false;
                }
                if (email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '-'){
                    if (email.charAt(i) == email.charAt(i+1) || email.charAt(i+1) == '@'){
                        return false;
                    }
                }
            }
            for (int i = email.indexOf('@') + 1; i < email.length(); i++){
                if (email.charAt(i) == '@'){
                    return false;
                }
                if (email.charAt(i) == '#' || email.charAt(i) == '$' || email.charAt(i) == '%'){
                    return false;
                }
                if (email.charAt(i) == '.' || email.charAt(i) == '_' || email.charAt(i) == '-'){
                    if (email.charAt(i) == email.charAt(i+1)){
                        return false;
                    }
                    if (email.length() - (i+1) < 2){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}