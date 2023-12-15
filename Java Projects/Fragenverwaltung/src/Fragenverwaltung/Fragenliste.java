package Fragenverwaltung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
   * Standardkonstruktor legt eine Liste für Fragen- Daten
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
   * Methode entfernt Frage aus der Liste.
   *
   * @param frage Frage
   */
  public void remove(Frage frage)
  {
    daten.remove(frage);
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
   * Methode prüft, ob die Fragenliste leer ist.
   *
   * @return true, wenn Liste leer
   */
  public boolean isEmpty()
  {
    return daten.isEmpty();
  }

  /**
   * Methode liefert die Frage an der Index-Stelle.
   *
   * @param index int
   * @return Frage
   */
  public Frage getFrage(int index)
  {
    return daten.get(index);
  }

  /**
   * Methode fügt Frage an der Index-Stelle ein.
   *
   * @param index int
   * @param frage Frage
   */
  public void setFrage(int index, Frage frage)
  {
    daten.set(index, frage);
  }

  /**
   * Methode liefert den Index an welcher die Frage
   * gespeichert ist.
   *
   * @param frage Frage
   * @return Index
   */
  public int getFrageIndex(Frage frage)
  {
    return daten.indexOf(frage);
  }

//  /**
//   * Methode sortiert die Fragenliste.
//   */
//  public void sortieren()
//  {
//    Collections.sort(daten);
//  }

  /**
   * Methode speichert die Liste in einer CSV-Datei. Dabei
   * werden die einzelnen Datensätze in folgender Form
   * gespeichert:<br>
   * {@code Fragenummer;Frage;Auswahl1;Auswahl2;Auswahl3;Antwortnummer}<br>
   *
   * @param file Datei
   * @throws IOException Ausnahmesituation beim Speichern
   */
  public void speichernListeInDatei(File file)
          throws IOException
  {
    BufferedWriter ausgabeStream = new BufferedWriter(
            new FileWriter(file));
    for (Frage frage : daten)
    {
      String zeile
              = Integer.toString(frage.getFrageNummer())
              + ';' + frage.getFrage()
              + ';' + frage.getAuswahl1()
              + ';' + frage.getAuswahl2()
              + ';' + frage.getAuswahl3()
              + ';' + Integer.toString(frage.getAntwortNummer()) + '\n';
      ausgabeStream.write(zeile);
    }
    ausgabeStream.close();
  }

  /**
   * Methode liest die Daten der Datei in die 
   * Fragenliste ein.
   * @param file Datei
   * @throws IOException wenn ein Einlesefehler vorliegt.
   */
  public void einlesenListeAusDatei(File file)
                                      throws IOException
  {
    BufferedReader einleseStream = new BufferedReader(
            new FileReader(file));
    while (einleseStream.ready())
    {
      String zeile = einleseStream.readLine();
      String[] frageDaten = zeile.split(";");
      Frage frage = new Frage();
      try
      {
        frage.setFrageNummer(
                Integer.parseInt(frageDaten[0]));
        frage.setFrage(frageDaten[1]);
        frage.setAuswahl1(frageDaten[2]);
        frage.setAuswahl2(frageDaten[3]);
        frage.setAuswahl3(frageDaten[4]);
        frage.setAntwortNummer(Integer.parseInt(frageDaten[5]));
      }catch (NumberFormatException nfe )
      {
        throw new IOException
                ("Falsche Fragenummer beim Einlesen.");
      }
      catch (ArrayIndexOutOfBoundsException aioobe)
      {
        throw new IOException
                ("Falsche Datensatzstruktur beim Einlesen.");
      }
      daten.add(frage);
    }
    einleseStream.close();
  }
} // Ende der Klasse Fragenliste