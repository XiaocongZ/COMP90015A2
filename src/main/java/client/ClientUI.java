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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TODO user name before message
//TODO new file
//TODO event
//TODO event listener
//TODO disable kick self
public class ClientUI extends JFrame{
    //private DefaultListModel listModel;

    private ClientUserList clientUserList;

    private ClientMessenger clientMessenger;

    private ClientDrawer clientDrawer;
    private JPanel mainPanel;

    private JPanel canvas;
    private BufferedImage bImg;

    /**
     * lock for not only drawing, but reset...
     */
    private Lock drawLock;

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

    public ClientMessenger getClientMessenger() {
        return clientMessenger;
    }

    public void setClientDrawer(ClientDrawer clientDrawer){
        this.clientDrawer = clientDrawer;
        canvasListener.setClientDrawer(clientDrawer);
    }

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

        drawLock = new ReentrantLock();
        //TODO
        canvasListener = new CanvasListener(this);


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

        clientMessenger = new ClientMessenger(chatArea);

        //clientDrawer = new ClientDrawer(this, )


        //add listeners
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String msg = chatInputField.getText();
                try {
                    clientMessenger.commit(msg);
                } catch (RemoteException ex) {
                    System.err.println("RemoteException when send message: " + ex);
                }
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
                canvas.getGraphics().setColor(c);
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

    public void draw(String[] command){
        //TODO username
        if(command[0] == "CanvasListener"){
            return;
        }
        drawLock.lock();
        draw(command, canvas.getGraphics());
        draw(command, bImg.getGraphics());
        drawLock.unlock();
    }

    /**
     * should only invoke when drawLock is held.
     * @param command
     * @param graphics
     */
    private void draw(String[] command, Graphics graphics){

        switch (command[1]){
            case "Line":
                graphics.drawLine(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Oval":
                graphics.drawOval(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Circle":
                graphics.drawOval(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Rect":
                graphics.drawRect(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Free":
                graphics.drawOval(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Triangle":
                graphics.drawLine(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                graphics.drawLine(Integer.valueOf(command[2]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[3]));
                graphics.drawLine(Integer.valueOf(command[4]), Integer.valueOf(command[3]), Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;
            case "Text":
                //name type size string x y
                Font f = new Font(null, Font.PLAIN, Integer.valueOf(command[2]));
                graphics.setFont(f);
                graphics.drawString(command[3], Integer.valueOf(command[4]), Integer.valueOf(command[5]));
                break;

        }
    }

}
