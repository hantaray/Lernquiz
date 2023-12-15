package lernquiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse beschreibt eine Fragenliste.
 *
 * @author Alexander Hantke
 */
public class Fragenliste 
{
  private List<Frage> daten;  // Eigenschaft

  /**
   * Standardkonstruktor legt eine Liste für Frage- Daten
   * an. Die Liste basiert auf der Klasse
   * java.util.ArrayList&lt;E&gt;.
   */
  public Fragenliste()
  {
    daten = new ArrayList<>();
  }

  /**
   * Methode fügt die Frage der Liste hinzu.
   *
   * @param frage Frage
   */
  public void add(Frage frage)
  {
    daten.add(frage);
  }

  /**
   * Methode liefert die Frage an der Index-Stelle.
   * @param index int
   * @return Frage
   */
  public Frage getFrage(int index)
  {
    return daten.get(index);
  }
  
  /**
   * Methode liefert die Antwortnummer an der Index-Stelle.
   * @param index int
   * @return Antwortnummer
   */
  public Frage getAntwortNummer(int index)
  {
    return daten.get(index);
  }
 
  /**
   * Methode ermittelt die Anzahl der Fragen in der Liste.
   *
   * @return Anzahl der Fragen
   */
  public int size()
  {
    return daten.size();
  }
  
   /**
   * Methode liest die Daten der Datei in die 
   * Fragenliste ein.
   * @param file Datei
   * @throws IOException wenn ein Einlesefehler vorliegt.
   */
  public void einlesenListeAusDatei(File file)
                                      throws Exception
  {
    BufferedReader einleseStream = new BufferedReader(
            new FileReader(file));
    
    while (einleseStream.ready())
    {
      String zeile = einleseStream.readLine();
      String[] frageDaten = zeile.split(";");
      Frage frage = new Frage();
    
      frage.setFrageNummer(
              Integer.parseInt(frageDaten[0]));
      frage.setFrage(frageDaten[1]);
      frage.setAuswahl1(frageDaten[2]);
      frage.setAuswahl2(frageDaten[3]);
      frage.setAuswahl3(frageDaten[4]);
      frage.setAntwortNummer(
              Integer.parseInt(frageDaten[5]));
 
      daten.add(frage);
    }
    einleseStream.close();
  }
} // Ende der Klasse Fragenliste
