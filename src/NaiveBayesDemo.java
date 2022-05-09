import java.io.File;
import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * @author Gowtham Girithar Srirangasamy
 *
 */
public class NaiveBayesDemo {
    /** file names are defined */
    public static final String TRAINING_DATA_SET_FILENAME = "vote.arff";
    public static final String TESTING_DATA_SET_FILENAME = "vote.arff";
    public static final String PREDICTION_DATA_SET_FILENAME = "vote.arff";

    /**
     * This method is to load the data set.
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Instances getDataSet(String fileName)  {
        /**
         * we can set the file i.e., loader.setFile("finename") to load the data
         */
        StringToWordVector filter = new StringToWordVector();
        int classIdx = 1;
        /** the arffloader to load the arff file */
        ArffLoader loader = new ArffLoader();
        /** load the traing data */
        try {
            loader.setSource(new File("vote.arff"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * we can also set the file like loader3.setFile(new
         * File("test-confused.arff"));
         */
        //loader.setFile(new File(fileName));
        Instances dataSet = null;
        try {
            dataSet = loader.getDataSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** set the index based on the data given in the arff files */
        dataSet.setClassIndex(classIdx);
        try {
            filter.setInputFormat(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dataSet = Filter.useFilter(dataSet, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    /**
     * This method is used to process the input and return the statistics.
     *
     * @throws Exception
     */
    public static void process() throws Exception {

        Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
        Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
        Instances predictingDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME);
        /** Classifier here is Linear Regression */
        Classifier classifier = new NaiveBayesMultinomial();
        /** */
        classifier.buildClassifier(trainingDataSet);
        /**
         * train the alogorithm with the training data and evaluate the
         * algorithm with testing data
         */
        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        /** Print the algorithm summary */
        System.out.println("** Naive Bayes Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(classifier);
        for (int i = 0; i < predictingDataSet.numInstances(); i++) {
            System.out.println(predictingDataSet.instance(i));
            double index = classifier.classifyInstance(predictingDataSet.instance(i));
            String className = trainingDataSet.attribute(0).value((int) index);
            System.out.println(className);
        }

    }

    public static void main(String[] args) {
        try {
            process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
