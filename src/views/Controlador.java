package views;

import data.Persistencia;
import domain.*;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {
    public static TipoAlimentacion[] getTiposAlimentacion(){
        return  TipoAlimentacion.values();
    }
    public static ArrayList<Especie> getEspecies(){
        return Persistencia.getEspecies();
    }
    public static ArrayList<Sector> getSectores(){
        return Persistencia.getSectores();
    }
    public static ArrayList<Pais> getPaises(){
        return Persistencia.getPaises();
    }
    
    public static ArrayList<AnimalViewModel> getAnimales(){
        ArrayList<AnimalViewModel> animales = new ArrayList<>();
        for(Mamifero animal : Persistencia.getAnimales()){
            animales.add(new AnimalViewModel(animal));
        }
        return animales;
    }
    
    public static void agregarAnimal(Especie especie,int edad, double peso,double valorFijo, Sector sector, Pais pais) {
        
            try {
                if(especie.getTipoAlimentacion() == TipoAlimentacion.CARNIVORO){
                    Persistencia.agregarAnimal(new Carnivoro(edad, peso, especie, sector, pais));
                }else{
                    Persistencia.agregarAnimal(new Herbivoro(edad,peso,especie,sector,valorFijo,pais));
                }
                
            } catch (InvalidPropertiesFormatException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static ComidaViewModel  calcularComida(){
        double totalCarnivoros = Persistencia.getTotalComida(TipoAlimentacion.CARNIVORO);
        double totalHerbivoros = Persistencia.getTotalComida(TipoAlimentacion.HERBIVORO);
        return new ComidaViewModel(totalCarnivoros, totalHerbivoros);
    }
}
