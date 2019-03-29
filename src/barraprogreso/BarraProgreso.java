/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package barraprogreso;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Ingeniero en Computación: Ricardo Presilla
 */
public class BarraProgreso extends JFrame {
    public final static int ONE_SECOND = 1000;
    private JProgressBar progressBar;
    private Timer timer;
    private JButton Boton;
    private LongTask tarea;
    private JTextArea CuadroTexto;
    private String nuevalinea;
    public BarraProgreso(){
        super("ProgressBarDemo");
        nuevalinea = System.getProperty("line.separator");
        tarea = new LongTask();
        //create the demo's UI
        Boton = new JButton("Inicio");
        Boton.setActionCommand("Inicio");
        Boton.addActionListener(new ButtonListener());
        progressBar = new JProgressBar(0, tarea.getLengthOfTask());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        CuadroTexto = new JTextArea(5, 20);
        CuadroTexto.setMargin(new Insets(5,5,5,5));
        CuadroTexto.setEditable(false);
        JPanel panel = new JPanel();
        panel.add(Boton);
        panel.add(progressBar);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(CuadroTexto), BorderLayout.CENTER);
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        //create a timer
        timer = new Timer(ONE_SECOND, new TimerListener());
    }
    //the actionPerformed method in this class
    //is called each time the Timer "goes off"
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            progressBar.setValue(tarea.getCurrent());
            CuadroTexto.append(tarea.getMessage() + nuevalinea);
            CuadroTexto.setCaretPosition(CuadroTexto.getDocument().getLength());
            if (tarea.done()) {
                Toolkit.getDefaultToolkit().beep();
                timer.stop();
                Boton.setEnabled(true);
                progressBar.setValue(progressBar.getMinimum());
            }
        }
    }
    /*the actionPerformed method in this class is called when the user presses the start button*/
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            Boton.setEnabled(false);
            tarea.go();
            timer.start();
        }
    }
        public static void main(String[] args) {
        JFrame frame = new BarraProgreso();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
}
