import sun.security.util.ArrayUtil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;


public class ShowAllCarsPanel extends JPanel implements ActionListener, ChangeListener
{
	private CarFleetSystem carSystem;
	private Car[] carList;
	private int currentIndex = 0;
	private JLabel headingLabel = new JLabel("Show all makes and models");
	private JButton previousButton = new JButton("Previous");
	private JButton nextButton = new JButton("Next");
	private JButton deleteButton = new JButton("Delete");
	private JButton editButton = new JButton("Edit");
	private JPanel buttonPanel = new JPanel();
	private CarDetailsComponents carComponents = new CarDetailsComponents();
	private boolean carsUpdated = false;

	public ShowAllCarsPanel(CarFleetSystem carSys)
	{
		carSystem = carSys;
		carList = carSystem.getAllCars();

		if (carList.length > 0)
			carComponents.displayDetails(carList[0]);

		carSys.addCarUpdateListener(this);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		previousButton.addActionListener(this);
		nextButton.addActionListener(this);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		headingLabel.setAlignmentX(0.5f);

		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(editButton);

		add(Box.createVerticalStrut(10));
		add(headingLabel);
		add(Box.createVerticalStrut(15));
		carComponents.add(buttonPanel, "Center");
		add(carComponents);

		carList = carSystem.getAllCars();
	}

	
	public void actionPerformed(ActionEvent ev)
	{
		if (ev.getSource() == previousButton)
			previousButtonClicked();
		else if (ev.getSource() == nextButton)
			nextButtonClicked();
		else if (ev.getSource() == deleteButton)
			deleteButtonClicked();
		else if (ev.getSource() == editButton)
			editButtonClicked();
	}

	
	public void carsUpdated(CarUpdateEvent ev)
	{
		if (ev.getSource() == carSystem)
		{
			
			carsUpdated = true;
		}
	}

	
	public void stateChanged(ChangeEvent ev) {

		if (ev.getSource() instanceof JTabbedPane) {
			JTabbedPane tab = (JTabbedPane) ev.getSource();

			if (tab.getSelectedIndex() == 2) {

				if (carsUpdated) {
					carList = carSystem.getAllCars();
					if (carList.length == 0) {
						carComponents.clearTextFields();
					} else {
						currentIndex = 0;
						carComponents.displayDetails(carList[currentIndex]);
					}
				}
			}
		}
	}

	
	private void nextButtonClicked()
	{
		if (currentIndex < carList.length - 1)
		{
			currentIndex++;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	
	private void previousButtonClicked()
	{
		if (currentIndex > 0)
		{
			currentIndex--;
			carComponents.displayDetails(carList[currentIndex]);
		}
		else
			JOptionPane.showMessageDialog(carSystem, "You can't navigate any further", "Alert", JOptionPane.ERROR_MESSAGE);
	}
	private void deleteButtonClicked(){
		{
			carSystem.removeCar(carList[currentIndex]);
			carSystem.getAllCars();
			JOptionPane.showMessageDialog(carSystem, "Car succesfully deleted", "Alert", JOptionPane.INFORMATION_MESSAGE);
			carSystem.checkempty();
			carSystem.setCarsUpdated();;
		}
	}
	private void editButtonClicked(){
		JFrame f = new JFrame("Edit Menu");
		JButton m1= new JButton("Manufacturer");
		JButton m2= new JButton("Model");
		JButton m4= new JButton("Year");
		JButton m5=new JButton("Kms");
		JButton m3=new JButton("Details");
		JButton m6=new JButton("Cancel");
		m1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String manufacturer;
				manufacturer = JOptionPane.showInputDialog("Input new manufacturer");
				carSystem.UpdateManufacturer(carList[currentIndex],manufacturer);
				carList[currentIndex].setManufacturer(manufacturer);
				carComponents.displayDetails(carList[currentIndex]);
				JOptionPane.showMessageDialog(carSystem, "Manufacturer succesfully updated", "Alert", JOptionPane.INFORMATION_MESSAGE);
				f.setVisible(false);
				carSystem.checkempty();
				carSystem.setCarsUpdated();
			}
		});
		m2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String model;
				model = JOptionPane.showInputDialog("Input new model");
				carList[currentIndex].setModel(model);
				carComponents.displayDetails(carList[currentIndex]);
				JOptionPane.showMessageDialog(carSystem, "Model succesfully updated", "Alert", JOptionPane.INFORMATION_MESSAGE);
				f.setVisible(false);
				carSystem.setCarsUpdated();
			}
		});
		m4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int year;
				year = Integer.parseInt(JOptionPane.showInputDialog("Input new year"));
				carList[currentIndex].setYear(year);
				carComponents.displayDetails(carList[currentIndex]);
				JOptionPane.showMessageDialog(carSystem, "Year succesfully updated", "Alert", JOptionPane.INFORMATION_MESSAGE);
				f.setVisible(false);
				carSystem.setCarsUpdated();
			}
		});
		m5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int kms;
				kms = Integer.parseInt(JOptionPane.showInputDialog("Input new Kilometers"));
				carList[currentIndex].setKilometers(kms);
				carComponents.displayDetails(carList[currentIndex]);
				JOptionPane.showMessageDialog(carSystem, "Kilometers succesfully updated", "Alert", JOptionPane.INFORMATION_MESSAGE);
				f.setVisible(false);
				carSystem.setCarsUpdated();
			}
		});
		m3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String details;
				details = JOptionPane.showInputDialog("Input new Details");
				carList[currentIndex].setInformation(details);
				carComponents.displayDetails(carList[currentIndex]);
				JOptionPane.showMessageDialog(carSystem, "Details succesfully updated", "Alert", JOptionPane.INFORMATION_MESSAGE);
				f.setVisible(false);
				carSystem.setCarsUpdated();
			}
		});
		m6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.setVisible(false);
			}
		});
		f.add(m1);
		f.add(m2);
		f.add(m4);
		f.add(m5);
		f.add(m3);
		f.add(m6);
		f.setSize(400, 400);
		f.setLayout(new GridLayout(6,1));
		f.setVisible(true);
	}
}