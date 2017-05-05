package GUI;

import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Renderer.Renderer;
import Renderer.Utils;

/**
 * Hlavni okno aplikace nastavujici zakladni vlastnosti OpenGL.
 * @author Radek Souèek
 */
public class MainFrame extends JFrame implements ActionListener{
    
    private static final int FPS = 60;
    private GLProfile profile;
    private GLCapabilities capabilities;
    private GLCanvas canvas;
    private Renderer ren;
    private DialogNavod help;
    
    public MainFrame(){
        this.setSize(800, 600);
        this.setTitle("Souèek Radek - Prùlet mìstem");

        // setup OpenGL Version 2
        profile= GLProfile.get(GLProfile.GL2);
        capabilities = new GLCapabilities(profile);
            capabilities.setRedBits(8);
            capabilities.setBlueBits(8);
            capabilities.setGreenBits(8);
            capabilities.setAlphaBits(8);
            capabilities.setDepthBits(24);
            //capabilities.setDoubleBuffered(false);

        // The canvas is the widget that's drawn in the JFrame
        canvas = new GLCanvas(capabilities);
        ren = new Renderer();
                canvas.addGLEventListener(ren);
                canvas.addMouseListener(ren);
                canvas.addMouseMotionListener(ren);
                canvas.addKeyListener(ren);
                canvas.setSize( 800, 600);                

        
        this.add(canvas);

        final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

        this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                                new Thread() {
                     @Override
                     public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                     }
                  }.start();
                        }
                });
        //testFrame.setTitle("");
        menu();
        Utils.setOknoNaStred(this);
        
        this.pack();
        this.setVisible(true);
        canvas.requestFocusInWindow();        
        animator.start();
    }
    
    
    private void menu(){
        JMenu menuNabidka = new JMenu("Nabídka");
        JMenuItem miMlha = new JMenuItem("Mlha");
        miMlha.addActionListener(this);
        menuNabidka.add(miMlha);
        JMenuItem miAnimace = new JMenuItem("Animace");
        miAnimace.addActionListener(this);
        menuNabidka.add(miAnimace);
        menuNabidka.addSeparator();   
        JMenuItem miHelp = new JMenuItem("Nápovìda");
        miHelp.addActionListener(this);
        menuNabidka.add(miHelp);
        menuNabidka.addSeparator();
        JMenuItem miQuit = new JMenuItem("Konec");
        miQuit.addActionListener(this);
        menuNabidka.add(miQuit);                
        
        // Add JMenu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuNabidka);
        setJMenuBar(menuBar);
    }
    
    @Override
     public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Konec":
                System.exit(0);
            case "Mlha":
                boolean mlha = ren.isMlha();
                ren.setMlha(!mlha);                
                break;
            case "Animace":
                ren.setAnimace(!ren.isAnimace());
                break;
            case "Nápovìda":
                if(help==null){
                    help = new DialogNavod();
                } else {
                    help.setVisible(true);
                }
                break;                            
        }
     }
    
    
}
