import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class BarChart extends JFrame {
    private HashMap<String, Integer> map;   // Frequency

    /**
     * Constructor
     * @param appTitle
     * @param xAxisTitle
     * @param yAxisTitle
     * @param map
     */
    public BarChart(String appTitle, String xAxisTitle, String yAxisTitle, HashMap<String, Integer> map) {
        super(appTitle);

        this.map = map;

        CategoryDataset dataset = createDataset();

        JFreeChart chart =  ChartFactory.createBarChart("Bar Chart", xAxisTitle, yAxisTitle, dataset,
                PlotOrientation.VERTICAL, true,true,false);

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();

        Random rand = new Random();     // Randomize color for each value
        for (int i = 0; i < this.map.keySet().size(); i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            renderer.setSeriesPaint(i, color);
        }

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String key : this.map.keySet()) {
            dataset.addValue(this.map.get(key), key, "");
        }

        return dataset;
    }
}  