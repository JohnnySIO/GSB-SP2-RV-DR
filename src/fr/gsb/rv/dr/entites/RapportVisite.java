/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.entites;

import java.time.LocalDate;


/**
 *
 * @author etudiant
 */
public class RapportVisite {

    private Visiteur leVisiteur;
    private Praticien lePraticien;
    
    private int numero;
    private LocalDate dateVisite;
    private LocalDate dateRedaction;
    private String bilan;
    private String motif;
    private int coefConfiance;
    private boolean lu;

    public RapportVisite(Visiteur leVisiteur, Praticien lePraticien, int numero, LocalDate dateVisite, LocalDate dateRedaction, String bilan, String motif, int coefConfiance, boolean lu) {
        this.leVisiteur = leVisiteur;
        this.lePraticien = lePraticien;
        this.numero = numero;
        this.dateVisite = dateVisite;
        this.dateRedaction = dateRedaction;
        this.bilan = bilan;
        this.motif = motif;
        this.coefConfiance = coefConfiance;
        this.lu = lu;
    }

    public RapportVisite() {
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }

    public Praticien getLePraticien() {
        return lePraticien;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getDateVisite() {
        return dateVisite;
    }

    public LocalDate getDateRedaction() {
        return dateRedaction;
    }

    public String getBilan() {
        return bilan;
    }

    public String getMotif() {
        return motif;
    }

    public int getCoefConfiance() {
        return coefConfiance;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLeVisiteur(Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }

    public void setLePraticien(Praticien lePraticien) {
        this.lePraticien = lePraticien;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDateVisite(LocalDate dateVisite) {
        this.dateVisite = dateVisite;
    }

    public void setDateRedaction(LocalDate dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void setCoefConfiance(int coefConfiance) {
        this.coefConfiance = coefConfiance;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    @Override
    public String toString() {
        return "RapportVisite{" + "leVisiteur=" + leVisiteur.getMatricule() + ", lePraticien=" + lePraticien.getNumero() + ", numero=" + numero + ", dateVisite=" + dateVisite + ", dateRedaction=" + dateRedaction + ", bilan=" + bilan + ", motif=" + motif + ", coefConfiance=" + coefConfiance + ", lu=" + lu + '}';
    }
    
    
    
    
}
