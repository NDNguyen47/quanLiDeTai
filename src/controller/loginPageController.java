package controller;


import java.beans.Statement;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

public class loginPageController implements Initializable {
    @FXML
    private Button changePass_backBtn;

    @FXML
    private PasswordField changePass_cPassword;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private TextField forgot_answer;

    @FXML
    private Button forgot_backBtn;

    @FXML
    private AnchorPane forgot_form;

    @FXML
    private Button forgot_proceedBtn;

    @FXML
    private ComboBox<?> forgot_selectQuestion;

    @FXML
    private TextField forgot_username;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_createAccount;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private TextField login_showPassword;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField signup_answer;

    @FXML
    private Button signup_btn;

    @FXML
    private PasswordField signup_cPassword;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_loginAccount;

    @FXML
    private PasswordField signup_password;

    @FXML
    private ComboBox<?> signup_selectQuestion;

    @FXML
    private TextField signup_username;

    public void login() {
        alertMessage alert = new alertMessage();
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            alert.errorMessage("Incorrect Username/Password");
        } else {
            String selectData = "SELECT * FROM user WHERE"
                    + "username = ? and password = ? ";

            if(login_selectShowPassword.isSelected()) {
                login_password.setText(login_showPassword.getText());
            } else {
                login_showPassword.setText(login_password.getText());
            }


            try {
                
                if (UserDAO.Instance().isUsernameExist(login_username.getText())) {
                    alert.successMessage("Successfully Login!");

                    Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                    
                    login_btn.getScene().getWindow().hide();

                } else {
                    alert.errorMessage("Incorrect Username/Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showPassword() {
        if (login_selectShowPassword.isSelected()) {
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        } else {
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    }

    public void forgotPassword() {
        alertMessage alert = new alertMessage();

        if (forgot_username.getText().isEmpty()
                || forgot_selectQuestion.getSelectionModel().getSelectedItem() == null
                || forgot_answer.getText().isEmpty()) {
            alert.errorMessage("please fill all blank fields");
        } else {
            String checkData = "SELECT username, question, answer FROM users"
                    + "WHERE username = ? AND question = ? AND answer = ?";

            try {

                if (UserDAO.Instance().isUsernameExist(forgot_username.getText())) {
                    signup_form.setVisible(false);
                    login_form.setVisible(false);
                    forgot_form.setVisible(false);
                    changePass_form.setVisible(true);
                } else {
                    alert.errorMessage("Incorrect information");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void forgotListQuestion() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        forgot_selectQuestion.setItems(listData);
    }

    public void register() {

        alertMessage alert = new alertMessage();

        if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty()
                || signup_password.getText().isEmpty() || signup_cPassword.getText().isEmpty()
                || signup_selectQuestion.getSelectionModel().getSelectedItem() == null
                || signup_answer.getText().isEmpty()) {
            alert.errorMessage("All fields are necessary to be filled");
        } else if (signup_password.getText() == signup_cPassword.getText()) {
            alert.errorMessage("Password does not match");
        } else if (signup_password.getText().length() < 8) {
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            try {

                if (UserDAO.Instance().isUsernameExist(signup_username.getText())) {
                    alert.errorMessage(signup_username.getText() + "is already taken");
                } else {
                    User user = new User();
                    user.setUsername(signup_username.getText());
                    user.setPassword(signup_password.getText());
                    user.setQuestion((String) signup_selectQuestion.getSelectionModel().getSelectedItem());
                    user.setAnswer(signup_answer.getText());
                    
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    user.setDate(String.valueOf(sqlDate));

                    UserDAO.Instance().insert(user);
                    alert.successMessage("Register Successfully!");

                    registerClearFields();

                    signup_form.setVisible(false);
                    login_form.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerClearFields() {
        signup_email.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_selectQuestion.getSelectionModel().clearSelection();
        signup_answer.setText("");
    }

    public void changPassword() {
        alertMessage alert = new alertMessage();

        if (changePass_password.getText().isEmpty() || changePass_cPassword.getText().isEmpty()) {
            alert.errorMessage("please fill all bank fields");
        } else if (!changePass_password.getText().equals(changePass_cPassword.getText())) {
            alert.errorMessage("Password does not match");
        } else if (changePass_password.getText().length() < 0) {
            alert.errorMessage("Invalid Password, at least 8 characters needed");
        } else {
            String updateData = "UPDATE users SET password = ?, update_date = ?"
                    + "WHERE username = '" + forgot_username.getText() + "'";

            try {
                User user = new User();
                user.setPassword(changePass_password.getText());

                Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    user.setDate(String.valueOf(sqlDate));

                alert.successMessage("Succesfully changed Password");
                signup_form.setVisible(false);
                login_form.setVisible(true);
                forgot_form.setVisible(false);
                changePass_form.setVisible(false);

                login_username.setText("");
                login_password.setVisible(true);
                login_password.setText("");

                login_showPassword.setVisible(false);

                login_selectShowPassword.setSelected(false);

                changePass_password.setText("");
                changePass_cPassword.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == signup_loginAccount || event.getSource() == forgot_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(true);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_createAccount) {
            signup_form.setVisible(true);
            login_form.setVisible(false);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        } else if (event.getSource() == login_forgotPassword) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);

            forgotListQuestion();
        } else if (event.getSource() == changePass_backBtn) {
            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);
        }
    }

    private String[] questionList = { "what is your favorite food?", "what is your favorite color?",
            "what is thew name of your pet?", "what is your most favorite sport?" };

    public void questions() {
        List<String> listQ = new ArrayList<>();
        for (String data : questionList) {
            listQ.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectQuestion.setItems(listData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        questions();

        forgotListQuestion();
    }

}
