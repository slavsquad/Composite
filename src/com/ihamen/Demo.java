package com.ihamen;

/**
 * Created by stepanenko.sg on 12.10.2017.
 */

import com.ihamen.Editor.ImageEditor;
import com.ihamen.Shapes.Circle;
import com.ihamen.Shapes.CompoundShape;
import com.ihamen.Shapes.Dot;
import com.ihamen.Shapes.Rectangle;

import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();




        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )
        );
    }
}