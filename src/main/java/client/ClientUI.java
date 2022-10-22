package client;

import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

//TODO user name before message
//TODO tool bar for saving file
//TODO event
//TODO event listener
//TODO disable kick self
public class ClientUI extends JFrame{
    //private DefaultListModel listModel;

    private ClientUserList clientUserList;
    private JPanel mainPanel;

    private JPanel canvas;
    private BufferedImage bImg;

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
    private JButton saveButton;
    private JButton loadButton;
    private JButton newButton;

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
        setSize(800, 515);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        setContentPane(mainPanel);
        setVisible(true);

        canvas.setBorder(BorderFactory.createLineBorder(Color.black));
        bImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgGraphics = bImg.createGraphics();
        imgGraphics.setBackground(Color.white);

        canvasListener = new CanvasListener( canvas.getGraphics(), imgGraphics);


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
                bImg.getGraphics().setColor(c);

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
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                File f = new File("screenshot.png");
                try {

                    ImageIO.write(bImg, "png", f);

                    System.out.println("saved");
                } catch (IOException ex) {
                    System.err.println("Save file failed: " + ex);
                }


            }
        });
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                File f = new File("screenshot.png");
                try {
                    bImg = ImageIO.read(f);
                } catch (IOException ex) {
                    System.err.println("Load file failed: " + ex);
                }

                canvas.getGraphics().drawImage(bImg, 0, 0, null);

                canvasListener.setImgGraphics(bImg.getGraphics());

                System.out.println("Loaded");

            }
        });
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String filename = (String) JOptionPane.showInputDialog(canvas, "Do you want to save current graphics?", "save", JOptionPane.PLAIN_MESSAGE, null, new String[]{"yes", "no"}, "yes");
                System.out.println(filename);
            }
        });
    }


}
