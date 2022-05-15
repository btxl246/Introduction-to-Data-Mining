import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;

/**
 * Processes ARFF
 */
public class ARFFProcessor {

    /**
     * Empty constructor
     */
    public ARFFProcessor() {
    }

    /**
     * Creates a .arff file from a .csv file
     * @param inputCSV fileName
     * @param outputARFF fileName
     */
    public void createARFF(String inputCSV, String outputARFF) {
        try {
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File(inputCSV));
            Instances data = loader.getDataSet();

            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File(outputARFF));
            saver.writeBatch();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
