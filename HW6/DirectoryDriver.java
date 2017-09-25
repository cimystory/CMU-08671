import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * @author Xiaoyu Bai
 *
 */
public class DirectoryDriver implements ActionListener, KeyListener {
    /**
     * Reference to student directory.
     */
    private Directory direc;
    /**
     * Reference to first name text area.
     */
    private JTextArea textFirstName;
    /**
     * Reference to last name text area.
     */
    private JTextArea textLastName;
    /**
     * Reference to Andrew ID text area.
     */
    private JTextArea textAndrew;
    /**
     * Reference to phone number text area.
     */
    private JTextArea textPhoneNum;
    /**
     * Reference to ADD button.
     */
    private JButton buttonAdd;
    /**
     * Reference to Andrew ID (to delete) text area.
     */
    private JTextArea textAndrewDel;
    /**
     * Reference to DELETE button.
     */
    private JButton buttonDel;
    /**
     * Reference to Search Key text area.
     */
    private JTextArea textSearchKey;
    /**
     * Reference to By Andrew ID button.
     */
    private JButton buttonByAndrew;
    /**
     * Reference to By First Name button.
     */
    private JButton buttonByFirstName;
    /**
     * Reference to By Last Name button.
     */
    private JButton buttonByLastName;
    /**
     * Reference to Results text area.
     */
    private JTextArea textResults;
    /**
     * Constructor where JFrame and other components are instantiated.
     */
    public DirectoryDriver() {
        /**
         * Create new directory.
         */
        direc = new Directory();
        /**
         * Window.
         */
        JFrame frame = new JFrame("Directory Driver");
        frame.setResizable(true);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Main panel that contains other panels.
         */
        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        /******************** ROW 1 ***********************/
        /**
         * Row 1 panel.
         */
        JPanel panelRow1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelRow1.setBorder(BorderFactory.createTitledBorder("Add a new student"));
        mainPanel.add(panelRow1);
        /**
         * Label of first name.
         */
        JLabel labelFirstName = new JLabel("First Name:", SwingConstants.LEFT);
        labelFirstName.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow1.add(labelFirstName);
        textFirstName = new JTextArea(2, 10);
        textFirstName.setLineWrap(true);
        panelRow1.add(textFirstName);
        /**
         * Label of last name.
         */
        JLabel labelLastName = new JLabel("Last Name:");
        labelLastName.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow1.add(labelLastName);
        textLastName = new JTextArea(2, 10);
        textLastName.setLineWrap(true);
        panelRow1.add(textLastName);
        /**
         * Label of Andrew ID.
         */
        JLabel labelAndrew = new JLabel("Andrew ID:");
        labelAndrew.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow1.add(labelAndrew);
        textAndrew = new JTextArea(2, 10);
        textAndrew.setLineWrap(true);
        panelRow1.add(textAndrew);
        /**
         * Label of phone number.
         */
        JLabel labelPhoneNum = new JLabel("Phone Number:");
        labelPhoneNum.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow1.add(labelPhoneNum);
        textPhoneNum = new JTextArea(2, 10);
        textPhoneNum.setLineWrap(true);
        panelRow1.add(textPhoneNum);
        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(this);
        panelRow1.add(buttonAdd);
        /******************** ROW 2 ***********************/
        /**
         * Row 2 panel.
         */
        JPanel panelRow2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelRow2.setBorder(BorderFactory.createTitledBorder("Delete a student"));
        mainPanel.add(panelRow2);
        /**
         * Label to Andrew ID to delete.
         */
        JLabel labelAndrewDel = new JLabel("Andrew ID:");
        labelAndrewDel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow2.add(labelAndrewDel);
        textAndrewDel = new JTextArea(2, 20);
        textAndrewDel.setLineWrap(true);
        panelRow2.add(textAndrewDel);
        buttonDel = new JButton("Delete");
        buttonDel.addActionListener(this);
        panelRow2.add(buttonDel);
        /******************** ROW 3 ***********************/
        /**
         * Row 3 panel.
         */
        JPanel panelRow3 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelRow3.setBorder(BorderFactory.createTitledBorder("Search student(s)"));
        mainPanel.add(panelRow3);
        /**
         * Label to search key.
         */
        JLabel labelSearchKey = new JLabel("Search Key:");
        labelSearchKey.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        panelRow3.add(labelSearchKey);
        textSearchKey = new JTextArea(2, 20);
        textSearchKey.setLineWrap(true);
        textSearchKey.addKeyListener(this);
        panelRow3.add(textSearchKey);
        buttonByAndrew = new JButton("By Andrew ID");
        buttonByAndrew.addActionListener(this);
        panelRow3.add(buttonByAndrew);
        buttonByFirstName = new JButton("By First Name");
        buttonByFirstName.addActionListener(this);
        panelRow3.add(buttonByFirstName);
        buttonByLastName = new JButton("By Last Name");
        buttonByLastName.addActionListener(this);
        panelRow3.add(buttonByLastName);
        /******************** ROW 4 ***********************/
        /**
         * Row 4 panel.
         */
        JPanel panelRow4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelRow4.setBorder(BorderFactory.createTitledBorder("Results"));
        mainPanel.add(panelRow4);
        textResults = new JTextArea(5, 75);
        textResults.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        textResults.setEnabled(false);
        textResults.setText("Current directory size is " + this.direc.size());
        JScrollPane scrollResults = new JScrollPane(textResults);
        panelRow4.add(scrollResults);
        /**
         * Register the main panel to the frame and set it as visible.
         */
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    /**
     * Constructor.
     * @param str Input CSV file.
     * @throws FileNotFoundException
     */
    public DirectoryDriver(String str) {
        this();
        if (str.length() <= 4) {
            throw new IllegalArgumentException("Illegal input file.");
        } else if (!str.substring(str.length() - 4).equalsIgnoreCase(".csv")) {
            throw new IllegalArgumentException("Input must be CSV file.");
        } else {
            System.out.println("Read data from " + str);
            try {
                /**
                 * CSV reader.
                 */
                CSVReader csv = new CSVReader(new FileReader(str));
                /**
                 * String array. First name, last name, andrew id, and phone number.
                 * Pass the first line which is header.
                 */
                String[] thisLine = csv.readCSVLine();
                while ((thisLine = csv.readCSVLine()) != null) {
                    direc.addStudent(new Student(thisLine[2], thisLine[0], thisLine[1], thisLine[3]));
                    System.out.println("Directory size is " + direc.size());
                }
                csv.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        textResults.setText("Current directory size is " + direc.size());
    }
    /**
     * Set focus to searchKey text area.
     */
    public void setFocus() {
        textSearchKey.requestFocus();
    }
    @Override
    /**
     * Action to perform.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAdd) {
            if (textAndrew.getText().trim().equals("")) {
                textResults.setText("Andrew ID missing.");
                return;
            } else if (textFirstName.getText().trim().equals("")) {
                textResults.setText("First name missing");
                return;
            } else if (textLastName.getText().trim().equals("")) {
                textResults.setText("Last name missing");
                return;
            }
            Student s = new Student(textAndrew.getText(), textFirstName.getText(),
                    textLastName.getText(), textPhoneNum.getText());
            try {
                direc.addStudent(s);
                textResults.setText("Student added: \n" + s + "\n");
                textResults.append("Current directory size is " + direc.size());
                textAndrew.setText("");
                textFirstName.setText("");
                textLastName.setText("");
                textPhoneNum.setText("");
            } catch (Exception e1) {
                textResults.setText("Student already exist with ID: " + textAndrew.getText());
            }
            return;
        }
        if (e.getSource() == buttonDel) {
            try {
                direc.deleteStudent(textAndrewDel.getText());
                textResults.setText("Student deleted: " + textAndrewDel.getText() + "\n");
                textResults.append("Current directory size is " + direc.size());
                textAndrewDel.setText("");
            } catch (Exception e2) {
                textResults.setText("Student does not exist with ID: " + textAndrewDel.getText());
            }
            return;
        }
        if (e.getSource() == buttonByAndrew) {
            Student s = direc.searchByAndrewId(textSearchKey.getText());
            if (s == null) {
                textResults.setText("No matching Andrew ID: " + textSearchKey.getText());
            } else {
                textResults.setText("Student info: \n" + s.toString());
                textSearchKey.setText("");
            }
            return;
        }
        if (e.getSource() == buttonByFirstName) {
            List<Student> s = direc.searchByFirstName(textSearchKey.getText());
            if (s.isEmpty()) {
                textResults.setText("No matching first name: " + textSearchKey.getText()
                        + " (case sensitive)");
            } else {
                textResults.setText("Student info: \n");
                for (int i = 0; i < s.size(); i++) {
                    textResults.append(s.get(i).toString() + "\n");
                }
                textSearchKey.setText("");
            }
            return;
        }
        if (e.getSource() == buttonByLastName) {
            List<Student> s = direc.searchByLastName(textSearchKey.getText());
            if (s.isEmpty()) {
                textResults.setText("No matching last name: " + textSearchKey.getText()
                        + " (case sensitive)");
            } else {
                textResults.setText("Student info: \n");
                for (int i = 0; i < s.size(); i++) {
                    textResults.append(s.get(i).toString() + "\n");
                }
                textSearchKey.setText("");
            }
            return;
        }
    }
    @Override
    /**
     * Set enter to press searchByAndrew button.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //textResults.setText("Hello");
            Student s = direc.searchByAndrewId(textSearchKey.getText());
            if (s == null) {
                textResults.setText("No matching Andrew ID: " + textSearchKey.getText());
            } else {
                textResults.setText("Student info: \n" + s.toString());
                textSearchKey.setText("");
                textSearchKey.requestFocus();
            }
        }
    }
    @Override
    /**
     * No use.
     */
    public void keyReleased(KeyEvent e) {
    }
    @Override
    /**
     * No use.
     */
    public void keyTyped(KeyEvent e) {
    }
    /**
     * Main function.
     * @param args Input CSV file (or empty).
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("Too many input argument.");
        } else if (args.length == 1) {
            new DirectoryDriver(args[0]).setFocus();
        } else if (args.length == 0) {
            new DirectoryDriver().setFocus();
        }
    }
}
