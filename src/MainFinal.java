import controleur.Simulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class MainFinal extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton visionA8VoisinsRadioButton;
    private JRadioButton visionA4VoisinsRadioButton;
    private JCheckBox appelerLaPrinceDeCheckBox;
    private JCheckBox ajouterLeMedecinCheckBox;
    private JSlider slider1;
    private int voisinages;
    private int vitesseSimulation;

    public MainFinal() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        voisinages = 4;
        vitesseSimulation = 500;
        slider1 = new JSlider();
        slider1.setMajorTickSpacing(100);
        slider1.setMinorTickSpacing(1);
        slider1.setInverted(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
                Thread thread = new Thread((Runnable) new Simulator(voisinages,vitesseSimulation,appelerLaPrinceDeCheckBox.isSelected(),ajouterLeMedecinCheckBox.isSelected()));
                System.out.println(vitesseSimulation);
                System.out.println(voisinages);
                System.out.println(appelerLaPrinceDeCheckBox.isSelected());
                System.out.println(ajouterLeMedecinCheckBox.isSelected());
                thread.start();
            }
        });
        
       
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        
        visionA4VoisinsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voisinages = 4;
            }
        });

        visionA8VoisinsRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voisinages = 8;
            }
        });


        slider1.addChangeListener(e -> vitesseSimulation = slider1.getValue());
    }

    private void onOK() {
        //this.setVisible(false);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }



    public static void main(String[] args) {
        MainFinal dialog = new MainFinal();
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        dialog.setVisible(true);
        System.exit(0);
    }
}
