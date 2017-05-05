package Renderer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 * Tria se statistickymi metodami obsahujici vsechny Display listy aplikace.
 * Oddeleno od tridy renderer z duvodu prehlednosti a velikosti.
 * @author Radek Sou�ek
 */
public class DListy {            
    
    //Display list pro vytvoření činžovního domu s trojúhelníkovou střechou + podstavy
    public static void vytvorListDum(GL2 gl) {
            gl.glNewList(1, GL2.GL_COMPILE);                        
                //BUDOVA
                gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glPushMatrix();
		gl.glLoadIdentity();
		gl.glColor3f(0f, 0f, 0f);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
            
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
                gl.glTexParameterf( GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR );
                gl.glTexParameterf( GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR );                                                                
                gl.glTexParameteri( GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT );
                gl.glTexParameteri( GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT );
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 2);
                gl.glEnable(GL2.GL_LIGHTING);
                                                                
                gl.glColor3f(0.0f, 0.0f,0.0f);
		gl.glBegin(GL2.GL_QUADS);
                    //ZADNI
                    gl.glNormal3d(-1, 0, 0);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 10.0f);
                    gl.glTexCoord2f(0.0f, 1f);
                    gl.glVertex3f(0.0f, 10.0f, 10.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 10.0f, 0.0f);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);                    
                    
                    //LEVA
                    gl.glNormal3d(0, -1, 0);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(10.0f, 0.0f, 10.0f);
                    gl.glTexCoord2f(0.0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 10.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);                                        
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 0.0f);
                    //PREDNI   
                    gl.glNormal3d(1, 0, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(10.0f, 10.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(10.0f, 10.0f, 10.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 0.0f, 10.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 0.0f);
                    //PRAVA
                    gl.glNormal3d(0, 1, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 10.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 10.0f, 10.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 10.0f, 10.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 10.0f, 0.0f);                                   
                    //SPODNI
                    gl.glNormal3d(0, 0, -1);
                    gl.glVertex3f(0.0f, 10.0f, 0.0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glVertex3f(10.0f, 0.0f, 0.0f);
                    gl.glVertex3f(10.0f, 10.0f, 0.0f);                                 
                gl.glEnd();
                
                //Klasicka trujuhelnikova strecha, normaly byly rucne spocitany.
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 5);
                gl.glColor3f(0.0f, 0.0f,0.0f);
		gl.glBegin(GL2.GL_TRIANGLES);
                    //HORNI
                    gl.glNormal3d(30, 0, 5);
                    gl.glTexCoord2f(1f, 0.5f);
                    gl.glVertex3f(5.0f, 5.0f, 13.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 10.0f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(10.0f, 10.0f, 10.0f);                    
                    
                    gl.glNormal3d(0, 30, 50);
                    gl.glTexCoord2f(1f, 0.5f);
                    gl.glVertex3f(5.0f, 5.0f, 13.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 10.0f, 10.0f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 10.0f, 10.0f);                    
                    
                    gl.glNormal3d(-30, 0, 50);
                    gl.glTexCoord2f(1f, 0.5f);
                    gl.glVertex3f(5.0f, 5.0f, 13.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 10.0f, 10.0f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 10.0f);                    
                    
                    gl.glNormal3d(0, -30, 50);
                    gl.glTexCoord2f(1f, 0.5f);
                    gl.glVertex3f(5.0f, 5.0f, 13.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 10.0f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 10.0f);                    
                gl.glEnd();
                
                //PODSTAVA - okoli + trava
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 3);
                gl.glBegin(GL2.GL_QUADS);                    
                    gl.glNormal3d(0, 0, 1);
                    gl.glColor3f(0.0f, 0.0f,0.0f);
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
            gl.glEndList();
    }            
    
    //Display list pro vytvoreni paneloveho domu + podstavy
    public static void vytvorListPanelak(GL2 gl) {
            gl.glNewList(2, GL2.GL_COMPILE);                        
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 4);
                gl.glColor3f(0.0f, 0.0f,0.0f);
		gl.glBegin(GL2.GL_QUADS);                
                    //ZADNI strana
                    gl.glNormal3d(-1, 0, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 15.0f);
                    gl.glTexCoord2f(0.0f, 1f);
                    gl.glVertex3f(0.0f, 20.0f, 15.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 20.0f, 0.0f);                                        
                                        
                    //PREDNI strana
                    gl.glNormal3d(1, 0, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(10.0f, 20.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(10.0f, 20.0f, 15.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 0.0f, 15.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 0.0f);
                gl.glEnd();
                            
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 6);
                gl.glColor3f(0.0f, 0.0f,0.0f);
		gl.glBegin(GL2.GL_QUADS);
                
                    gl.glNormal3d(0, -1, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 0.0f, 15.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 15.0f);                                                            

                    gl.glNormal3d(0, 1, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 20.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 20.0f, 15.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 20.0f, 15.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 20.0f, 0.0f);

                    gl.glNormal3d(0, 0, 1);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 20.0f, 15.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 15.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(10.0f, 0.0f, 15.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(10.0f, 20.0f, 15.0f);                
                
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
                
                //PODSTAVA
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 12);
                gl.glBegin(GL2.GL_QUADS);
                    gl.glNormal3d(0, 0, 1);
                    gl.glColor3f(0.0f, 0.0f,0.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(-5.0f, 35.0f, 0.0f);	
                    gl.glTexCoord2f(1f, 0f);
                    
                    gl.glVertex3f(-5.0f, -5.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    
                    gl.glVertex3f(15.0f, -5.0f, 0.0f);
                    gl.glTexCoord2f(0f, 1f);
                    
                    gl.glVertex3f(15.0f, 35.0f, 0.0f);
                gl.glEnd();   
                gl.glDisable(GL2.GL_TEXTURE_2D);
            gl.glEndList();
    }    
    
    //Display list pro vytvoreni stromu, prace s blendingem a Z-testem
    public static void vytvorListStrom(GL2 gl) {
        gl.glNewList(3, GL2.GL_COMPILE); 
                //gl.glDisable(GL2.GL_CULL_FACE);
                gl.glDepthMask(false);
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 7);
                gl.glColor3f(0.0f, 0.0f,0.0f);
                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
                
		gl.glBegin(GL2.GL_QUADS);                
                    //ZADNI strana
                    gl.glNormal3d(1, 0, 0);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(0.0f, 4.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 4.0f, 8.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 8.0f);
                gl.glEnd();
                gl.glBegin(GL2.GL_QUADS);                
                    //ZADNI strana
                    gl.glNormal3d(-1, 0, 0);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 8.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 4.0f, 8.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(0.0f, 4.0f, 0.0f);                                        
                gl.glEnd();
                
                gl.glRotatef(90, 0, 0, 1);
                gl.glTranslated(2, -2, 0);                  
                gl.glBegin(GL2.GL_QUADS);  
                    gl.glNormal3d(0, 1, 0);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 8.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 4.0f, 8.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(0.0f, 4.0f, 0.0f);                    
                gl.glEnd();  
                gl.glBegin(GL2.GL_QUADS); 
                    gl.glNormal3d(0, -1, 0);
                    
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(0.0f, 4.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 4.0f, 8.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 8.0f);                    
                gl.glEnd(); 
                gl.glDisable(GL2.GL_BLEND);
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glDepthMask(true);
                //gl.glEnable(GL2.GL_CULL_FACE);
            gl.glEndList();
    }       
    
    //Display list pro vytvoreni autobusu - prace s texturovanim a blendingem
    //Nejedna se o zakladni kvadr(6polygonu), byly pouzity dva polygony navic z duvodu mirneho zkoseni
    //predni � strany autobusu
    public static void vytvorListBus(GL2 gl) {
            gl.glNewList(4, GL2.GL_COMPILE);                        
                //gl.glBindTexture(GL2.GL_TEXTURE_2D, texturePole[0]);
                gl.glEnable(GL2.GL_TEXTURE_2D);
//                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);                                
                            
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 9);
                gl.glColor3f(0.0f, 0.0f,0.0f);
		gl.glBegin(GL2.GL_QUADS);
                    //PREDEK
                    gl.glNormal3d(0, -1, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.25f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 0.0f, 0.25f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(2.0f, 0.2f, 2.70f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.2f, 2.70f);                    
                                                                                                                                
                gl.glEnd();  
                
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 10);
                
                gl.glBegin(GL2.GL_QUADS);
                    //TOP             
                    gl.glNormal3d(0, 0, 1);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 5.0f, 3.0f);                    
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 1.0f, 3.0f);                    
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 1.0f, 3.0f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(2.0f, 5.0f, 3.0f);                
                    
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 1.0f, 3.0f);                    
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.5f, 2.8f);                    
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 0.5f, 2.8f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(2.0f, 1.0f, 3.0f);      
                    
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.5f, 2.8f);                    
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.2f, 2.7f);                    
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 0.2f, 2.7f);                    
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(2.0f, 0.5f, 2.8f);
                    
                    
                
                    //PODLAHA
                    gl.glNormal3d(0, 0, -1);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 5.0f, 0.33f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 0.33f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(2.0f, 0.0f, 0.33f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 5.0f, 0.33f);   
                gl.glEnd();
                
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 31);
                gl.glColor3f(0.0f, 0.0f,0.0f);                                
                gl.glEnable(GL2.GL_BLEND);                
                gl.glBegin(GL2.GL_QUADS);                    
                    //PREDNI strana     
                    gl.glNormal3d(-1, 0, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1.0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 3.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 5.0f, 3.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 5.0f, 0.0f);
//                    gl.glTexCoord2f(0f, 0f);
//                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
//                    gl.glTexCoord2f(0.0f, 1f);
//                    gl.glVertex3f(0.0f, 0.0f, 3.0f);
//                    gl.glTexCoord2f(1f, 1f);
//                    gl.glVertex3f(0.0f, 5.0f, 3.0f);
//                    gl.glTexCoord2f(1f, 0f);
//                    gl.glVertex3f(0.0f, 5.0f, 0.0f);
                gl.glEnd();
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 8);
                gl.glBegin(GL2.GL_QUADS);                    
                    //ZADNI strana
                    gl.glNormal3d(1, 0, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(2.0f, 5.0f, 0.f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(2.0f, 5.0f, 3.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(2.0f, 0.0f, 3.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 0.0f, 0.f);
                    
                gl.glEnd();
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 24);
                gl.glColor3f(0.0f, 0.0f,0.0f); 
		gl.glBegin(GL2.GL_QUADS);
                    //ZADEK
                    gl.glNormal3d(0, 1, 0);
                    gl.glTexCoord2f(1f, 0f);
                    gl.glVertex3f(0.0f, 5.0f, 0.35f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(0.0f, 5.0f, 3.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(2.0f, 5.0f, 3.0f);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(2.0f, 5.0f, 0.35f);
                gl.glEnd();
                gl.glDisable(GL2.GL_BLEND);                
                gl.glDisable(GL2.GL_TEXTURE_2D);
                //gl.glDisable(GL2.GL_BLEND);
            gl.glEndList();
    }    
    
    //Display list pro vytvoreni oploceni okolo elektrarny - prace s blendingem
    public static void vytvorListPlot(GL2 gl){
            gl.glNewList(5, GL2.GL_COMPILE);                        
                //gl.glBindTexture(GL2.GL_TEXTURE_2D, texturePole[0]);
                gl.glDisable(GL2.GL_CULL_FACE);
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 21);
                gl.glColor3f(0.0f, 0.0f,0.0f);
                gl.glEnable(GL2.GL_BLEND);
                gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
                
		gl.glBegin(GL2.GL_QUADS);                
                    gl.glNormal3d(0,1,0);
                    gl.glTexCoord2f(0f, 0f);
                    gl.glVertex3f(0.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1.0f, 0f);
                    gl.glVertex3f(18.0f, 0.0f, 0.0f);
                    gl.glTexCoord2f(1f, 1f);
                    gl.glVertex3f(18.0f, 0.0f, 3.0f);
                    gl.glTexCoord2f(0f, 1f);
                    gl.glVertex3f(0.0f, 0.0f, 3.0f);
                gl.glEnd();                             
                gl.glDisable(GL2.GL_BLEND);
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glEnable(GL2.GL_CULL_FACE);
            gl.glEndList();
       }     
    
    //Display list pro vytvoreni komimu elektrarny pomoci kvadrik
    public static void vytvorListKomin(GL2 gl, GLU glu){
            gl.glNewList(6, GL2.GL_COMPILE);
                gl.glDisable(GL2.GL_CULL_FACE);
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glColor3f(0.0f, 0.0f,0.0f);
                GLUquadric quadratic;
                quadratic= glu.gluNewQuadric();
                glu.gluQuadricNormals(quadratic, GLU.GLU_SMOOTH);// Vygeneruje normalove vektory (hladke)
                glu.gluQuadricTexture(quadratic, true);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 13);
                //gl.glTranslatef(0.0f,0.0f,-1.5f);// Vycentrování válce
                gl.glTranslated(0, 0, 0.01);
                glu.gluDisk(quadratic,6.0f,0f,32,32);            
                glu.gluCylinder(quadratic,6.0f,3f,7.0f,32,32);// Válec
                gl.glTranslated(0, 0, 3);
                glu.gluCylinder(quadratic,3f,4.5f,7.0f,32,32);// Válec 
                gl.glDisable(GL2.GL_TEXTURE_2D);
                glu.gluDeleteQuadric(quadratic);
                gl.glEnable(GL2.GL_CULL_FACE);
            gl.glEndList();
    }         
    
    //Display list pro vytvoreni kvetinacu u stromu na parkovisti okolo stadionu
    public static void vytvorListKvetinac(GL2 gl, GLU glu){
            gl.glNewList(7, GL2.GL_COMPILE);
                gl.glEnable(GL2.GL_TEXTURE_2D);
                gl.glDisable(GL2.GL_CULL_FACE);
                gl.glColor3f(0.0f, 0.0f,0.0f);
                GLUquadric quadratic1 = glu.gluNewQuadric();
                GLUquadric quadratic2 = glu.gluNewQuadric();
                glu.gluQuadricNormals(quadratic1, GLU.GLU_SMOOTH);// Vygeneruje normalove vektory (hladke)
                glu.gluQuadricTexture(quadratic1, true);
                glu.gluQuadricOrientation(quadratic1, GLU.GLU_OUTSIDE);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 26);                
                glu.gluCylinder(quadratic1,1.0f,1.0f,0.5f,32,32);                
                glu.gluDeleteQuadric(quadratic1);
                gl.glTranslated(0, 0, 0.5);                
                glu.gluQuadricNormals(quadratic2, GLU.GLU_SMOOTH);// Vygeneruje normalove vektory (hladke)
                glu.gluQuadricTexture(quadratic2, true);
                glu.gluQuadricOrientation(quadratic2, GLU.GLU_OUTSIDE);
                gl.glBindTexture(GL2.GL_TEXTURE_2D, 14);                
                gl.glNormal3d(0, 0, 1);               
                glu.gluCylinder(quadratic1,1.0f,0.05f,0.1f,32,32);                                                            
                glu.gluDeleteQuadric(quadratic2);
                gl.glDisable(GL2.GL_TEXTURE_2D);
                gl.glEnable(GL2.GL_CULL_FACE);
            gl.glEndList();
    } 
}
