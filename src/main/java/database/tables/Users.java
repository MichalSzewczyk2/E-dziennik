package database.tables;

import com.example.chatui.Utilis;

import java.util.ArrayList;

public class Users implements DBTable {

    private static Users activeUser;

    private static Users activeParent;

    private static ArrayList<Users> userList;

    private int user_id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String mail;
    private int phone_number;
    private position_user position;

    public Users() {}

    public static void setActiveUser(Users user){
        activeUser = user;
    }

    public static Users getActiveUser(){
        if(activeUser == null){
            activeUser = new Users();
        }
        return activeUser;
    }

    public static Users getActiveParent() {
        return activeParent;
    }

    public static void setActiveParent(Users activeParent) {
        Users.activeParent = activeParent;
    }

    public static void addUserToList(Users user){
        Users.userList.add(user);
    }

    public static ArrayList<Users> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<Users> userList) {
        Users.userList = userList;
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

    public int getUserId() {
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

    public int getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(int phone_number) {
        this.phone_number = phone_number;
    }

    public position_user getPosition() {
        return position;
    }

    public void setPosition(position_user position) {
        this.position = position;
    }

    public ArrayList<String> getPositionsToADD(){
        ArrayList<String> positions = new ArrayList<>();
        positions.add("Nauczyciel");
        positions.add("Uczeń");
        positions.add("Rodzic");
        positions.add("Sekretariat");
        positions.add("Administrator");
        return positions;
    }

    public String getNaturalPosition(){
        switch(position){
            case ADMIN:
                return "Administrator";
            case UCZEN:
                return "Uczeń";
            case RODZIC:
                return "Rodzic";
            case NAUCZYCIEL:
                return "Nauczyciel";
            case SEKRETARIAT:
                return "Sekretariat";
            default:
                return null;
        }
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

    public static Users decryptUser(Users user) {
        Users result = new Users();
        result.setUser_id(user.getUserId());
        result.setLogin(Utilis.decrypt(user.getLogin()));
        result.setPassword(Utilis.decrypt(user.getPassword()));
        result.setName(Utilis.decrypt(user.getName()));
        result.setSurname(Utilis.decrypt(user.getSurname()));
        result.setMail(Utilis.decrypt(user.getMail()));
        result.setPhoneNumber(user.getPhoneNumber());
        result.setPosition(user.getPosition());
        return result;
    }

    public static Users encryptUser(Users user) {
        Users result = new Users();
        result.setUser_id(user.getUserId());
        result.setLogin(Utilis.encrypt(user.getLogin()));
        result.setPassword(Utilis.encrypt(user.getPassword()));
        result.setName(Utilis.encrypt(user.getName()));
        result.setSurname(Utilis.encrypt(user.getSurname()));
        result.setMail(Utilis.encrypt(user.getMail()));
        result.setPhoneNumber(user.getPhoneNumber());
        result.setPosition(user.getPosition());
        return result;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
