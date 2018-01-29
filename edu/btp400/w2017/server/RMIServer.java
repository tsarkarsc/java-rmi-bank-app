/** 
* The RMIServer creates a BankImpl object (remote bank object) and binds it to an 
* RMI registry so that RMIClient objects can communicate with the remote bank
* 
* @author Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package edu.btp400.w2017.server;

import com.java.accounts.*;
import edu.btp400.w2017.client.*;
import edu.btp400.w2017.common.*;
import java.rmi.*;
import java.rmi.server.*;

public class RMIServer
{
   public static void main(String[] args) 
   {
	   BankImpl b1; 
	  try 
	  {
			System.out.println("Starting a server..." );
			System.out.println("Creating a bank object....");
		    b1 = new BankImpl("Seneca@York"); 
			loadBank(b1); 
			
			System.out.println( "Binding remote objects to rmi registry" );
			
			java.rmi.registry.Registry registry = java.rmi.registry.LocateRegistry.createRegistry(5678); 
			
			registry.rebind( "yorkbank", b1 );
			System.out.println( "These remote objects are waiting for clients..." );
	  }
	  catch( Exception e ) 
	  {
	    System.out.println( "Error: " + e );
      }
	  
	  System.out.println( "... bye bye" );
      System.out.println( "... the main thread is put into a wait state!!!" );
   }
   
   private static void loadBank(BankImpl b1) throws RemoteException
   {
		System.out.println("Creating account objects");
		Savings s1 = new Savings("Doe, John" , "A1" , 100 , 0.15);
		Savings s2 = new Savings("Ryan, Mary" , "A2" , 200 , 0.25);
		Chequing c1 = new Chequing("Doe, John" , "B1" , 300, 0.25, 10); 
		Chequing c2 = new Chequing("Ryan, Mary", "B2" , 400, 0.50, 20);
		GIC g1 = new GIC("Doe, John" , "C1" , 6000 , 2, 1.50);
		GIC g2 = new GIC("Ryan, Mary" , "C2" , 15000 , 4, 2.50);
		System.out.println("Status of adding account one object to bank: " + b1.addAccount(s1));
		System.out.println("Status of adding account two object to bank: " + b1.addAccount(s2));
		System.out.println("Status of adding account three object to bank: " + b1.addAccount(c1));
		System.out.println("Status of adding account four object to bank: " + b1.addAccount(c2));
		System.out.println("Status of adding account five object to bank: " + b1.addAccount(g1));
		System.out.println("Status of adding account six object to bank: " + b1.addAccount(g2));
   }
}