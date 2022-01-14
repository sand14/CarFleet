

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;


public class ShowCarsList extends JPanel implements ChangeListener
{
    private CarFleetSystem carSystem;
    private Car[] carList;
    private JPanel topPanel = new JPanel();
    private JLabel headingLabel = new JLabel("Show all makes and models");
    private boolean carsUpdated = false;
    String[] columns = new String[]{
            "Nr","Manufacturer", "Model" , "Km", "Age"
    };
    DefaultTableModel model= new DefaultTableModel(columns,0);

    public ShowCarsList(CarFleetSystem carSys) {
        carSystem = carSys;
        carList = carSystem.getAllCars();
        carSys.addCarUpdateListener(this);
        headingLabel.setAlignmentX(0.5f);
        setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        for(int i=0; i<carList.length;i++){
            model.addRow(new Object[]{ 1+i, carList[i].getManufacturer(), carList[i].getModel(),carList[i].getKilometers(),carList[i].getAge()});
        }
        JTable table = new JTable(model);
        JScrollPane table2 = new JScrollPane(table);
        topPanel.add(headingLabel);
        topPanel.add(table2);


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
                    carList = carSystem.getAllCars();
                    int rowCount= model.getRowCount();
                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
                    for(int i =0 ; i<carList.length;i++){
                        model.addRow(new Object[]{ 1+i, carList[i].getManufacturer(), carList[i].getModel(),carList[i].getKilometers(),carList[i].getAge()});
                    }

                    carsUpdated = false;
                }
            }
        }
    }
}