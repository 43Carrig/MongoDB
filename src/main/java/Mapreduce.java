import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mapreduce {
    public JPanel Main;
    private JLabel mapreduceLabel;
    private JButton mapreduceButton;

    public Mapreduce() {
        mapreduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
