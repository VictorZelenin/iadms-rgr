package ua.kpi.cad.iadms.rgr;

import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        Classifier classifier = buildClassifier();

        testClassifier(classifier);
    }

    private static Classifier buildClassifier() throws Exception {
        Instances trainingData = readDataFromFile("rgr_train.arff");

        trainingData.setClassIndex(trainingData.numAttributes() - 1);

        //Make tree
        J48 tree = new J48();
        String[] options = new String[] {"-U"};

        FilteredClassifier classifier = new FilteredClassifier();
        classifier.setClassifier(tree);

        tree.setOptions(options);
        tree.buildClassifier(trainingData);
        classifier.buildClassifier(trainingData);

        //Print tree
        System.out.println(tree);
        new DrawableDisplayer(tree).display();

        return classifier;
    }

    private static void testClassifier(Classifier classifier) throws Exception {
        Instances testData = readDataFromFile("test_data.arff");
        testData.setClassIndex(testData.numAttributes() - 1);

        PrintWriter printWriter = new PrintWriter(new FileWriter("res.dat"));
        int index = 1;
        for (Instance instance : testData) {
            double v = classifier.classifyInstance(instance);
            String row = validateInstanceAttrs(instance) + " : " + testData.classAttribute().value((int) v);
            System.out.println(row);
            printWriter.println(index + "\t" + row);
            index++;
        }
        printWriter.close();
    }

    private static Instances readDataFromFile(String filename) throws Exception {
        return new ConverterUtils.DataSource(filename).getDataSet();
    }

    private static String validateInstanceAttrs(Instance instance) {
        return instance.toString().substring(0, instance.toString().length() - 2);
    }
}
