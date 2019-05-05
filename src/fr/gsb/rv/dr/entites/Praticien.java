/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

/**
 *
 * @author root
 */
public class Praticien {
    private int numero;
    private String nom;
    private String ville;
    private double coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int dernierCoefConfiance;
    private String adresse;
    private String codePostal;
    private String prenom;

    public Praticien(int numero, String nom, String ville, double coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance, String adresse, String codePostal, String prenom) {
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.prenom = prenom;
    }
    
    
    public Praticien(int numero,String nom,String ville,double coefNotoriete,LocalDate dateDerniereVisite,int dernierCoefConfiance){
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
        
    }
    
    
    
    public Praticien(){
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getCoefNotoriete() {
        return coefNotoriete;
    }

    public void setCoefNotoriete(double coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    @Override
    public String toString() {
        return "Praticien{" + "numero=" + numero + ", nom=" + nom + ", ville=" + ville + ", coefNotoriete=" + coefNotoriete + ", dateDerniereVisite=" + dateDerniereVisite + ", dernierCoefConfiance=" + dernierCoefConfiance + '}';
    }

    public Praticien(int numero) {
        this.numero = numero;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
    
    
}
