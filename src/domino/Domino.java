
package domino;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Domino {

       
    public static void main(String[] args) {
      Maquina m = new Maquina();
      
      String nombre;
      String nombre1;
        
        ArrayList <Ficha> ListF= new ArrayList<>(28);
 
        ArrayList <Ficha> List1= new ArrayList<>(14);
        ArrayList <Ficha> List2= new ArrayList<>(14); 
       
        m.generador(ListF);
        m.revolver(ListF);
        m.repartir(ListF,List1,List2);       
        
        
        System.out.println("...Ingrese nombre del jugador 1... \n");
        nombre = JOptionPane.showInputDialog(" INGRESE EL NOMBRE DEL JUGADOR 1 \n");
        while(nombre.isEmpty()){
            System.out.println("...Ingrese nombre del jugador 1... \n");
        nombre = JOptionPane.showInputDialog(" INGRESE EL NOMBRE DEL JUGADOR 1 \n");
        }
        
        
        System.out.println("...Ingrese nombre del jugador 2... \n");
        nombre1 = JOptionPane.showInputDialog(" INGRESE EL NOMBRE DEL JUGADOR 2 \n");
        while(nombre1.isEmpty()){
            System.out.println("...Ingrese nombre del jugador 2... \n");
        nombre1 = JOptionPane.showInputDialog(" INGRESE EL NOMBRE DEL JUGADOR 2 \n");
        }
        
        
        
        m.juego(nombre,nombre1,ListF,List1,List2);
        m.conteo(nombre, List1, nombre1, List2);
        
       
    }
    
   
    
}
