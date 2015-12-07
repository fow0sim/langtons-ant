package eu.simmig.langdonant;

public class LangdonAntApplication {

    public static void main(String args[]) {
        final AntRunner antRunner = new AntRunner();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                antRunner.run();
            }
        });
    }

}
