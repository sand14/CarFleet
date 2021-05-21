import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SearchByNamePanel extends JPanel implements ActionListener
{
    private Car[] carList;
    private CarFleetSystem carSystem;
    private int currentIndex = 0;
    private JLabel headingLabel = new JLabel("Search on Manufacturer");
    private JLabel nameLabel = new JLabel("Car manufacturer");
    private JButton searchButton = new JButton("Search");
    private JButton resetButton = new JButton("Reset");
    private JButton previousButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JTextField nameCombo = new JTextField(20);
    private JPanel topPanel = new JPanel();
    private JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel searchButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel navigateButtonsPanel = new JPanel();
    private CarDetailsComponents carComponents = new CarDetailsComponents();


    public SearchByNamePanel(CarFleetSystem carSys)
    {
        carSystem = carSys;
        setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        previousButton.addActionListener(this);
        nextButton.addActionListener(this);
        resetButton.addActionListener(this);
        searchButton.addActionListener(this);

        namePanel.add(nameLabel);
        namePanel.add(nameCombo);
        searchButtonsPanel.add(searchButton);
        searchButtonsPanel.add(resetButton);
        navigateButtonsPanel.add(previousButton);
        navigateButtonsPanel.add(nextButton);
        namePanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));
        searchButtonsPanel.setBorder(new javax.swing.border.EmptyBorder(new Insets(0, 5, 0, 0)));

        headingLabel.setAlignmentX(0.5f);

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(headingLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(namePanel);
        topPanel.add(searchButtonsPanel);
        topPanel.add(Box.createVerticalStrut(15));
        carComponents.add(navigateButtonsPanel, "Center");
        carComponents.setVisible(false);

        add(topPanel, "North");
        add(carComponents, "Center");
    }


    public void actionPerformed(ActionEvent ev)
    {
        if (ev.getSource() == searchButton)
            searchButtonClicked();
        else if (ev.getSource() == previousButton)
            previousButtonClicked();
        else if (ev.getSource() == nextButton)
            nextButtonClicked();
        else if (ev.getSource() == resetButton)
            resetButtonClicked();
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


    private void resetButtonClicked()
    {
        currentIndex = 0;
        carList = null;
        carComponents.setVisible(false);
        nameCombo.setText(null);
    }


    private void searchButtonClicked()
    {

        String man = nameCombo.getText();
        String manufacturer=man.toUpperCase();
        if (manufacturer != null)
        {
            carList = carSystem.search(manufacturer);
        }

        if (carList.length > 0)
        {
            currentIndex = 0;
            carComponents.setVisible(true);
            carComponents.displayDetails(carList[0]);

            if (carList.length == 1)
            {
                nextButton.setEnabled(false);
                previousButton.setEnabled(false);
            }
            else
            {
                nextButton.setEnabled(true);
                previousButton.setEnabled(true);
            }

            carSystem.repaint();
        }
        else
            JOptionPane.showMessageDialog(carSystem, "Sorry, no search results were returned", "Search failed", JOptionPane.WARNING_MESSAGE);
    }
}