_**Introduction :**_

prOrganizer is a Java Swing based computer application , I basically made it as a tool to use to organize my daily task while working on projects/studies , anything that takes some planning honestly.

![image](https://github.com/Yash-29-10-2003/prOrganizer/assets/89728102/242cd0ec-6220-4269-b795-bbae48af32fd)


_**Documentation :**_

This documentation is for the main aspects regarding the project.

**-Adding a task-**

We have an "Add Task" buttont that opens up a new window where we can choose different parameters for our each individual task listing :

![image](https://github.com/Yash-29-10-2003/prOrganizer/assets/89728102/1b3778ec-254f-4cea-ac07-995afa4f9200)


After taking all the info in the new window the information is redirected to the addTask() method in the MyFrame class .
This function basically construct each task and add it into the main panel that contains all the tasks.

**-Layouts , and how tasks work.-**

There is no layout for the frame , it has a panel component in it with a scroll , this panel (tasksPanel) has a box layout.
tasksPanel acts as a panel that holds all the panels.
After tasksPanel , there is taskEntry (box layout) , which is basically a panel that holds all the component for a certain task.
Both tasksPanel and tasksEntry both are box is for comfortable adding of tasks in a list format.
Each taskEntry() holds 2 different panels , an infoPanel (this holds all the info for a task as labels , like task name , deadline etc) and a buttonsPanel(this holds all the buttons that function on each individual task , ie. remove etc.)
Following is a basic wireframe that diagramatically explain how these task panels work.

![image](https://github.com/Yash-29-10-2003/prOrganizer/assets/89728102/f29287e3-c286-4cee-bd7f-75d47f492868)

**-Date , JDatePicker-**

Here , to get the deadline input i have used the JDatePicker 1.3.4 (https://sourceforge.net/projects/jdatepicker/files/Releases/1.3.x/jdatepicker-1.3.4.jar/download)
There is no real documentation for this version out there , so i mainly referenced stack overflow posts like (https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker)
The DateLabelFormatter class just works as a formatter for the date picker.
After taking the value from the date picker , i convert it into a "day , date , month" format for more readability. (E, d MMM)

![image](https://github.com/Yash-29-10-2003/prOrganizer/assets/89728102/a642e3a6-60e7-4ff2-abf8-c46b28acf908)


**-Clock-**

There is a clock at the top right corner of the main window , for the convenience of checking time with relation to the deadline.
We basically fetch the current day , date and time from the calendar library and update it every 1000ms using a thread.

![image](https://github.com/Yash-29-10-2003/prOrganizer/assets/89728102/a0e8b976-cc99-45b6-823c-db38bbd96ebb)

**-Removing tasks-**

Users can either remove individual tasks using the remove button present on each task or clear all tasks using the clear all button .

**-Editting Individual Tasks-**

We can edit individual tasks by clicking on the edit button on each task.
It basically deletes the original task and opens a new add task window with pre set values from the original task, the user can edit the task and add it.
Couldnt get the date to work here.

**-Progress Bar-**

A progress bar is present that keeps track of how many tasks are present vs how many tasks present are completed , it updates whenever a task is removed / added or completed.
