/** 
* The RMIClient is a conversion of the FinancialApp class from Assignment1
* made to work with an RMI server (a remote bank).
* 
* The BASE CODE (that could be reused for this A2) for this class and the account classes was copied from 
* Andrew's Assignment 1
*
* @author Andrew Koung
* @version 2.0
* @since 2017-04-05
*/

package edu.btp400.w2017.client;

import com.java.accounts.*;
import edu.btp400.w2017.server.*;
import edu.btp400.w2017.common.*;
import java.rmi.*;
import java.rmi.server.*;
import javax.swing.JOptionPane;   

public class RMIClient
{
   static int choice;
   public static void main(String[] args) 
   {
	    int flag = 0;
		String url = "rmi://localhost:5678/";
		int periodyears;
		double savRate, balance, servicecharge, maxtransactions, annualinterest;
		String accountinformation, typeofaccount, accountnumber;
		String[] splitinformation; 
		
		try 
		{
			System.out.println( "Running a client application..." );

 			System.out.println( "+++ retrieving bindings from the RMI Registry\n" +
			                    "  + these services have been registered with the RMI Registry:" );
              
			// use of RMI URL
 			String names[] = Naming.list( "rmi://localhost:5678" );
			
			for(int k=0; k < names.length; k++)
    				System.out.println( "....... " + names[k] );
 
			System.out.println( "\n+++ get remote references\n" +
			                    "  + bind local object variables to remote objects..." ); 
			
			RemoteBank r1 = (RemoteBank) Naming.lookup( url + "yorkbank");
			
			do 
			{
				StringBuffer s = new StringBuffer();  
				
				s.append("\n1. Add an account. \n");
				s.append("2. Close an account. \n");
				s.append("3. Search by account name. \n");
				s.append("4. Search by account balance. \n");
				s.append("5. Exit \n");
				System.out.println(s);
				
				String optionInput; 
				optionInput = JOptionPane.showInputDialog("Which option?");
				choice = Integer.parseInt(optionInput); 
				
				if(choice == 1) 
				{
					typeofaccount = JOptionPane.showInputDialog("Please enter the account type (SAV/CHQ/GIC):");
					accountinformation = JOptionPane.showInputDialog("Please enter account information at one line \n (e.g. Doe,John;A1234;1000.00;0.15 for SAV) \n (e.g Doe,John;B1234;5000.00;0.25;10 for CHQ) \n (e.g Doe,John;C1234;3000.00;2;1.5 for GIC)");
					splitinformation = accountinformation.split(";");
					
					if(typeofaccount.equals("SAV"))
				{
					savRate = Double.parseDouble(splitinformation[3]);
					balance = Double.parseDouble(splitinformation[2]);
					r1.addAccount(new Savings(splitinformation[0],splitinformation[1], balance, savRate));
				}
				else if(typeofaccount.equals("CHQ"))
				{
					balance = Double.parseDouble(splitinformation[2]);
					servicecharge = Double.parseDouble(splitinformation[3]); 
					maxtransactions = Double.parseDouble(splitinformation[4]); 
					r1.addAccount(new Chequing(splitinformation[0],splitinformation[1],balance, servicecharge, maxtransactions));
				}
				else if(typeofaccount.equals("GIC"))
				{
					balance = Double.parseDouble(splitinformation[2]);
					periodyears = Integer.parseInt(splitinformation[3]); 
					annualinterest = Double.parseDouble(splitinformation[4]);
					r1.addAccount(new GIC(splitinformation[0],splitinformation[1],balance, periodyears, annualinterest));
				}
				else 
				{
					System.out.println("Did not input any of the options");
				}
				
				}
				else if(choice == 2) 
				{	
					accountnumber = JOptionPane.showInputDialog("Input the account number you'd wish to remove: (e.g A1)");
					r1.removeAccount(accountnumber);
				}
				else if(choice == 3) 
				{
					String accountname = JOptionPane.showInputDialog("Input the account name you'd wish to display: (e.g Doe, John) INCLUDE THE SPACE");
					Account[] searchoutput = r1.searchByAccountName(accountname);
					
					for(int i=0; i < searchoutput.length; i++) 
					{
						System.out.println(searchoutput[i]);
					}
				}
				else if(choice == 4) 
				{
					String finalbalancesearch = JOptionPane.showInputDialog("Input the final balance amount you'd wish to search:");
					double doublefinalbalancesearch = Double.parseDouble(finalbalancesearch); 
					//BigDecimal bigdecimalfinalbalancesearch = new BigDecimal(doublefinalbalancesearch); 
					
					Account[] searchoutput = r1.search(doublefinalbalancesearch);  
					
					for(int i=0; i < searchoutput.length; i++) 
					{
						System.out.println(searchoutput[i]);
					}
				}
				else if(choice == 5) 
				{
					flag = 1;
				}
			}
			while(flag == 0);
			{
				System.out.println("Thank you!");
			}
		}
		catch(Exception e)
		{
			
		}
   }
   
   static void findaccountbyNameAndDeposit(RemoteBank bank, String accountname, double depositval) throws RemoteException
	{
		Account[] searchoutput = bank.searchByAccountName(accountname);
	
		for(int i=0 ; i < searchoutput.length; i++) 
		{
			searchoutput[i].deposit(depositval);	
			
			if(searchoutput[i] instanceof Savings) 
			{
				Savings savingacc = (Savings) searchoutput[i]; 
				savingacc.getTaxAmount();
			}
		}	
	}
   
}