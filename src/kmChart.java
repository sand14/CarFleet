import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;


public class kmChart extends JPanel implements ChangeListener
{
    private CarFleetSystem carSystem;
    private Car[] carList;
    private JPanel topPanel = new JPanel();
    private JLabel headingLabel = new JLabel("Km Chart");
    private boolean carsUpdated = false;
    private ChartPanel chartPanel;


    public kmChart(CarFleetSystem carSys){
        carSystem = carSys;
        carList = carSystem.getAllCars();
        carSys.addCarUpdateListener(this);
        headingLabel.setAlignmentX(0.5f);
        setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(headingLabel);


        add(topPanel, "North");
        carList = carSystem.getAllCars();
        JFreeChart pieChart = ChartFactory.createPieChart("Cars km", createDataSet());
        chartPanel = new ChartPanel(pieChart);
        topPanel.add(chartPanel);


    }

    private PieDataset createDataSet(){
        DefaultPieDataset dataSet = new DefaultPieDataset();
        double firstcat = 0;
        double secondcat=0;
        double thirdcat=0;
        double fourthcat=0;
        for (int i=0;i<carList.length;i++){
            if(carList[i].getKilometers() <=50000)
                firstcat++;
            else if(carList[i].getKilometers() > 50000 && carList[i].getKilometers() <=100000)
                secondcat++;
            else if(carList[i].getKilometers() > 100000 && carList[i].getKilometers() <=200000)
                thirdcat++;
            else if(carList[i].getKilometers() > 200000)
                fourthcat++;
        }
        dataSet.setValue("<50k", firstcat);
        dataSet.setValue("50k-100k", secondcat);
        dataSet.setValue("100k-200k", thirdcat);
        dataSet.setValue("200k>",fourthcat);
        return dataSet;
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

            if (tab.getSelectedIndex() == 7)
            {
                if (carsUpdated)
                {
                    carList = carSystem.getAllCars();
                    topPanel.remove(chartPanel);
                    JFreeChart pieChart = ChartFactory.createPieChart("Cars age", createDataSet());
                    chartPanel = new ChartPanel(pieChart);
                    topPanel.add(chartPanel);
                    carsUpdated = false;
                }
            }
        }
    }
}
