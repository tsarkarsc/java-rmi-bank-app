/** 
* The GIC class holds user data for a bank account of the GIC type,
* such as the user's full name, account number, balance, period of investment,
* and interest rate.
*
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package com.java.accounts;

import java.math.*;  

public class GIC extends Account implements Taxable
{
	private int periodYears; 
	private BigDecimal annualinterestrate; 
	private BigDecimal balance;
	private BigDecimal partialmaturitybalancecalculation;
	private BigDecimal maturitybalance; 
	private BigDecimal interestincome;
	private BigDecimal interestincometaxed;
	private BigDecimal theTax = new BigDecimal("15");
	
	public GIC(String fullnameval, String accountnumberval, double accountbalanceval, int periodyearsval, double annualinterestrateval)
	{
		super(fullnameval , accountnumberval, accountbalanceval);
		periodYears = periodyearsval;
		annualinterestrate = new BigDecimal(annualinterestrateval);
		
		calculateTax(annualinterestrateval);
	}
	
	public GIC()
	{
		super(); 
		periodYears = 1; 
		annualinterestrate = new BigDecimal("1.25");
	}
	
	public boolean withdraw(double amount) 
	{
		return false;
	}
	
	public void deposit(double amount)
	{
		
	}
	
	public void calculateTax(double taxRate)
	{
		MathContext mcTwo = new MathContext(6);
		
		BigDecimal one = new BigDecimal("1"); 
		balance = new BigDecimal(super.getBalance());

		annualinterestrate = new BigDecimal(taxRate); 
		partialmaturitybalancecalculation = (((annualinterestrate.movePointLeft(2)).add(one)).pow(periodYears));
		maturitybalance = ((balance.multiply(partialmaturitybalancecalculation)).round(mcTwo));
		interestincome = maturitybalance.subtract(balance);
		interestincometaxed = interestincome.multiply(theTax.movePointLeft(2));
	}
	
	public double getTaxAmount()
	{
		return interestincometaxed.doubleValue();
	} 
	
	public String createTaxStatement()
	{
		StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
		s.append("Tax Rate: " + "%" + annualinterestrate + "\n");
		s.append("Account Number: " +getAccountNumber() + "\n"); 
		s.append("Interest income: " + "$" + interestincome + "\n");
		s.append("Amount of tax: " + "$" + interestincometaxed + "\n\n");
		
		String str = s.toString(); //Casting stringbuffer to string
		return str;	
	}
	
	public boolean equals( Object z ) 
	{
		boolean result = false;
		
			if ( z instanceof GIC ) 
			{
				GIC z2 = (GIC) z;
				if ((z2.getFullname().equals(getFullname())) && (z2.getAccountNumber().equals(getAccountNumber())) && (z2.getBalance() == (getBalance())) && (z2.periodYears == periodYears) && (z2.annualinterestrate.equals(annualinterestrate.movePointLeft(1))))
				{
			           result = true;
				}
		      }

		return result;
	}
	
	public String toString() 
	{ 
		
		StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
		s.append("\n\nName: " + getFirstname() + ", " + getLastname() + "\n");
		s.append("Number: " +getAccountNumber() + "\n"); 
		s.append("Current Balance: " + "$" + super.getBalance() + "\n");
		s.append("Type: GIC" + "\n"); 
		s.append("Annual Interest Rate: %" + annualinterestrate + "\n"); 
		s.append("Period of investment: " + periodYears + "\n"); 
		s.append("Interest Income at Maturity: $" + interestincome + "\n");
		s.append("Balance at Maturity: $" + maturitybalance);
		
		
		String str = s.toString(); //Casting stringbuffer to string
		return str;	
	}
}