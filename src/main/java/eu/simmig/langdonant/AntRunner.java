package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntRunner {
    public static final int LOOPS = 100;

    JFrame frame;
    AntPlayground canvas;
    GridSpace grid;
    LangdonAnt ant;

    public void run() {
        frame = new JFrame("Langdons Ant");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        int scale = 4;
        grid = new GridSpace(width/(2 * scale), height/(2 * scale));
        ant = new LangdonAnt(width/(4 * scale), height/(4 * scale), grid);
        buildScreen(frame);
        frame.setPreferredSize(new Dimension(width/2, height/2));
        frame.setLocation(width/4, height/4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void buildScreen(JFrame frame) {
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAntRunner();
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
        buttons.add(quitButton);
        Container contents = frame.getContentPane();
        contents.add(buttons, BorderLayout.SOUTH);
        contents.add(canvas, BorderLayout.CENTER);
    }

    protected void startAntRunner() {
        try {
            for (int i = 0; i < LOOPS; i += 1) {
                DrawPoint point = ant.step();
            }
        } catch (IndexOutOfBoundsException ex) {
            grid.setDidEscape(true);
        }
        canvas.repaint();
    }

}
