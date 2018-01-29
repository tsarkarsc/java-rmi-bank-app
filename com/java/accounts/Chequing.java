/** 
* The chequing class holds user data for a bank account of the Chequing type,
* such as the user's full name, account number, balance, service charge per transaction,
* and maximum number of transactions.
*
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package com.java.accounts;

import java.math.*;  

public class Chequing extends Account
{
	private BigDecimal servicecharge; 
	private BigDecimal servicechargemultiplier;
	private BigDecimal maxtransactions; 
	private BigDecimal finalbalance;
	private BigDecimal balance;
	private BigDecimal servicechargetotal;
	private String transactionhistory[]; 
	private int count = 0;
	
	public Chequing(String fullnameval, String accountnumberval, double accountbalanceval, double servicechargeval, double maxtransactionsval)
	{	
		super(fullnameval , accountnumberval, accountbalanceval);
	
		int max = (int) maxtransactionsval;
		transactionhistory = new String[max];
		servicecharge = new BigDecimal(servicechargeval); 
		maxtransactions = new BigDecimal(maxtransactionsval);		
	}
	
	public Chequing() 
	{
		super(); 
		servicecharge = new BigDecimal("0.25"); 
		maxtransactions = new BigDecimal("3");
	}
	
	
	public boolean withdraw(double amount) 
    {
		double balanceholder; 
		
		String amounttoString = Double.toString(amount); 
		double doublemaxtransactions = maxtransactions.doubleValue(); 
		int countcheck = (int) doublemaxtransactions;
		
		if((count < countcheck) || (amount < 0))
		{
			balanceholder = super.getBalance(); 
			balanceholder = balanceholder - amount; 
			
			if(balanceholder > 0 )
			{
				setBalance(balanceholder);
				transactionhistory[count] = "-" + amounttoString + ", ";
				count++;
				return true;
			}
			else 
			{
				System.out.println("Insufficient funds on one of the extra attempts to withdraw");
				return false; 
			}
		}
		else
		{
			System.out.println("Max transactions made"); 
			return false;
		}	
    }
	
	@Override
	public double getBalance() 
    {
	   MathContext mc = new MathContext(3);  
	   double multiplier = (double) count;
	   servicechargemultiplier = new BigDecimal(multiplier);
	   servicechargetotal = servicecharge.multiply(servicechargemultiplier, mc);
	   balance = new BigDecimal(nonOverrideGetBalance());
	   
	   finalbalance = balance.subtract(servicechargetotal, mc);
	   return finalbalance.doubleValue();
    }
	
	
	public void deposit(double amount)
	{ 
		String amounttoString = Double.toString(amount); 
		double doublemaxtransactions = maxtransactions.doubleValue(); 
		int countcheck = (int) doublemaxtransactions; 
		double balanceholder = super.getBalance(); 
		balanceholder = balanceholder + amount; 
		
		if(count < countcheck) 
		{
			setBalance(balanceholder);
			transactionhistory[count] = amounttoString + ", "; 
			count++;
		}
	}
	
	public boolean equals( Object z ) 
	{
		boolean result = false;

			if ( z instanceof Chequing ) 
			{
				Chequing z2 = (Chequing) z;
				if ((z2.getFullname().equals(getFullname())) && (z2.getAccountNumber().equals(getAccountNumber())) && (z2.getBalance() == (getBalance())) && (z2.servicecharge.equals(servicecharge)) && (z2.maxtransactions.equals(maxtransactions)))
				{
			           result = true;
				}
		      }

		return result;
	}
	
	public String toString() 
	{
		MathContext mc = new MathContext(2);   
		double multiplier = (double) count;
		servicechargemultiplier = new BigDecimal(multiplier);
		servicechargetotal = servicecharge.multiply(servicechargemultiplier, mc);
		
		StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
		s.append("\n\nName: " + getFirstname() + ", " + getLastname() + "\n");
		s.append("Number: " +getAccountNumber() + "\n"); 
		s.append("Current Balance: " + "$" + super.getBalance() + "\n");
		s.append("Type: CHQ" + "\n"); 
		s.append("Service Charge: $" + servicecharge.setScale(2) + "\n"); 
		s.append("Total Service Charges: $" + servicechargetotal.setScale(2) + "\n"); 
		s.append("Number of Transactions Allowed: " + maxtransactions + "\n"); 
		s.append("List of Transactions: ");
		for(int i= 0 ; i < count ; i++) 
		{
			s.append(transactionhistory[i]);
		}
		s.append("\nFinal balance: $" + this.getBalance() + "\n");
		 
		
		String str = s.toString(); //Casting stringbuffer to string
		return str;	
	}
}