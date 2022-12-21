package database.tables;

import java.util.ArrayList;

public class Users implements DBTable {

    private static Users activeUser;

    private int user_id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String mail;
    private int phone_number;
    private position_user position;

    public Users() {}

    public void setActiveUser(Users user){
        activeUser = user;
    }

    public Users getActiveUser(){
        if(activeUser == null){
            activeUser = new Users();
        }
        return activeUser;
    }

    public Users(int user_id, String login, String password, String name, String surname, String mail, int phone_number, position_user position) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone_number = phone_number;
        this.position = position;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public position_user getPosition() {
        return position;
    }

    public void setPosition(position_user position) {
        this.position = position;
    }

    public ArrayList<String> getPositionsToADD(){
        ArrayList<String> positions = new ArrayList<String>();
        positions.add("Nauczyciel");
        positions.add("Uczeń");
        positions.add("Rodzic");
        positions.add("Sekretariat");
        positions.add("Administrator");
        return positions;
    }

    public void setPositionByString(String pos){
        switch (pos) {
            case "admin":
                this.position = position_user.ADMIN;
                break;
            case "Administrator":
                this.position = position_user.ADMIN;
                break;
            case "sekretariat":
                this.position = position_user.SEKRETARIAT;
                break;
            case "Sekretariat":
                this.position = position_user.SEKRETARIAT;
                break;
            case "nauczyciel":
                this.position = position_user.NAUCZYCIEL;
                break;
            case "Nauczyciel":
                this.position = position_user.NAUCZYCIEL;
                break;
            case "rodzic":
                this.position = position_user.RODZIC;
                break;
            case "Rodzic":
                this.position = position_user.RODZIC;
                break;
            case "uczen":
                this.position = position_user.UCZEN;
                break;
            case "Uczeń":
                this.position = position_user.UCZEN;
                break;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", phone_number=" + phone_number +
                ", position=" + position +
                '}';
    }
}