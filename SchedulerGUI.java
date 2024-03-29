import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class SchedulerGUI extends JFrame implements ActionListener {
	private int width = 1000;
	private int height = 400;
	JTextField[] fileFields; 
	JButton[] b;
	JButton submitButton;
	Color background;

	public SchedulerGUI() {
		super("SchedulerGUI");
		setSize(width, height);
		background = new Color(250, 250, 250); // 230, 230, 250 <- off white //255, 250, 245 <- slight lavender 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Panel 1 --> Holds the title & directions text
		JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(3,1));
			// Title
			JLabel title = new JLabel("Cavs in The Classroom Scheduler");
			title.setFont(new Font("Century Gothic", Font.BOLD, 20));
			title.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(title);
			
			// Directions for submitting files 
			JLabel directions = new JLabel("<HTML><center>Please download all of your Excel/Google Drive Sheets as TSV files! "
					+ "<br> For each sheet below, please select the path of the files on your computer. </center></HTML>");
			directions.setFont(new Font("Century Gothic", Font.ITALIC, 15));
			directions.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(directions);
			
			//Set background color
			panel1.setBackground(background);
		add(panel1,BorderLayout.NORTH);

		// Panel 2 --> input fields and buttons for file inputs
		JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3,5, 20, 20));
			
			// Initialize labels, fields, and button arrays
			JLabel[] messageFile = new JLabel[3];
			fileFields = new JTextField[3];
			b = new JButton[3];
	
			// Set the text of each label
			messageFile[0] = new JLabel("<HTML> <b> Teachers: </b> <br> <h4> "
					+ "Note: File must contain the word \"teacher\" at least! </h4> </HTML> ");
			messageFile[1] = new JLabel("<HTML> <b> Curry  & New Volunteers: </b> <br>"
					+ "<h5> Note: File must contain the words \"Curry\" or \"new\" at least! </h5> </HTML> ");
			messageFile[2] = new JLabel("<HTML> <b> Returning volunteers: </b> <br> <h4> "
					+ "Note: File must contain the word \"returning\" at least! </h4> </HTML> ");
	
			// Initialize each element and format it equally
			for(int i=0; i<3; i++) {
				// Field initialization and formatting 
				fileFields[i] = new JTextField("");
				fileFields[i].setBorder(new LineBorder(Color.BLACK, 2));
				fileFields[i].setEditable(false);
				// Button initialization and formatting
				b[i] = new JButton("Select File " + (i+1));
				b[i].addActionListener(this);
				b[i].setBackground(new Color(66,165, 245));
				b[i].setFont(new Font("Century Gothic", Font.PLAIN, 18));
				b[i].setForeground(Color.WHITE);
				b[i].setOpaque(true);
				b[i].setBorderPainted(false);
				// Label formatting
				messageFile[i].setFont(new Font("Century Gothic", Font.PLAIN, 20));
				messageFile[i].setHorizontalAlignment(JLabel.CENTER);
				// Add each elements sequentially so that all of
				panel2.add(messageFile[i]);
				panel2.add(fileFields[i]);
				panel2.add(b[i]);
			}
			panel2.setBackground(background);
		add(panel2,BorderLayout.CENTER);

		// Panel 3 --> Submit button and directions
		JPanel panel3 = new JPanel();
			panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel3.setMaximumSize(new Dimension(50, 50));
			panel3.setLayout(new GridLayout(1,2, 10,10));
	
			JLabel messageSubmit = new JLabel("<HTML><U>Hit Submit once ALL file paths have been entered to run the program:</U><HTML>"); //"<HTML><U>YOUR TEXT HERE</U></HTML>"
			messageSubmit.setFont(new Font("Century Gothic", Font.PLAIN, 15));
			messageSubmit.setHorizontalAlignment(JLabel.CENTER);
			panel3.add(messageSubmit);
	
			submitButton = new JButton("SUBMIT");
			setSubmitButtonEnabled(false);
			submitButton.addActionListener(this);
			submitButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
			submitButton.setForeground(Color.WHITE);
			submitButton.setOpaque(true);
			submitButton.setBorderPainted(false);
			panel3.add(submitButton);
			panel3.setBackground(background);
		add(panel3,BorderLayout.SOUTH);
		// Pack the elements together
		this.pack();
	}
	private void setSubmitButtonEnabled(boolean enabled) {
		this.submitButton.setEnabled(enabled);
		if(enabled) {
			submitButton.setBackground(new Color(66,165, 245));  // green: 38, 166, 91 Light pink: 244, 121, 131
		} else {
			submitButton.setBackground(new Color(189, 189, 189));  // Light pink: 244, 121, 131
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Select File 1")) {
			setSelectedField(0);
		}
		else if (action.equals("Select File 2")) { 
			setSelectedField(1);
		}
		else if (action.equals("Select File 3")) { 
			setSelectedField(2);
		}
		else if (action.equals("SUBMIT")) {
			calledSubmit();
		}
	}
	public void setSelectedField(int i) {
		// create an object of JFileChooser class 
		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		j.removeChoosableFileFilter(j.getAcceptAllFileFilter());
		j.addChoosableFileFilter(new FileNameExtensionFilter("TSV files (*.tsv)", "tsv"));
		// invoke the showsSaveDialog function to show the save dialog 
		int r = j.showOpenDialog(null);
		// if the user selects a file 
		if (r == JFileChooser.APPROVE_OPTION) { 
			// set the label to the path of the selected file 
			fileFields[i].setText(j.getSelectedFile().getAbsolutePath());
		}
		// if the user cancelled the operation 
		else {
			JOptionPane.showMessageDialog(null,"User cancelled file input");
		}
		setSubmitButtonEnabled(!Arrays.stream(fileFields).anyMatch(x -> x.getText().equals("")));
	}
	public void calledSubmit() {
		boolean check = true;
		// Check if inputs exist and are valid
		for(int i=0; i<3; i++) {
			// Check if none of the fields are blank
			if(fileFields[i].getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Not all file paths were entered!");
				check = false;
				break;
			}
			// Check if none of the fields are not tsv files
			if(fileFields[i].getText().contains(".tsv")==false) {
				JOptionPane.showMessageDialog(null,"Not all files are tsvs!");
				check = false;
				break;
			}
		}
		//If checks were passed, then execute the input submission
		if(check) {
			//execute Submit
			String[] fileNames = new String[3];
			for(int i=0; i<3; i++) {
				fileNames[i] = fileFields[i].getText();
			}
			if(fileNames[0].toLowerCase().contains("teacher") == false ) {
				JOptionPane.showMessageDialog(null,"The first file isn't a teacher file!");
			}
			else if(fileNames[1].toLowerCase().contains("curry") == false && fileNames[0].toLowerCase().contains("new") == false  ) {
				JOptionPane.showMessageDialog(null,"The first file isn't a Curry and New Volunteers file!");
			}
			else if(fileNames[2].toLowerCase().contains("return") == false ) {
				JOptionPane.showMessageDialog(null,"The first file isn't a returning volunteers file!");
			}
			processInput(fileNames);
		}
	}

	private <T> void shuffleArray(ArrayList<T> list) {
	    for(int i = list.size() - 1; i >= 1; i--) {
	    	int j = (int)Math.floor(Math.random() * (i + 1));
	    	T temp = list.get(i);
	    	list.set(i, list.get(j));
	    	list.set(j, temp);
		}
	}

	public void processInput(String[] filenames) {
	    // todo: make sure we are resilient to empty columns
		ArrayList<Teacher> teachers;
		ArrayList<Volunteer> volunteers;
		ArrayList<Driver> drivers;
		int loops = 0;
		int unassignedTolerance = 8;
		while(true) {

			Excel_Input.teachers = new ArrayList<>();
			Excel_Input.drivers = new ArrayList<>();
			Excel_Input.volunteers = new ArrayList<>();

			Excel_Input.makeTeachers(filenames[0]);
			Excel_Input.makeNewParticipants(filenames[1]);
			Excel_Input.makeReturnees(filenames[2]);

			teachers = Excel_Input.getTeachers();
			volunteers = Excel_Input.getVolunteers();
			drivers = Excel_Input.getDrivers();

			for(Volunteer v : volunteers) {
				for(DayOfWeek d : DayOfWeek.values()) {
					shuffleArray(v.getAvailability().getAvailabilityForDay(d));
				}
			}
			for(Driver v : drivers) {
				for(DayOfWeek d : DayOfWeek.values()) {
					shuffleArray(v.getAvailability().getAvailabilityForDay(d));
				}
			}
			for(Teacher t : teachers) {
				for(DayOfWeek d : DayOfWeek.values()) {
					shuffleArray(t.getAvailability().getAvailabilityForDay(d));
				}
			}

			shuffleArray(drivers);
			shuffleArray(volunteers);
			shuffleArray(teachers);


			Scheduler myScheduler = new Scheduler(drivers, volunteers, teachers);
			myScheduler.assignDrivers();
			myScheduler.assignRiders();

			// count unassigned riders
			int c = 0;
			for(Volunteer r : volunteers) {
			    if(r.getAssignment() == null) c++;
			}
			System.out.println(c + " unassigned riders (tolerance = " + unassignedTolerance + " )");
			if(loops % 1000 == 0) unassignedTolerance += 1;
			if(c <= unassignedTolerance) break;
			loops++;
		}
		try {
			// export the TSV file
			String tsvContents = Exportable.createTsv(drivers);
			tsvContents += "\n\n\n";
			tsvContents += Exportable.createTsv((List<Volunteer>) volunteers.stream().filter(x -> x.getAssignment() == null).collect(Collectors.toList()));

			JFileChooser saveChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			saveChooser.removeChoosableFileFilter(saveChooser.getAcceptAllFileFilter());
			saveChooser.addChoosableFileFilter(new FileNameExtensionFilter("TSV files (*.tsv)", "tsv"));
			int r = saveChooser.showSaveDialog(null);
			if(r == JFileChooser.APPROVE_OPTION) {
				File outpath = saveChooser.getSelectedFile();
				if(outpath.isDirectory()) outpath = new File(outpath, "out.tsv");
				String[] parts = outpath.getName().split(".");
				if(parts.length > 0) {
					String extension = parts[parts.length - 1];
					if(!extension.equals("tsv"))
						outpath = new File(outpath.getParent(), outpath.getName() + ".tsv");
				} else {
					outpath = new File(outpath.getParent(), outpath.getName() + ".tsv");
				}
				FileWriter fw = new FileWriter(outpath.getAbsolutePath(), false);
				fw.write(tsvContents);
				fw.close();
			}

		} catch (java.io.IOException e) {
			// ruh roh
			JOptionPane.showMessageDialog(null,"There was an error! Exiting system now.");
			System.exit(0);
		}
		JOptionPane.showMessageDialog(null,"The schedule has been saved successfully!");
		System.exit(0);
	}

	public static void main(String[] args) throws IOException
	{
		SchedulerGUI obj = new SchedulerGUI();
		obj.setVisible(true);
	}
}
