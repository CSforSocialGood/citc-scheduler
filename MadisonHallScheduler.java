import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.io.IOException;
import java.util.Arrays;
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
public class MadisonHallScheduler extends JFrame implements ActionListener {
	private int width = 1000;
	private int height = 400;
	JTextField[] fileFields; 
	JButton[] b;
	JButton submitButton;
	Color background;

	public MadisonHallScheduler() {
		super("MadisonHallScheduler");
		setSize(width, height);
		background = new Color(255, 250, 245); // 230, 230, 250 <- off white //255, 250, 245 <- slight lavender 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Panel 1 --> Holds the title & directions text
		JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(3,1));
			// Title
			JLabel title = new JLabel("Cavs in The Classroom Scheduler");
			title.setFont(new Font("Times New Roman", Font.BOLD, 20));
			title.setHorizontalAlignment(JLabel.CENTER);
			panel1.add(title);
			
			// Directions for submitting files 
			JLabel directions = new JLabel("<HTML><center>Please download all of your Excel/Google Drive Sheets as TSV files! "
					+ "<br> For each sheet below, please select the path of the files on your computer. </center></HTML>");
			directions.setFont(new Font("Times New Roman", Font.ITALIC, 15));
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
			messageFile[0] = new JLabel("Teachers: ");
			messageFile[1] = new JLabel("Curry & New Volunteers: ");
			messageFile[2] = new JLabel("Returning Volunteers: ");
	
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
				messageFile[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
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
			messageSubmit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
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
			submitButton.setBackground(new Color(38, 166, 91));  // Light pink: 244, 121, 131
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
		}
	}
	public static void main(String[] args) throws IOException
	{
		MadisonHallScheduler obj = new MadisonHallScheduler();
		obj.setVisible(true);
	}
}
