import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//Main Screen of the Application
public class MyFrame extends JFrame implements ActionListener {
    public static ImageIcon titleImage = new ImageIcon("Assets/bigclocka.png");
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
    public static String note;
    JButton clearAllButton;
    NewWindow taskWindow;
    JScrollPane scrollPane;
    public static int totalTasks = 0;
    public static int tasksDone = 0;
    static JProgressBar bar;
    MyFrame(){

        //========================Heading========================//
        headingLabel = new JLabel(" \uD83D\uDD57     prOrganizer");   //https://www.alt-codes.net/clock-symbols
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
        tasksPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        //creating an initial black struct to level out with the borders ..


        scrollPane = new JScrollPane(tasksPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(40,160,655,450);
        scrollPane.setVisible(true);
        //=========================Tasks=========================//

        //========================Buttons========================//
        addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(200,100,100,30);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.setBackground(Color.BLACK);
        addTaskButton.addActionListener(this);

        clearAllButton = new JButton("Clear All");
        clearAllButton.setBounds(450,100,100,30);
        clearAllButton.setFocusPainted(false);
        clearAllButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        clearAllButton.setForeground(Color.WHITE);
        clearAllButton.setBackground(Color.BLACK);
        clearAllButton.addActionListener(this);
        //========================Buttons========================//

        //========================Progress========================//
        // we update the progress bar via updateProgressBar() every time the value of total tasks or task done is changed
        bar = new JProgressBar();
        bar.setValue(0);
        bar.setBounds(60,650,610,30);
        bar.setStringPainted(false);   // shows the % on the bar if true
        bar.setFont(new Font("Monospaced" , Font.BOLD , 12));
        bar.setBackground(new Color(40,40,40));
        bar.setForeground(Color.BLACK);
        //========================Progress========================//


        this.setTitle("prOrganizer");
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
        this.add(bar);

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
    public static void addTask(String taskName, String priority, Date deadline, String taskType, String note) {
        JPanel taskEntry = new JPanel();
        taskEntry.setLayout(new BoxLayout(taskEntry, BoxLayout.X_AXIS));
        taskEntry.setBackground(Color.GRAY);
        taskEntry.setMaximumSize(new Dimension(625, 100));  //making it so that the panel takes 630 px atleast
        taskEntry.setPreferredSize(new Dimension(625, 100));  //makes it so that the panels dont shrink
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 5);
        taskEntry.setBorder(blackBorder);

        JLabel taskLabel = new JLabel("  Task: " + taskName);
        taskLabel.setForeground(Color.LIGHT_GRAY);
        taskLabel.setFont(new Font("Monospaced" , Font.BOLD , 22));
        taskLabel.setBounds(0,0,480,20);

        JLabel priorityLabel = new JLabel("   Priority: " + priority);
        priorityLabel.setForeground(Color.LIGHT_GRAY);
        priorityLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        priorityLabel.setBounds(0,25,200,20);

        String formattedDate;   //just formatting the date here in a more gui friendly manner
        Date date = deadline;   //perivously, the date was also mentioning the exact time in hh:mm:ss
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM");
            formattedDate = dateFormat.format(date);
        } else {
            formattedDate = "not set";
        }
        JLabel deadlineLabel = new JLabel("   Deadline: " + formattedDate);
        deadlineLabel.setForeground(Color.LIGHT_GRAY);
        deadlineLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        deadlineLabel.setBounds(0,45,250,20);


        JLabel taskTypeLabel = new JLabel("Task Type: " + taskType);
        taskTypeLabel.setForeground(Color.LIGHT_GRAY);
        taskTypeLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        taskTypeLabel.setBounds(200,25,200,20);

        JLabel noteLabel = new JLabel("   Note: " + note);
        noteLabel.setForeground(Color.LIGHT_GRAY);
        noteLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        noteLabel.setBounds(0,65,450,20);

        JLabel doneLabel = new JLabel("Done: ");
        doneLabel.setForeground(Color.LIGHT_GRAY);
        doneLabel.setFont(new Font("Monospaced" , Font.BOLD , 15));
        doneLabel.setBounds(250,45,100,20);

        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(300 , 45 , 20 , 20);
        checkBox.setBackground(Color.BLACK);
        checkBox.setFocusable(false);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()){
                    tasksDone ++;
                } else{
                    tasksDone --;
                }
                updateProgressBar();
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        removeButton.setBackground(Color.BLACK);
        removeButton.setForeground(Color.LIGHT_GRAY);
        removeButton.setBounds(4,10,77,30);
        removeButton.setFocusPainted(false);

        JButton editButton = new JButton("Edit");
        editButton.setFont(new Font("Monospaced" , Font.BOLD , 12));
        editButton.setBackground(Color.BLACK);
        editButton.setForeground(Color.LIGHT_GRAY);
        editButton.setBounds(4,50,77,30);
        editButton.setFocusPainted(false);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasksPanel.remove(taskEntry);
                tasksPanel.revalidate();
                tasksPanel.repaint();
                totalTasks -= 1;
                if(checkBox.isSelected()){
                    tasksDone -=1;
                }
                updateProgressBar();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tasksPanel.remove(taskEntry);
                tasksPanel.revalidate();
                tasksPanel.repaint();
                totalTasks -= 1;
                if(checkBox.isSelected()){
                    tasksDone -=1;
                }
                updateProgressBar();
                addTaskButton.doClick();
                NewWindow.taskField.setText(taskName);
                NewWindow.priorityComboBox.setSelectedItem(priority);
                NewWindow.noteField.setText(note);
                if (taskType == "New") {
                    NewWindow.taskTypeButton1.setSelected(true);
                } else if (taskType == "Debug") {
                    NewWindow.taskTypeButton2.setSelected(true);
                } else if (taskType == "Refactor") {
                    NewWindow.taskTypeButton3.setSelected(true);
                }
                //NewWindow.datePicker.getModel().setDate(date.getDate(),date.getMonth(),date.getYear());
                //this datePicker method for some reason doesn't work , for now ill just skip the deadline ..
            }
        });

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(null);
        infoPanel.setBounds(0,0,500,100);
        infoPanel.setPreferredSize(new Dimension(500,100));
        infoPanel.setBackground(Color.BLACK);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        buttonsPanel.setPreferredSize(new Dimension(50,100));
        buttonsPanel.setBackground(Color.GRAY);

        infoPanel.add(taskLabel);
        infoPanel.add(priorityLabel);
        infoPanel.add(deadlineLabel);
        infoPanel.add(taskTypeLabel);
        infoPanel.add(noteLabel);
        infoPanel.add(doneLabel);
        infoPanel.add(checkBox);

        buttonsPanel.add(removeButton);
        buttonsPanel.add(editButton);

        taskEntry.add(infoPanel);
        taskEntry.add(buttonsPanel);
        tasksPanel.add(taskEntry);
        totalTasks += 1;
        updateProgressBar();
    }

    public void clearAll(){
        tasksPanel.removeAll();
        tasksPanel.revalidate();
        tasksPanel.repaint();
        totalTasks = 0;
        tasksDone = 0;
        updateProgressBar();
    }
    public static void updateProgressBar() {
        int progress = (int)((double) tasksDone / totalTasks * 100);
        bar.setValue(progress);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addTaskButton){
            taskWindow = new NewWindow();
            addTaskButton.setEnabled(false);
        }
        if(e.getSource() == clearAllButton){
            clearAll();
        }
    }
}
