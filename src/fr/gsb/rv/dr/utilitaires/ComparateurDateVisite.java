/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.utilitaires;

import fr.gsb.rv.dr.entites.Praticien;
import java.util.Comparator;

/**
 *
 * @author root
 */
public class ComparateurDateVisite implements Comparator<Praticien>{
    public int compare( Praticien o1, Praticien o2 ) {
        if(o1.getDateDerniereVisite().equals(o2.getDateDerniereVisite()) ){
            return 0;
        }
        else if(o1.getDateDerniereVisite().isAfter(o2.getDateDerniereVisite())){
            return 1;
        }
        else {
            return -1;
        }
    }
}
