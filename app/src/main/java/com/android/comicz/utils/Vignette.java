package com.android.comicz.utils;

/**
 * Clase que representa una viñeta del comic
 */
public class Vignette {

  private int xI = 0;
  private int xF = 0;
  private int yI = 0;
  private int yF = 0;

  private int xI2 = -1;
  private int xF2 = -1;
  private int yI2 = -1;
  private int yF2 = -1;

  private int seq = -1;
  private int pag = -1;

  private Dimension resolution;

  private String nameRes = "";

  /**
   * Constructor para una viñeta definida por un solo rectángulo
   */
  public Vignette(int xI, int yI, int xF, int yF, int pag, int seq, Dimension res) {

    this.xI = xI;
    this.xF = xF;
    this.yI = yI;
    this.yF = yF;

    this.pag = pag;
    this.seq = seq;

    this.setResolution(res);
  }

  /**
   * Constructor para una viñeta definida por dos rectángulos
   */
  public Vignette(int xI, int yI, int xF, int yF, int xI2, int yI2, int xF2, int yF2, int pag,
      int seq, Dimension res) {

    this.xI = xI;
    this.xF = xF;
    this.yI = yI;
    this.yF = yF;

    this.xI2 = xI2;
    this.xF2 = xF2;
    this.yI2 = yI2;
    this.yF2 = yF2;

    this.pag = pag;
    this.seq = seq;

    this.setResolution(res);
  }


  /**
   * Devuelve true si el punto está dentro de la viñeta
   */
  public boolean insideVignnette(float x, float y, float ratio) {

    return (x >= (Math.round((float) xI * ratio)) &&
        x <= (Math.round((float) xF * ratio)) &&
        y >= (Math.round((float) yI * ratio)) &&
        y <= (Math.round((float) yF * ratio)))
        ||
        (x >= (Math.round((float) xI2 * ratio)) &&
            x <= (Math.round((float) xF2 * ratio)) &&
            y >= (Math.round((float) yI2 * ratio)) &&
            y <= (Math.round((float) yF2 * ratio)));
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public int getPag() {
    return pag;
  }

  public void setPag(int pag) {
    this.pag = pag;
  }

  public String getNameRes() {
    return nameRes;
  }

  public void setNameRes(String nameRes) {
    this.nameRes = nameRes;
  }

  public Dimension getResolution() {
    return resolution;
  }

  public void setResolution(Dimension resolution) {
    this.resolution = resolution;
  }

  public int getxI() {
    return xI;
  }

  public int getxF() {
    return xF;
  }

  public int getyI() {
    return yI;
  }

  public int getyF() {
    return yF;
  }

  public int getxI2() {
    return xI2;
  }

  public int getxF2() {
    return xF2;
  }

  public int getyI2() {
    return yI2;
  }

  public int getyF2() {
    return yF2;
  }


}
