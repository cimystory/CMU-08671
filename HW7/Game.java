import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Xiaoyu Bai
 */
public class Game implements ActionListener {
    /**
     * Off pattern.
     */
    private static final String STR_OFF = "   ";
    /**
     * Up pattern.
     */
    private static final String STR_ON = ":-)";
    /**
     * Down pattern.
     */
    private static final String STR_HIT = ":-O";
    /**
     * Off color.
     */
    private static final Color COL_OFF = Color.lightGray;
    /**
     * Up color.
     */
    private static final Color COL_ON = Color.green;
    /**
     * Down color.
     */
    private static final Color COL_HIT = Color.red;
    /**
     * Start button.
     */
    private JButton buttonStart;
    /**
     * Mole buttons.
     */
    private JButton[] buttonArrayMole;
    /**
     * Text field of time left.
     */
    private JTextField textFieldTimeLeft;
    /**
     * Text field of score.
     */
    private JTextField textFieldScore;
    /**
     * Counter from 20 to 0.
     */
    private int counter;
    /**
     * Score.
     */
    private int score;
    /**
     * Random integer generator.
     */
    private static Random randNum = new Random();
    /**
     * Constructor.
     */
    public Game() {
        /**
         * Window.
         */
        JFrame frame = new JFrame("Whack-a-moleSample GUI");
        frame.setResizable(true);
        frame.setSize(600, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * Main panel.
         */
        JPanel mainPanel = new JPanel(new FlowLayout());
        /**
         * Start button.
         */
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(this);
        mainPanel.add(buttonStart);
        /**
         * Time left label.
         */
        JLabel labelTimeLeft = new JLabel("     Time Left: ", SwingConstants.LEFT);
        labelTimeLeft.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        mainPanel.add(labelTimeLeft);
        /**
         * Time left text field.
         */
        textFieldTimeLeft = new JTextField(10);
        textFieldTimeLeft.setEditable(false);
        textFieldTimeLeft.setVisible(true);
        mainPanel.add(textFieldTimeLeft);
        /**
         * Score label.
         */
        JLabel labelScore = new JLabel("     Score: ", SwingConstants.LEFT);
        labelScore.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        mainPanel.add(labelScore);
        /**
         * Score text field.
         */
        textFieldScore = new JTextField(10);
        textFieldScore.setEditable(false);
        textFieldScore.setVisible(true);
        mainPanel.add(textFieldScore);
        /**
         * Panel for moles.
         */
        JPanel panelMole = new JPanel(new GridLayout(8, 8));
        mainPanel.add(panelMole);
        /**
         * Button array for moles.
         */
        buttonArrayMole = new JButton[64];
        for (int i = 0; i < 64; i++) {
            buttonArrayMole[i] = new JButton(STR_OFF);
            buttonArrayMole[i].setBackground(COL_OFF);
            buttonArrayMole[i].setPreferredSize(new Dimension(60, 30));
            buttonArrayMole[i].addActionListener(this);
            panelMole.add(buttonArrayMole[i]);
        }
        // Set window to be visible.
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    /**
     * Runnable for moles.
     * @author Xiaoyu Bai
     */
    public class MoleRunnable implements Runnable {
        /**
         * Button which is controled by this thread.
         */
        private JButton buttonMole;
        /**
         * Constructor.
         * @param button mole button corresponds to the thread.
         */
        public MoleRunnable(JButton button) {
            this.buttonMole = button;
            buttonMole.setText(STR_OFF);
            buttonMole.setBackground(COL_OFF);
        }
        @Override
        public void run() {
            synchronized (buttonMole) {
                try {
                    while (counter >= 0) {
                        int timeSleep = randNum.nextInt(4000);
                        if (timeSleep < 1000) {
                            timeSleep = 1000;
                        }
                        Thread.sleep(randNum.nextInt(timeSleep));
                        if (buttonMole.getText().equals(STR_OFF)) {
                            buttonMole.setText(STR_ON);
                            buttonMole.setBackground(COL_ON);
                        } else {
                            buttonMole.setText(STR_OFF);
                            buttonMole.setBackground(COL_OFF);
                            Thread.sleep(timeSleep);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (counter <= 0) {
                    buttonMole.setText(STR_OFF);
                    buttonMole.setBackground(COL_OFF);
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Each mole button corresponds to one thread.
         */
        Thread[] threadMole = new Thread[64];
        if (e.getSource() == buttonStart) {
            buttonStart.setEnabled(false);
            counter = 20;
            score = 0;
            textFieldScore.setText(Integer.toString(score));
            Thread timeThread = new Thread(new TimeRunnable());
            timeThread.start();
            for (int i = 0; i < 64; i++) {
                threadMole[i] = new Thread(new MoleRunnable(buttonArrayMole[i]));
                threadMole[i].start();
            }
        }
        for (int i = 0; i < 64; i++) {
            if (e.getSource() == buttonArrayMole[i]) {
                if (counter >= 0) {
                    if (buttonArrayMole[i].getText().equals(STR_ON)) {
                        score++;
                        textFieldScore.setText(Integer.toString(score));
                        buttonArrayMole[i].setText(STR_HIT);
                        buttonArrayMole[i].setBackground(COL_HIT);
                    }
                }
            }
        }
    }
    /**
     * Nested class implementing runnable to run timing thread.
     * @author Xiaoyu Bai
     */
    public class TimeRunnable implements Runnable {
        @Override
        public void run() {
            try {
                // Counter for 1 second.
                while (counter >= 0) {
                    textFieldTimeLeft.setText(Integer.toString(counter));
                    counter--;
                    Thread.sleep(1000);
                }
                // After counter reaches 0, set all buttons off.
                if (counter <= 0) {
                    for (int i = 0; i < 64; i++) {
                        buttonArrayMole[i].setText(STR_OFF);
                        buttonArrayMole[i].setBackground(COL_OFF);
                    }
                }
                // After finishing, sleep for 5 seconds.
                Thread.sleep(5000);
            } catch (InterruptedException errTime) {
                throw new AssertionError(errTime);
            }
            counter = 20;
            score = 0;
            textFieldTimeLeft.setText(Integer.toString(counter));
            textFieldScore.setText(Integer.toString(score));
            buttonStart.setEnabled(true);
        }
    }
    /**
     * Main method.
     * @param args none.
     */
    public static void main(String[] args) {
        new Game();
    }
}
