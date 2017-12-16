package ua.kpi.cad.iadms.rgr;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Get File

        ArffLoader loader = new ArffLoader();
        loader.setSource(new File("rgr_train.arff"));

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

//        BufferedReader datafile = readDataFile("rgr_train.arff");
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


    }
}
