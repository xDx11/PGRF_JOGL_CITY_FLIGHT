/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

/**
 *  Tøída LetterBox, neboli kolizní obálka sloužící k vytvoøení obálek okolo objektù ve scénì.
 *  Jedná se o nejjednodušší typ kolizních obálek, kdy strany kvádru jsou rovnobìžné s osamy souøadnic.
 * @author Radek Souèek
 */
public class LetterBox {
    private double x,y,z;
    private double sizeX, sizeY, sizeZ;
    private int name;
    
    
    /**
     *  Parametrický konstruktor vytvoøení 
     * @param positionX - poèáteèní pozice na ose X
     * @param positionY - poèáteèní pozice na ose Y
     * @param positionZ - poèáteèní pozice na ose Z
     * @param sizeX - velikost obálky na ose X
     * @param sizeY - velikost obálky na ose Y
     * @param sizeZ - velikost obálky na ose Z
     * @param name - identifikátor obálky, jaký obsahuje objekt
     */
    public LetterBox(double positionX, double positionY, double positionZ, double sizeX, double sizeY, double sizeZ, int name){
        this.x = positionX;
        this.y = positionY;
        this.z = positionZ;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.name = name;        
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getSizeX() {
        return sizeX;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
    }

    public double getSizeY() {
        return sizeY;
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
    }

    public double getSizeZ() {
        return sizeZ;
    }

    public void setSizeZ(double sizeZ) {
        this.sizeZ = sizeZ;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
