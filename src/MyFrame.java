import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Main Screen of the Application
public class MyFrame extends JFrame implements ActionListener {
    ImageIcon titleImage = new ImageIcon("Assets/bigclocka.png");
    JPanel headingPanel;
    JLabel headingLabel;
    JPanel clockPanel;
    public static JPanel tasksPanel;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;
    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;
    public static JButton addTaskButton;
    public static String task;
    public static String priority;
    public static Date deadline;
    public static String taskType;
    JButton clearAllButton;
    NewWindow taskWindow;
    JScrollPane scrollPane;
    MyFrame(){

        //========================Heading========================//
        headingLabel = new JLabel(" \uD83D\uDD57     Time Manager");   //https://www.alt-codes.net/clock-symbols
        headingLabel.setForeground(Color.LIGHT_GRAY);
        headingLabel.setFont(new Font("Monospaced", Font.BOLD , 41));
        headingLabel.setBounds(0 , 0 , 570 , 70);

        headingPanel = new JPanel();
        headingPanel.setBackground(Color.BLACK);
        headingPanel.setBounds(0,0,555,70);
        headingPanel.setLayout(null);
        headingPanel.add(headingLabel);
        //========================Heading========================//

        //=========================Clock=========================//
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Monospaced",Font.BOLD,11));
        dayLabel.setForeground(Color.LIGHT_GRAY);

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Monospaced",Font.BOLD,11));
        dateLabel.setForeground(Color.LIGHT_GRAY);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Monospaced",Font.BOLD,25));
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        clockPanel = new JPanel();
        clockPanel.setLayout(new FlowLayout());
        clockPanel.setBounds(555,0,180,70);
        clockPanel.setBackground(Color.BLACK);
        clockPanel.add(timeLabel);
        clockPanel.add(dayLabel);
        clockPanel.add(dateLabel);
        //=========================Clock=========================//

        //=========================Tasks=========================//
        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        //tasksPanel.setBounds(40,160,655,450);  //you don't need to put in bounds for task panel since we add this to scroll pane
        tasksPanel.setBackground(Color.BLACK);
        tasksPanel.setMaximumSize(new Dimension(655, Integer.MAX_VALUE));

        scrollPane = new JScrollPane(tasksPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(40,160,655,450);
        scrollPane.setVisible(true);
        //=========================Tasks=========================//

        //========================Buttons========================//
        addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(322,100,100,30);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.setBackground(Color.BLACK);
        addTaskButton.addActionListener(this);

        clearAllButton = new JButton("Clear All Tasks");
        clearAllButton.setBounds(298,650,150,30);
        clearAllButton.setFocusPainted(false);
        clearAllButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        clearAllButton.setForeground(Color.WHITE);
        clearAllButton.setBackground(Color.BLACK);
        //========================Buttons========================//


        this.setTitle("Time Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750 , 750);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(40,40,40));
        this.setIconImage(titleImage.getImage());
        this.setLayout(null);
        this.add(headingPanel);
        this.add(clockPanel);
        this.add(addTaskButton);
        this.add(clearAllButton);
        this.add(scrollPane);

        setTime();
    }
    public void setTime(){
        while(true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
    public static void addTask(String taskName, String priority, Date deadline, String taskType) {
        JPanel taskEntry = new JPanel();
        taskEntry.setLayout(new BoxLayout(taskEntry, BoxLayout.X_AXIS));
        taskEntry.setBackground(new Color(40,40,40));
        taskEntry.setMaximumSize(new Dimension(617, 100));  //making it so that the panel takes 630 px atleast
        taskEntry.setPreferredSize(new Dimension(617, 100));  //makes it so that the panels dont shrink

        JLabel taskLabel = new JLabel("  Task: " + taskName);
        taskLabel.setForeground(Color.LIGHT_GRAY);
        taskLabel.setFont(new Font("Monospaced" , Font.BOLD , 12));

        JLabel priorityLabel = new JLabel("Priority: " + priority);
        priorityLabel.setForeground(Color.LIGHT_GRAY);
        priorityLabel.setFont(new Font("Monospaced" , Font.BOLD , 12));

        JLabel deadlineLabel = new JLabel("Deadline: " + deadline);
        deadlineLabel.setForeground(Color.LIGHT_GRAY);
        deadlineLabel.setFont(new Font("Monospaced" , Font.BOLD , 12));

        JLabel taskTypeLabel = new JLabel("Task Type: " + taskType);
        taskTypeLabel.setForeground(Color.LIGHT_GRAY);
        taskTypeLabel.setFont(new Font("Monospaced" , Font.BOLD , 12));


        taskEntry.add(taskLabel);
        taskEntry.add(Box.createHorizontalStrut(20)); // Spacing
        taskEntry.add(priorityLabel);
        taskEntry.add(Box.createHorizontalStrut(20)); // Spacing
        taskEntry.add(deadlineLabel);
        taskEntry.add(Box.createHorizontalStrut(20)); // Spacing
        taskEntry.add(taskTypeLabel);
        tasksPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        tasksPanel.add(taskEntry);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addTaskButton){
            taskWindow = new NewWindow();
            addTaskButton.setEnabled(false);
        }
    }
}
