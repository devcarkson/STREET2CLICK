import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    private JButton startButton, lapButton;
    private JTextArea lapTimesTextArea;
    private Timer timer;
    private long startTime, elapsedTime, pausedTime;

    public Stopwatch() {
        setTitle("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        add(timeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Start");
        lapButton = new JButton("Lap");

        startButton.addActionListener(this);
        lapButton.addActionListener(this);

        buttonPanel.add(startButton);
        buttonPanel.add(lapButton);
        add(buttonPanel, BorderLayout.CENTER);

        lapTimesTextArea = new JTextArea(10, 20);
        lapTimesTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        lapTimesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lapTimesTextArea);
        add(scrollPane, BorderLayout.SOUTH);

        timer = new Timer(1000, this);

        // Adjust font sizes and button sizes based on screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        if (screenWidth <= 600) { // Mobile view
            timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
            lapTimesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
            startButton.setPreferredSize(new Dimension(100, 50));
            lapButton.setPreferredSize(new Dimension(100, 50));
        } else if (screenWidth <= 1024) { // Tablet view
            timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
            lapTimesTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
            startButton.setPreferredSize(new Dimension(150, 75));
            lapButton.setPreferredSize(new Dimension(150, 75));
        } else { // Desktop view
            timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
            lapTimesTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
            startButton.setPreferredSize(new Dimension(200, 100));
            lapButton.setPreferredSize(new Dimension(200, 100));
        }

        pack();
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (startButton.getText().equals("Start")) {
                if (elapsedTime == 0) {
                    startTime = System.currentTimeMillis();
                } else {
                    startTime = System.currentTimeMillis() - elapsedTime;
                }
                timer.start();
                startButton.setText("Stop");
                lapButton.setText("Lap");
            } else if (startButton.getText().equals("Stop")) {
                timer.stop();
                startButton.setText("Resume");
                lapButton.setText("Reset");
                pausedTime = System.currentTimeMillis();
            } else if (startButton.getText().equals("Resume")) {
                long now = System.currentTimeMillis();
                startTime += now - pausedTime;
                timer.start();
                startButton.setText("Stop");
                lapButton.setText("Lap");
            }
        } else if (e.getSource() == lapButton) {
            if (lapButton.getText().equals("Lap")) {
                String lapTime = timeLabel.getText();
                lapTimesTextArea.append(lapTime + "\n");
                lapTimesTextArea.setCaretPosition(lapTimesTextArea.getDocument().getLength());
            } else {
                elapsedTime = 0;
                startTime = 0;
                lapTimesTextArea.setText("");
                timeLabel.setText("00:00:00");
            }
        } else if (e.getSource() == timer) {
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimeLabel(elapsedTime);
        }
    }

    private void updateTimeLabel(long time) {
        long hours = time / 3600000;
        long minutes = (time % 3600000) / 60000;
        long seconds = (time % 60000) / 1000;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(timeString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.setVisible(true);
        });
    }
}
