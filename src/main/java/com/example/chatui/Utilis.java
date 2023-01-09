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


}
