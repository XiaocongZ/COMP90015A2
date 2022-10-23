package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.rmi.RemoteException;


public class CanvasListener implements MouseListener, MouseMotionListener {
    private String name = "CanvasListener";

    private String shape;

    private ClientUI clientUI;

    private ClientDrawer clientDrawer;

    public void setClientDrawer(ClientDrawer clientDrawer) {
        this.clientDrawer = clientDrawer;
    }

    public CanvasListener(ClientUI clientUI){
        this.clientUI = clientUI;
        shape = "None";
    }

    private int xPressed;
    private int yPressed;

    private int xReleased;

    private int yReleased;
    /*
    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setImgGraphics(Graphics imgGraphics){
        this.imgGraphics = imgGraphics;
    }
    */

    public void setShape(String shape){
        this.shape = shape;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int xClicked = e.getX();
        int yClikcked = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        xPressed = e.getX();
        yPressed = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        xReleased = e.getX();
        yReleased = e.getY();
        String[] command = null;
        if(shape=="Oval"){
            command = new String[]{name, shape, String.valueOf(Math.min(xPressed, xReleased)),
                    String.valueOf(Math.min(yPressed, yReleased)),
                    String.valueOf(Math.abs(xPressed - xReleased)),
                    String.valueOf(Math.abs(yPressed - yReleased))};
            clientUI.draw(command);

        } else if (shape=="Rect") {
            command = new String[]{name, shape, String.valueOf(Math.min(xPressed, xReleased)),
                    String.valueOf(Math.min(yPressed, yReleased)),
                    String.valueOf(Math.abs(xPressed - xReleased)),
                    String.valueOf(Math.abs(yPressed - yReleased))};
            clientUI.draw(command);
        } else if (shape=="Circle") {
            command = new String[]{name, shape, String.valueOf(Math.min(xPressed, xReleased)),
                    String.valueOf(Math.min(yPressed, yReleased)),
                    String.valueOf(Math.abs(xPressed - xReleased)),
                    String.valueOf(Math.abs(xPressed - xReleased))};
            clientUI.draw(command);
        } else if (shape=="Line") {
            command = new String[]{name, shape, String.valueOf(xPressed),
                    String.valueOf(yPressed),
                    String.valueOf(xReleased),
                    String.valueOf(yReleased)};
            clientUI.draw(command);
        } else if (shape=="Triangle") {
            command = new String[]{name, shape, String.valueOf(xPressed),
                    String.valueOf(yPressed),
                    String.valueOf(xReleased),
                    String.valueOf(yReleased)};

            clientUI.draw(command);

        } else if (shape=="Text") {
            String text = JOptionPane.showInputDialog("Annotate:");
            if(text!=null) {
                command = new String[]{name, shape, String.valueOf(Math.max(Math.abs(xPressed - xReleased), 5)),
                        text,
                        String.valueOf(xPressed),
                        String.valueOf(yPressed)};
                clientUI.draw(command);
            }
        }
        if(command != null) {
            try {

                clientDrawer.commit(command);
            } catch (RemoteException ex) {
                System.err.println("RemoteException when commit draw: " + ex);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xDragged = e.getX();
        int yDragged = e.getY();
        if(shape == "Free"){
            String[] command = new String[]{name, shape, String.valueOf(xDragged),
                    String.valueOf(yDragged),
                    String.valueOf(2),
                    String.valueOf(2)};
            clientUI.draw(command);

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
