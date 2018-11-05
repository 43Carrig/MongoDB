import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete {
    public JPanel Main;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JLabel lblGetText;
    private JButton btnDelete;
    private JTextField tfEnter;

    public Delete() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                c.deleteCar(tfEnter.getText());
            }
        });
    }

    public static void main (String args[])
    {
        JFrame frame = new JFrame("Delete");
        frame.setContentPane(new Delete().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
//        frame.setSize(300, 300);
        frame.setVisible(true);

    }
}
