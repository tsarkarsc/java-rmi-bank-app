/** 
* The BankImpl class is the Bank class from A1 converted to work as a remote object.
* It extends UnicastRemoteObject and implements the user defined RemoteBank interface
* 
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package edu.btp400.w2017.server;

import edu.btp400.w2017.common.*;
import com.java.accounts.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;  

public class BankImpl extends UnicastRemoteObject implements RemoteBank 
{

	private String bankname; 
	private ArrayList<Account> listofaccounts = new ArrayList<Account>();

	public BankImpl(String bankname) throws RemoteException
	{
		this.bankname = bankname;
	}

	public BankImpl() throws RemoteException
	{ 
		this("Seneca@York"); 
	};


	public boolean addAccount(Account obj) throws RemoteException
	{
			if(obj == null) 
			{ 
				return false; 
			}
			else 
			{ 
				if(listofaccounts.contains(obj.getAccountNumber()))
				{
					System.out.println("Duplicate account being added"); 
					return false;
				}
			}
			
			listofaccounts.add(obj); 
			return true;
	
	}

	public Account removeAccount(String accountnumber) throws RemoteException
	{	
		int index = 0;
		Account obj;
		

			for(Account accounts : listofaccounts) 
			{
				if(accounts.getAccountNumber().equals(accountnumber)) 
				{
					obj = accounts;
					index = listofaccounts.indexOf(obj); 
				}
			}
		
			obj = listofaccounts.get(index); 
			listofaccounts.remove(index); 
			return obj;	
		 
		
	}
	
	
	public Account[] search(double balancesearch) throws RemoteException
	{	
		Account[] accountholder;
		int balancecountmatch = 0;  
		Account[] searchAccount = (Account[]) listofaccounts.toArray(new Account[listofaccounts.size()]); 
		
		
			for(int i=0; i < listofaccounts.size(); i++) 
			{
				if(searchAccount[i].getBalance() == balancesearch) 
				{
					balancecountmatch++;
				}
			} 
			
			if(balancecountmatch == 0) 
			{
				accountholder = new Account[0]; 
				return accountholder;
			}
			else 
			{
				accountholder = new Account[balancecountmatch]; 
				
				int k = 0; 
				for(int j=0; j < listofaccounts.size(); j++) 
				{
					if(searchAccount[j].getBalance() == balancesearch) 
					{
						System.arraycopy(searchAccount,j,accountholder,k,1); 
						k++;
					}
				}
			}
		
			return accountholder;
		
	
	}
	
	public Account[] searchByAccountName(String accountName) throws RemoteException
	{
		Account[] accountholder;
		int balancecountmatch = 0; 
		Account[] searchAccount = (Account[]) listofaccounts.toArray(new Account[listofaccounts.size()]); 
		
		
			for(int i=0; i < listofaccounts.size(); i++) 
			{
				if(searchAccount[i].getFullname().equals(accountName.trim()))
				{
					balancecountmatch++;
				}
			}
		
			if(balancecountmatch == 0) 
			{
				accountholder = new Account[0]; 
				return accountholder;
			}
			else 
			{
				accountholder = new Account[balancecountmatch]; 
				
				int k = 0; 
				for(int j=0; j < listofaccounts.size(); j++) 
				{
					if(searchAccount[j].getFullname().equals(accountName.trim()))
					{
						System.arraycopy(searchAccount,j,accountholder,k,1); 
						k++;
					}
				} 
			}
		
			return accountholder;
		
		
	}
	
	public Account[] getAllAccounts()
	{
		Account [] arrayholder = (Account[]) listofaccounts.toArray(new Account[listofaccounts.size()]);
		
		if(arrayholder.length == 0) 
		{
			System.out.println("Array is empty");
		}
		return arrayholder;
		
		
		
	}
	
	public String toString() 
	{
		String s;

				
		s =   "*****************************************\n" +
              "*          Accounts listed below        *\n" +
	          "*****************************************\n" +
              "Bank name: " + bankname + "\n" +
              "Accounts currently opened: " + listofaccounts.size()  +  "\n\n" +
			  "" +listofaccounts + "\n" + 
			  "********************************************" + "\n\n";
			  
			return s;
	} 
		
		/**
 * @param z Object
 */
	public boolean equals(Object z) 
	{
			boolean result = false;

			
				if ( z instanceof BankImpl ) 
				{
					BankImpl z2 = (BankImpl) z;
					if ((z2.listofaccounts.containsAll(listofaccounts)) && listofaccounts.containsAll(z2.listofaccounts) && (z2.listofaccounts.size() == listofaccounts.size()))
					{
						result = true;
					}
		        }
		
			return result;
	}
}