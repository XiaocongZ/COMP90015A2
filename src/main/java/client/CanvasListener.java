package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class CanvasListener implements MouseListener, MouseMotionListener {

    private String shape;

    private Graphics graphics;

    public CanvasListener(Graphics graphics){
        this.graphics = graphics;
        shape = "None";

    }

    private int xPressed;
    private int yPressed;

    private int xReleased;

    private int yReleased;



    public void setShape(String shape){
        this.shape = shape;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int xClicked = e.getX();
        int yClikcked = e.getY();
        if(shape == "Text") {
            String text = JOptionPane.showInputDialog("Annotate:");
            Font f = new Font(null, Font.PLAIN,  10);
            graphics.setFont(f);
            graphics.drawString(text, xClicked, yClikcked);
        }
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
        if(shape!="None"){
            graphics.drawOval(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(yPressed - yReleased));
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
