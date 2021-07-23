package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("ID")
    public int id ;
    @SerializedName("UserKey")
    public String userKey ;
    @SerializedName("Email")
    public String email ;
    @SerializedName("Username")
    public String username ;
    @SerializedName("GenderID")
    public int genderID ;
    @SerializedName("Gender")
    public Gender Gender ;
    @SerializedName("Age")
    public int age ;
    @SerializedName("Town")
    public String town ;
    @SerializedName("AccountTypeID")
    public int accountTypeID ;
    @SerializedName("AccountType")
    public AccountType AccountType ;
    @SerializedName("ProvinceID")
    public int provinceID ;
    @SerializedName("Province")
    public Province province;
    @SerializedName("UserImageID")
    public int userImageID;
    @SerializedName("UserImage")
    public UserImage userImage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGenderID() {
        return genderID;
    }

    public void setGenderID(int genderID) {
        this.genderID = genderID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAccountTypeID() {
        return accountTypeID;
    }

    public void setAccountTypeID(int accountTypeID) {
        this.accountTypeID = accountTypeID;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getUserImageID() {
        return userImageID;
    }

    public void setUserImageID(int userImageID) {
        this.userImageID = userImageID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return Gender;
    }

    public void setGender(Gender gender) {
        Gender = gender;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public AccountType getAccountType() {
        return AccountType;
    }

    public void setAccountType(AccountType accountType) {
        AccountType = accountType;
    }
}


