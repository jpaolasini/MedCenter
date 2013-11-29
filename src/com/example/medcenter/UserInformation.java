package com.example.medcenter;

public class UserInformation {
  static String userName;
  static String userPassword;
  static String firstName;
  static String lastName;
  static String userType;
  static int userAge;
  static double[] weight;
  static double height;
  static String bloodType;
  static String prescripions;

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

  public int getUserAge() {
    return userAge;
  }

  public double[] getWeight() {
    return weight;
  }

  public double getheight() {
    return height;
  }

  public String getPrescripions() {
    return prescripions;
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

  public void setUserAge(int userAge) {
    this.userAge = userAge;
  }

  public void setWeight(double[] weight) {
    this.weight = weight;
  }

  public void setheight(double height) {
    this.height = height;
  }

  public void setPrescripions(String prescripions) {
    this.prescripions = prescripions;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public static String mergePatientInfo(String username, String password,
    String firstName, String lastName, String userType, String height,
    String weight, String age, String bloodType) throws Exception {
    String username1 = new String(AESencrp.encrypt(username.getBytes()));
    String password1 = new String(AESencrp.encrypt(password.getBytes()));
    String firstName1 = new String(AESencrp.encrypt(firstName.getBytes()));
    String lastName1 = new String(AESencrp.encrypt(lastName.getBytes()));
    String userType1 = new String(AESencrp.encrypt(userType.getBytes()));
    String age1 = new String(AESencrp.encrypt(age.getBytes()));
    String weight1 = new String(AESencrp.encrypt(weight.getBytes()));
    String height1 = new String(AESencrp.encrypt(height.getBytes()));
    String bloodType1 = new String(AESencrp.encrypt(bloodType.getBytes()));

    String parser = ",";
    String mergedData = username + parser + password + parser + firstName
        + parser + lastName + parser + userType + parser + age + parser
        + weight1 + parser + height1 + parser + bloodType1;
    return mergedData;
  }

  public static String[] parseInfo(String userInfo) {
    String[] tokens = userInfo.split(",");

    return tokens;
  }
}
