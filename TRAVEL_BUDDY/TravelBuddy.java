import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelBuddy {
    // Locations from Riverside to Sta Lucia
    private static String[] locationsToStaLucia = {
        "Riverside Terminal", "Coa", "Sandigan", "Batasan", "Kalayaan",
        "Freedom Park", "Petron Palengke", "Talanay", "Northview",
        "Banaba", "Dona Pepeng", "Fairlane", "Edwardson", "Concepcion",
        "Markington", "LRT Northbound", "Dela Paz Tawid", "Robinson Metro East", "Sta Lucia"
    };

    // Locations from Sta Lucia to Riverside
    private static String[] locationsToRiverside = {
        "Sta Lucia", "Robinson Metro East", "Dela Paz Tawid", "LRT Northbound", 
        "Markington", "Concepcion", "Edwardson", "Fairlane", "Dona Pepeng", 
        "Banaba", "Northview", "Talanay", "Petron Palengke", "Freedom Park", 
        "Kalayaan", "Batasan", "Sandigan", "Coa", "Riverside Terminal"
    };

    // Distances from Riverside to Sta Lucia in km
    private static double[] distancesFromRiverside = {
        0.0, 1.2, 2.5, 3.8, 5.0, 6.3, 7.5, 8.9, 10.2,
        11.5, 12.8, 14.0, 15.3, 16.7, 18.0, 19.2, 20.5, 21.7, 23.0
    };

    // Distances from Sta Lucia to Riverside in km (reverse order)
    private static double[] distancesToRiverside = {
        23.0, 21.7, 20.5, 19.2, 18.0, 16.7, 15.3, 14.0, 12.8,
        11.5, 10.2, 8.9, 7.5, 6.3, 5.0, 3.8, 2.5, 1.2, 0.0
    };

    // Base fare per kilometer
    private static final double BASE_RATE_PER_KM = 2.0; // Example: PHP 2.0 per kilometer

    public static void main(String[] args) {
        JFrame frame = new JFrame("TravelBuddy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel directionLabel = new JLabel("Select your trip direction:");
        directionLabel.setBounds(10, 20, 200, 25);
        panel.add(directionLabel);

        String[] directions = { "Riverside to Sta. Lucia", "Sta. Lucia to Riverside" };
        JComboBox<String> directionList = new JComboBox<>(directions);
        directionList.setBounds(200, 20, 150, 25);
        panel.add(directionList);

        JLabel pickupLabel = new JLabel("Pick up-point:");
        pickupLabel.setBounds(10, 60, 200, 25);
        panel.add(pickupLabel);

        JComboBox<String> pickupList = new JComboBox<>(locationsToStaLucia);
        pickupList.setBounds(200, 60, 150, 25);
        panel.add(pickupList);

        JLabel dropoffLabel = new JLabel("Drop off-point:");
        dropoffLabel.setBounds(10, 100, 200, 25);
        panel.add(dropoffLabel);

        JComboBox<String> dropoffList = new JComboBox<>(locationsToStaLucia);
        dropoffList.setBounds(200, 100, 150, 25);
        panel.add(dropoffList);

        JLabel discountLabel = new JLabel("Select Fare Type:");
        discountLabel.setBounds(10, 140, 150, 25);
        panel.add(discountLabel);

        String[] fareTypes = {"Regular", "Discounted"};
        JComboBox<String> discountList = new JComboBox<>(fareTypes);
        discountList.setBounds(200, 140, 150, 25);   
        panel.add(discountList);

        JButton calculateButton = new JButton("Calculate Fare");
        calculateButton.setBounds(150, 180, 150, 25);
        panel.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int direction = directionList.getSelectedIndex();
                int pickupIndex = pickupList.getSelectedIndex();
                int dropoffIndex = dropoffList.getSelectedIndex();
                int fareTypeIndex = discountList.getSelectedIndex();

                // Choose the correct array based on the direction
                double[] distances = (direction == 0) ? distancesFromRiverside : distancesToRiverside;

                // Calculate the distance between the pickup and drop-off points
                double distance = Math.abs(distances[dropoffIndex] - distances[pickupIndex]);

                // Calculate the base fare based on distance
                double price = distance * BASE_RATE_PER_KM;

                // Apply a 20% discount for seniors
                if (fareTypeIndex == 1) {
                    price *= 0.8;
                }

                // Display the fare result
                JOptionPane.showMessageDialog(panel, "Your trip is from: " + locationsToStaLucia[pickupIndex] + 
                    " to " + locationsToStaLucia[dropoffIndex] + " as a " + (fareTypeIndex == 1 ? "Senior" : "Regular") + 
                    " passenger. Distance: " + distance + " km. Fare: " + price + " PHP.");
            }
        });
    }
}
