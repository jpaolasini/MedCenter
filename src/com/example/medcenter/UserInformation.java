package com.example.medcenter;

import java.util.List;

public class UserInformation {
  static String userName;
  static String userPassword;
  static String firstName;
  static String lastName;
  static String gender;
  static String userType;
  static int[] userAge;
  static int[] weight;
  static int height;
  static String bloodType;
  static String lastTetnus;
  static String lastFlu;
  static String hasHernia;
  static String hasBloodInUrine;
  static String hasDiabetes;
  static String isPregnant;
  static String[] prescriptions;
  static String appointmentDate;
  

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUserType() {
    return userType;
  }

  public int[] getUserAge() {
    return userAge;
  }

  public double getheight() {
    return height;
  }

  public String getBloodType() {
    return bloodType;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public void setUserAge(int[] userAge) {
    this.userAge = userAge;
  }


  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public static String mergePatientInfo(String username, String password,
    String firstName, String lastName, String gender, String userType, String height,
    List<Integer> weight, List<Integer> age, String bloodType, String lastTetnus,String lastFlu,
		String hasHernia, String hasBloodInUrine, String hasDiabetes,List<String> prescriptions) throws Exception {
	
	//Set up string[] to a string.
	String parser = ",";
	String prescriptionString = "";
	String weightString = "";
	String ageString = "";
	for(int i = 0; i < weight.size(); i++)
	{if(i==0){
		weightString = weightString+weight.get(i);
	}
	else{
		weightString = weightString + ":"+weight.get(i) ;
	}
	}for(int i = 0; i < age.size(); i++)
	{if(i==0){
		ageString = ageString + age.get(i);}
	else{
		ageString = ageString + ":" + age.get(i);
	}
	}
	
	for(int i = 0; i < prescriptions.size(); i++)
	{
		if(i == 0){
			prescriptionString = prescriptionString + prescriptions.get(i);
		}
		else
		{
			prescriptionString = prescriptionString + ":" + prescriptions.get(i);
		}
	}

    String mergedData = username + parser + password + parser + firstName
        + parser + lastName + parser + gender + parser + userType + parser + "[" + ageString + "]" + parser
        + "[" + weightString + "]" + parser + height + parser + bloodType + parser + lastTetnus + parser + lastFlu + parser + hasHernia + parser
		+ hasBloodInUrine + parser + hasDiabetes + parser + "[" + prescriptionString + "]";
    return mergedData;
  }

  public static String[] parseInfo(String userInfo) {
    String[] tokens = userInfo.split(",");

    return tokens;
  }
}
