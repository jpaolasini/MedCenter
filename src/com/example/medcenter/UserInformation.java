package com.example.medcenter;

public class UserInformation {
  static String userName;
  static String userPassword;
  static String firstName;
  static String lastName;
  static String gender;
  static String userType;
  static int[] userAge;
  static double[] weight;
  static double height;
  static String bloodType;
  static String lastTetnus;
  static String lastFlu;
  static String hasHernia;
  static String hasBloodInUrine;
  static String hasDiabetes;
  static String isPregnant;
  static String[] prescriptions;
  

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

  public double[] getWeight() {
    return weight;
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

  public void setWeight(double[] weight) {
    this.weight = weight;
  }

  public void setheight(double height) {
    this.height = height;
  }


  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public static String mergePatientInfo(String username, String password,
    String firstName, String lastName, String gender, String userType, String height,
    String weight, String age, String bloodType, String lastTetnus,String lastFlu,
		String hasHernia, String hasBloodInUrine, String hasDiabetes, String[] prescriptions) throws Exception {

	String parser = ",";
	String prescriptionString = "";
	for(int i = 0; i < prescriptions.length; i++)
	{
		prescriptionString = prescriptionString + prescriptions[i] + ":";
	}

    String mergedData = username + parser + password + parser + firstName
        + parser + lastName + parser + gender + parser + userType + parser + age + parser
        + weight + parser + height + parser + bloodType + parser + lastTetnus + parser + lastFlu + parser + hasHernia + parser
		+ hasBloodInUrine + parser + hasDiabetes + parser + "[" + prescriptionString + "]";
    return mergedData;
  }

  public static String[] parseInfo(String userInfo) {
    String[] tokens = userInfo.split(",");

    return tokens;
  }
}
