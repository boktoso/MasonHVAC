/*
Robert Hayman
IT 206 DL1
Assignment 10
Maintenance class that extends Account.  Holds specific information about maintenance on units.
There is a flat fee of $505 for maintenance.  To have Fall Service is $120 per unit, and Spring Service is $100 per unit.

THIS IS USED FOR EDUCATIONAL PURPOSES ONLY
*/
public class Maintenance extends Account{

   private static double FLAT_FEE = 505;
   private static double FALL_SERVICE = 120;
   private static double SPRING_SERVICE = 100;
   
   private boolean fallService;
   private boolean springService;
   
   Maintenance(){
      super("", "", "", 1, false);
      this.fallService = false;
      this.springService = false;
   }
   
   Maintenance(String a, String b, String c, int i, boolean tf){
      super(a, b, c, i, tf);
      this.fallService = false;
      this.springService = false;
   }
   
   public void setFallService(boolean service){ this.fallService = service;}
   public void setSpringService(boolean service){ this.springService = service;}
   
   public boolean getFallService(){ return this.fallService;}
   public boolean getSpringService(){ return this.springService;}
   public int numOfFall(){ if(this.fallService){return 1; } else{ return 0; }}
   
   public String toString(){
      String output = "";
      
      output += "Name: " + getName() + "\n";
      output += "Phone: " + getPhone() + "\n";
      output += "Email: " + getEmail() + "\n";
      output += "Units: " + getUnits() + "\n";
      output += "Alumni: " + getAlumni() + "\n";
      output += "Fall Service: " + this.fallService + "\n";
      output += "Spring Service: " + this.springService + "\n";
      
      return output;
   }
   //Calculates cost and if they are Alumni
   public double calculateCost(){
      double cost = 0;
      double warrantyCost = 0;
      if(this.springService){ warrantyCost += FALL_SERVICE; }
      if(this.springService){ warrantyCost += SPRING_SERVICE; }
      cost += FLAT_FEE + warrantyCost;
      return cost;
   }
   
   public double calculateAlumni(){
      double cost = 0;
      double warrantyCost = 0;
      if(this.springService){ warrantyCost += FALL_SERVICE; }
      if(this.springService){ warrantyCost += SPRING_SERVICE; }
      cost += FLAT_FEE + warrantyCost;
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
