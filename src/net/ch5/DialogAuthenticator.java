package net.ch5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class DialogAuthenticator extends Authenticator {
    private JDialog passwordDialog;
    private JTextField usernameFiled=new JTextField(20);
    private JPasswordField passwordField=new JPasswordField(20);
    private JButton okButton=new JButton("Ok");
    private JButton cancelButton=new JButton("Cancel");
    private JLabel mainLabel=new JLabel("Пожалуйста, введите имя пользователя и пароль: ");

    public DialogAuthenticator(){
        this("",new JFrame());
    }

    public DialogAuthenticator(String username){
        this(username,new JFrame());
    }

    public DialogAuthenticator(JFrame parent){
        this("",parent);
    }

    public DialogAuthenticator(String username,JFrame parent){
        this.passwordDialog=new JDialog(parent,true);
        Container pane=passwordDialog.getContentPane();
        pane.setLayout(new GridLayout(4,1));


        parent.setSize(300,200);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setVisible(true);

        JLabel userLabel=new JLabel("Имя пользователя: ");
        JLabel passwordLabel=new JLabel("Пароль: ");
        pane.add(mainLabel);
        JPanel p2=new JPanel();
        p2.add(userLabel);
        p2.add(usernameFiled);
        usernameFiled.setText(username);
        pane.add(p2);
        JPanel p3=new JPanel();
        p3.add(passwordLabel);
        p3.add(passwordField);
        pane.add(p3);
        JPanel p4=new JPanel();
        p4.add(okButton);
        p4.add(cancelButton);
        pane.add(p4);
        passwordDialog.pack();

        parent.add(pane);

        ActionListener al=new OKResponse();
        okButton.addActionListener(al);
        passwordField.addActionListener(al);
        cancelButton.addActionListener(new CancelResponse());
    }

    private void show(){
        String prompt=this.getRequestingPrompt();
        if (prompt==null){
            String site=this.getRequestingSite().getHostName();
            String protocol=this.getRequestingProtocol();
            int port=this.getRequestingPort();
            if (site!=null & protocol!=null){
                prompt=protocol+"://"+site;
                if (port>0) prompt+=":"+port;
            }else {
                prompt="";
            }
        }
        mainLabel.setText("Пожалуйста, введите имя пользователя и пароль для "+
                prompt+": ");
        passwordDialog.pack();
        passwordDialog.setVisible(true);
    }

    PasswordAuthentication response=null;

    class OKResponse implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);
            // Пароль возвращается в виде массива символов
            // по соображениям безопасности
            char[] password=passwordField.getPassword();
            String username=usernameFiled.getText();
            // Сотрите пароль на случай, если он будет использован снова.
            passwordField.setText("");
            response=new PasswordAuthentication(username,password);
        }
    }

    class CancelResponse implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordDialog.setVisible(false);
            // Сотрите пароль на случай, если он будет использован снова.
            passwordField.setText("");
            response=null;
        }
    }

    public PasswordAuthentication getPasswordAuthentication(){
        this.show();
        return this.response;
    }
}
