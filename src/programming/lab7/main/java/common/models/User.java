package common.models;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable,Validator {

    private String login;
    private String password;

    public User(String login, String password) {

        this.login = login;
        this.password = password;
    }





    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    @Override
    public boolean validate() {
        if (this.login == null|| this.login.length()>40 ) return false;
        return (password !=null);

    }

}
