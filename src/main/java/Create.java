import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Create {
    public JPanel Main;
    private JPanel left;
    private JLabel lblCarID;
    private JLabel lblCarRegistration;
    private JLabel lblMake;
    private JLabel lblModel;
    private JLabel lblYear;
    private JLabel lblPrice;
    private JLabel lblFuelType;
    private JLabel lblEngineSizeCC;
    private JLabel lblTransmission;
    private JLabel lblColor;
    private JLabel lblNumberOfDoors;
    private JPanel bottom;
    private JButton btnCreate;
    private JPanel right;
    private JLabel lblSold;
    private JTextField tfNumberOfDoors;
    private JTextField tfCarId;
    private JTextField tfColor;
    private JTextField tfCarRegistration;
    private JTextField tfTransmission;
    private JTextField tfEngineSizeCC;
    private JTextField tfSold;
    private JTextField tfCarMake;
    private JTextField tfFuelType;
    private JTextField tfPrice;
    private JTextField tfYear;
    private JTextField tfModel;
    private JCheckBox chkIsSold;


    public Create() {
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                System.out.println("First read all");

                Car car = new Car(tfCarId.getText(), tfCarRegistration.getText(), chkIsSold.isSelected(), tfCarMake.getText(), tfModel.getText(), tfYear.getText(), tfPrice.getText(), tfFuelType.getText(), tfEngineSizeCC.getText(), tfTransmission.getText(), tfColor.getText(), tfNumberOfDoors.getText());

                c.addCar(car);

            }
        });
    }
    public static void main(String args[])
    {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Create");
                frame.setContentPane(new Create().Main); //
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
//                frame.setSize(350, 350);
                frame.setVisible(true);
            }
        });

    }
}
