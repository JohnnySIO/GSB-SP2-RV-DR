package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select v.vis_matricule,v.vis_nom,v.vis_prenom, tra_role, jjmmaa\n" +
        "from Visiteur v inner join Travailler t\n" +
        "where v.vis_matricule = t.vis_matricule\n" +
        "and v.vis_matricule = '"+matricule+"'\n" +
        "and v.vis_mdp = '"+mdp+"'\n" +
        "and jjmmaa = (select max(jjmmaa) from Travailler where vis_matricule = '"+matricule+"')"
        + "and t.tra_role = 'Délégué';" ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
 
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom( resultat.getString("vis_prenom"));
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static List<Praticien> getPraticiensHesitants() throws ConnexionException{
        List<Praticien> liste = new ArrayList<Praticien>();
        
        
        String requete = "select *\n" +
                            "from RapportVisite rv,Praticien p\n" +
                            "where rv.pra_num = p.pra_num\n" +
                            "and rv.rap_coefConfiance < 5\n" +
                            "and rv.rap_date_visite = (\n" +
                            "select max(rv2.rap_date_visite)\n" +
                            "from RapportVisite rv2\n" +
                            "where rv.pra_num = rv2.pra_num\n" +
                            "and rv2.vis_matricule = rv.vis_matricule);";
        try {
            Connection connexion = ConnexionBD.getConnexion();
            Statement stmt = connexion.createStatement();
            ResultSet rst = stmt.executeQuery(requete);
             
            while(rst.next()) {
                Praticien pra = new Praticien();
                pra.setNumero(rst.getInt("pra_num"));
                pra.setNom(rst.getString("pra_nom"));
                pra.setVille(rst.getString("pra_ville"));
                pra.setCoefNotoriete(rst.getDouble("pra_coefNotoriete"));
                pra.setDateDerniereVisite(rst.getDate("rap_date_visite").toLocalDate());
                pra.setDernierCoefConfiance(rst.getInt("rap_coefConfiance"));
                
                liste.add(pra);
            }
            return liste;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeleGsbRv.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    
    public static List<Visiteur> getVisiteurs() throws ConnexionException{
        List<Visiteur> liste = new ArrayList<Visiteur>();
        
        
        String requete = "select vis_matricule,vis_nom,vis_prenom\n" +
                            "from Visiteur;";
        
        try {
            Connection connexion = ConnexionBD.getConnexion();
            Statement stmt = connexion.createStatement();
            ResultSet rst = stmt.executeQuery(requete);
             
            while(rst.next()) {
                Visiteur vis = new Visiteur();
                vis.setMatricule(rst.getString("vis_matricule"));
                vis.setNom(rst.getString("vis_nom"));
                vis.setPrenom(rst.getString("vis_prenom"));
                
                
                liste.add(vis);
            }
            return liste;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeleGsbRv.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public static List<RapportVisite> getRapportsVisite(String matricule,int mois,int annee) throws ConnexionException{
        List<RapportVisite> liste = new ArrayList<RapportVisite>();
        
        
        LocalDate date = LocalDate.of(annee, mois, 1);
        
        String dateReq = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        
        
        String requete = "select *\n" +
                            "from RapportVisite rv,Motif m,Praticien p \n" +
                            "where rv.mo_code = m.mo_code \n"+
                            "and p.pra_num = rv.pra_num \n" +
                            "and vis_matricule = '"+matricule+"'\n" +
                            "and rap_date_visite like '"+dateReq+"%' ;";
        
        System.out.println(requete);
        
        try {
            Connection connexion = ConnexionBD.getConnexion();
            Statement stmt = connexion.createStatement();
            ResultSet rst = stmt.executeQuery(requete);
             
            while(rst.next()) {
                
                RapportVisite rv = new RapportVisite();
                
                rv.setNumero(rst.getInt("rap_num"));
                rv.setDateVisite(rst.getDate("rap_date_visite").toLocalDate());
                rv.setDateRedaction(rst.getDate("rap_date_redaction").toLocalDate());
                rv.setBilan(rst.getString("rap_bilan"));
                rv.setMotif(rst.getString("mo_libelle"));
                rv.setCoefConfiance(rst.getInt("rap_coefConfiance"));
                rv.setLu(rst.getBoolean("rap_lu"));
                
                
                Visiteur leVis = new Visiteur();
                leVis.setMatricule(rst.getString("vis_matricule"));
                rv.setLeVisiteur(leVis);
                
                Praticien lePra = new Praticien();
                lePra.setNumero(rst.getInt("pra_num"));
                lePra.setNom(rst.getString("pra_nom"));
                lePra.setVille(rst.getString("pra_ville"));
                rv.setLePraticien(lePra);
                
                
                
                liste.add(rv);
            }
            return liste;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeleGsbRv.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void setRapportsVisiteLu(String matricule,int numRapport) throws ConnexionException{
      
         
        String requete = "update RapportVisite "
                + "set rap_lu = 1 where rap_num = ? "
                + "and vis_matricule = ?;";
        System.out.println(requete);
        
        try {
            Connection connexion = ConnexionBD.getConnexion();
            PreparedStatement pstmt = connexion.prepareStatement(requete);
            
            pstmt.setInt(1, numRapport);
            pstmt.setString(2, matricule);
            
            pstmt.executeUpdate();
            
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(ModeleGsbRv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

