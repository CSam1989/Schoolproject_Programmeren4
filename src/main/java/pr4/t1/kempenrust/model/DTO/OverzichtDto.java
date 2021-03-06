package pr4.t1.kempenrust.model.DTO;

import pr4.t1.kempenrust.model.BoekingDetail;
import pr4.t1.kempenrust.model.Kamer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OverzichtDto {
    private int dagenInMaand;
    private int maand;
    private int jaar;
    private LocalDate datum;
    private ArrayList<Kamer> kamers;
    private BoekingDetail[][] overzicht;

    public int getDagenInMaand() {
        return dagenInMaand;
    }

    public void setDagenInMaand(int dagenInMaand) {
        this.dagenInMaand = dagenInMaand;
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }

    public void setKamers(ArrayList<Kamer> kamers) {
        this.kamers = kamers;
    }

    public BoekingDetail[][] getOverzicht() {
        return overzicht;
    }

    public void setOverzicht(BoekingDetail[][] overzicht) {
        this.overzicht = overzicht;
    }
}
