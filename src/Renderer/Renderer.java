package Renderer;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.media.opengl.GL;

/**
 * Hlavní aplikaèní tøída
 * @author Radek Soucek
 */
public class Renderer implements GLEventListener, MouseListener,
		MouseMotionListener, KeyListener {                  
    
	GL2 gl;
	GLAutoDrawable glDrawable;
	GLU glu;
	GLUT glut;

	int width, height, dx, dy, x, y;
	int ox, oy;
        
	float zenit;
	float azimut;
	double ex, ey, ez, px, py, pz, cenx, ceny, cenz, ux, uy, uz;
        double oldPx, oldPy, oldPz;
	float step, rot = 0, trans = 0;	
	double a_rad, z_rad;
	//long oldmils = System.currentTimeMillis();
        long mils;
        long oldmils;
	long oldFPSmils;
        double	fps;
        
        boolean start = false;
        boolean mlha = false;
        boolean noc = false;
        boolean sun = false;
        boolean light = true;
        boolean animace = true;
        boolean palDeska = false;
        boolean zem = false;
        boolean padani = false;
        boolean padam = false;
        boolean kolizeStrechy = false;
        boolean generovani = false;
        boolean vygenerovano = false;
        boolean kolize;
        double rychlost = 0;
        double rychlost2 = 0;
        double uhel= 0;
        double uhelLetadla = 0;
        float motor=0;
        float pad=0;
        
        // Konstanty pro volání display listù
        private static final int DUM = 1;
        private static final int PANELAK = 2;
        private static final int STROM= 3;
        private static final int BUS = 4;
        private static final int PLOT = 5;
        private static final int KOMIN = 6;
        private static final int KVETINAC = 7;        
        
        private final Set<Integer> pressed = new HashSet<Integer>();
        private CollisionDetection collDetec;
        private ArrayList<LetterBox> listBox;        
        
	@Override
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = glDrawable.getGL().getGL2();
		glu = new GLU();
		glut = new GLUT();
                		
                gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);           // Pøední i zadní stranu vyplòujeme
                gl.glClearDepth(1.0f);                                          // Nastavení Z bufferu
                gl.glEnable(GL.GL_DEPTH_TEST);                                  // Zapnutí Z bufferu
                gl.glDepthFunc(GL.GL_LEQUAL);                                   // The Type Of Depth Testing To Do
                gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
                gl.glEnable(GL2.GL_NORMALIZE);         // Pøepoèítávání normál v pøípadì, kdy se používá funkce glScale
                
                gl.glEnable(GL2.GL_LINE_SMOOTH);                                // Zapnuti antialiasingu
                gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
                
                gl.glEnable(GL2.GL_CULL_FACE);         // Zapnuti orezavani odvracenych stran
                gl.glCullFace(GL2.GL_BACK);           // Odvracena strana je zadni strana 
                gl.glFrontFace(GL2.GL_CCW);          // Odvracenou stranu pozname dle posloupnosti bodu polygonu - proti smeru hodinovych rucicek

		Utils.nactiTextury();                // Nacteni vsech textur
                
                System.out.println("Loading D-Lists..."); //Kompilace display listù
                System.out.print("/");DListy.vytvorListDum(gl);
                System.out.print("/");DListy.vytvorListPanelak(gl);
                System.out.print("/");DListy.vytvorListStrom(gl);
                System.out.print("/");DListy.vytvorListBus(gl);
                System.out.print("/");DListy.vytvorListPlot(gl);
                System.out.print("/");DListy.vytvorListKomin(gl, glu); 
                System.out.print("/");DListy.vytvorListKvetinac(gl, glu);
                System.out.println();
                System.out.println("Loading D-Lists complete!");
                
                float[] mat_dif = new float[] {0.8f,0.8f,0.8f,1};   // nastaveni materialu
		float[] mat_spec = new float[] {0.3f,0.3f,0.3f,1};  // nastaveni materialu
		float[] mat_amb = new float[] {0.4f,0.4f,0.4f,1};   // nastaveni materialu

                gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_amb, 0); 
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_dif, 0); 
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_spec, 0); 

                gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_AMBIENT);
                gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_DIFFUSE);
                gl.glColorMaterial(GL2.GL_FRONT, GL2.GL_SPECULAR);                                                
                
                gl.glEnable(GL2.GL_TEXTURE_2D);
                
                collDetec = new CollisionDetection(); // Vytvoreni koliznich obalek objektu sceny
                listBox = collDetec.getListBox();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
            glDrawable = drawable;
            gl = glDrawable.getGL().getGL2();

            mils = System.currentTimeMillis();
            //fps = 1000 / (double)(mils - oldmils + 1);
            //System.out.println("/////////// FPS: "+ fps);
            //oldFPSmils=mils;

            float speed = 10; // pocet stupnu rotace za vterinu
            float step2 = speed * (mils - oldmils) / 1000.0f; // krok za jedno
            step = (mils - oldmils) / 1000.0f; 		                                

            trans = 50 * step;
            rot += 360 * step / 10f;

            //REZIMY DNE
                if(mlha){
                    gl.glPushMatrix();
                    gl.glEnable(GL2.GL_FOG);
                    {
                      float fogColor[] ={ 1f, 1f, 1f, 0.5f };                      
                      gl.glFogi(GL2.GL_FOG_MODE, GL.GL_LINEAR);
                      gl.glFogfv(GL2.GL_FOG_COLOR, fogColor, 0);
                      gl.glFogf(GL2.GL_FOG_DENSITY, 0.35f);
                      gl.glHint(GL2.GL_FOG_HINT, GL.GL_DONT_CARE);
                      gl.glClearColor(1f, 1f, 1f, 1.0f);                        
                    }
                } else if(noc) {
                    gl.glPushMatrix();
                    gl.glEnable(GL2.GL_FOG);
                    {
                    float fogColor[] ={ 0f, 0f, 0f, 0.0f };                      
                    gl.glFogi(GL2.GL_FOG_MODE, GL.GL_LINEAR);
                    gl.glFogfv(GL2.GL_FOG_COLOR, fogColor, 0);
                    gl.glFogf(GL2.GL_FOG_DENSITY, 0.25f);
                    gl.glHint(GL2.GL_FOG_HINT, GL.GL_DONT_CARE);                                                                                 
                    }
                //Snizeni osvetleni v noci
                    float[] light_amb = new float[] {0.4f,0.4f,0.4f,1};// nastaveni ambientni slozky
                    float[] light_dif = new float[] {0.8f,0.8f,0.8f,1};// nastaveni difusni slozky
                    float[] light_spec = new float[] {0.2f,0.2f,0.2f,1};// nastaveni zrcadlove slozky
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_amb,0);
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_dif,0);
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_spec,0);
                } else {
                //NORMALNI REZIM
                    gl.glDisable(GL2.GL_FOG);
                    gl.glFogi(GL2.GL_FOG_MODE, GL.GL_NONE);

                    float[] light_amb = new float[] {1,1,1,1};// nastaveni ambientni slozky
                    float[] light_dif = new float[] {1,1,1,1};// nastaveni difusni slozky
                    float[] light_spec = new float[] {1,1,1,1};// nastaveni zrcadlove slozky
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_amb,0);
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_dif,0);
                    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_spec,0);
                }                                

            // vymazani obrazovky a Z-bufferu
                gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
            //PROJECTION
                gl.glMatrixMode(GL2.GL_PROJECTION);
                gl.glLoadIdentity();
                glu.gluPerspective(45, width / (float) height, 0.1f, 5000.0f);                        

                if(sun){
                    gl.glShadeModel(GL2.GL_SMOOTH);
                    if(light)gl.glEnable(GL2.GL_LIGHT0);		                    
                    gl.glViewport(0,0, width , height );
                }                    

            //MODELVIEW                
                gl.glMatrixMode(GL2.GL_MODELVIEW);
                gl.glLoadIdentity();                                 

            //Transformace pro sky box
                if(!mlha && !noc){
                    gl.glPushMatrix();		
                        gl.glLoadIdentity();
                        gl.glRotatef(-zenit, 1.0f, 0, 0);
                        gl.glRotatef(azimut, 0, 1.0f, 0);			
                        //glu.gluLookAt(0, 0, 0, ex, ey, ez, ux, uy, uz);	                        
                        Objekt.skyBoxDen(gl);		
                    gl.glPopMatrix();
                }  else if(noc){
                        gl.glFogi( GL2.GL_FOG_MODE, GL.GL_LINEAR );
                        gl.glFogf(GL2.GL_FOG_START, 1.0f);
                        gl.glFogf(GL2.GL_FOG_END, 50.0f);
                    gl.glPushMatrix();		
                        gl.glLoadIdentity();
                        gl.glRotatef(-zenit, 1.0f, 0, 0);
                        gl.glRotatef(azimut, 0, 1.0f, 0);
                        gl.glScaled(3, 3, 3);
                        Objekt.skyBoxNoc(gl);		
                    gl.glPopMatrix();

                } else {                    
                    gl.glFogi( GL2.GL_FOG_MODE, GL.GL_LINEAR );
                    gl.glFogf(GL2.GL_FOG_START, 1.0f);
                    gl.glFogf(GL2.GL_FOG_END, 100.0f);
                }                                  				                        

            /*
             * TRANSFORMACE SCENY
             * RIZENI VYSKY - PAD DOLU == motor == 0   
             *   boolean padani = identifikování režimu pádu
             *   boolean padam = identifikování zda letadlo aktuálnì padá (øítí se k zemi)
             *   double pad = pomocná promenna k øízení výšky letu a klesání/stoupání             
             */
                //System.out.println("//////////////////");
                //System.out.println("-py-pad:" + (-py-pad));
                //System.out.println("-py:" + (-py));
                //System.out.println("padani:" + (padani));
                //System.out.println("zem:" + (zem));
                if(padani){                               //Režim padání
                    if(!zem && motor == 0){               //Letadlo padá v pøípadì, že není na zemi a motor je 0
                        pad-=0.1;      
                        padam = true;
                    } else if(zem && motor != 0) {        //Vzlétání ze zemì
                        if(-py-pad<0) pad+=0.1; 
                    }
                    if(motor == 0){                        
                        if(-py-pad>0) {
                            pad+=0.1;
                            zem=true;
                            padam = false;
                            py+=pad;
                            pad = 0;
                        }
                        if(-py>0) py=0.02;
                        gl.glRotatef(-zenit,1.0f,0,0); 
                        gl.glRotatef(azimut,0,1.0f,0);
                        gl.glTranslated(-px,-py-pad,-pz);
                    } else {
                        //pad = 0;
                        if(-py-pad<0 && zem){
                            zem=false;
                            py+=pad;
                            pad = 0;                                
                        }                               
                        padam = false;
                        if(-py>0) py=0.02;
                        gl.glRotatef(-zenit,1.0f,0,0); 
                        gl.glRotatef(azimut,0,1.0f,0);
                        gl.glTranslated(-px,-py-pad,-pz);                                                        
                    }
                } else {
                    //PADANI VYPNUTO                        
                    if(-py>0) py=0.02;            // nelze proletet zemi                        
                    gl.glRotatef(-zenit,1.0f,0,0); 
                    gl.glRotatef(azimut,0,1.0f,0);
                    gl.glTranslated(-px,-py-pad,-pz);                                                
                }
            // ZAPNUTI SVETLA Z POHLEDU POZOROVATELE                                                                        
                if(!sun){                        
                    if(light){
                        float[] light_position=  new float[] {(float)px,(float)py,(float)pz,1.0f};//smer - umisteni v nekonecnu                            
                        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position,0);
                        gl.glEnable(GL2.GL_LIGHTING);
                        gl.glEnable(GL2.GL_LIGHT0);
                        gl.glEnable(GL2.GL_COLOR_MATERIAL);
                        gl.glShadeModel(GL2.GL_SMOOTH);
                    } else {
                        gl.glDisable(GL2.GL_LIGHTING);
                        gl.glDisable(GL2.GL_LIGHT0);
                        gl.glDisable(GL2.GL_COLOR_MATERIAL);
                    }                                                
                }                    

            gl.glPushMatrix();                
            gl.glTranslatef(30, -2, -50); //Posunuti a rotace cele sceny
            gl.glRotatef(-90, 1, 0, 0);

            //Vykreslovani objektu sceny
            //BUDOVY                                
                gl.glPushMatrix();                     
                gl.glCallList(DUM);		                    
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(20, 0, 0);
                gl.glCallList(DUM);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(0, 20, 0);
                gl.glCallList(DUM);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(20, 20, 0);
                gl.glCallList(DUM);
                gl.glPopMatrix();		                            


            //PANELAKY
                gl.glPushMatrix();
                gl.glTranslated(40, 0, 0);
                gl.glCallList(PANELAK);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(60, 0, 0);                    
                gl.glCallList(PANELAK);
                gl.glPopMatrix();                

                gl.glPushMatrix();
                gl.glTranslated(30, -20, 0);
                gl.glRotatef(90, 0, 0, 1);
                gl.glCallList(PANELAK);
                gl.glPopMatrix();                

                gl.glPushMatrix();
                gl.glTranslated(70, -20, 0);
                gl.glRotatef(90, 0, 0, 1);
                gl.glCallList(PANELAK);
                gl.glPopMatrix();                

            //MRAKODRAPY
                gl.glPushMatrix();
                gl.glTranslated(60, 40, 0);                
                Objekt.vytvorMrakodrap(gl, 50);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(40, 40, 0);                
                Objekt.vytvorMrakodrap(gl, 30);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(60, 60, 0);                
                Objekt.vytvorMrakodrap(gl, 60);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(40, 60, 0);                
                Objekt.vytvorMrakodrap(gl, 40);
                gl.glPopMatrix();

            //KOMINY ELEKTRARNY
                gl.glPushMatrix();
                gl.glTranslated(27, 67, 0); 
                gl.glCallList(KOMIN);          
                gl.glTranslated(-13, 0, -3);
                gl.glCallList(KOMIN);          
                gl.glTranslated(-12, -11, -3);
                gl.glCallList(KOMIN);          
                gl.glTranslated(0, -13, -3);
                gl.glCallList(KOMIN);          
                gl.glPopMatrix();

            //ELEKTRARNA
                gl.glPushMatrix();
                gl.glTranslated(30, 40, 0); 
                gl.glRotatef(90, 0, 0, 1);
                Objekt.Elektrarna(gl);
                gl.glPopMatrix();                                                               

            //Trava pod elektrarnu
                gl.glPushMatrix();
                gl.glTranslated(15, 55, 0);                
                Objekt.travaCtverecTR(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(-5, 55, 0);
                Objekt.travaCtverec(gl);                
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(-5, 35, 0);                                
                Objekt.travaCtverecR(gl);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(15, 35, 0);
                Objekt.travaCtverecLTR(gl);
                gl.glPopMatrix();
            //KONEC TRAVY

            //STADION + LOGO
                gl.glPushMatrix();
                gl.glTranslatef(0, 20, 0);

                gl.glPushMatrix();
                gl.glTranslated(-25, -20, 0);                
                gl.glRotatef(180, 0, 0, 1);
                Objekt.Stadion(gl, glu);                                     
                gl.glPopMatrix();

                gl.glPushMatrix();                
                gl.glTranslated(-16, -8, 7);                
                gl.glRotated(160, 0, 0, 1);
                Objekt.Logo(gl);
                gl.glPopMatrix();
            //KONEC STADIONU

            //PARKOVISTE POD STADIONEM
                gl.glPushMatrix();
                gl.glTranslated(-25, -45, 0);                
                Objekt.parkCtverec(gl);
                gl.glTranslated(0, 20, 0);                
                Objekt.parkCtverec(gl);
                gl.glTranslated(0, 20, 0);                
                Objekt.parkCtverec(gl);
                gl.glTranslated(-20, 0, 0);                
                Objekt.parkCtverecGrey(gl);
                gl.glTranslated(0, -20, 0);                
                Objekt.parkCtverecGrey(gl);
                gl.glTranslated(0, -20, 0);                
                Objekt.parkCtverecGrey(gl);
                gl.glPopMatrix();
            //KONEC PARKOVISTE

            //AUTOBUSY NA PARKOVISTI
                gl.glPushMatrix();                    
                // Podmínky pro správné poøadí vykreslení objektù z dùvodù blendingu
                if((azimut>0 && azimut<180) || (azimut>-360 && azimut<-180)){ 
                    gl.glTranslated(-14.75, 10, 0);
                    gl.glRotated(-45, 0, 0, 1);
                    gl.glCallList(BUS);
                    gl.glTranslated(-5, -5, 0);
                    gl.glCallList(BUS);
                    gl.glTranslated(-5, -5, 0);
                    gl.glCallList(BUS);
                    gl.glTranslated(-5, -5, 0);
                    gl.glCallList(BUS);
                } else if(azimut<0 || azimut >-180) {                                        
                    gl.glTranslated(-36, 10, 0);
                    gl.glRotated(-45, 0, 0, 1);
                    gl.glCallList(BUS);
                    gl.glTranslated(5, 5, 0);
                    gl.glCallList(BUS);
                    gl.glTranslated(5, 5, 0);
                    gl.glCallList(BUS);
                    gl.glTranslated(5, 5, 0);
                    gl.glCallList(BUS);
                }


                gl.glPopMatrix();                
                gl.glPopMatrix();
            //Konec autobusoveho parkoviste

            //LOUKA
                gl.glPushMatrix();
                gl.glTranslatef(-25, 55, 0);
                Objekt.travaCtverec(gl);
                gl.glTranslatef(0, -20, 0);
                Objekt.travaCtverec(gl);
                gl.glTranslatef(-20, 0, 0);
                Objekt.travaCtverec(gl);
                gl.glTranslatef(0, 20, 0);
                Objekt.travaCtverec(gl);                                
                gl.glPopMatrix();
            //KONEC LOUKY

            //AUTOBUSY ANIMACE - jizda po silnici
                gl.glPushMatrix();                                
                if(animace){                  
                    rychlost -= step2;
                    rychlost2 -= step2;
                }
                if(rychlost > -87){ // zatoceni autobusu
                    gl.glTranslated(80, 36, 0);                
                    gl.glRotatef(-90, 0, 0, 1);
                    gl.glTranslated(0, rychlost, 0);                
                }else if(rychlost <= -87 && rychlost > -150){
                    gl.glTranslated(-7, 40+80, 0);                
                    gl.glTranslated(0, rychlost, 0);                
                } else if(rychlost <= -150){ // vraceni autobusu na zacatek cesty
                    rychlost = 0;
                }                                    
                gl.glCallList(BUS);
                gl.glPopMatrix();

                gl.glPushMatrix();
                gl.glTranslated(36, -20, 0);
                gl.glRotatef(180, 0, 0, 1);
                if(rychlost2 < -100) rychlost2=0;
                gl.glTranslated(0, rychlost2, 0);                
                gl.glCallList(BUS);
                gl.glPopMatrix();
            //KONEC AUTOBUSU ANIMACE

            //Pøípadné vygenerování vìtšího mìsta    
                if(generovani){
                    vygenerujSidliste(4,5);
                    vygenerujMestoDomu(5,11);                
                }            

                double posunX = 30;
                double posunY = -2;
                double posunZ = -50; 
                    //System.out.println("//////////////////");
                    //System.out.println("px: " + (-pz+posunZ));
                    //System.out.println("py:" + (px-posunX));
                    //System.out.println("pz:" + (py-posunY));

            //STROMY a PLOTY
                vykresliPloty();
                vykresliStromy();



            if(sun) vytvorSlunceAOsvetleni();                                        
            gl.glPopMatrix();                                                 

            if(mlha || noc) gl.glPopMatrix();                                                
            if(palDeska) Objekt.vytvorPalubniDesku(gl, sun);                

            gl.glDisable(GL2.GL_LIGHTING);
            gl.glDisable(GL2.GL_LIGHT0);

            updateControl();
            oldmils = mils; 
            gl.glViewport(0, 0, width, height);

            //Vykreslovani textu na palubni desku, rychlost je násobena 4x (pro vizuální efekt) a výška 2x
            if(palDeska){
                gl.glColor3f(1f, 0f, 0f);
                if(padam && !kolizeStrechy){
                    DrawStr(-0.72f, -0.91f, 0f, "Rychlost: 15");
                } else {
                    DrawStr(-0.72f, -0.91f, 0f, "Rychlost: " + (int)motor*4);
                }

                DrawStr(-0.20f, -0.91f, 0f, "Vyska: " + (int)(py+pad+2)*2);
                if(padam && !kolizeStrechy) DrawStr(0.45f, -0.91f, 0f, "PADAME!");                                        
            }
	}
        
        
    /*
     * Puvodní metoda z ukázkových pøíkladù na JOGL
     * Author: PGRF_UHK
     */
    public void DrawStr(float x, float y, float z, String s) {
		gl.glDisable(GL2.GL_DEPTH_TEST);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glRasterPos2f(x, y);
		//glut.glutBitmapString(GLUT.BITMAP_8_BY_13, s);
                glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, s);
		gl.glPopMatrix();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glPopMatrix();
		gl.glEnable(GL2.GL_DEPTH_TEST);		
	}	

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            this.width = width;
            this.height = height;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
            }
            ox = e.getX();
            oy = e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
            }
    }
    
    //Øízení pohledu pomocí rozlížení myší.
    @Override
    public void mouseDragged(MouseEvent e) {
            dx = e.getX() - ox;
            dy = e.getY() - oy;
            ox = e.getX();
            oy = e.getY();

            zenit += dy;
            if (zenit > 90)
                    zenit = 90;
            if (zenit <= -90)
                    zenit = -90;
            azimut += dx;
            azimut = azimut % 360;
            a_rad = azimut * Math.PI / 180;
            z_rad = zenit * Math.PI / 180;
            ex = Math.sin(a_rad) * Math.cos(z_rad);
            ey = Math.sin(z_rad);
            ez = -Math.cos(a_rad) * Math.cos(z_rad);
            ux = Math.sin(a_rad) * Math.cos(z_rad + Math.PI / 2);
            uy = Math.sin(z_rad + Math.PI / 2);
            uz = -Math.cos(a_rad) * Math.cos(z_rad + Math.PI / 2);
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }        

    /*
     * Metoda pro zjištìní, zda klávesa (pøedána parametrem) je v seznamu aktuálnì stisknutých
     */
    private boolean checkKey(int ch){
        Iterator<Integer> itr = pressed.iterator();
            while(itr.hasNext()){
                int key = itr.next();
                if(key == ch) return true;
            }            
        return false;
    }
    
    /*
     * Speciální metoda pro øízení veškerého pohybu letadla volána pokaždé v metodì vykreslení display.
     * Java KeyListenery mají problém s detekcí kombinací kláves, proto jsem zvolil toto øešení
     */
    private void updateControl(){
        Iterator<Integer> itr = pressed.iterator();
            while(itr.hasNext()){
                int ch = itr.next();
                    
                //Øízení smìrovek letadla
                if (ch == KeyEvent.VK_A || ch == KeyEvent.VK_LEFT) { 
                        azimut-=2;
                }
                if (ch == KeyEvent.VK_D || ch == KeyEvent.VK_RIGHT) {   
                        azimut+=2;
                }   
                if (ch == KeyEvent.VK_UP) {
                        zenit-=1;                                
                } 
                if (ch == KeyEvent.VK_DOWN) {
                        zenit+=1;
                }        
            }
            
            
            azimut = azimut % 360;
            a_rad = azimut * Math.PI / 180;
            z_rad = zenit * Math.PI / 180;
            ex = Math.sin(a_rad) * Math.cos(z_rad);
            ey = Math.sin(z_rad);
            ez = -Math.cos(a_rad) * Math.cos(z_rad);
            ux = Math.sin(a_rad) * Math.cos(z_rad + Math.PI / 2);
            uy = Math.sin(z_rad + Math.PI / 2);
            uz = -Math.cos(a_rad) * Math.cos(z_rad + Math.PI / 2);

            if(padani && !zem && motor<=10){      // Kdyz pada letadlo, tak aby nepadalo kolmo k zemi.                                                     
                boolean testKolize = false;                    
                if(!kolizeStrechy){
                        for(int i = 0; i<listBox.size();i++)
                        {
                            if(collDetec.checkCollision(listBox.get(i), px,py,pz,pad)){
                                kolize = true;
                                motor = 0; 
                                testKolize = true;
                            } 
                        }
                     if(padam && !testKolize) {

                        for(int i = 0; i<listBox.size();i++)
                        {
                            if(collDetec.checkCollisionNextDown(listBox.get(i), px,py,pz,pad)){
                                pad+=0.1;
                                kolizeStrechy=true;
                                kolize = true;
                                motor = 0;                                                                                               
                            } 
                        }
                    }
                } else {
                    pad+=0.1;
                }
                if(!kolize && !kolizeStrechy && !testKolize){
                    oldPx = px;
                    oldPy = py;
                    oldPz = pz;
                }

                if(!kolize && !kolizeStrechy) {                        
                        float motorRychlost = 10 * (mils - oldmils) / 1000.0f; 
                        //System.out.println(motorRychlost);
                        px += ex * motorRychlost;
                        py += ey * motorRychlost;
                        pz += ez * motorRychlost;                                                                         
                } else {
                    motor = 0;
                    px = oldPx;
                    py = oldPy;
                    pz = oldPz;
                    kolize = false;
                }  

            //REZIM PADANI VYPNUT    
            } else if(motor>0 && !kolize){
                kolize = false;
                for(int i = 0; i<listBox.size();i++) // Procházíme celý seznam kolizních obálek a zjišujeme kolizi
                    {
                        if(collDetec.checkCollision(listBox.get(i), px,py,pz,pad)){ // Kontrola kolize pozice pozorovatele                            
                            kolize = true;
                            motor = 0; // naraz = nulova rychlost                                                                                   
                        } 
                    }     
                if(!kolize) { //Nedoslo ke kolizi
                    float motorRychlost = motor * (mils - oldmils) / 1000.0f; 
                    oldPx = px;
                    oldPy = py;
                    oldPz = pz;
                    px += ex * motorRychlost;
                    py += ey * motorRychlost;
                    pz += ez * motorRychlost;

                } else { 
                    // V pøípadì kolize se sníží rychlost na 0 (náraz) 
                    // a pozice se navrátí do pøedchozí nekolizní pozice.
                    motor = 0;
                    px = oldPx;
                    py = oldPy;
                    pz = oldPz;
                    kolize = false;
                }  
            }                 

            if(checkKey(KeyEvent.VK_W)){ //ZRYCHLOVANI DO 50 ... MAX = 50
                if(motor<50) motor+=0.4;                // zrychloání
                else if(motor>=50) motor=50;            // maximální rychlost 50
                if(kolizeStrechy) kolizeStrechy=false; // odlepeni ze strechy v rezimu pádu
                if(padam){
                    py+=pad;
                    pad = 0;
                }
            } else if (checkKey(KeyEvent.VK_S)) { // BRZDA ... MIN = 0
                if(motor>0) motor-=0.8;
                else if(motor<=0) motor=0;
            } else if (!checkKey(KeyEvent.VK_W) && !checkKey(KeyEvent.VK_S)){ // VOLNOBEH = dochazi ke zpomalovani
                if(motor>0) motor-=0.1;
                else if(motor<=0) motor=0;
            }  

    }
        
    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
        if(start) start = false;

    }

    /*
    * Key Listener používám pouze pro pøepínání rùzných režimù aplikace
    */
    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());

            switch (e.getKeyCode()) {
            case KeyEvent.VK_F:
                    if(padani) {
                        py+=pad;
                        pad = 0;
                    }
                    padani = !padani;

                    break;		
            case KeyEvent.VK_M:
                    noc = false;
                    mlha = !mlha;
                    if(mlha) {
                        gl.glDisable(GL2.GL_FOG);                            
                    }
                    break;	
            case KeyEvent.VK_N:
                    mlha = false;
                    noc = !noc;
                    if(noc) {
                        gl.glDisable(GL2.GL_FOG);                            
                    }
                    break;
            case KeyEvent.VK_B:
                    animace = !animace;
                    break;
            case KeyEvent.VK_L:
                    light = !light;
                    break;
            case KeyEvent.VK_K:
                    sun = !sun;
                    break;
            case KeyEvent.VK_P:
                    palDeska = !palDeska;
                    break;
            case KeyEvent.VK_G:
                    generovani = !generovani;
                    System.out.println(listBox.size());
                    break;		
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }        
       
    //Metoda pro vykreslení stromu na dané souøadnice
    private void strom(double x, double y, double z){
        gl.glPushMatrix();
        gl.glTranslated(x,y,z);                
        gl.glCallList(STROM);
        gl.glPopMatrix();
    }  

    //Metoda pro vykreslení stromu i s kvìtináèem na dané souøadnice
    private void stromAndKvetinac(double x, double y, double z){
        gl.glPushMatrix();
        gl.glTranslated(x,y,z);                
        gl.glCallList(KVETINAC);                
        gl.glTranslated(0,-2,0);                            
        gl.glCallList(STROM);
        gl.glPopMatrix();
    }        
    
    //Vykreslení všech stromù ve scénì
    private void vykresliStromy(){
        strom(45, -20, 0);

        // Podmínky pro správné poøadí vykreslení objektù z dùvodù blendingu
        if((-90 < azimut && azimut < 90) || (azimut>-360 && azimut<-270) || (azimut<360 && azimut>270)){                                                                                
            stromAndKvetinac(-42, 30, 0);
            stromAndKvetinac(-42, 20, 0);
            stromAndKvetinac(-42, 10, 0);
            stromAndKvetinac(-42, 0, 0);
            stromAndKvetinac(-42, -10, 0);
            stromAndKvetinac(-42, -20, 0);                    
            strom(5, -15, 0);
            strom(5, -20, 0);
        } else if((azimut>90 || azimut<-90)){
            stromAndKvetinac(-42, -20, 0);
            stromAndKvetinac(-42, -10, 0);
            stromAndKvetinac(-42, 0, 0);
            stromAndKvetinac(-42, 10, 0);
            stromAndKvetinac(-42, 20, 0);
            stromAndKvetinac(-42, 30, 0);
            strom(5, -20, 0);
            strom(5, -15, 0);                    
        }

        if((azimut>0 && azimut<180) || (azimut>-360 && azimut<-180)){
            stromAndKvetinac(-12, -20, 0);
            stromAndKvetinac(-22, -20, 0);
            stromAndKvetinac(-32, -20, 0);
            strom(65, 25, 0);
            strom(45, 25, 0);
        } else if(azimut<0 || azimut >-180) {                                        
            stromAndKvetinac(-32, -20, 0);
            stromAndKvetinac(-22, -20, 0);
            stromAndKvetinac(-12, -20, 0);
            strom(45, 25, 0);
            strom(65, 25, 0);

        }                
    }                                               
        
    private void vykresliPloty() {
        gl.glPushMatrix();
        gl.glDepthMask(false);
        gl.glTranslated(15, 37, 0);                 
        gl.glCallList(PLOT);
        gl.glTranslated(-18, 0, 0);
        gl.glCallList(PLOT);
        gl.glTranslated(-2, 0, 0);                
        gl.glCallList(PLOT);                
        gl.glRotatef(90, 0, 0, 1);                
        gl.glCallList(PLOT);
        gl.glTranslated(18, 0, 0);
        gl.glCallList(PLOT);
        gl.glTranslated(2, 0, 0);                
        gl.glCallList(PLOT);                                
        gl.glTranslated(18, 0, 0);                
        gl.glRotatef(-90, 0, 0, 1);                
        gl.glCallList(PLOT);
        gl.glTranslated(18, 0, 0);
        gl.glCallList(PLOT);
        gl.glTranslated(2, 0, 0);                
        gl.glCallList(PLOT);     

        gl.glTranslated(18, 0, 0);
        gl.glRotatef(-90, 0, 0, 1);                
        gl.glCallList(PLOT);
        gl.glTranslated(23, 0, 0);

                //POSLEDNI CAST PLOTU - kratsi z duvodu vjezdu do elektrarny
                gl.glDisable(GL2.GL_CULL_FACE);
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 21);
                gl.glColor3f(0.0f, 0.0f,0.0f);           
                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

                gl.glBegin(GL2.GL_QUADS);                
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(15.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(15.0f, 0.0f, 3.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 3.0f);
                gl.glEnd();                             
                gl.glDisable(GL2.GL_BLEND);
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glEnable(GL2.GL_CULL_FACE);
        gl.glDepthMask(true);
        gl.glPopMatrix();
    }                                                                            
    
    public boolean isMlha() {
        return mlha;
    }
    public void setMlha(boolean mlha) {
        this.mlha = mlha;
    }
    public boolean isAnimace() {
        return animace;
    }
    public void setAnimace(boolean animace) {
        this.animace = animace;
    }    

    private void vytvorSlunceAOsvetleni() {
    //Vytvoøení slunce    
        Objekt.vytvorSlunce(gl, glu, uhel);                                               
    //Nastaveni svetla
        gl.glPushMatrix();
            if(animace) {
                float VelikostrotaceSlunce = 5f;
                float rotaceSlunce = VelikostrotaceSlunce * (mils - oldmils) / 1000.0f; // krok za jedno
                uhel= (uhel-rotaceSlunce)%360;
            }
            gl.glRotated(uhel, 0, 0, 1); 
            float[] light_position=  new float[] {200,200,100,0.0f};//smer - umisteni v nekonecnu                        
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position,0);
            gl.glEnable(GL2.GL_LIGHTING);
            gl.glEnable(GL2.GL_LIGHT0);
            gl.glEnable(GL2.GL_COLOR_MATERIAL);
            gl.glShadeModel(GL2.GL_SMOOTH);
            if(!light){
                gl.glDisable(GL2.GL_LIGHTING);
                gl.glDisable(GL2.GL_LIGHT0);
                gl.glDisable(GL2.GL_COLOR_MATERIAL);
            }
            gl.glPopMatrix();                                                                                       
    }
       
    //Generovani panelovych domu. Metoda zaroven vytvari kolizni obalky a pridava je do listu.
    private void vygenerujSidliste(int radek, int sloupec){
        gl.glPushMatrix();
        gl.glTranslated(80, -20, 0);  
        double x= 79.5;
        double y= -20.5;
        
        for(int i = 0; i<radek;i++){                                
            gl.glPushMatrix();
            for(int j=0; j<sloupec;j++){
                gl.glCallList(PANELAK);
                if(!vygenerovano){
                    //System.out.println("x: "+ x);
                    //System.out.println("y: "+ y);
                    LetterBox dum = new LetterBox(y, x, 0, 21, 11, 16, PANELAK);
                    listBox.add(dum);                    
                }
                gl.glTranslated(20, 0, 0);
                x = x + 20;
            }              
            x = x - (sloupec*20);
            gl.glPopMatrix();
            gl.glTranslated(0,40, 0);
            y = y+40;
        }                         
        gl.glPopMatrix(); 
    }    
    
    //Generovani cinzovnich domu. Metoda zaroven vytvari kolizni obalky a pridava je do listu.
    private void vygenerujMestoDomu(int radek, int sloupec){
        gl.glPushMatrix();
        gl.glTranslated(-40, -40, 0);  
        double x= -40.5;
        double y= -40.5;
        for(int i = 0; i<radek;i++){                    
            gl.glPushMatrix();
            for(int j=0; j<sloupec;j++){
                gl.glCallList(DUM);                
                if(!vygenerovano){
                    //System.out.println("x: "+ x);
                    //System.out.println("y: "+ y);
                    LetterBox dum = new LetterBox(y, x, 0, 11, 11, 15, DUM);
                    listBox.add(dum);                    
                }
                gl.glTranslated(20, 0, 0);
                x = x + 20;
            }                    
            x = x - (sloupec*20);
            gl.glPopMatrix();
            gl.glTranslated(0,-20, 0);
            y = y-20;
        }           
        vygenerovano = true;
        gl.glPopMatrix(); 
    }       
}