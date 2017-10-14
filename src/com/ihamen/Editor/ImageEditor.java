package com.ihamen.Editor;

import com.ihamen.Shapes.CompoundShape;
import com.ihamen.Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageEditor extends JFrame{
    private CompoundShape allShapes;
    private CanvasPanel canvasPanel;

    public ImageEditor(String title) {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        allShapes = new CompoundShape();
        canvasPanel = new CanvasPanel();
        canvasPanel.setBackground(Color.WHITE);

        add(canvasPanel);
        setSize(640,480);
        setVisible(true);
    }

    public void loadShapes(com.ihamen.Shapes.Shape... shapes) {
        allShapes.clear();
        allShapes.add(shapes);
    }

    class CanvasPanel extends JPanel{

        private int startX;
        private int startY;
        private static final int PADDING = 10;

        public CanvasPanel() {

            setOpaque(true);

            addMouseListener(new MouseAdapter() {


                @Override
                public void mousePressed(MouseEvent e) {

                    allShapes.unSelect();
                    allShapes.selectChildAt(e.getX(), e.getY());
                    startX = e.getX();
                    startY = e.getY();
                    repaint();

                }


            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    Shape shape = allShapes.getSelectedShape();
                    int shiftX,shiftY,oldX,oldY,newX,newY,x,y,width,height;

                    if (shape != null) {

                        oldX = shape.getX();
                        oldY = shape.getY();

                        shiftX = e.getX() - startX;
                        shiftY = e.getY() - startY;

                        shape.move(shiftX, shiftY);

                        newX = shape.getX();
                        newY = shape.getY();

                        x = Math.min(oldX,newX)-1;
                        y = Math.min(oldY,newY)-1;

                        width = shape.getWidth()+Math.abs(shiftX)+2;
                        height = shape.getHeight()+Math.abs(shiftY)+2;

                        repaint(x,y,width,height);

                        startX = e.getX();
                        startY = e.getY();
                    }
                }

            });

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            allShapes.paint(g);
        }
    }
}
