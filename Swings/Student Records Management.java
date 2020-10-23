import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;
public class StudentManager extends JFrame implements ActionListener {
	ArrayList<Student> students;
	JTextField textField1,textField2,textField3;
	ButtonGroup btnGrp1;
	public StudentManager() {
		students = new ArrayList<>();
		//Enter the Roll Number label
		JLabel label1 = new JLabel("Enter the Student Roll Number :");
		label1.setBounds(20,20,340,30);
		label1.setFont(new Font("Calibri",Font.BOLD,20));
		label1.setForeground(Color.RED);
		add(label1);
		//Enter the Roll Number text field
		textField1 = new JTextField();
		textField1.setBounds(20, 50, 340, 30);
		textField1.setFont(new Font("Calibri",Font.BOLD,20));
		add(textField1);
		//Enter the Name label
		JLabel label2 = new JLabel("Enter the Student Name :");
		label2.setBounds(20,90,340,30);
		label2.setFont(new Font("Calibri",Font.BOLD,20));
		label2.setForeground(Color.RED);
		add(label2);
		//Enter the Name text field
		textField2 = new JTextField();
		textField2.setBounds(20, 120, 340, 30);
		textField2.setFont(new Font("Calibri",Font.BOLD,20));
		add(textField2);
		//Enter the Marks label
		JLabel label3 = new JLabel("Enter the Student Marks :");
		label3.setBounds(20,160,340,30);
		label3.setFont(new Font("Calibri",Font.BOLD,20));
		label3.setForeground(Color.RED);
		add(label3);
		//Enter the marks text field
		textField3 = new JTextField();
		textField3.setBounds(20, 190, 340, 30);
		textField3.setFont(new Font("Calibri",Font.BOLD,20));
		add(textField3);
		//Label  Sort By
		JLabel label4 = new JLabel("Sort By :");
		label4.setBounds(20,240,340,30);
		label4.setFont(new Font("Calibri",Font.BOLD,25));
		add(label4);
		//Radio Buttons for Sorting
		JRadioButton rb1 = new JRadioButton("Roll Number");
		rb1.setBounds(20, 280, 340, 20);
		rb1.setActionCommand("Roll Number");
		rb1.setFont(new Font("Calibri",Font.BOLD,17));
		add(rb1);
		//Radio Buttons for Sorting
		JRadioButton rb2 = new JRadioButton("Name");
		rb2.setActionCommand("Name");
		rb2.setBounds(20, 310, 340, 20);
		rb2.setFont(new Font("Calibri",Font.BOLD,17));
		add(rb2);
		//Radio Buttons for Sorting
		JRadioButton rb3 = new JRadioButton("Marks");
		rb3.setActionCommand("Marks");
		rb3.setBounds(20, 340, 340, 20);
		rb3.setFont(new Font("Calibri",Font.BOLD,17));
		add(rb3);
		//Button Group
		btnGrp1 = new ButtonGroup();
		btnGrp1.add(rb1);
		btnGrp1.add(rb2);
		btnGrp1.add(rb3);
		//Operation Buttons
		//Button Insert
		JButton button1 = new JButton("Insert");
		button1.setFont(new Font("Calibri",Font.BOLD,20));
		button1.setBounds(20,380,340,30);
		button1.addActionListener(this);
		add(button1);
		//Button Sort
		JButton button2 = new JButton("Sort");
		button2.setFont(new Font("Calibri",Font.BOLD,20));
		button2.setBounds(20,430,340,30);
		button2.addActionListener(this);
		add(button2);
		//Button Display
		JButton button3 = new JButton("Display");
		button3.setFont(new Font("Calibri",Font.BOLD,20));
		button3.setBounds(20,480,340,30);
		button3.addActionListener(this);
		add(button3);		
		//Frame Initialization
		setSize(400,600);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new StudentManager();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String txt = btn.getText();
		if(txt.equals("Insert")) {
			String rollNumber = textField1.getText();
			String name = textField2.getText();
			String marks = textField3.getText();
			if(rollNumber.matches("") || name.matches("") || marks.matches("")) {
				JOptionPane.showMessageDialog(this, "Invalid Inputs : Try Again","Alert",JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				try {
					students.add(new Student(Integer.parseInt(rollNumber),name,Integer.parseInt(marks)));
					JOptionPane.showMessageDialog(this, "Added Successfully","Alert",JOptionPane.INFORMATION_MESSAGE);
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(this, "Invalid Inputs : Try Again","Alert",JOptionPane.ERROR_MESSAGE);
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
				}
			}
		}
		else if(txt.equals("Sort")) {
			try {
				String sel = btnGrp1.getSelection().getActionCommand(); 
				if(sel.equals("Roll Number")) {
					Collections.sort(students,new sortByRollNumber());
				}
				else if(sel.equals("Marks")) {
					Collections.sort(students,new sortByMarks());
				}
				else {
					Collections.sort(students,new sortByName());
				}
				JOptionPane.showMessageDialog(this,"Records sorted by " + sel ,"Alert",JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(this,"Please Select a Parameter you want to Sort By.","Alert",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
class Student {
	int rollNumber;
	String name;
	int marks;
	Student(int rn,String n,int m) {
		this.rollNumber = rn;
		this.name = n;
		this.marks = m;
	}
	public String toString() {
		return "{" + " Roll Number : " + this.rollNumber +", Name : " + this.name + ", Marks : " +  this.marks + " }";
	}
}
class sortByRollNumber implements Comparator<Student> {
	public int compare(Student s1 , Student s2) {
		return Integer.compare(s1.rollNumber,s2.rollNumber);
	}
}
class sortByName implements Comparator<Student> {
	public int compare(Student s1 , Student s2) {
		return s1.name.compareTo(s2.name);
	}
}
class sortByMarks implements Comparator<Student> {
	public int compare(Student s1 , Student s2) {
		return Integer.compare(s1.marks,s2.marks);
	}
}