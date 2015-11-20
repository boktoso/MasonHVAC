/*
Robert Hayman
IT 206 DL1
Assignment 10
This is the main class that runs the following classes, Account, Installation, Maintenance, and Repair.
Accounts is an abstract class that holds the basic information for a customer, and is expanded upon by either having a Maintenance, Installation, or Reapair account.
The current maximum number of accounts is 206.
After selecting an account and putting in the correct information, it will print out a quick invoice of that account and ask if you would like to add another.
After all accounts are put in, you can close the application which will print out a final invoice of all customers in the account.

THIS IS USED FOR EDUCATIONAL PURPOSES ONLY
*/
import javax.swing.JOptionPane;
public class MasonHVAC{

   public static void main(String[] args){
      final int MAX_ACCOUNTS = 206; // Maximum number of accounts available
      Account[] account = new Account[MAX_ACCOUNTS];
      addAccount(account); //Runs a loop to create accounts until the user is finished, or hits 206 accounts.
   }
   
   //Continues to add more accounts into the Account array class, will stop when the maximum number is reached or if the user decides to exit.
   public static void addAccount(Account[] account){
      boolean cont = true;
      int index = 0;
      //Used to save run-time and calculate the final invoice printed
      double totalInvoice = 0;
      
      
      Object[] options = {"Installation Account", "Maintenance Account", "Repair Account", "Exit"};
      String menuList = "What type of account would you like to add?";
      do{
         int accountType;
         accountType = JOptionPane.showOptionDialog(null, menuList, "Account Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         switch(accountType){
    	  	case 0:  account[index] = addInstallation();
    	  			break;
    	  	case 1:  account[index] = addMaintenance();
    	  			break;
    	  	case 2:  account[index] = addRepair();
    	  			break;
    	  	case 3: cont=false;
    	  			break;
  			default: //should never hit this point, but just in case, some code is available
  				throw new RuntimeException("Unexpected Error!");
    	  	}
         if(cont==true){
            String calcCost = "Estimated Cost: ";
            calcCost += String.format("$%.2f", account[index].calculateCost()) + "\n";
            if(account[index].getAlumni()){
               calcCost += "Cost After Alumni Discount: ";
               calcCost += String.format("$%.2f", account[index].calculateAlumni()) + "\n";
            }
            
            //Adds to the running total of invoices
            if(account[index].getAlumni()){
               totalInvoice += account[index].calculateAlumni();
            }
            else{
               totalInvoice += account[index].calculateCost();
            }
            
            JOptionPane.showMessageDialog(null, account[index].toString() + calcCost);
            
            index++;
            if(index < 206){
            
               int choice;
               
               choice = JOptionPane.showConfirmDialog(null, "Would you like to add another account?", null, JOptionPane.YES_NO_OPTION);
               
               if(choice == JOptionPane.NO_OPTION){
                  cont = false;
               }
            }
            else{
               cont = false;
               JOptionPane.showMessageDialog(null, "Maximum amount of accounts have been created.");
            }   
         }
      }while(cont == true);
      
      printInvoice(account, index, totalInvoice); //Prints the final invoice once the accounts are finished being created.

   }
   
   //Prints the final invoice with the specific details given.
   //The invoice needs to include the following:
   //How many accounts were created - index
   //Total amount of money billed through invoices - totalInvoice
   //How many customer accounts wish to have a warranty - numOfWarranty
   //Total number of labor hours charged for repairs - totalLabor
   //Total number of customers requesting Fall Services. - numOfFall
   
   public static void printInvoice(Account[] account, int index, double totalInvoice){
      String output = "";
      
      int numOfWarranty = 0;
      int totalLabor = 0;
      int numOfFall = 0;
      
      output += "Invoice Summary\n";
      output += "Number of customers added: " + index + "\n";
      output += "Total amount billed through invoices: " + String.format("$%.2f", totalInvoice) + "\n";
      for(int a=0; a < index; a++){
         numOfWarranty += account[a].numOfWarranty();
      }
      output += "Customers who wish to have warranty: " + numOfWarranty + "\n";
      for(int b=0; b < index; b++){
         totalLabor += account[b].totalLabor();
      }
      output += "Number of labor hours charged for repairs: " + totalLabor + "\n";
      for(int c=0; c < index; c++){
         numOfFall += account[c].numOfFall();
      }
      output += "Number of Fall Services requested: " + numOfFall + "\n";
      
      JOptionPane.showMessageDialog(null, output);
   }
   
   public static Installation addInstallation(){ //Installation Account Variables
      String name;
      String phone;
      String email;
      int units;
      boolean alumni;
      
      Installation installation = new Installation();
      
      do{
         name = JOptionPane.showInputDialog(null, "Enter the customer's name:");
         if(installation.setName(name) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid name.");
         }
      }while(installation.setName(name) == false);
      
      do{
         phone = JOptionPane.showInputDialog(null, "Enter the customer's phone number (XXX-XXX-XXXX):");
         if(installation.setPhone(phone) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid phone number.");
         }
      }while(installation.setPhone(phone) == false);
      
      do{
         email = JOptionPane.showInputDialog(null, "Enter the customer's e-mail (bsait@gmu.edu):");
         if(installation.setEmail(email) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid e-mail.");
         }
      }while(installation.setEmail(email) == false);
      
      int alumniC;
      alumniC = JOptionPane.showConfirmDialog(null, "Are they Alumni?", null, JOptionPane.YES_NO_OPTION);
      if(alumniC == JOptionPane.YES_OPTION){
         installation.setAlumni(true);
      }
      else{ installation.setAlumni(false); }
      
      do{
         units = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the amount of units (between 1 and 4 inclusively):"));
         if(installation.setUnits(units) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid amount of units.");
         }
      }while(installation.setUnits(units) == false);
      
      boolean warranty;
      int warrChoice;
      warrChoice = JOptionPane.showConfirmDialog(null, "Would they like a warranty on their newly installed machines?", null, JOptionPane.YES_NO_OPTION);
      if(warrChoice == JOptionPane.YES_OPTION){
         installation.setWarranty(true);
      }
      else{ installation.setWarranty(false); }
      
      return installation;
   }   
   
   public static Maintenance addMaintenance(){ //Maintenance Account Variables
      String name;
      String phone;
      String email;
      int units;
      boolean alumni;
      
      Maintenance maintenance = new Maintenance();
      
      do{
         name = JOptionPane.showInputDialog(null, "Enter the customer's name:");
         if(maintenance.setName(name) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid name.");
         }
      }while(maintenance.setName(name) == false);
      
      do{
         phone = JOptionPane.showInputDialog(null, "Enter the customer's phone number (XXX-XXX-XXXX):");
         if(maintenance.setPhone(phone) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid phone number.");
         }
      }while(maintenance.setPhone(phone) == false);
      
      do{
         email = JOptionPane.showInputDialog(null, "Enter the customer's e-mail (bsait@gmu.edu):");
         if(maintenance.setEmail(email) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid e-mail.");
         }
      }while(maintenance.setEmail(email) == false);
      
      int alumniC;
      alumniC = JOptionPane.showConfirmDialog(null, "Are they Alumni?", null, JOptionPane.YES_NO_OPTION);
      if(alumniC == JOptionPane.YES_OPTION){
         maintenance.setAlumni(true);
      }
      else{ maintenance.setAlumni(false); }
      
      do{
         units = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the amount of units (between 1 and 4 inclusively):"));
         if(maintenance.setUnits(units) == false){
            JOptionPane.showMessageDialog(null, "Error, invalid amount of units.");
         }
      }while(maintenance.setUnits(units) == false);

      boolean fallService;
      boolean springService;
      int fall;
      int spring;
      
      fall = JOptionPane.showConfirmDialog(null, "Do they want Fall Service?", null, JOptionPane.YES_NO_OPTION);
      if(fall == JOptionPane.YES_OPTION){
         maintenance.setFallService(true);
      }
      else{ maintenance.setFallService(false); }
      
      spring = JOptionPane.showConfirmDialog(null, "Do they want Spring Service?", null, JOptionPane.YES_NO_OPTION);
      if(spring == JOptionPane.YES_OPTION){
         maintenance.setSpringService(true);
      }
      else{ maintenance.setSpringService(false); }
      
      return maintenance;
   }   
   
    public static Repair addRepair(){ //Repair Account Variables
         String name;
         String phone;
         String email;
         int units;
         boolean alumni;
         
         Repair repair = new Repair();
         
         do{
            name = JOptionPane.showInputDialog(null, "Enter the customer's name:");
            if(repair.setName(name) == false){
               JOptionPane.showMessageDialog(null, "Error, invalid name.");
            }
         }while(repair.setName(name) == false);
         
         do{
            phone = JOptionPane.showInputDialog(null, "Enter the customer's phone number (XXX-XXX-XXXX):");
            if(repair.setPhone(phone) == false){
               JOptionPane.showMessageDialog(null, "Error, invalid phone number.");
            }
         }while(repair.setPhone(phone) == false);
         
         do{
            email = JOptionPane.showInputDialog(null, "Enter the customer's e-mail (bsait@gmu.edu):");
            if(repair.setEmail(email) == false){
               JOptionPane.showMessageDialog(null, "Error, invalid e-mail.");
            }
         }while(repair.setEmail(email) == false);
         
         int alumniC;
         alumniC = JOptionPane.showConfirmDialog(null, "Are they Alumni?", null, JOptionPane.YES_NO_OPTION);
         if(alumniC == JOptionPane.YES_OPTION){
            repair.setAlumni(true);
         }
         else{ repair.setAlumni(false); }
         
         do{
            units = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the amount of units (between 1 and 4 inclusively):"));
            if(repair.setUnits(units) == false){
               JOptionPane.showMessageDialog(null, "Error, invalid amount of units.");
            }
         }while(repair.setUnits(units) == false);
                     
         int laborHours = 0;
         String[] partList = new String[5];
         double[] partCost = new double[5];
         
         do{
            try{
               laborHours = Integer.parseInt(JOptionPane.showInputDialog(null, "How many hours of Labor are required? (Whole numbers only between 1 and 10): "));
               if(repair.setLaborHours(laborHours) == false){
                  JOptionPane.showMessageDialog(null, "Error, invalid amount of labor hours.");
               }
            }
            catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null, "Error, invalid amount of labor hours.");
            }   
         }while(repair.setLaborHours(laborHours) == false);
         
         int count = 0;
         boolean moreParts = false;
         do{
            boolean set1 = false;
            boolean set2 = false;
            do{
               partList[count] = JOptionPane.showInputDialog(null, "Enter the name of the part:");
               if(partList[count] != null){
                  set1 = true;
               }
               else{
                  JOptionPane.showMessageDialog(null, "Error, invalid part name.");
               }
            }while(set1 == false);
            do{
               partCost[count] = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the dollar value of the part: (between 5 and 500, inclusively):"));
               if(partCost[count] > 4 && partCost[count] < 501){
                  set2 = true;
               }
               else{
                  JOptionPane.showMessageDialog(null, "Error, invalid part price.");
               }
            }while(set2 == false);
            int moreP;
            moreP = JOptionPane.showConfirmDialog(null, "Would you like to add another part (maximum is 5)?", null, JOptionPane.YES_NO_OPTION);
            if(moreP == JOptionPane.YES_OPTION){
               moreParts = true;
            }
            else{ moreParts = false; }
            set1 = false;
            set2 = false;
            count++;
         }while((count < 5) && (moreParts == true));
         repair.setPartList(partList, count);
         repair.setPartCost(partCost, count);
         
         return repair;  
      }
   
}
