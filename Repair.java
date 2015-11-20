/*
Robert Hayman
IT 206 DL1
Assignment 10
Installation class that extends Account.  Holds specific information about repairing units.
Repairs start with a travel fee of $75.  For each hour of labor (not counting partial hours), it is $60 per hour.  Part's charge is between $5 and $500, and 25% of the cost of parts goes as a surchage in the initial invoice.

THIS IS USED FOR EDUCATIONAL PURPOSES ONLY
*/
public class Repair extends Account{
   
   private static double TRAVEL_FEE = 75; //travel fee cost
   private static double LABOR_HOUR = 60; //cost of each labor hour
   private static double PARTS_SURCHARGE = 0.25; //percentage of the amount of parts will be charged
   private String[] partList = new String[5];
   private double[] partCost = new double[5];
   private int laborHours;
   
   Repair(){
      super("", "", "", 1, false);
      this.laborHours = 0;
   }
   
   Repair(String a, String b, String c, int i, boolean tf){
      super(a, b, c, i, tf);
      this.laborHours = 0;
   }
   
   
   public void setPartList(String[] listOfParts, int count){
      for(int x=0; x < count; x++){
         this.partList[x] = listOfParts[x];
      }
   }
   public void setPartCost(double[] listOfCost, int count){
      for(int x=0; x < count; x++){
         this.partCost[x] = listOfCost[x];
      }
   }
   public boolean setLaborHours(int hours){
      if(hours < 11 && hours > 0){
         this.laborHours = hours;
         return true;
      }
      return false;
   }
   
   public String[] getPartList(){ return this.partList; }
   public double[] getPartCost(){ return this.partCost; }
   public int getLaborHours(){ return this.laborHours; }
   public int totalLabor(){ return this.laborHours; }
   
   public String toString(){
      String output = "";
      
      output += "Name: " + super.getName() + "\n";
      output += "Phone: " + super.getPhone() + "\n";
      output += "Email: " + super.getEmail() + "\n";
      output += "Units: " + super.getUnits() + "\n";
      output += "Alumni: " + super.getAlumni() + "\n";
      output += "Labor Hours: " + this.laborHours + "\n";
      output += "Parts:\n";
      for(int x=0; x < this.partList.length; x++){
         if(this.partList[x] != null){
		      output += this.partList[x] + " " + String.format("$%.2f", this.partCost[x]) + "\n";
         }
      }
      return output;
   }
   //Calculates cost and if they are Alumni
   public double calculateCost(){ 
      double cost = 0;
      for(int x=0; x < this.partCost.length; x++){
         if(this.partCost[x] != 0){
		      cost += (this.partCost[x] * PARTS_SURCHARGE);
         }
      }
      cost += TRAVEL_FEE + (LABOR_HOUR * this.laborHours);
      return cost;
   }
   public double calculateAlumni(){ 
      double cost = 0;
      for(int x=0; x < this.partCost.length; x++){
         if(this.partCost[x] != 0){
		      cost += (this.partCost[x] * PARTS_SURCHARGE);
         }
      }
      cost += TRAVEL_FEE + (LABOR_HOUR * this.laborHours);
      cost = cost - (cost * 0.05);
      return cost;
   }
   
   //Setters
   public boolean setName(String aName){
      if(aName != null){
         super.setName(aName);
         return true;
      }
      
      return false;
   }
   
   //Checks format of phone number, in XXX-XXX-XXXX format
   public boolean setPhone(String aPhone){
      if(aPhone.length()!=12){return false;}
         for(int x = 0; x < aPhone.length(); x++)
         {
            if(x != 3 && x != 7)
            {
               if(!Character.isDigit(aPhone.charAt(x)))
               {
   			      return false;
               }
            }
            else
            {
      			if(aPhone.charAt(3)!= '-' || aPhone.charAt(7)!= '-'){
                  return false;
      			}
            }
         }
      super.setPhone(aPhone);   
      return true;
	}
   //Checks to make sure that the email is at least a@b.c
   public boolean setEmail(String aEmail){
      int atPos = aEmail.indexOf('@');
      int dotPos = aEmail.indexOf('.');
      if (atPos > 0 && dotPos > 0){
         if(atPos < dotPos){
            if((aEmail.charAt(0) != '@' || aEmail.charAt(0) != '.') && (aEmail.charAt(atPos + 1) != '.') && (aEmail.charAt(dotPos + 1) != '.')){
               super.setEmail(aEmail);
               return true;
            }
            return false;
         }
         return false;
      }
      
      return false;
   }
   //Must be between 1 and 4 inclusively
   public boolean setUnits(int numUnits){
      if(numUnits > 0 && numUnits < 5 ){ super.setUnits(numUnits); return true;}
      else{ return false; }
   }
   public void setAlumni(boolean isAlumni){ super.setAlumni(isAlumni); }
}
