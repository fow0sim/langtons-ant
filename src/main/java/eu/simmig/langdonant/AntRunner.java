package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntRunner implements ActionListener {
    public static final int LOOPS = 30;
    public static final int DELAY = 50;
    public static final int SCALE = 4;
    public static final String RULE = "RL";

    JFrame frame;
    AntScreen canvas;
    int playgroundWidth;
    int playgroundHeight;
    JButton runButton;
    JButton stopButton;
    JButton resetButton;
    JLabel status;
    JTextField counter;
    JTextField rule;
    JTextField loops;
    AntPlayground grid;
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
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAntRunner();
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
        canvas = new AntScreen(grid);
        buttons.setLayout(new FlowLayout());
        buttons.add(runButton);
        buttons.add(stopButton);
        buttons.add(resetButton);
        buttons.add(quitButton);
        status = new JLabel("Ant is resting");
        Container bottomPane = new Container();
        bottomPane.setLayout(new BorderLayout());
        bottomPane.add(buttons, BorderLayout.CENTER);
        bottomPane.add(status, BorderLayout.SOUTH);
        Container contents = frame.getContentPane();
        contents.add(bottomPane, BorderLayout.SOUTH);
        contents.add(buildControls(), BorderLayout.WEST);
        contents.add(canvas, BorderLayout.CENTER);
    }

    protected Container buildControls() {
        Container controls = new Container();
        int width = 150;
        int height = 24;
        Font labelFont = new Font("Helvetica", Font.BOLD, 12);
        Dimension size = new Dimension(width, height);
        controls.setLayout(new FlowLayout());
        controls.setPreferredSize(new Dimension(width, 1));
        JLabel counterLabel = new JLabel("Iterations");
        counterLabel.setPreferredSize(size);
        counterLabel.setVerticalAlignment(JLabel.BOTTOM);
        counterLabel.setFont(labelFont);
        counter = new JTextField("0");
        counter.setPreferredSize(size);
        counter.setEditable(false);
        JLabel ruleLabel = new JLabel("Rule");
        ruleLabel.setPreferredSize(size);
        rule = new JTextField(RULE);
        rule.setPreferredSize(size);
        ruleLabel.setVerticalAlignment(JLabel.BOTTOM);
        ruleLabel.setFont(labelFont);
        JLabel loopLabel = new JLabel("Loops");
        loopLabel.setPreferredSize(size);
        loopLabel.setVerticalAlignment(JLabel.BOTTOM);
        loopLabel.setFont(labelFont);
        loops = new JTextField(Integer.toString(LOOPS));
        loops.setPreferredSize(size);
        controls.add(counterLabel);
        controls.add(counter);
        controls.add(ruleLabel);
        controls.add(rule);
        controls.add(loopLabel);
        controls.add(loops);
        return controls;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ant.step();
            status.setText("Ant is moving");
            counter.setText(Integer.toString(ant.getIterations()));
        } catch (IndexOutOfBoundsException ex) {
            grid.setDidEscape(true);
            stopAntRunner();
            ant = null;
            status.setText("Ant has escaped grid");
        }
        canvas.repaint();
    }

    protected void startAntRunner() {
        toggleControls();
        if (ant == null) {
            grid.init();
            ant = new LangdonAnt(grid.getWidth() / 2, grid.getHeight() / 2, grid, rule.getText());
            ant.setLoops(Integer.parseInt(loops.getText()));
            timer = new Timer(DELAY, this);
        }
        timer.start();
    }

    protected void resetAntRunner() {
        ant = null;
    }

    protected void stopAntRunner() {
        toggleControls();
        timer.stop();
        status.setText("Ant is resting");
    }

    protected void toggleControls() {
        boolean base = runButton.isEnabled();
        runButton.setEnabled(!base);
        stopButton.setEnabled(base);
        resetButton.setEnabled(!base);
        rule.setEditable(!base);
        loops.setEditable(!base);
    }

}
