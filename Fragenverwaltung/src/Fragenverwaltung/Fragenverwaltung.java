package Fragenverwaltung;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Klasse Fragenverwaltung stellt die Verbindung her 
 zwischen der Benutzeroberfläche und der Fragenliste.
 * Die Klasse enthält die main()-Methode.
 * @author hantke
 */
public class Fragenverwaltung
{
  // Eigenschaften, Attribute, Variablen
  // Referenz auf Benutzeroberfläche
  private BenutzeroberflaecheFragenverwaltung gui; 
  // Referenz auf die Fragenliste
  private Fragenliste liste;
  // Zeigervariable: Index des aktuellen Datensatzes
  private int zeiger;
  // Merker, ob Daten geändert wurden
  private boolean aenderungDaten;
  
  /**
   * Standardkonstruktor. Erstellt eine neue
   * BenutzeroberflächeFRagenverwaltung und setzt dieses auf visible.
   * Erstellt eine neue Fragenliste.
   * Setzt den Zeiger auf -1 und aenderungsdaten auf false
   */
  public Fragenverwaltung()
  {
    // Aufbau einer Kompositionsbeziehung
    gui = new BenutzeroberflaecheFragenverwaltung(this);
    gui.setVisible(true);
    liste = new Fragenliste();
    zeiger = -1; // Liste ist leer
    aenderungDaten = false; 
  }
  
   /**
   * Start-Methode. Wird in der main()-Methode aufgerufen.
   * @throws Exception falls Daten nicht eingelesen werden können
   */

  public void start()
  {
    // Daten aus Datei einlesen - Datei öffnen
    try{
      File file = gui.getFile("Einlesen der Fragedaten");
      if ( file != null )
      {
        liste.einlesenListeAusDatei(file);
        gui.anzeigenStatus(liste.size() + " Datensätze eingelesen!");
      }
    }
    catch (FileNotFoundException fnf)
    {
      gui.anzeigenStatus("Datei nicht gefunden");
    }
    catch (IOException ioe)
    {
      // hier Anzeige Status
      gui.anzeigenStatus(ioe.getMessage());
    }
    // Daten wurden eingelesen
    if ( liste.size() > 0 ) 
    {
      zeiger = 0;
      // Daten sortiert in Tabelle anzeigen
//      liste.sortieren();
      gui.anzeigenTabelle(liste);
      // Ersten Datensatz in Eingabefeldern anzeigen
      gui.anzeigenFrage(liste.getFrage(zeiger));
      // Basiszustand einstellen
      gui.einstellenZustand(Zustand.BASIS);
    }
    if ( zeiger == -1 ) // Keine Daten eingelesen
    {
        gui.dispose();
    }
  }
  
  /**
   * Methode zeigt den Datensatz entsprechend der
   * Benutzernavigation an.
   * @param richtung die Richtung
   */
  public void navigation(int richtung)
  {
    switch (richtung)
    {
      case Zustand.FIRST:
        zeiger = 0;
        break;
      case Zustand.BACK:
        if (zeiger > 0)
          zeiger--;
        break;
      case Zustand.NEXT:
        if (zeiger < liste.size()-1)
          zeiger++;
        break;
      case Zustand.LAST:
        zeiger = liste.size()-1;
    }
    gui.anzeigenFrage(liste.getFrage(zeiger));
        
  }
  
  /**
   * Methode beendet die Anwendung. Wenn Daten in der 
   * Fragenliste geändert wurden, werden diese gespeichert.
   */
  public void beenden()
  {
    if (aenderungDaten)
    {
      int antw = JOptionPane.showConfirmDialog(
              gui, "Sollen die Änderungen gespeichert werden?",
              "Änderung speichern?",
              JOptionPane.YES_NO_OPTION, 
              JOptionPane.QUESTION_MESSAGE);
      if ( antw == JOptionPane.YES_OPTION )
      {
        try
        {
        File file = gui.getFile("Daten speichern");
        if (file != null)
          liste.speichernListeInDatei(file);
        }catch(FileNotFoundException fne)
        {
          gui.anzeigenStatus("Datei nicht gefunden!");
        }catch (IOException ioe)
        {
          // Anzeige Status
          gui.anzeigenStatus((ioe.getMessage()));
        }
      }
    }
    System.exit(0);
  }
  
  /**
   * Methode bricht eine Eingabe von neuen Personendaten 
   * oder geänderten Daten ab.
   */
  public void abbrechen()
  {
    if ( zeiger >= 0 )
    {
      gui.anzeigenFrage(liste.getFrage(zeiger));
      gui.einstellenZustand(Zustand.BASIS);
    }
    else
    {
      gui.einstellenZustand(Zustand.LEER);
    }
  }
 
  /**
   * Methode übernimmt die Frage in die Daten-Liste.
   * @param frage Frage
   * @param zustand Zustand der Benutzeroberfläche
   */
  public void speichernInListe(Frage frage, int zustand)
  {
    if ( zustand == Zustand.NEU)
    {
      this.liste.add(frage);
      this.gui.anzeigenStatus(frage + " hinzugefügt");
    }
    if ( zustand == Zustand.Aendern )
    {
      this.liste.setFrage(zeiger, frage);
      this.gui.anzeigenStatus(frage + " geändert.");
    }
    this.aenderungDaten = true;
//    this.liste.sortieren();
    this.zeiger = this.liste.getFrageIndex(frage);
    this.gui.anzeigenTabelle(liste);
    this.gui.einstellenZustand(Zustand.BASIS);
  }
  
  /**
   * Methode ermöglicht das löschen der aktuell
   * ausgewählten Frage. Vor dem Löschenn erfolgt eine
   * Sicherheitsabfrage.
   */
  public void loeschen()
  {
    Frage frage = liste.getFrage(zeiger);
    int antw = JOptionPane.showConfirmDialog(gui, 
            "Wollen Sie die " + frage + " wirklich löchen?", 
            "Löschabfrage",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
    // Falls Nein oder Schließen-Kreuz geklickt
    if ( antw == JOptionPane.NO_OPTION 
            || antw == JOptionPane.CLOSED_OPTION )
    {
      return; // dann Methode beenden
    }
    // Frage aus Liste löschen
    liste.remove(frage);
    this.gui.anzeigenStatus(frage + " gelöscht!");
    this.aenderungDaten = true;
    // Falls Liste leer ist
    if ( liste.isEmpty())
    {
      this.gui.einstellenZustand(Zustand.LEER);
      gui.anzeigenTabelle(null);
      zeiger = -1;
      return;
    }
    // Falls der letzte Datensatz gelöscht wird
    if ( zeiger == liste.size())
    {
      zeiger = 0;
    }
    
    this.gui.anzeigenFrage(liste.getFrage(zeiger));
    this.gui.anzeigenTabelle(liste);
  }
} // Ende der Fragenverwaltung
