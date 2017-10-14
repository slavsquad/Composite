package com.ihamen.Shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompoundShape extends BaseShape {
    private Shape selectedShape;
    protected List<com.ihamen.Shapes.Shape> children = new ArrayList<>();

    public CompoundShape(){
        super(0, 0, Color.BLACK);
    }

    public CompoundShape(com.ihamen.Shapes.Shape... components) {
        super(0, 0, Color.BLACK);
        add(components);
    }

    public void add(com.ihamen.Shapes.Shape component) {
        children.add(component);
    }

    public void add(com.ihamen.Shapes.Shape... components) {
        children.addAll(Arrays.asList(components));
    }

    public void remove(com.ihamen.Shapes.Shape child) {
        int i = children.indexOf(child);
        children.remove(i);
    }

    public void remove(CompoundShape components) {
        children.removeAll(Arrays.asList(components));
    }

    public void clear() {
        children.clear();
    }

    @Override
    public int getX() {
        if (children.size() == 0) {
            return 0;
        }
        int x = children.get(0).getX();
        for (com.ihamen.Shapes.Shape child : children) {
            if (child.getX() < x) {
                x = child.getX();
            }
        }
        return x;
    }

    @Override
    public int getY() {
        if (children.size() == 0) {
            return 0;
        }
        int y = children.get(0).getY();
        for (com.ihamen.Shapes.Shape child : children) {
            if (child.getY() < y) {
                y = child.getY();
            }
        }
        return y;
    }

    @Override
    public int getWidth() {
        int maxWidth = 0;
        int x = getX();
        for (com.ihamen.Shapes.Shape child : children) {
            int childsRelativeX = child.getX() - x;
            int childWidth = childsRelativeX + child.getWidth();
            if (childWidth > maxWidth) {
                maxWidth = childWidth;
            }
        }
        return maxWidth;
    }

    @Override
    public int getHeight() {
        int maxHeight = 0;
        int y = getY();
        for (com.ihamen.Shapes.Shape child : children) {
            int childsRelativeY = child.getY() - y;
            int childHeight = childsRelativeY + child.getHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight;
    }

    @Override
    public void move(int x, int y) {
        for (com.ihamen.Shapes.Shape child : children) {
            child.move(x, y);
        }
    }

    @Override
    public Boolean isInsideBounds(int x, int y) {
        for (com.ihamen.Shapes.Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unSelect() {
        super.unSelect();
        for (com.ihamen.Shapes.Shape child : children) {
            child.unSelect();
            selectedShape = null;
        }
    }

    public Boolean selectChildAt(int x, int y) {
        for (com.ihamen.Shapes.Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                child.select();
                selectedShape = child;
                return true;
            }
        }
        return false;
    }

    public Shape getSelectedShape() {
        return selectedShape;
    }

    @Override
    public void paint(Graphics graphics) {
        if (isSelected()) {
            enableSelectionStyle(graphics);
            graphics.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            disableSelectionStyle(graphics);
        }

        for (com.ihamen.Shapes.Shape child : children) {
            child.paint(graphics);
        }
    }
}