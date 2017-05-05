/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Tøída se statistickými metodami pro vykreslovaní objektù do scény.
 * Oddìleno od tøídy renderer z dùvodù pøehlednosti a velikosti.
 * @author Radek Soucek
 */
public class Objekt {
    
    //Vykreslení skyboxu pro denní režím - modrá obloha s mraky
    public static void skyBoxDen(GL2 gl) {                
        gl.glEnable(GL2.GL_TEXTURE_2D);        
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 1);

        gl.glBegin(GL2.GL_QUADS);                
            //LEVA
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(-1500, -1500, -1500);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3d(-1500, 1500, -1500);		
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-1500, 1500, 1500);                
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3d(-1500, -1500, 1500);                    
            //PRAVA	
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3d(1500, -1500, 1500); 
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(1500, 1500, 1500);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3d(1500, 1500, -1500);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3d(1500, -1500, -1500);	
            //SMER POZOROVATELE
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-1500, 1500, -1500);
            gl.glTexCoord2f(0.0f, 0.0f);		
            gl.glVertex3d(-1500, -1500, -1500);
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(1500, -1500, -1500);
            gl.glTexCoord2f(1.0f, 1.0f);		
            gl.glVertex3d(1500, 1500, -1500);
            //ZADA POZOROVATELE                                    
            gl.glTexCoord2f(1.0f, 1.0f);		
            gl.glVertex3d(1500, 1500, 1500);
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(1500, -1500, 1500);
            gl.glTexCoord2f(0.0f, 0.0f);		
            gl.glVertex3d(-1500, -1500, 1500);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-1500, 1500, 1500);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);

        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3f(0.824f, 0.933f, 1);
            //SPODNI            
            gl.glVertex3d(-1500, -1500, 1500);
            gl.glVertex3d(1500, -1500, 1500);  
            gl.glVertex3d(1500, -1500, -1500); 
            gl.glVertex3d(-1500, -1500, -1500);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3f(0.329f, 0.718f, 1);
            //HORNI            
            gl.glVertex3d(-1500, 1500, -1500);            
            gl.glVertex3d(1500, 1500, -1500);            
            gl.glVertex3d(1500, 1500, 1500);            
            gl.glVertex3d(-1500, 1500, 1500);
        gl.glEnd();                
    }
    
  //Vykreslení skyboxu pro noèní režím - tmavá obloha s hvìzdami
    public static void skyBoxNoc(GL2 gl) {                
        gl.glEnable(GL2.GL_TEXTURE_2D);        
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 23);

        gl.glBegin(GL2.GL_QUADS);                
            //LEVA
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(-15, -15, -15);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3d(-15, 15, -15);		
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-15, 15, 15);                
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3d(-15, -15, 15);                    
            //PRAVA	
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3d(15, -15, 15); 
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(15, 15, 15);
            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3d(15, 15, -15);
            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3d(15, -15, -15);	
            //SMER POZOROVATELE
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-15, 15, -15);
            gl.glTexCoord2f(0.0f, 0.0f);		
            gl.glVertex3d(-15, -15, -15);
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(15, -15, -15);
            gl.glTexCoord2f(1.0f, 1.0f);		
            gl.glVertex3d(15, 15, -15);
            //ZADA POZOROVATELE                                    
            gl.glTexCoord2f(1.0f, 1.0f);		
            gl.glVertex3d(15, 15, 15);
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(15, -15, 15);
            gl.glTexCoord2f(0.0f, 0.0f);		
            gl.glVertex3d(-15, -15, 15);
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-15, 15, 15);
            
            
            
            gl.glTexCoord2f(1.0f, 1.0f);		
            gl.glVertex3d(-15, 15, -15);            
            gl.glTexCoord2f(1.0f, 0.0f);		
            gl.glVertex3d(15, 15, -15); 
            gl.glTexCoord2f(0.0f, 0.0f);		
            gl.glVertex3d(15, 15, 15); 
            gl.glTexCoord2f(0.0f, 1.0f);
            gl.glVertex3d(-15, 15, 15);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);

                     
    }
    
    // Metoda pro vykreslení mrakodrapu. Jelikož každý mrakodrap má rozdílnou výšku,
    // není možné využít display list
    public static void vytvorMrakodrap(GL2 gl, float vyska) {                                   
        //BUDOVA
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 11);
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glBegin(GL2.GL_QUADS);
            //ZADNI  
            gl.glNormal3d(-1, 0, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, vyska);
            gl.glTexCoord2f(0.0f, 1f);
            gl.glVertex3f(0.0f, 10.0f, vyska);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(0.0f, 10.0f, 0.0f);
            //LEVA
            gl.glNormal3d(0, -1, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(10.0f, 0.0f, vyska);
            gl.glTexCoord2f(0.0f, 1f);
            gl.glVertex3f(0.0f, 0.0f, vyska);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            //PREDNI  
            gl.glNormal3d(1, 0, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(10.0f, 10.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(10.0f, 10.0f, vyska);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, vyska);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
            //PRAVA
            gl.glNormal3d(0, 1, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 10.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 10.0f, vyska);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 10.0f, vyska);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 10.0f, 0.0f);

            //SPODNI
            gl.glNormal3d(0, 0, -1);
            gl.glVertex3f(0.0f, 10.0f, 0.0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
            gl.glVertex3f(10.0f, 10.0f, 0.0f);                                 
        gl.glEnd();

        //STRECHA
        
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 27);
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glBegin(GL2.GL_QUADS);
            //HORNI
            gl.glNormal3d(0, 0, 1);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 10.0f, vyska);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, vyska);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, vyska);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 10.0f, vyska);                                                        
        gl.glEnd();


        //PODSTAVA - okoli + trava        
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 3);
        gl.glBegin(GL2.GL_QUADS);
            gl.glNormal3d(0, 0, 1);
            float color[] = { 0.0f, 0.0f, 0.0f };
            gl.glColor3fv(color, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(-5.0f, 15.0f, 0.0f);	
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(-5.0f, -5.0f, 0.0f);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(15.0f, -5.0f, 0.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(15.0f, 15.0f, 0.0f);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }    
    
    // Metoda pro vykresleni budovy elektrarny
    public static void Elektrarna(GL2 gl){
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 30);
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glBegin(GL2.GL_QUADS);
            //ZADNI strana
            gl.glNormal3d(-1, 0, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(0.0f, 1f);
            gl.glVertex3f(0.0f, 20.0f, 7.0f);
             gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(0.0f, 20.0f, 0.0f);
            //PREDNI strana
            gl.glNormal3d(1, 0, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(10.0f, 20.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(10.0f, 20.0f, 7.0f);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
        gl.glEnd();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 6);
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glBegin(GL2.GL_QUADS);
        
            gl.glNormal3d(0, -1, 0);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);                    
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);

            gl.glNormal3d(0, 1, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 20.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 20.0f, 7.0f);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 20.0f, 7.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 20.0f, 0.0f);

            gl.glNormal3d(0, 0, 1);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 20.0f, 7.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, 7.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 20.0f, 7.0f);                

            gl.glNormal3d(0, 0, -1);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(0.0f, 20.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(10.0f, 20.0f, 0.0f);                                 
        gl.glEnd();           
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }
    
    // Metoda pro vykresleni budovy stadionu za pouziti kvadrik v OpenGL
    public static void Stadion(GL2 gl, GLU glu){
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_TEXTURE_2D);      
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 19);
        gl.glColor3f(0.0f, 0.0f,0.0f);

        GLUquadric quadratic;
        quadratic= glu.gluNewQuadric();
        
        glu.gluQuadricNormals(quadratic, GLU.GLU_SMOOTH);// Vygeneruje normalove vektory (hladke)
        glu.gluQuadricTexture(quadratic, true);                        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        gl.glTranslated(0, 0, 0.001);
        glu.gluDisk(quadratic,15.0f,0f,32,32);            
        glu.gluCylinder(quadratic,15.0f,15.0f,7.0f,32,32);
        gl.glTranslated(0, 0, 7);
        glu.gluCylinder(quadratic,15.0f,4f,2f,32,32); 
        gl.glTranslated(0, 0, 2);
        gl.glRotated(180, 0, 1, 0);
        glu.gluDisk(quadratic,4.0f,0f,32,32);            
        gl.glRotatef(90, 1, 1, 1);
        gl.glDisable(GL2.GL_TEXTURE_2D);
        //gl.glRasterPos2f(10, 10);
        //glut.glutBitmapString(GLUT.BITMAP_8_BY_13, "O2 ARENA");
    }
    
    // Metoda pro vykresleni tabule/loga nad stadionem.
    public static void Logo(GL2 gl){
        gl.glDisable(GL2.GL_CULL_FACE);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, 22);
        gl.glColor3f(0.0f, 0.0f,0.0f);
        gl.glBegin(GL2.GL_QUADS);
            //ZADNI strana         
            gl.glNormal3d(0, -1, 0);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(10.0f, 0.0f, 0.0f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(10.0f, 0.0f, 4.0f);
            gl.glTexCoord2f(0.0f, 1f);
            gl.glVertex3f(0.0f, 0.0f, 4.0f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_CULL_FACE);
    }
    
    
    // Ruzne metody pro vykresleni vzoru zemÄ›.
    public static void travaCtverecTR(GL2 gl){
       gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 17);
            gl.glBegin(GL2.GL_QUADS);
                float color[] = { 0.0f, 0.0f, 0.0f };
                gl.glNormal3d(0, 0, 1);
                gl.glColor3fv(color, 0);
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);	
                gl.glTexCoord2f(1f, 0f);                    
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);                    
                gl.glVertex3f(20.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(0f, 1f);                    
                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
       gl.glDisable(GL2.GL_TEXTURE_2D);
    }       
    public static void travaCtverecLTR(GL2 gl){
       gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 16);
            gl.glBegin(GL2.GL_QUADS);
                float color[] = { 0.0f, 0.0f, 0.0f };
                gl.glNormal3d(0, 0, 1);
                gl.glColor3fv(color, 0);
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);	
                gl.glTexCoord2f(1f, 0f);

                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);

                gl.glVertex3f(20.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(0f, 1f);

                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
       gl.glDisable(GL2.GL_TEXTURE_2D);
    }       
    public static void travaCtverecR(GL2 gl){
       gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 18);
            gl.glBegin(GL2.GL_QUADS);
                gl.glNormal3d(0, 0, 1);
                float color[] = { 0.0f, 0.0f, 0.0f };
                gl.glColor3fv(color, 0);
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);	
                gl.glTexCoord2f(1f, 0f);                    
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);                    
                gl.glVertex3f(20.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(0f, 1f);                    
                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
       gl.glDisable(GL2.GL_TEXTURE_2D);
    }       
    public static void travaCtverec(GL2 gl){
       gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 14);
            gl.glBegin(GL2.GL_QUADS);                
                gl.glNormal3d(0, 0, 1);
                float color[] = { 0.0f, 0.0f, 0.0f };
                gl.glColor3fv(color, 0);
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);	
                gl.glTexCoord2f(1f, 0f);                    
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);                    
                gl.glVertex3f(20.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(0f, 1f);                    
                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
       gl.glDisable(GL2.GL_TEXTURE_2D);
    }       
    public static void parkCtverec(GL2 gl){
       gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 20);
            gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glBegin(GL2.GL_QUADS);               
                gl.glNormal3d(0, 0, 1);
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);	
                gl.glTexCoord2f(1f, 0f);                    
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);                    
                gl.glVertex3f(20.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(0f, 1f);                    
                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
       gl.glDisable(GL2.GL_TEXTURE_2D);
    }
    public static void parkCtverecGrey(GL2 gl){ 
        gl.glEnable(GL2.GL_TEXTURE_2D);
            gl.glBindTexture(GL2.GL_TEXTURE_2D, 25);
            gl.glColor3f(0.0f, 0.0f,0.0f);
            gl.glBegin(GL2.GL_QUADS);     
                gl.glNormal3d(0, 0, 1);                
                gl.glTexCoord2f(0f, 0f);
                gl.glVertex3f(0.0f, 20.0f, 0.0f);
                gl.glTexCoord2f(1f, 0f); 
                gl.glVertex3f(0.0f, 0.0f, 0.0f);
                gl.glTexCoord2f(1f, 1f);
                gl.glVertex3f(20.0f, 0.0f, 0.0f);  
                gl.glTexCoord2f(0f, 1f);
                gl.glVertex3f(20.0f, 20.0f, 0.0f);
            gl.glEnd();
        gl.glDisable(GL2.GL_TEXTURE_2D);
    }
    
    // Metoda pro vykresleni pilotniho kokpitu = palubni desky
    public static void vytvorPalubniDesku(GL2 gl, boolean sun) {
        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();
                
                //Spodni cast palubni desky, nutnost pouziti blendingu z duvodu tvaru
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 28);
                gl.glBegin(GL2.GL_QUADS);
                    if(sun){
                        gl.glNormal3d(0, 0, -1);
                    } else { 
                        //gl.glNormal3d(1, 0, 0);                        
                        gl.glNormal3d(0, 0, -1);
                    }
                    
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex2f(-1.0f, -0.5f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex2f(-1f, -1f);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex2f(1f, -1f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex2f(1f, -0.5f);
                gl.glEnd();
                gl.glDisable(GL2.GL_BLEND);

                // Horni a delici cast palubni desky
                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 29);
                gl.glBegin(GL2.GL_QUADS);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex2f(-1.0f, 1f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex2f(-1f, -0.5f);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex2f(1f, -0.5f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex2f(1f, 1f);
                gl.glEnd();                            
                gl.glDisable(GL2.GL_BLEND);
                gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }
    
    // Metoda pro vykresleni slunce pro znazorneni smeru rotujicihoho svetla
    public static void vytvorSlunce(GL2 gl, GLU glu, double uhel){
        //SLUNCE
            gl.glPushMatrix();  
                    gl.glDisable(GL2.GL_LIGHTING);
                    gl.glRotated(uhel, 0, 0, 1);  
                    gl.glTranslatef(240,240,100);
                    gl.glColor3f(1.0f, 1.0f, 0.0f);
                    GLUquadric quadratic1 = glu.gluNewQuadric();
                    glu.gluQuadricNormals(quadratic1, GLU.GLU_SMOOTH);
                    glu.gluSphere(quadratic1,20f,32,32);
                    gl.glEnable(GL2.GL_LIGHTING);
            gl.glPopMatrix(); 
    }
    
}

