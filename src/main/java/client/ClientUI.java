package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientUI extends JFrame{
    private DefaultListModel listModel;
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

    public ClientUI(){
        setTitle("WhiteBoard-Client");
        setSize(800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setContentPane(mainPanel);
        setVisible(true);

        canvas.setBorder(BorderFactory.createLineBorder(Color.black));
        canvasListener = new CanvasListener(canvas.getGraphics());


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
        ovalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Graphics graphics = canvas.getGraphics();
                canvasListener.setShape("Oval");
                //graphics.drawOval(100, 100, 50, 5);
            }
        });
        triButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Graphics graphics = canvas.getGraphics();
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
