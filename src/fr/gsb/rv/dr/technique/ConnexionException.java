package fr.gsb.rv.dr.technique;


public class ConnexionException extends Exception {
    
    @Override
    public String getMessage(){
        return "Erreur de connexion BD" ;
    }
    
}
