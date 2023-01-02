package com.example.chatui;

import database.repository.GroupMessageRepository;
import database.repository.GroupNameRepository;
import database.repository.MessagesRepository;
import database.repository.UserRepository;
import database.tables.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ChatsController {

    @FXML
    private ListView<GroupName> chatList;

    @FXML
    private TextArea chat;

    @FXML
    private TextField input;

    @FXML
    private TextField searchField;

    @FXML
    private Label chatName;

    @FXML
    private ImageView errimg;

    @FXML
    private Button sendButton;

    @FXML
    private Button directButton;

    @FXML
    private Button groupButton;

    private GroupName groupName;

    private Users user;

    @FXML
    public void initialize(){
        chatName.setOpacity(0);
        user = new Users().getActiveUser();
        groupName = GroupName.getGroupName();
        if(groupName != null){
            ArrayList<Messages> messages = new MessagesRepository().getMessagesByChatId(groupName.getChat_id());
            chatName.setOpacity(1);
            chatName.setText(trimLastCharacters(groupName.getName(),3));
            for(Messages mes : messages){
                Users user = new UserRepository().getUserById(mes.getUser_id());
                String m = chat.getText();
                m += "[" + user.getName() + " " + user.getSurname() + "]: " + mes.getMessage() + "\n";
                chat.setText(m);
            }
        }
        chatList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<GroupName> call(ListView<GroupName> ListView) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(chatList.getPrefWidth() - 20);
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
        ArrayList<GroupName> chatL = new ArrayList<>();
        ArrayList<GroupMessage> chats = new GroupMessageRepository().getGroupMessagesByUserId(user.getUserId());
        for(GroupMessage gr: chats) {
            GroupName gn = new GroupNameRepository().getGroupNameByChatId(gr.getChat_id());
            if(gr.getLast_read() != new MessagesRepository().getNewestMessageInChat(gr.getChat_id()).getMessage_id()){
                gn.setName(gn.getName() + " !!!");
            }else{
                gn.setName(gn.getName() + "    ");
            }
            chatL.add(gn);
        }
        chatList.getItems().addAll(chatL);

        chatList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<GroupName>() {
            @Override
            public void changed(ObservableValue<? extends GroupName> observableValue, GroupName announcements, GroupName t1){
                groupName = chatList.getSelectionModel().getSelectedItem();
            }
        });

    }

    @FXML
    public void goBack(ActionEvent event) {
        GroupName.setGroupName(null);
        new Utilis().goToStarPage();
    }

    @FXML
    public void seeChat(){
        GroupName.setGroupName(groupName);
        chat.setText("");
        ArrayList<Messages> messages = new MessagesRepository().getMessagesByChatId(groupName.getChat_id());
        chatName.setOpacity(1);
        chatName.setText(trimLastCharacters(groupName.getName(),3));
        for(Messages mes : messages){
            Users user = new UserRepository().getUserById(mes.getUser_id());
            String m = chat.getText();
            m += "[" + user.getName() + " " + user.getSurname() + "]: " + mes.getMessage() + "\n";
            chat.setText(m);
        }
        new GroupMessageRepository().setLastRead(groupName.getChat_id(), user.getUserId(),
                new MessagesRepository().getNewestMessageInChat(groupName.getChat_id()).getMessage_id());

        reloadPage();
    }

    @FXML
    public void send(){
        Messages msg = new Messages();
        msg.setChat_id(groupName.getChat_id());
        msg.setUser_id(user.getUserId());
        msg.setDate(Date.valueOf(LocalDate.now()));
        msg.setMessage(input.getText());
        new MessagesRepository().addMessage(msg);

        seeChat();
    }

    @FXML
    public void search() {
        String s = searchField.getText();
        GroupName gr = new GroupNameRepository().getGroupNameByName(s);
        if(gr.getName() != null){
            groupName = gr;
            groupName.setName(groupName.getName() + "    ");
        }
        else {
            errimg.setOpacity(1);
        }
        seeChat();
    }

    @FXML
    public void createChat() {
        Main m = new Main();
        try{
            m.changeScene("addChat.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String trimLastCharacters(String input, int i) {
        if (input.length() > i) {
            return input.substring(0, input.length() - i);
        } else {
            return input;
        }
    }

    public static void reloadPage(){
        Main m = new Main();
        try{
            m.changeScene("chats.fxml",1280,720);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
