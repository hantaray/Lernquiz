package Fragenverwaltung;

/**
 * Interface Zustand enthält Konstanten zur Steuerung der 
 * Benutzeroberfläche.
 * @author ahantke
 */
public interface Zustand
{
  // Konstanten zur Steuerung der verschiedenen 
  // Anzeigezustäne
  /**
   * Konstante für den Basis-Zustand der Benutzeroberfläche.
   */
  public int BASIS = 1;
  
  /**
   * Konstante für den Neu-Zustand der Benutzeroberfläche.
   */
  public int NEU = 2;
  
  /**
   * Konstante für den Ändern-Zustand der Benutzeroberfläche.
   */
  public int Aendern = 3;
  
  /**
   * Konstante für den Leer-Zustand der Benutzeroberfläche.
   */
  public int LEER = 4;
  
  /**
   * Konstante für die Navigations auf den ersten Datensatz.
   */
  public int FIRST = 5;
  
  /**
   * Konstante für die Navigations auf den vorherigen Datensatz.
   */
  public int BACK = 6;
  
  /**
   * Konstante für die Navigations auf den nächsten Datensatz.
   */
  public int NEXT = 7;
  
  /**
   * Konstante für die Navigations auf den letzten Datensatz.
   */
  public int LAST = 8;
}
