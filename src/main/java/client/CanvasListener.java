package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class CanvasListener implements MouseListener, MouseMotionListener {

    private String shape;

    private Graphics graphics;

    private Graphics imgGraphics;

    public CanvasListener(Graphics graphics, Graphics imgGraphics){
        this.graphics = graphics;
        this.imgGraphics = imgGraphics;
        shape = "None";
    }

    private int xPressed;
    private int yPressed;

    private int xReleased;

    private int yReleased;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setImgGraphics(Graphics imgGraphics){
        this.imgGraphics = imgGraphics;
    }

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
        if(shape=="Oval"){
            graphics.drawOval(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(yPressed - yReleased));
            imgGraphics.drawOval(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(yPressed - yReleased));
        } else if (shape=="Rect") {
            graphics.drawRect(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(yPressed - yReleased));
            imgGraphics.drawRect(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(yPressed - yReleased));
        } else if (shape=="Circle") {
            graphics.drawOval(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(xPressed - xReleased));
            imgGraphics.drawOval(Math.min(xPressed, xReleased), Math.min(yPressed, yReleased), Math.abs(xPressed - xReleased), Math.abs(xPressed - xReleased));
        } else if (shape=="Line") {
            graphics.drawLine(xPressed, yPressed, xReleased, yReleased);
            imgGraphics.drawLine(xPressed, yPressed, xReleased, yReleased);
        } else if (shape=="Triangle") {
            graphics.drawLine(xPressed, yPressed, xReleased, yReleased);
            imgGraphics.drawLine(xPressed, yPressed, xReleased, yReleased);
            //horizontal
            graphics.drawLine(xPressed, yPressed, xReleased, yPressed);
            imgGraphics.drawLine(xPressed, yPressed, xReleased, yPressed);
            //vertical
            graphics.drawLine(xReleased, yPressed, xReleased, yReleased);
            imgGraphics.drawLine(xReleased, yPressed, xReleased, yReleased);
        } else if (shape=="Text") {
            if(shape == "Text") {
                String text = JOptionPane.showInputDialog("Annotate:");
                if(text!=null) {
                    Font f = new Font(null, Font.PLAIN, Math.min(Math.abs(xPressed - xReleased), 5));
                    graphics.setFont(f);
                    graphics.drawString(text, xPressed, yPressed);
                    imgGraphics.setFont(f);
                    imgGraphics.drawString(text, xPressed, yPressed);
                }
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
            graphics.drawOval(xDragged, yDragged,2, 2);
            imgGraphics.drawOval(xDragged, yDragged,2, 2);

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
