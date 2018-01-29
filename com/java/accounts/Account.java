/** 
* The account class holds user data for a bank account
* such as the user's full name, account number, and balance.
*
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/


package com.java.accounts;

import java.io.Serializable;

public class Account implements Serializable
{
		private String fullname; 
		private String accountnumber; 
		private String firstnamez; 
		private String lastnamez; 
		private double accountbalance; 		
		
		public Account(String fullnameval, String accountnumberval, double accountbalanceval)
		{
			
			String regexsearch = "(.+),\\s*(.+)";
			if(fullnameval == null)
			{
				fullname = "";
			}
			else if(fullnameval.matches(regexsearch))
			{
				String[] splitname = fullnameval.split(",");
				firstnamez = splitname[1]; 
				firstnamez = firstnamez.replaceAll("\\s+","");
				lastnamez = splitname[0];
				fullname = lastnamez + ", " + firstnamez;
			}
			
			accountnumber = accountnumberval;
			
			if(accountbalanceval < 0) 
			{
				accountbalanceval = 0; 
				accountbalance = accountbalanceval;
			}
			else 
			{
				accountbalance = accountbalanceval;
			}
			
		}
			
		public Account() 
		{
			fullname = "";
			accountnumber = ""; 
			accountbalance = 0;
		}
		
		public String getFullname() 
		{
			String returnfull = ""; 
			if(fullname == null) 
			{ 
				returnfull = ""; 
				return returnfull; 
			} 
			else
			{
				return fullname;
			}	
		}
		 
		public String getFirstname() 
		{ 
			String valreturn;

			if(fullname == null) 
			{ 
			   valreturn = "";	
			}
			else 
			{
				if(fullname.isEmpty() || fullname.trim().length() == 0) 
				{
					valreturn = "";
				}
				else 
				{
					valreturn = firstnamez;
				}
			}
				
			return valreturn;
		}
		

		
		public String getLastname() 
		{ 
			String valreturn2;

			if(fullname == null) 
			{ 
			   valreturn2 = "";	
			}
			else 
			{
				if(fullname.isEmpty() || fullname.trim().length() == 0) 
				{
					valreturn2 = "";
				}
				else 
				{
					valreturn2 = lastnamez;
				}
			}
				
			return valreturn2;
		}
		
		public String getAccountNumber()
		{
			return accountnumber;
		}
		
		public double getBalance()
		{
			return accountbalance;
		}
		
		public double nonOverrideGetBalance() 
		{
			return accountbalance;
		}
		
		public void setBalance(double accountbalance) 
		{
			this.accountbalance = accountbalance;
		}
		
		//Version toString() 1.0 with String variable
		/*
		public String toString() 
		{
			String s;

		  s =   "*****************************************\n" +
              	        "*           Account Information         *\n" +
	          	"*****************************************\n" +
              		"First name: " + getFirstname() + "\n" +
              		"Last name: " + getLastname() + "\n" +
              		"Account number: " + getAccountNumber() + "\n" +
              		"Account balance: " + getBalance() + "\n\n";

			return s;
		}
		*/ 
		
		//Version toString() 2.0 with StringBuffer variable
		public String toString() 
		{
			StringBuffer s = new StringBuffer(); //Creating a variable of stringbuffer class
			s.append("*****************************************\n"); 
			s.append("*          Accounts Information         *\n"); 
			s.append("*****************************************\n"); 
			s.append("Name: " + getFirstname() + ", " + getLastname() + "\n");
			s.append("Number: " + "A " +getAccountNumber() + "\n"); 
			s.append("Current Balance: " + "$" + getBalance() + "\n\n");
			s.append("********************************************" + "\n\n"); 
			 
			String str = s.toString(); //Casting stringbuffer to string
			return str;	
		}
		
		/*
		public int hashCode() 
		{
			    int hash = 17;
		
				hash = hash * 37 + fullname.hashCode();                 // type: int
				hash = hash * 37 + accountnumber.hashCode();    // type: String
				hash = hash * 37 + accountbalance;
				return hash;
		}
		*/ 
		
		public boolean equals( Object z ) 
		{
			boolean result = false;

				if ( z instanceof Account ) 
				{
					Account z2 = (Account) z;
					if ((z2.fullname.equals(fullname)) && (z2.accountnumber.equals(accountnumber)) && (z2.accountbalance == (accountbalance)))
					{
			            result = true;
					}
		        }

			return result;
		}

		//****UPDATE**** 
		//Has two new functions withdraw() and deposit() 
		
		public boolean withdraw(double amount) 
		{
			double balancetest = accountbalance; 
			
			balancetest = balancetest - amount; 
			
			if(balancetest < 0) 
			{
				System.out.println("You don't have enough balance to withdraw that amount of money");
				return false;
			}
			else if(amount < 0) 
			{
				System.out.println("Amount sent was a negative value");
				return false;
			}
			else
			{
				accountbalance = accountbalance - amount; 
				System.out.println("You have deducted $" + amount + " from your account"); 
				return true;
			}
		}
		
		public void deposit(double amount)
		{
			if(amount < 0) 
			{
				System.out.println("Amount sent was a negative value");
			}
			else 
			{
				accountbalance = accountbalance + amount; 
				System.out.println("You have deposited $" + amount + " into your account"); 
			}
		}
		
		
}
