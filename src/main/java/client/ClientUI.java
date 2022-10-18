package client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientUI extends JFrame{
    private DefaultListModel listModel;
    private JPanel mainPanel;

    private JPanel canvas;
    private JButton sendButton;
    private JButton kickButton;

    private JTextField chatInputField;
    private JList userList;
    private JScrollPane userPane;
    private JScrollPane chatPane;
    private JTextArea chatArea;

    public ClientUI(){
        setTitle("WhiteBoard-Client");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setContentPane(mainPanel);




        setVisible(true);


        /*
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String msg = chatInputField.getText();

                msg = chatField.getText() + msg + "\n";
                chatField.setText(msg);
                chatInputField.setText("");
            }
        });

         */

        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String msg = chatInputField.getText();
                msg = chatArea.getText() + msg + "\n";
                chatArea.setText(msg);
                chatInputField.setText("");
            }
        });
        kickButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listModel.addElement("zxc");

            }
        });
    }

    public static void main(String[] args){
        ClientUI myUI = new ClientUI();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");


        userList = new JList(listModel);
        userPane = new JScrollPane();
        userPane.getViewport().add(userList);

    }
}
