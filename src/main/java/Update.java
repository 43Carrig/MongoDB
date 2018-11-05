import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update {
    public JPanel Main;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JButton btnUpdate;
    private JTextField tfGetByID;
    private JTextField tfCarId;
    private JTextField tfNumberOfDoors;
    private JTextField tfIsSold;
    private JTextField tfColor;
    private JTextField tfCarRegistration;
    private JTextField tfCarMake;
    private JTextField tfEngineSizeCC;
    private JTextField tfTransmission;
    private JTextField tfModel;
    private JTextField tfFuelType;
    private JTextField tfYear;
    private JTextField tfPrice;
    private JLabel lblBefore;
    private JLabel lblCarId;
    private JLabel lblCarIsSold;
    private JLabel lblCarRegistration;
    private JLabel lblCarMake;
    private JLabel lblModel;
    private JLabel lblYear;
    private JLabel lblPrice;
    private JLabel lblFuelType;
    private JLabel lblEngineSizeCC;
    private JLabel lblTransmission;
    private JLabel lblColor;
    private JLabel lblNumberOfDoors;
    private JButton btnGetByID;
    private JCheckBox chkIsSold;

    public Update() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                Car car = new Car(tfCarId.getText(), tfCarRegistration.getText(), chkIsSold.isSelected(), tfCarMake.getText(), tfModel.getText(), tfYear.getText(), tfPrice.getText(), tfFuelType.getText(), tfEngineSizeCC.getText(), tfTransmission.getText(), tfColor.getText(), tfNumberOfDoors.getText());

                c.updateCarDetails(car);

            }
        });
        btnGetByID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                System.out.println("Second read all");
                Car car = c.getCarById(tfGetByID.getText());

                tfCarId.setText(car.getCarId());
                tfColor.setText(car.getColor());
                tfTransmission.setText(car.getTransmission());
                tfEngineSizeCC.setText(car.getEngineSizeCC());
                chkIsSold.setSelected(car.getIsSold());
                tfCarRegistration.setText(car.getCarRegistration());
                tfCarMake.setText(car.getCarMake());
                tfFuelType.setText(car.getFuelType());
                tfPrice.setText(car.getPrice());
                tfModel.setText(car.getCarModel());
                tfYear.setText(car.getYear());
                tfNumberOfDoors.setText(car.getNumberOfDoors());

                tfColor.setEditable(true);
                tfTransmission.setEditable(true);
                tfEngineSizeCC.setEditable(true);
                tfCarRegistration.setEditable(true);
                tfCarMake.setEditable(true);
                tfFuelType.setEditable(true);
                tfPrice.setEditable(true);
                tfModel.setEditable(true);
                chkIsSold.setEnabled(true);
                tfYear.setEditable(true);
                tfNumberOfDoors.setEditable(true);

                btnUpdate.setEnabled(true);

                btnGetByID.setEnabled(false);
                tfGetByID.setEnabled(false);
            }
        });
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("Update");
        frame.setContentPane(new Update().Main); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
