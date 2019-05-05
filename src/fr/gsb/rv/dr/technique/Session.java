/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

/**
 *
 * @author etudiant
 */
public class Session {
   private static Session session = null;
   private Visiteur leVisiteur;
   
   private Session( Visiteur leVisiteur) {
        this.leVisiteur = leVisiteur;
    }
   
   public Session(){
       
   }

    
    
    public static void ouvrir(Visiteur leVisiteur) {
        session = new Session(leVisiteur);
    }

    public static void fermer(){
        session = null;
    }
    
    public static Session getSession() {
        return session;
    }
    
    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }
    

    public static boolean estOuverte() {
        if(session == null){
        return false;
        }
        else {
            return true;
        }
    }


    
   
}
