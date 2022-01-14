import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;



public class ageChart extends JPanel implements ChangeListener {
    private CarFleetSystem carSystem;
    private Car[] carList;
    private JPanel topPanel = new JPanel();
    private JLabel headingLabel = new JLabel("Age Chart");
    private boolean carsUpdated = false;

    public ageChart(CarFleetSystem carSys){
        carSystem = carSys;
        carList = carSystem.getAllCars();
        carSys.addCarUpdateListener(this);
        headingLabel.setAlignmentX(0.5f);
        setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(headingLabel);


        add(topPanel, "North");
        carList = carSystem.getAllCars();

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

            if (tab.getSelectedIndex() == 6)
            {
                if (carsUpdated)
                {
                    
                    carsUpdated = false;
                }
            }
        }
    }
}
