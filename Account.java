/*
Robert Hayman
IT 206 DL1
Assignment 10
Account abstract class that holds all of the basic information of the other three classes: Installaiton, Maintenance, and Repair.

THIS IS USED FOR EDUCATIONAL PURPOSES ONLY
*/
public abstract class Account{
   
   private String name;
   private String phone;
   private String email;
   private int units;
   private boolean alumni;
   
   //creates empty Account
   Account(){
      this.name = "";
      this.phone = "";
      this.email = "";
      this.units = 1;
      this.alumni = false;
   }
   
   Account(String a, String b, String c, int i, boolean tf){
      this.name = a;
      this.phone = b;
      this.email = c;
      this.units = i;
      this.alumni = tf;
   }
   //Setters
   public boolean setName(String aName){
      if(aName != null){
         this.name = aName;
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
      this.phone = aPhone;   
      return true;
	}
   //Checks to make sure that the email is at least a@b.c
   public boolean setEmail(String aEmail){
      int atPos = aEmail.indexOf('@');
      int dotPos = aEmail.indexOf('.');
      if (atPos > 0 && dotPos > 0){
         if(atPos < dotPos){
            if((aEmail.charAt(0) != '@' || aEmail.charAt(0) != '.') && (aEmail.charAt(atPos + 1) != '.') && (aEmail.charAt(dotPos + 1) != '.')){
               this.email = aEmail;
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
      if(numUnits > 0 && numUnits < 5 ){ this.units = numUnits; return true;}
      else{ return false; }
   }
   public void setAlumni(boolean isAlumni){ this.alumni = isAlumni; }
   
   //Getters
   public String getName(){ return this.name; }
   public String getPhone(){ return this.phone; }
   public String getEmail(){ return this.email; }
   public int getUnits(){ return this.units; }
   public boolean getAlumni(){ return this.alumni; }
   
   //Absract methods.
   public String toString(){
      String output = "";
      
      output += "Name: " + this.name + "\n";
      output += "Phone: " + this.phone + "\n";
      output += "Email: " + this.email + "\n";
      output += "Units: " + this.units + "\n";
      output += "Alumni: " + this.alumni + "\n";
      
      return output;
   }
   public double calculateCost(){ return 0;}
   public double calculateAlumni(){ return 0;}
   
   public int totalLabor(){ return 0; }
   public int numOfWarranty(){ return 0; }
   public int numOfFall(){ return 0; }
}   
