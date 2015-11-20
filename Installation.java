/*
Robert Hayman
IT 206 DL1
Assignment 10
Installation class that extends Account.  Holds specific information about installating a new unit.
Installation comes with a $4500 cost, and adding warranty is $200 per unit.

THIS IS USED FOR EDUCATIONAL PURPOSES ONLY
*/
public class Installation extends Account{
   
   private final double INITIAL_COST = 4500;
   private final double WARRANTY_COST = 200;
   private boolean warranty;
   
   Installation(){
      super("", "", "", 1, false);
      this.warranty = false;
   }
   
   Installation(String a, String b, String c, int i, boolean tf){
      super(a, b, c, i, tf);
      this.warranty = false;
   }
   
   public void setWarranty(boolean sWar){ this.warranty = sWar; }
   public boolean getWarranty(){ return this.warranty; }
   
   public int numOfWarranty(){ if(this.warranty){return 1;} else{return 0;} }
   
   public String toString(){
      String output = "";
      
      output += "Name: " + getName() + "\n";
      output += "Phone: " + getPhone() + "\n";
      output += "Email: " + getEmail() + "\n";
      output += "Units: " + getUnits() + "\n";
      output += "Alumni: " + getAlumni() + "\n";
      output += "Warranty: " + this.warranty + "\n";
      return output;
   }
   //Calculates cost and if they are Alumni
   public double calculateCost(){ 
      double cost = 0;
      double warrantyCost = 0;
      if(this.warranty == true){
         warrantyCost += WARRANTY_COST * getUnits();
      }
      cost += INITIAL_COST + warrantyCost;
      return cost;
   }
   public double calculateAlumni(){ 
      double cost = 0;
      double warrantyCost = 0;
      if(this.warranty == true){
         warrantyCost += WARRANTY_COST * getUnits();
      }
      cost += INITIAL_COST + warrantyCost;
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
      if ((atPos > 0) && (dotPos > 0)){
         if(atPos < dotPos){
            if(((aEmail.charAt(0) != '@') || (aEmail.charAt(0) != '.')) && (aEmail.charAt(atPos + 1) != '.') && (aEmail.charAt(dotPos + 1) != '.')){
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
