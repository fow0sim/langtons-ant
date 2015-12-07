package eu.simmig.langdonant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AntRunner {
    JFrame frame;
    JTextArea grid;

    public void run() {
        frame = new JFrame("Langdons Ant");
        buildScreen(frame);
        centerFrame(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setPreferredSize(new Dimension(width/2, height/2));
        frame.setLocation(width/4, height/4);
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
        grid = new JTextArea();
        buttons.setLayout(new FlowLayout());
        buttons.add(runButton);
        buttons.add(quitButton);
        Container contents = frame.getContentPane();
        contents.add(buttons, BorderLayout.SOUTH);
        contents.add(grid, BorderLayout.CENTER);
    }

    protected void startAntRunner() {
        grid.setLineWrap(true);
        grid.append("Now you have to add the implementation\n");
    }

}
