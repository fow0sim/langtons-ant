package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntRunner implements ActionListener {
    public static final int LOOPS = 1;
    public static final int DELAY = 50;
    public static final int SCALE = 4;

    JFrame frame;
    AntPlayground canvas;
    int playgroundWidth;
    int playgroundHeight;
    JButton runButton;
    JButton stopButton;
    GridSpace grid;
    LangdonAnt ant;
    private Timer timer;

    public void run() {
        frame = new JFrame("Langdons Ant");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        playgroundHeight = height / (2 * SCALE);
        playgroundWidth = width / (2 * SCALE);
        grid = new GridSpace(playgroundWidth, playgroundHeight);
        buildScreen(frame);
        frame.setPreferredSize(new Dimension(width/2, height/2));
        frame.setLocation(width/4, height/4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void buildScreen(JFrame frame) {
        runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAntRunner();
            }
        });
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAntRunner();
            }
        });
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Container buttons = new Container();
        canvas = new AntPlayground(grid);
        buttons.setLayout(new FlowLayout());
        buttons.add(runButton);
        buttons.add(stopButton);
        buttons.add(quitButton);
        Container contents = frame.getContentPane();
        contents.add(buttons, BorderLayout.SOUTH);
        contents.add(canvas, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            for (int i = 0; i < LOOPS; i += 1) {
                DrawPoint point = ant.step();
            }
        } catch (IndexOutOfBoundsException ex) {
            grid.setDidEscape(true);
            stopAntRunner();
            timer = null;
        }
        canvas.repaint();
    }

    protected void startAntRunner() {
        stopButton.setEnabled(true);
        runButton.setEnabled(false);
        if (timer == null) {
            grid.init();
            ant = new LangdonAnt(playgroundWidth / 2, playgroundHeight / 2, grid);
            timer = new Timer(DELAY, this);
        }
        timer.start();
    }

    protected void stopAntRunner() {
        stopButton.setEnabled(false);
        runButton.setEnabled(true);
        timer.stop();
    }

}
