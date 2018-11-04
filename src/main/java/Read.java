import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Read {
    public JPanel Main;
    private JPanel left;
    private JPanel Right;
    private JPanel Bottom;
    private JLabel lblBefore;
    private JLabel lblID;
    private JLabel lblIsSold;
    private JLabel lblRegistration;
    private JLabel lblCarMake;
    private JLabel lblModel;
    private JLabel lblYear;
    private JLabel lblPrice;
    private JLabel lblFuelType;
    private JLabel lblEngineSizeCC;
    private JLabel lblTransmission;
    private JLabel lblColor;
    private JLabel lblNumberOfDoors;
    private JTextField tfBefore;
    private JTextField tfColor;
    private JTextField tfTransmission;
    private JTextField tfEngineSizeCC;
    private JTextField tfCarId;
    private JTextField tfIsSold;
    private JTextField tfCarRegistration;
    private JTextField tfCarMake;
    private JTextField tfFuelType;
    private JTextField tfPrice;
    private JTextField tfModel;
    private JTextField tfYear;
    private JTextField tfGetById;
    private JTextField tfNumberOfDoors;
    private JButton btnFind;

    public Read() {
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                System.out.println("Second read all");
                c.getCarById(tfGetById.getText());
            }
        });
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("Read");
        frame.setContentPane(new Read().Main); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
//        frame.setSize(300, 1000);
        frame.setVisible(true);
    }
}
