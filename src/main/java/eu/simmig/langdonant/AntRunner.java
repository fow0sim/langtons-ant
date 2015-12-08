package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntRunner {
    public static final int LOOPS = 100;

    JFrame frame;
    Container canvas;
    GridSpace grid;
    LangdonAnt ant;

    public void run() {
        frame = new JFrame("Langdons Ant");
        buildScreen(frame);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setPreferredSize(new Dimension(width/2, height/2));
        frame.setLocation(width/4, height/4);
        grid = new GridSpace(width/2, height/2);
        ant = new LangdonAnt(width/4, height/4, grid);
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
        canvas = new JTextArea();
        buttons.setLayout(new FlowLayout());
        buttons.add(runButton);
        buttons.add(quitButton);
        Container contents = frame.getContentPane();
        contents.add(buttons, BorderLayout.SOUTH);
        contents.add(canvas, BorderLayout.CENTER);
    }

    protected void startAntRunner() {
        for (int i = 0; i < LOOPS; i += 1) {
            DrawPoint point = ant.step();
            Color color = (point.getColor() == GridSpace.BLACK) ? Color.black : Color.white;
            Graphics g = canvas.getGraphics();
            g.setColor(color);
            g.drawLine(point.getX(), point.getY(), point.getX(), point.getY());
            g.dispose();
        }
    }


}
