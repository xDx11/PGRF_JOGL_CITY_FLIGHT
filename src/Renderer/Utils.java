package Renderer;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

/**
 *  Trida s pomocnymi metodami pro zpracovani textur, vycentrovani okna na stred a pozicování okna do obrazovky
 * @author Radek Soucek
 */
public class Utils {        
    
    
    /**
     *  Statická tøída pro naètení textur do opengl bufferu.
     *  Na zaèátku jsou vytvoøeny promìnné File, které odkazují na relativní místo uložení textur.
     *  Promìnné texture 1-31 jsou zde z dùvodu usnadnìní práce s indexy do bufferu s texturami pro následné 
     *  bindování textur k urèeným objektùm.
     *  + celá metoda obsahuje výpis informací ohlednì naèítání aplikace pøi startu (metoda init)
     */
    public static void nactiTextury(){
        File fileBrick = new File("img\\houseOne.jpg");
        File fileGrass = new File("img\\grassEdit.jpg");
        File file = new File("img\\skySide.jpg");;
        File filePanel = new File("img\\panelak.jpg");
        File fileRoof = new File ("img\\roofYellow.jpg");
        File filePanelZed = new File("img\\panelZedEd.jpg");
        File fileStrom = new File("img\\strom2.png");
        File fileBside = new File("img\\busSide.png");
        File fileBfront = new File("img\\busFront.png");
        File fileBtop = new File("img\\busTop.png");
        File fileMrako = new File("img\\skyscraper.jpg");
        File fileGrassW = new File("img\\grassWidth.jpg");
        File fileKomin = new File("img\\komin.jpg");
        File fileGrassNor = new File("img\\grass.jpg");
        File fileGrassTR = new File("img\\grassTR.jpg");
        File fileGrassLR = new File("img\\grassLR.jpg");
        File fileGrassLTR = new File("img\\grassLTR.jpg");
        File fileGrassR = new File("img\\grassR.jpg");
        File fileStad = new File("img\\stadion2.png");
        File fileParkTop = new File("img\\parkTop.jpg");
        File filePlot = new File("img\\fence2.png");
        File fileLogo = new File("img\\bwin.jpg");
        File fileSkyNight = new File("img\\night_sky2.jpg");
        File fileBusZadek = new File("img\\busZadek.jpg");
        File fileAsfalt = new File("img\\asfalt.jpg");
        File fileWood = new File("img\\woodWall.jpg");
        File fileHeliport = new File("img\\heliport.jpg");
        File fileDeska = new File("img\\deska.png");
        File fileDeskaTop = new File("img\\deskaTop.png");
        File fileFactory = new File("img\\factory.jpg");
        File fileBusSideTran = new File("img\\busSideTran.png");
        Texture texture, texture2, texture3, texture4, texture5, texture6, textureStrom7, 
                textureBside8,textureBFront9, textureBtop10, texture11, texture12, texture13,
                texture14, texture15, texture16, texture17, texture18, texture19, texture20, texture21,
                texture22, texture23, texture24, texture25, texture26, texture27, texture28, texture29,
                texture30, texture31;
        try {
                System.out.println("Loading texture...");
                System.out.print("/");texture = TextureIO.newTexture(file, true);
                System.out.print("/");texture2 = TextureIO.newTexture(fileBrick, true);
                System.out.print("/");texture3 = TextureIO.newTexture(fileGrass, true);
                System.out.print("/");texture4 = TextureIO.newTexture(filePanel, true);
                System.out.print("/");texture5 = TextureIO.newTexture(fileRoof, true);
                System.out.print("/");texture6 = TextureIO.newTexture(filePanelZed, true);
                System.out.print("/");textureStrom7 = TextureIO.newTexture(fileStrom, true);
                System.out.print("/");textureBside8 = TextureIO.newTexture(fileBside, true);
                System.out.print("/");textureBFront9 = TextureIO.newTexture(fileBfront, true);
                System.out.print("/");textureBtop10 = TextureIO.newTexture(fileBtop, true);
                System.out.print("/");texture11 = TextureIO.newTexture(fileMrako, true);
                System.out.print("/");texture12 = TextureIO.newTexture(fileGrassW, true);
                System.out.print("/");texture13 = TextureIO.newTexture(fileKomin, true);
                System.out.print("/");texture14 = TextureIO.newTexture(fileGrassNor, true);
                System.out.print("/");texture15 = TextureIO.newTexture(fileGrassLR, true);
                System.out.print("/");texture16 = TextureIO.newTexture(fileGrassLTR, true);
                System.out.print("/");texture17 = TextureIO.newTexture(fileGrassTR, true);
                System.out.print("/");texture18 = TextureIO.newTexture(fileGrassR, true);
                System.out.print("/");texture19 = TextureIO.newTexture(fileStad, true);
                System.out.print("/");texture20 = TextureIO.newTexture(fileParkTop, true);
                System.out.print("/");texture21 = TextureIO.newTexture(filePlot, true);
                System.out.print("/");texture22 = TextureIO.newTexture(fileLogo, true);
                System.out.print("/");texture23 = TextureIO.newTexture(fileSkyNight, true);
                System.out.print("/");texture24 = TextureIO.newTexture(fileBusZadek, true);
                System.out.print("/");texture25 = TextureIO.newTexture(fileAsfalt, true);
                System.out.print("/");texture26 = TextureIO.newTexture(fileWood, true);
                System.out.print("/");texture27 = TextureIO.newTexture(fileHeliport, true);
                System.out.print("/");texture28 = TextureIO.newTexture(fileDeska, true);
                System.out.print("/");texture29 = TextureIO.newTexture(fileDeskaTop, true);                        
                System.out.print("/");texture30 = TextureIO.newTexture(fileFactory, true); 
                System.out.print("/");texture31 = TextureIO.newTexture(fileBusSideTran, true); 
                System.out.println();
                System.out.println("Loading complete!");
        } catch (IOException e) {
                System.err.println("Chyba cteni souboru s texturou");
        }
    }
    
     /**
     * Metoda pro zjisteni nastaveneho rozliseni
     * @return Point(sirka,vyska)
     */
     public static Point getRozliseni(){
        Toolkit t = Toolkit.getDefaultToolkit();
        int sirka = t.getScreenSize().width;
        int vyska = t.getScreenSize().height;
        return new Point(sirka,vyska);
    }
    
    /**
     * Metoda umistujici okno na stred obrazovky
     * @param c Component
     */
    public static void setOknoNaStred(Component c){
        Point p = getRozliseni();
        c.setLocation(p.x/2-c.getWidth()/2, p.y/2-c.getHeight()/2);
    }
    
    /**
     * Metoda umistujici dialogove okno napovedy napravo od hlavniho okna aplikace
     * @param c Component
     */
    public static void setOknoVedleHlavniho(Component c){
        Point p = getRozliseni();
        c.setLocation(p.x/2-c.getWidth()/2-575, p.y/2-c.getHeight()/2);
    }        
}
