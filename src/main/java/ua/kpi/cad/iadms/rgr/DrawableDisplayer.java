package ua.kpi.cad.iadms.rgr;

import weka.core.Drawable;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawableDisplayer {

    private final Drawable drawable;
    private JFrame frame;

    public DrawableDisplayer(Drawable drawable) {
        this.drawable = drawable;
    }

    public void display() {
        initializeFrame();
        Component component = getComponent(drawable);
        display(component);
    }

    private Component getComponent(Drawable drawable) {
        try {
            String graph = drawable.graph();
            return new TreeVisualizer(null, graph, new PlaceNode2());
        } catch (Exception e) {
            return new TextArea("Error occured while building the tree");
        }
    }

    private void initializeFrame() {
        frame = new JFrame("Decision Tree");
        frame.setSize(1400, 700);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    private void display(Component component) {
        frame.getContentPane().add(component, BorderLayout.CENTER);
        frame.setVisible(true);
        if (component instanceof TreeVisualizer) {
            ((TreeVisualizer) component).fitToScreen();
        }
    }
}
