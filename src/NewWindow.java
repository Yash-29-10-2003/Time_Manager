import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewWindow implements ActionListener{
    JFrame frame2 ;
    JButton addTaskButton2;
    public NewWindow(){

        addTaskButton2 = new JButton("Add Task");
        addTaskButton2.setBounds(210,500,100,30);
        addTaskButton2.setFocusPainted(false);
        addTaskButton2.setFont(new Font("Monospaced" , Font.BOLD , 12));
        addTaskButton2.setForeground(Color.WHITE);
        addTaskButton2.setBackground(Color.BLACK);
        addTaskButton2.addActionListener(this);

        frame2 = new JFrame("Add Task");
        frame2.setVisible(true);
        frame2.setResizable(false);
        frame2.setSize(520 , 600);
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(new Color(40,40,40));
        frame2.add(addTaskButton2);


        frame2.addWindowListener(new WindowAdapter() {            //this basically enables the original add task button when this win is closed
            @Override
            public void windowClosing(WindowEvent e) {
                MyFrame.addTaskButton.setEnabled(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addTaskButton2) {
            frame2.dispose();
            MyFrame.addTaskButton.setEnabled(true);
        }
    }
}
