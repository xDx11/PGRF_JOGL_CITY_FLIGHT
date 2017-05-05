package Renderer;

import java.util.ArrayList;

/**
 * Trida pro praci s kolizemi ve scene.
 * @author Radek Soucek
 */
public class CollisionDetection {
    private static final int DUM = 1;
    private static final int PANELAK = 2;
    private static final int STROM= 3;
    private static final int BUS = 4;
    private static final int PLOT = 5;
    private static final int KOMIN = 6;
    private static final int KVETINAC = 7;
    private static final int MRAKODRAP = 8;
    
    private ArrayList<LetterBox> listBox;
    
    
    /**
     * Bezparametricky konstruktor, ve kterem dochazi k vytvoreni koliznich obalek objektu zakladni sceny,
     * ktere jsou nasledne pridany do ArrayListu listBox, obsahujici­ vsechny kolizni obalky
     */
    public CollisionDetection(){
        listBox = new ArrayList<>();
        LetterBox dumOne = new LetterBox(-0.5, -0.5, 0, 11, 11, 15, DUM);
        LetterBox dumTwo = new LetterBox(19.5, -0.5, 0, 11, 11, 15, DUM);
        LetterBox dumThird = new LetterBox(-0.5, 19.5, 0, 11, 11, 15, DUM);
        LetterBox dumFourth = new LetterBox(19.5, 19.5, 0, 11, 11, 15, DUM);

        LetterBox panelakFirst = new LetterBox(-0.5, 39.5, 0, 21, 11, 16, PANELAK);
        LetterBox panelakSecond = new LetterBox(-0.5, 59.5, 0, 21, 11, 16, PANELAK);
        LetterBox panelakThird = new LetterBox(-20.5, 9.5, 0, 11, 21, 16, PANELAK);
        LetterBox panelakFourth = new LetterBox(-20.5, 49.5, 0, 11, 21, 16, PANELAK);

        LetterBox MrakoOne = new LetterBox(39.5, 39.5, 0, 11, 11, 30.5, MRAKODRAP);
        LetterBox MrakoSecond = new LetterBox(59.5, 59.5, 0, 11, 11, 60.5, MRAKODRAP);
        LetterBox MrakoThird = new LetterBox(59.5, 39.5, 0, 11, 11, 40.5, MRAKODRAP);
        LetterBox MrakoFourth = new LetterBox(39.5, 59.5, 0, 11, 11, 50.5, MRAKODRAP);                        

        LetterBox Komin1 = new LetterBox(37, -4, 0, 13, 12, 12, KOMIN);                        
        LetterBox Komin2 = new LetterBox(50, -4, 0, 13, 12, 12, KOMIN);                        
        LetterBox Komin3 = new LetterBox(60, 9, 0, 13, 12, 12, KOMIN);                        
        LetterBox Komin4 = new LetterBox(60, 22, 0, 13, 12, 12, KOMIN); 

        LetterBox Elektrarna = new LetterBox(39.5, 9.5, 0, 11, 21, 7.5, 0);


        LetterBox Bus1 = new LetterBox(28.5, -36, 0, 5.5, 6.5, 3, BUS);
        LetterBox Bus2 = new LetterBox(28.5, -29, 0, 5.5, 6.5, 3, BUS);
        LetterBox Bus3 = new LetterBox(28.5, -22, 0, 5.5, 6.5, 3, BUS);
        LetterBox Bus4 = new LetterBox(28.5, -16, 0, 5.5, 6.5, 3, BUS);

        LetterBox Stadion = new LetterBox(-15.1, -40, 0, 31, 31, 9.5, 0);


        listBox.add(dumOne);
        listBox.add(dumTwo);
        listBox.add(dumThird);
        listBox.add(dumFourth);
        listBox.add(panelakFirst);
        listBox.add(panelakSecond);
        listBox.add(panelakThird);
        listBox.add(panelakFourth);
        listBox.add(MrakoOne);
        listBox.add(MrakoSecond);
        listBox.add(MrakoThird);
        listBox.add(MrakoFourth);
        listBox.add(Komin1);
        listBox.add(Komin2);
        listBox.add(Komin3);
        listBox.add(Komin4);
        listBox.add(Elektrarna);
        listBox.add(Bus1);
        listBox.add(Bus2);
        listBox.add(Bus3);
        listBox.add(Bus4);
        listBox.add(Stadion);
    }

    
    /**
     *  Hlavni kolizni metoda pro zjistovani, zda se pozorovatel nachazi v kolizi ci ne.
     *  Jedna se o jednoduche podminky, zda se pozice pozorovatele(px, py, pz) nachazi v danem letterboxu
     * @param a - dana kolizni obalka, pro kterou zjistujeme kolizi
     * @param px - pozice pozorovatele
     * @param py - pozice pozorovatele
     * @param pz - pozice pozorovatele
     * @param pad - promenna pripocitavani k vysce pozorovatele (rezim padani)
     * @return - vraci­ true/false zda ke kolizi doslo ci ne
     */
    public boolean checkCollision(LetterBox a, double px, double py, double pz, double pad){                   
    	 // Menší divoèina z dùvodu poèáteèního posunutí a orotování scény o 90stupnu (došlo trochu k prohození os souøadnic)
            double posunX = 30;
            double posunY = -2;
            double posunZ = -50;
           
           //Kontrola na ose X
           if(Math.abs((-pz+posunZ) - a.getX()) < a.getSizeX() && !((-pz+posunZ) <a.getX()))
           {
              //Kontrola na ose Y
              if(Math.abs((px-posunX) - a.getY()) < a.getSizeY() && !((px-posunX) <a.getY()))
              {
                  //Kontrola na ose Z
                  if(Math.abs((py-posunY+pad) - a.getZ()) < a.getSizeZ() && !((py - posunY + pad) <a.getZ()))
                  {
                     return true;
                  }
              }
           }
        return false;
    }
    
    /**
     *  Kolizní metoda pro zjišování, zda se následná pozice pod pozorovatelem nachází v kolizi.
     *  Tato metoda je použita pouze v režimu padání, kdy pozorovateli klesne rychlost a padá k zemi
     *  Jedná se o jednoduché podmínky, zda se pozice pozorovatele(px, py, pz) nachází v daném letterboxu
     * @param a - daná kolizní obálka, pro kterou zjišujeme kolizi
     * @param px - pozice pozorovatele
     * @param py - pozice pozorovatele
     * @param pz - pozice pozorovatele
     * @param pad - promìnná pøipoèítávánná k výšce pozorovatele (režim padání)
     * @return
     */
    public boolean checkCollisionNextDown(LetterBox a, double px, double py, double pz, double pad){                   
       double posunX = 30;
       double posunY = -2;
       double posunZ = -50;            
       //Kontrola na ose X
       if(Math.abs((-pz+posunZ) - a.getX()) < a.getSizeX() && !((-pz+posunZ) <a.getX()))
       {
          //Kontrola na ose Y
          if(Math.abs((px-posunX) - a.getY()) < a.getSizeY() && !((px-posunX) <a.getY()))
          {
              //Kontrola na ose Z
              if(Math.abs((py-posunY+pad-2) - a.getZ()) < a.getSizeZ() && !((py - posunY +pad -2) <a.getZ()))
              {
                 return true;
              }
          }
       }
       return false;
    } 
    
    public ArrayList<LetterBox> getListBox() {
        return listBox;
    }

    public void setListBox(ArrayList<LetterBox> listBox) {
        this.listBox = listBox;
    }
}
