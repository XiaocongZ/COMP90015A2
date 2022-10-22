package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

//TODO user name before message
//TODO tool bar for saving file
//TODO event
//TODO event listener
//TODO thread safety of ClientUserList
public class ClientUI extends JFrame{
    //private DefaultListModel listModel;

    private ClientUserList clientUserList;
    private JPanel mainPanel;

    private JPanel canvas;

    private CanvasListener canvasListener;
    private JButton sendButton;
    private JButton kickButton;

    private JTextField chatInputField;
    private JList userList;
    private JScrollPane userPane;
    private JScrollPane chatPane;
    private JTextArea chatArea;
    private JButton circButton;
    private JButton rectButton;
    private JButton ovalButton;
    private JButton triButton;
    private JButton lineButton;
    private JButton freeButton;
    private JButton textButton;
    private JButton colorButton;
    /*
    public DefaultListModel getListModel(){
        return listModel;
    }

    public void setListModel(DefaultListModel listModel){
        this.listModel = listModel;
    }

    */
    public ClientUI(ClientUserList clientUserList){
        setTitle("WhiteBoard-Client");
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setContentPane(mainPanel);
        setVisible(true);

        canvas.setBorder(BorderFactory.createLineBorder(Color.black));
        canvasListener = new CanvasListener(canvas.getGraphics());

        chatArea.setEditable(false);

        if(clientUserList != null){
            this.clientUserList = clientUserList;
        }

        userList = new JList(clientUserList.getListModel());

        userPane.getViewport().add(userList);

        try {
            clientUserList.add("JOHN");
            clientUserList.add("Doe");
            clientUserList.add("shawn");
            clientUserList.add("yukash");
        } catch (RemoteException e) {
            System.err.println("RemoteException when add user: " + e);
        }


        //add listeners
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
                if(userList.getSelectedValue() != null) {
                    try {
                        clientUserList.removeElement((String) userList.getSelectedValue());
                    }
                    catch (RemoteException er){
                        System.err.println("RemoteException when kick user: " + er);
                    }
                }
            }
        });
        ovalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                canvasListener.setShape("Oval");

            }
        });
        triButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                canvasListener.setShape("Triangle");
            }
        });

        canvas.addMouseListener(canvasListener);
        canvas.addMouseMotionListener(canvasListener);
        textButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasListener.setShape("Text");
            }
        });

        freeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasListener.setShape("Free");
            }
        });
        rectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasListener.setShape("Rect");
            }
        });
        colorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Color c = JColorChooser.showDialog(canvas, "Choose color", null);
                canvasListener.getGraphics().setColor(c);

            }
        });
        circButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasListener.setShape("Circle");
            }
        });
        lineButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvasListener.setShape("Line");
            }
        });
        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                //TODO show canvas after resize
                //canvas.repaint();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
