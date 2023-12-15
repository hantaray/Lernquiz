package Fragenverwaltung;

/**
 * Klasse Frage beschreibt eine Frage mit Fragenummer,
 * Frage, Auswahl1, Auswah2, Auswahl3 und Antwortnummer.
 * @author Alexander Hantke
 */
public class Frage 
{
  // Eigenschaften
  private int frageNummer;
  private String frage;
  private String auswahl1;
  private String auswahl2;
  private String auswahl3;
  private int antwortNummer;

  /**
   * Methode liefert die Fragenummer der Frage.
   * @return die Fragennummer
   */
  public int getFrageNummer()
  {
    return frageNummer;
  }

  /**
   * Methode setzt die Fragennummer der Frage.
   * @param frageNummer the frageNummer to set
   */
  public void setFrageNummer(int frageNummer)
  {
    this.frageNummer = frageNummer;
  }

  /**
   * Methode liefert die Frage der Frage.
   * @return Frage
   */
  public String getFrage()
  {
    return frage;
  }

  /**
   * Methode setzt die Frage der Frage.
   * @param frage zu setzende Frage
   */
  public void setFrage(String frage)
  {
    this.frage = frage;
  }

  /**
   * Methode liefert die Auswahl1 der Frage
   * @return Auswahl1
   */
  public String getAuswahl1()
  {
    return auswahl1;
  }
  
  /**
   * Methode setzt die Auswahl1 der Frage
   * @param auswahl1 zu setzende Auswahl1
   */
  public void setAuswahl1(String auswahl1)
  {
    this.auswahl1 = auswahl1;
  }

  /**
   * Methode liefert die Auswahl2 der Frage
   * @return Auswahl2
   */
  public String getAuswahl2()
  {
    return auswahl2;
  }
  
  /**
   * Methode setzt die Auswahl2 der Frage
   * @param auswahl2 zu setzende Auswahl2
   */
  public void setAuswahl2(String auswahl2)
  {
    this.auswahl2 = auswahl2;
  }

  /**
   * Methode liefert die Auswahl3 der Frage
   * @return Auswah3
   */
  public String getAuswahl3()
  {
    return auswahl3;
  }

  /**
   * Methode setzt die Auswahl3 der Frage
   * @param auswahl3 zu setzende Auswahl3
   */
  public void setAuswahl3(String auswahl3)
  {
    this.auswahl3 = auswahl3;
  }

  /**
   * Methode liefert die Antwortnummer der Frage
   * @return Antwortnummer
   */
  public int getAntwortNummer()
  {
    return antwortNummer;
  }

  /**
   * Methode setzt die Antwortnummer der Frage
   * @param antwortNummer zu setzende Antwortnummer
   */
  public void setAntwortNummer(int antwortNummer)
  {
    this.antwortNummer = antwortNummer;
  }

  /**
   * Standardkonstruktor erzeugt ein Fragen-Objekt. 
   * Wobei alle Eigenschaften mit null-Referenzen bzw 
   * 0-Werten versehen werden.
   */
  public Frage()
  {
    this.frageNummer = 0;
    this.frage = null;
    this.auswahl1 = null;
    this.auswahl2 = null;
    this.auswahl3 = null;
    this.antwortNummer = 0;
  }

  /**
   * Konstruktor erzeugt ein Fragen-Objekt mit den 
   * übergebenen Eigenschaften.
   * @param frageNummer die Fragenummer
   * @param frage  die Frage
   * @param auswahl1 die Auswahl1
   * @param auswahl2 die Auswahl2
   * @param auswahl3 die Auswahl3
   * @param antwortNummer  die Antwortnummer
   */
  public Frage(int frageNummer, String frage, 
                String auswahl1, String auswahl2, 
                String auswahl3, int antwortNummer)
  {
    this.frageNummer = frageNummer;
    this.frage = frage;
    this.auswahl1 = auswahl1;
    this.auswahl2 = auswahl2;
    this.auswahl3 = auswahl3;
    this.antwortNummer = antwortNummer;
  }

  /**
   * Methode erzeugt ein String mit allen Werten der Frage.
   * Der String gibt folgendes an:
   * {@code Fragenummer: Frage, Auswahl1, Auswahl2,
   * Auswahl3, Antwortnummer}
   * @return String 
   */
  @Override
  public String toString()
  {
    return "Frage "  + frageNummer + ": "
            + frage + ", auswahl1=" + auswahl1 + ", auswahl2=" 
            + auswahl2 + ", auswahl3=" + auswahl3 
            + ", antwortNummer=" + antwortNummer;
  }
} // Ende der Klasse Frage
