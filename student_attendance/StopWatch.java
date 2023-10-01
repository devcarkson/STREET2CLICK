import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JLabel timeLabel;
    private JButton startButton, lapButton, pauseButton, resumeButton, stopButton;
    private Timer timer;
    private long startTime, pausedTime, elapsedTime;

    public Stopwatch() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timeLabel);

        startButton = new JButton("Start");
        lapButton = new JButton("Lap");
        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        stopButton = new JButton("Stop");

        startButton.addActionListener(this);
        lapButton.addActionListener(this);
        pauseButton.addActionListener(this);
        resumeButton.addActionListener(this);
        stopButton.addActionListener(this);

        add(startButton);
        add(lapButton);
        add(pauseButton);
        add(resumeButton);
        add(stopButton);

        timer = new Timer(1000, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startTime = System.currentTimeMillis();
            timer.start();
        } else if (e.getSource() == lapButton) {
            String lapTime = timeLabel.getText();
            System.out.println("Lap Time: " + lapTime);
        } else if (e.getSource() == pauseButton) {
            pausedTime = System.currentTimeMillis();
            timer.stop();
        } else if (e.getSource() == resumeButton) {
            long pauseDuration = System.currentTimeMillis() - pausedTime;
            startTime += pauseDuration;
            timer.start();
        } else if (e.getSource() == stopButton) {
            timer.stop();
            timeLabel.setText("00:00:00");
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