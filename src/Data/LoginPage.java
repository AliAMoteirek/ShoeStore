package Data;

import Model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class LoginPage {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();
    Map<String, String> logininfo;


    LoginPage(Map<String, String> loginInfoOriginal) {

        logininfo = loginInfoOriginal;      // logininfo is r.getLoginInfo in practice

        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);

        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);

        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> {
            if (e.getSource() == loginButton) {

                String userID = userIDField.getText();
                String password = String.valueOf(userPasswordField.getPassword());

                if (logininfo.containsKey(userID)) {
                    if (logininfo.get(userID).equals(password)) {
                        messageLabel.setForeground(Color.green);
                        messageLabel.setText("Login successful");
                        frame.dispose();
                        WelcomePage welcomePage = new WelcomePage(userID.substring(0,userID.indexOf('@')));
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Wrong password");
                    }

                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Username not found");
                }
            }
        });

        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> {
            if (e.getSource() == resetButton) {
                userIDField.setText("");
                userPasswordField.setText("");
            }
        });

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

}