/** 
* The Taxable interface declares functions related to the calculation and
* generation of tax related data and statements.
*
* @author Andrew Koung, Tanvir Sarkar
* @version 2.0
* @since 2017-04-05
*/

package com.java.accounts;

public interface Taxable 
{
   public void calculateTax(double taxRate);
   public double getTaxAmount(); 
   public String createTaxStatement();
}
