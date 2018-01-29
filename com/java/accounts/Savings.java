/** 
* The Savings class holds user data for a bank account of the Savings type
* such as the user's full name, account number, balance, and interest rate.
*
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package com.java.accounts;

import java.math.*;  

public class Savings extends Account implements Taxable
{ 
	private BigDecimal annualinterestRate; 
	private BigDecimal balance; 
	private BigDecimal interestIncome;
	private BigDecimal tax;
	private BigDecimal finalbalance;
	private BigDecimal hundred = new BigDecimal("100");
	private BigDecimal taxRate = new BigDecimal("15");
	
	
	public Savings(String fullnameval, String accountnumberval, double accountbalanceval, double interestrateval)
	{		
		super(fullnameval , accountnumberval, accountbalanceval);
	
		annualinterestRate = new BigDecimal(interestrateval);
		
		getTaxAmount();
	}
	
	public Savings() 
	{
		super(); 
		annualinterestRate = new BigDecimal("0.10");
	}
	
	public void calculateTax(double annualtaxRate)
	{
		MathContext mc = new MathContext(2); 
	
		BigDecimal annualInterestRateInterestIncome = new BigDecimal(annualtaxRate);
		BigDecimal annualInterestRateHundred;
		
	    balance = new BigDecimal(nonOverrideGetBalance());
		annualInterestRateHundred = annualInterestRateInterestIncome.divide(hundred);
		interestIncome = balance.multiply(annualInterestRateHundred, mc);
		if (interestIncome.compareTo(BigDecimal.ZERO) > 50)
		{
			tax = interestIncome.multiply(taxRate.movePointLeft(2), mc);
		}
		else 
		{
			tax = new BigDecimal("0");
		}
	}	 
	
	public double getTaxAmount() 
	{
		double annualInterestRateDouble = annualinterestRate.doubleValue();
		calculateTax(annualInterestRateDouble);
		return tax.doubleValue();
	}
	
	public String createTaxStatement()
	{
			StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
			s.append("Tax Rate: " + "%" + taxRate  + "\n");
			s.append("Account Number: " + getAccountNumber() + "\n"); 
			s.append("Interest income: " + "$" + interestIncome + "\n");
			s.append("Amount of tax: " + "$" + tax + "\n\n");
			 
			String str = s.toString(); //Casting stringbuffer to string
			return str;	
	} 
	
	
	public double getBalance() 
    {
	   BigDecimal currentbalancez = new BigDecimal(super.getBalance());
	   
	   finalbalance = currentbalancez.add(interestIncome); 
	   return finalbalance.doubleValue();
    } 
	
	public boolean equals( Object z ) 
	{
		boolean result = false;

			if ( z instanceof Savings ) 
			{
				Savings z2 = (Savings) z;
				
				if ((z2.getFullname().equals(getFullname())) && (z2.getAccountNumber().equals(getAccountNumber())) && (z2.getBalance() == (getBalance())) && (z2.annualinterestRate.equals(annualinterestRate)))
				{
			           result = true;
				}
		     }

		return result;
	}
	
	public String toString() 
	{
		MathContext mc = new MathContext(2);   
		
		StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
		s.append("\n\n");
		s.append("Name: " +super.getFirstname() + ", " + super.getLastname() + "\n");
		s.append("Number: " +getAccountNumber() + "\n"); 
		s.append("Current Balance: " + "$" + super.getBalance() + "\n");
		s.append("Type: SAV" + "\n"); 
		s.append("Interest Rate: %" + annualinterestRate.round(mc) + "\n"); 
		s.append("Interest Income: $" + interestIncome + "\n"); 
		s.append("Final balance: $" + this.getBalance() + "\n");
			 
		String str = s.toString(); //Casting stringbuffer to string
		return str;	
	}
}