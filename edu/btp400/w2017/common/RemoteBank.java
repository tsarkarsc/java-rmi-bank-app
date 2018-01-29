/** 
* The Remotebank interface extends Remote and contains the method declarations for the methods
* that a BankImpl class may define
* 
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package edu.btp400.w2017.common;

import java.rmi.*; 
import com.java.accounts.*;
import edu.btp400.w2017.client.*;
import edu.btp400.w2017.server.*;

public interface RemoteBank extends Remote
{
   public boolean addAccount(Account obj) throws RemoteException;
   public Account removeAccount(String accountnumber) throws RemoteException;
   public Account[] search(double balancesearch) throws RemoteException;
   public Account[] searchByAccountName(String accountName) throws RemoteException;
}
