import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Formatter;
import java.util.Properties;
import java.text.SimpleDateFormat;

public class NewWindow implements ActionListener{
    JFrame frame2 ;
    JButton addTaskButton2;
    JTextField taskField;
    JLabel taskNameLabel;
    JLabel priorityLabel;
    JLabel deadlineLabel;
    JComboBox priorityComboBox;


    public NewWindow(){
        //========================Labels========================//
        taskNameLabel = new JLabel("Task :");
        taskNameLabel.setBounds(20 ,10 ,100,30);
        taskNameLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        taskNameLabel.setForeground(Color.WHITE);

        priorityLabel = new JLabel("Priority :");
        priorityLabel.setBounds(20 ,42 ,150,30);
        priorityLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        priorityLabel.setForeground(Color.WHITE);

        deadlineLabel = new JLabel("Deadline :");
        deadlineLabel.setBounds(20 ,75 ,150,30);
        deadlineLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        deadlineLabel.setForeground(Color.WHITE);
        //========================Labels========================//

        //========================Inputs========================//
        taskField = new JTextField();
        taskField.setBounds(110,17,190,26);
        taskField.setFont(new Font("Monospaced" , Font.BOLD , 12));
        taskField.setForeground(Color.WHITE);
        taskField.setBackground(Color.BLACK);
        taskField.setCaretColor(Color.WHITE);

        String[] options = {"High","Medium","Low"};
        priorityComboBox = new JComboBox(options);
        priorityComboBox.setBounds(150,47,150,26);
        priorityComboBox.setFont(new Font("Monospaced" , Font.BOLD , 12));
        priorityComboBox.setBackground(Color.BLACK);
        priorityComboBox.setForeground(Color.WHITE);
        priorityComboBox.addActionListener(this);

        //========================Inputs========================//

        //========================DatePicker========================//
        // refer https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker
        UtilDateModel model = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        // Don't know about the formatter, but there it is...
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(130,77,170,26);
        datePicker.setForeground(Color.WHITE);
        datePicker.setBackground(new Color(40,40,40));
        datePicker.getJFormattedTextField().setBackground(Color.BLACK);
        datePicker.getJFormattedTextField().setForeground(Color.WHITE);
        //========================DatePicker========================//

        //========================Buttons========================//
        addTaskButton2 = new JButton("Add Task");
        addTaskButton2.setBounds(210,250,100,30);
        addTaskButton2.setFocusPainted(false);
        addTaskButton2.setFont(new Font("Monospaced" , Font.BOLD , 12));
        addTaskButton2.setForeground(Color.WHITE);
        addTaskButton2.setBackground(Color.BLACK);
        addTaskButton2.addActionListener(this);
        //========================Buttons========================//


        frame2 = new JFrame("Add Task");
        frame2.setVisible(true);
        frame2.setResizable(false);
        frame2.setSize(420 , 400);
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(new Color(40,40,40));
        frame2.add(addTaskButton2);
        frame2.add(taskField);
        frame2.add(taskNameLabel);
        frame2.add(priorityLabel);
        frame2.add(priorityComboBox);
        frame2.add(deadlineLabel);
        frame2.add(datePicker);

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
            MyFrame.task = taskField.getText();
            frame2.dispose();
            MyFrame.addTaskButton.setEnabled(true);
        }
    }
}
