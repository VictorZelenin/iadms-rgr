package ua.kpi.cad.iadms.rgr;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.gui.treevisualizer.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class GrapViewerTest {
    public static void main(String[] args) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setSource(new File("maitre.arff"));

        //Get the data
        Instances data = loader.getDataSet();
//        reader.close();

        //Setting class attribute
        data.setClassIndex(data.numAttributes() - 1);

        //Make tree
        J48 tree = new J48();

        String[] options = new String[1];
        options[0] = "-U";
        tree.setOptions(options);
        tree.buildClassifier(data);

        //Print tree
        System.out.println(tree);

        //Predictions with test and training set of data

//        BufferedReader datafile = readDataFile("maitre.arff");
//        BufferedReader testfile = readDataFile("maitretest.txt");

//        Instances train = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);  // from somewhere
//        Instances test = new Instances(testfile);
        data.setClassIndex(data.numAttributes() - 1);    // from somewhere
        // train classifier
        Classifier cls = new J48();
        cls.buildClassifier(data);
        // evaluate classifier and print some statistics
        Evaluation eval = new Evaluation(data);
//        eval.evaluateModel(cls, test);
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));

        //////------------------------

        weka.core.logging.Logger.log(weka.core.logging.Logger.Level.INFO,
                "Logging started");
        // put in the random data generator right here
        // this call with import java.lang gives me between 0 and 1 Math.random
        TreeBuild builder = new TreeBuild();

        Node top = null;
        NodePlace arrange = new PlaceNode2();
        top = builder.create(new StringReader("digraph atree { top [label=\"the top\"] a [label=\"the first node\"] b [label=\"the second nodes\"] c [label=\"comes off of first\"] top->a top->b b->c }"));
//            top = builder.create(new FileReader(args[0]));

        // int num = Node.getCount(top,0); NOT USED
        // System.out.println("counter counted " + num + " nodes");
        // System.out.println("there are " + num + " nodes");
        TreeVisualizer a = new TreeVisualizer(null, top, arrange);
        a.setSize(800, 600);
//         a.setTree(top);
        JFrame f = new JFrame();
        // a.addMouseMotionListener(a);
        // a.addMouseListener(a);
        // f.add(a);
        Container contentPane = f.getContentPane();
        contentPane.add(a);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(800, 600);
        f.setVisible(true);
        // f.
        // find_prop(top);
//         a.setTree(top,arrange);//,(num + 1000), num / 2 + 1000);
    }
}
