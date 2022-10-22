package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//TODO user name before message
//TODO tool bar for saving file
//TODO event
//TODO event listener
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

        chatArea.setEditable(false);

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
                if(userList.getSelectedIndex() != -1) {
                    listModel.removeElementAt(userList.getSelectedIndex());
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
        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        userList = new JList(listModel);
        userPane = new JScrollPane();
        userPane.getViewport().add(userList);

    }
}
