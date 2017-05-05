package GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import Renderer.Utils;

/**
 * @author Radek Soucek
 * Tato trida slouzi pouze jako dialogove okno, ktere zobrazi zadani a napovedu k ovladani programu
 */
public class DialogNavod extends JDialog {
    private static final long serialVersionUID = -4520811232848183031L;
    Container pane;

    public DialogNavod(){
        setTitle("Nápovìda");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        pack();
        setSize(350,600);
        Utils.setOknoVedleHlavniho(DialogNavod.this);
        setVisible(true);	
    }
    
    
    public void init(){
    	pane= this.getContentPane();
        pane.setLayout(new FlowLayout()); 
        StringBuilder buff = new StringBuilder();
        buff.append("<html>");
        buff.append(String.format("<h1 align=center style=\" color: red; \"> B7 - Prùlet mìstem </h1>"));
        buff.append(String.format("<h2 align=center style=\" color: red; \"> Animace 3D </h2>"));
        buff.append(String.format("<ul style=\" margin-left: 20px; \"><li> <span style=\"color: red\">Pøedmìt:</span> Poèítaèová grafika 2 </li>"));
        buff.append(String.format("<li> <span style=\"color: red\">Autor:</span> Radek Souèek </li>"));
        buff.append(String.format("<li> <span style=\"color: red\">Ãškoly:</span> Kolize s objekty a dynamika letu </li>"));
        buff.append("</ul>");
        buff.append("</html>");
        JLabel uvod = new JLabel(buff.toString());
        JLabel ovladani = new JLabel("<html> "+                    
                                     "<span style=\"color: red\">Kamera:</span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                                    "Rozhlížení pomocí myši <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                                    "W,S - Plyn, brzda<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                                    "A(Left),D(Right),UP,DOWN - smìrovky<br>"+    
                                     "<span style=\"color: red\">Režim pádu:</span>  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                     "F - zapnout/vypnout<br>" +                                                                          
                                     "<span style=\"color: red\">Palubní deska: </span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +                                                    
                                     "P - zapnout/vypnout<br>" +
                                     "<span style=\"color: red\">Osvìtlení: </span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +                                                    
                                     "K - Pozorovatel/Slunce<br>" +
                                     "<span style=\"color: red\">Svìtlo: </span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +                                                    
                                     "L - zapnout/vypnout<br>" +
                                     "<span style=\"color: red\">Animace: </span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +                                                    
                                     "B - zapnout/vypnout<br>" +
                                     "<span style=\"color: red\">Mlha:</span>  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                     "M - zapnout/vypnout<br>" +                                     
                                     "<span style=\"color: red\">Noc: </span><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +                                                    
                                     "N - zapnout/vypnout<br>" +                                     
                                     "</html>");
	pane.add(uvod);
        pane.add(ovladani);        
    }    
}
