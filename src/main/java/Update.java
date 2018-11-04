import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update {
    public JPanel Main;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JButton btnUpdate;
    private JTextField tfBefore;
    private JTextField tfAfter;
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
    private JLabel lblAfter;

    public Update() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
