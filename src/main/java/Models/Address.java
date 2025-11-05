package Models;

public class Address implements  Validator {
    private String zipCode; //Длина строки не должна быть больше 26, Поле может быть null

    public Address(String zipCode) {
        this.zipCode = zipCode;
    }


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean validate(){
        return (zipCode.length()<26);
    }

    @Override
    public String toString() {
        return zipCode;
    }
}


