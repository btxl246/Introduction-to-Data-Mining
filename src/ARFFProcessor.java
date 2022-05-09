import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.*;

public class ARFFProcessor {
    public static void main(String[] args) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File("./new_data.csv"));
        Instances data = loader.getDataSet();

        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File("./new_data.arff"));
        saver.writeBatch();
    }
}
