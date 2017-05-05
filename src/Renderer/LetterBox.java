/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

/**
 *  T��da LetterBox, neboli kolizn� ob�lka slou��c� k vytvo�en� ob�lek okolo objekt� ve sc�n�.
 *  Jedn� se o nejjednodu��� typ kolizn�ch ob�lek, kdy strany kv�dru jsou rovnob�n� s osamy sou�adnic.
 * @author Radek Sou�ek
 */
public class LetterBox {
    private double x,y,z;
    private double sizeX, sizeY, sizeZ;
    private int name;
    
    
    /**
     *  Parametrick� konstruktor vytvo�en� 
     * @param positionX - po��te�n� pozice na ose X
     * @param positionY - po��te�n� pozice na ose Y
     * @param positionZ - po��te�n� pozice na ose Z
     * @param sizeX - velikost ob�lky na ose X
     * @param sizeY - velikost ob�lky na ose Y
     * @param sizeZ - velikost ob�lky na ose Z
     * @param name - identifik�tor ob�lky, jak� obsahuje objekt
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
