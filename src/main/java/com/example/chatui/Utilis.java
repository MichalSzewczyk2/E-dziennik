package com.example.chatui;

import database.repository.UserRepository;
import database.tables.Users;
import database.tables.position_user;

import java.io.IOException;

public class Utilis {

    public void goToStarPage(){
        Main m = new Main();
        Users user = new Users().getActiveUser();
        try {
            if(user.getPosition() == position_user.UCZEN){
                m.changeScene("start_page.fxml",1280,720);
            }
            else if(user.getPosition() == position_user.ADMIN){
                m.changeScene("admin_start_page.fxml",1280,720);
            }
            else if(user.getPosition() == position_user.NAUCZYCIEL){
                m.changeScene("teacher_start_page.fxml",1280,720);

            }
            else if(user.getPosition() == position_user.RODZIC){
                Users.setActiveParent(Users.getActiveUser());
                Users.setActiveUser(new UserRepository().getUserByParentId(Users.getActiveParent().getUserId()));
                System.out.println(Users.getActiveUser());
                System.out.println(Users.getActiveParent());
                m.changeScene("start_page.fxml",1280,720);
            }
            else if(user.getPosition() == position_user.SEKRETARIAT){
                m.changeScene("admin_start_page.fxml",1280,720);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public static String encrypt(String plainText) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    encryptedText.append((char) ('A' + (c - 'A' + 5) % 26));
                } else {
                    encryptedText.append((char) ('a' + (c - 'a' + 5) % 26));
                }
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    plainText.append((char) ('A' + (c - 'A' - 5 + 26) % 26));
                } else {
                    plainText.append((char) ('a' + (c - 'a' - 5 + 26) % 26));
                }
            } else {
                plainText.append(c);
            }
        }
        return plainText.toString();
    }


}
