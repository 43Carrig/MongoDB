import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Mapreduce {
    public JPanel Main;
    private JButton mapreduceButton;
    private JList listStats;

    public Mapreduce() {
        mapreduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MongoClient mongoClient = new MongoClient("localhost", 27017);

                MongoDatabase database = mongoClient.getDatabase("testCarDb");

                MongoCRUD c = new MongoCRUD(mongoClient, database);

                ArrayList<String> result = c.mapReduce();

                listStats.setListData(result.toArray());
            }
        });
    }

    public static void main(String args[])
    {
        JFrame frame = new JFrame("Mapreduce");
        frame.setContentPane(new Mapreduce().Main); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
