package lernquiz;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Klasse Quiz stellt die Verbindung her 
 * zwischen der Benutzeroberfläche und der Fragenliste.
 * Die Klasse enthält die main()-Methode.
 * @author hantke
 */
public class Quiz
{
  // Eigenschaften, Attribute, Variablen
  // Referenz auf Benutzeroberfläche
  private BenutzeroberflaecheQuiz gui; 
  // Referenz auf die Personenliste
  private Fragenliste liste;
  // Zeigervariable: Index der aktuellen Frage
  private int zeiger;
  // eine Zufallszahl für die Auswahl der Frage aus der Fragenliste
  private int zufallsZahl;
  // zählt, wie viele Fragen bereits gestellt wurden
  public int zaehler; 
  // die Anzahl der vom Spieler gewählten Fragen
  public int anzahlFragen;
  // das Ergebnis der richtig beantworteten Fragen
  public int ergebnis;
  // Ein Array mit den Zufallszahlen
  public int [] zufallszahlen;
  
  /**
   * Standardkonstruktor. Erstellt eine neue
   * BenutzeroberflächeQuiz und setzt dieses auf visible.
   * Erstellt eine neue Fragenliste.
   * Setzt den Zeiger auf 0.
   */
  public Quiz()
  {
    // Aufbau einer Kompositionsbeziehung
    gui = new BenutzeroberflaecheQuiz(this);
    liste = new Fragenliste();
  }
 
  /**
   * Start-Methode. Wird in der main()-Methode aufgerufen.
   * @throws Exception falls Daten nicht eingelesen werden können
   */
  
  public void start() throws Exception
  {
    // Fragen-Daten werden aus der .csv-Datei 
    // in den Arbeitsspeicher eingelesen
    File file = gui.getFile("Einlesen der Fragedaten");
    liste.einlesenListeAusDatei(file); 
    
    // Methode anzahlFragen() wird aufgerufen und der 
    // Variablen anzahlFragen zugewiesen
    anzahlFragen = anzahlFragen();

    // zaehler wird auf 1 gesetzt (ende des Quiz, 
    // wenn zaehler = anzahlFragen
    zaehler = 1;
    // ergebnis wird auf o gesetzt, da noch keine richtig
    // beantworteten Fraen
    ergebnis = 0;
    
    // Array mit anzahlFragen an zufallszahlen wird erstellt
    zufallszahlen = new int[anzahlFragen];
    for (int i = 0; i <= zufallszahlen.length-1; i++)
    {
      zufallszahlen[i] = zufallsZahl(1,liste.size());
    }
    
    // Die erste Frage wird angezeigt
    gui.setVisible(true);
    auswahl();
     
      
  }
  
  /**
   * Methode zeigt eine Frage, sowie die Auswahlmöglichkeiten
   * an der Position der ersten Zufallszahl an und 
   * zeiger wird um eins erhöht.
   */
  public void auswahl()
  {
    gui.anzeigeFrage(liste.getFrage(zufallszahlen[zeiger]));
    zeiger++;    
  }
  
  /** 
   * Methode gibt die Lösung (Antwortnummer) für die Frage aus.
   * 
   * @param frage die aktuelle Frage
   * @return loesung die Antwortnummer
   */
  public int loesung(Frage frage)
  {
    int loesung = frage.getAntwortNummer();
    return loesung;
  }
  
  /** 
   * Methode beendet die Anwendung.
   */
  public void beenden()
  {
    System.exit(0);
  }
  
  /**
   * Methode erfragt den Benutzer über einen Dialog 
   * nach der Anzahl der Fragen
   * @return anzahlfragen die Anzhal der Fragen als Integer
   */
  public int anzahlFragen()
  {
    String anzahlFragen = "";
   
      anzahlFragen = JOptionPane.showInputDialog(null, 
            "Bitte geben Sie die Anzahl der Fragen ein: ", "Anzahl der Fragen?", 
            JOptionPane.QUESTION_MESSAGE);
      if (anzahlFragen == "")
          beenden();
      return Integer.parseInt(anzahlFragen);
  }
  
  /**
   * Methode erstellt eine Zufallszahl zwischen einem min-Wert
   * und einem max-Wert
   * @param min der Mindestwert
   * @param max der Maximalwetrt
   * @return zufallsZahl die Zufallsahl
   */
  public int zufallsZahl(int min, int max)
  {
      zufallsZahl = (int)((max-min)*Math.random()+min);
      return zufallsZahl;
  }
  
  /**
   * Methoder fragt den Benutzer über einen Dialog, 
   * ob das Quiz wiederholt werden 
   * @return wahr wenn Anwendung wiederholt werden soll
   */
  public boolean quizWiederholen() 
  {
    gui.setVisible(false);
    int nochmal = JOptionPane.showConfirmDialog(null, 
            "Sie haben " + ergebnis 
                    + " Fragen richtig beantwortet!\n"
                    + "Wollen Sie das Quiz wiederholen?",
            "Quiz wiederholen?", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (nochmal == JOptionPane.NO_OPTION)
      System.exit(0);
    else
    {
      Quiz anwendung = new Quiz();
      try
      {
        anwendung.start();
      } catch (Exception ex)
      {
        new Exception("Anwendung kann nicht gestartet werden!");
      }
    } 
    return true;
  }
} // Ende Quiz
