package com.example.medcenter;

public class DoctorNurseInformation {
  static String type;
  static String userName;
  static String userPassword;
  static String firstName;
  static String lastName;
  static String department;
  static String number;
  static String email;

  public String getType() {
    return type;
  }

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

  public String getDepartment() {
    return department;
  }

  public String getNumber() {
    return number;
  }

  public String getEmail() {
    return email;
  }

  public void setType(String type) {
    this.type = type;
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

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public static String mergeDoctorNurseInfo(String type, String username,
      String password, String firstName, String lastName, String department,
      String number, String email) throws Exception {

    String parser = ",";
    String mergedData = username + parser + password + parser
        + firstName + parser + lastName + parser + " " + parser +  type + parser + department + parser
        + number + parser + email;
    return mergedData;
  }

  public static String[] parseInfo(String userInfo) {
    String delims = ",";
    String[] tokens = userInfo.split(delims);

    return tokens;
  }
}
