package util.model;

public class Client {
    private String name;
    private String accountNumber;
    private String address;
    private int mobilePhoneNumber;
    private int PhoneNumber;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accNum) {
        this.accountNumber = accNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public int getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(int number) {
        mobilePhoneNumber = number;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int number) {
        PhoneNumber = number;
    }
}