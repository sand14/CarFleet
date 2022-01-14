import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class manufacturerChart extends JPanel implements ChangeListener
{
    private CarFleetSystem carSystem;
    private Car[] carList;
    private JPanel topPanel = new JPanel();
    private JLabel headingLabel = new JLabel("Age Chart");
    private boolean carsUpdated = false;
    private ChartPanel chartPanel;


    public manufacturerChart(CarFleetSystem carSys){
        carSystem = carSys;
        carList = carSystem.getAllCars();
        carSys.addCarUpdateListener(this);
        headingLabel.setAlignmentX(0.5f);
        setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(headingLabel);


        add(topPanel, "North");
        carList = carSystem.getAllCars();
        JFreeChart barChart= ChartFactory.createBarChart("Cars Kms", "Manufacturer","Number", createDataSet());
        chartPanel = new ChartPanel(barChart);
        topPanel.add(chartPanel);


    }

    private CategoryDataset createDataSet(){
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        int manufacturers = (int)carSystem.getStatistics(CarFleetSystem.MANUFACTURERS_COUNT);

        for(int i=0; i<carList.length;i++){
        dataset.setValue(0, "Number", carList[i].getManufacturer());
        }
        for(int i=0; i<carList.length;i++){
            dataset.incrementValue(1, "Number", carList[i].getManufacturer());
        }
        return dataset;
    }
    public void carsUpdated(CarUpdateEvent ev)
    {
        if (ev.getSource() == carSystem)
        {

            carsUpdated = true;
        }
    }
    public void stateChanged(ChangeEvent ev)
    {

        if (ev.getSource() instanceof JTabbedPane)
        {
            JTabbedPane tab = (JTabbedPane)ev.getSource();

            if (tab.getSelectedIndex() == 8)
            {
                if (carsUpdated)
                {
                    carList = carSystem.getAllCars();
                    topPanel.remove(chartPanel);
                    JFreeChart barChart= ChartFactory.createBarChart("Cars Kms", "Manufacturer","Number", createDataSet());
                    chartPanel = new ChartPanel(barChart);
                    topPanel.add(chartPanel);
                    carsUpdated = false;
                }
            }
        }
    }
}
