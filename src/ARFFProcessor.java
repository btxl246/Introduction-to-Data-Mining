import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;

public class ARFFProcessor {
    public ARFFProcessor() {
    }

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
