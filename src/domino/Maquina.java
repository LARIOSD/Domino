
package domino;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Maquina {

    public Maquina() {    
        
    }
    
    public void generador( ArrayList <Ficha> ListF){
    
        for (int i=0; i<7 ; i++) {
            for (int j=i; j<7; j++) {
                Ficha aux = new Ficha(i,j);
                ListF.add(aux);
            }
        } 
    }
    
    public void revolver (ArrayList <Ficha> ListF){
        int r,r1;
        Ficha aux;
        Ficha aux1;
         
       for(int i=0; i<=30; i++ ){
           r = (int) (Math.random() * 27) ;
            aux=ListF.get(r); 
            ListF.remove(r);
            
           r1 = (int) (Math.random() * 27) ; 
           aux1=ListF.get(r1);
           ListF.remove(r1);
           
           ListF.add(r,aux1);
           ListF.add(r1,aux);      
       }
    }
    
 public void repartir(ArrayList <Ficha> ListF, ArrayList <Ficha> List1, ArrayList <Ficha> List2){ 

        for(int i = 0; i< 14;i++){
            List1.add(ListF.remove(0));
        }
     
       for(int i = 0 ; i < 14; i++){
            List2.add(ListF.remove(0)); 
        }
        
 }
 
 private void fichasJugadas(ArrayList <Ficha> ListF){
     System.out.println("...Fichas Jugadas... ");
 for (int j = 0; j <ListF.size(); j++) {
   System.out.print("["+ListF.get(j).izq+"|"+ListF.get(j).der+"] ");    
        }
 System.out.println(" ");
 }
 
private void mostrarFichas(String a, ArrayList<Ficha> aux){ 
    int b;
    System.out.println("Fichas Jugador "+a+" ");
    for (int i = 0; i <aux.size(); i++) {
        b=i+1;   
            System.out.print("("+b+")["+aux.get(i).izq+"|"+aux.get(i).der+"] ");
        }
    System.out.println(" ");
       
}

private  int ganarBloqueo(ArrayList<Ficha> ListA , ArrayList<Ficha> ListB){
    int a=0,b=0;
        for(int i=0; i<ListA.size();i++){
         a+= (ListA.get(i).izq + ListA.get(i).der);   
        }
        for(int j=0; j<ListB.size();j++){
         b+= (ListB.get(j).izq + ListB.get(j).der);   
        }
        if(a<b){
            return 1;
        }else if(a>b){
            return 2;
        }else return 3;  
        
}

private static boolean ganador(String jugador , ArrayList<Ficha> aux){
        if(aux.isEmpty()){
            System.out.println("\n            GAME OVER            ");
            System.out.println(".... EL ganador es "+jugador+"....");
            return true;
        }
        return false;
}

private void ganadorBloqueo(String jugador , ArrayList<Ficha> aux){
           
            System.out.println("\n            GAME OVER            ");
            System.out.println("     Despues del conteo de fichas   ");
            System.out.println("....   EL ganador es "+jugador+"  ....");  
            mostrarFichas(jugador,aux);
}

private void empateBloqueo(){
            System.out.println("\n                     GAME OVER            ");
            System.out.println("             Despues del conteo de fichas   ");
            System.out.println("....Ambos jugadores tienen la misma catidad de fichas....");  
            System.out.println("                      ¡  EMPATE  !                       ");
         
}

private  int jugar(String jugador,ArrayList<Ficha> ListA , ArrayList<Ficha> ListB){
    Scanner entrada = new Scanner (System.in);
    int s1=0;
    int n = 0,x,aux;
    boolean sig;
    
    System.out.println("\nEs el turno de "+jugador);
    fichasJugadas(ListB);
    mostrarFichas(jugador,ListA);
    
    for(int i=0; i<ListA.size();i++){
        if(ListA.get(i).izq == ListB.get(0).izq || ListA.get(i).der == ListB.get(0).izq || ListA.get(i).izq == ListB.get(ListB.size()-1).der || ListA.get(i).der == ListB.get(ListB.size()-1).der ){
            s1++;
        }
    }
            
    if(s1!=0){
        sig=fichaValida(jugador, ListA, ListB);
        while(sig==false){
            System.out.println(".......  ERROR  .......");
            System.out.println("Ingrese una ficha valida");
            sig=fichaValida(jugador, ListA, ListB);
        }
        return 0;
    }else 
        System.out.println("NO TIENE FICHAS PARA JUGAR");
    return 1;
}


 public void juego(String nombre,String nombre1,ArrayList <Ficha> ListF, ArrayList <Ficha> List1, ArrayList <Ficha> List2){
     int a=0;
     int una=0,dos=0;
     for(int i=0 ; i < List1.size() ; i++){
         if(List1.get(i).izq == 6  && List1.get(i).der == 6){
             ListF.add(List1.remove(i));
             fichasJugadas(ListF);
             mostrarFichas(nombre,List1);
         }
     }
         
     for(int j=0 ; j < List2.size() ; j++){
         if(List2.get(j).izq == 6  && List2.get(j).der == 6){
             ListF.add(List2.remove(j));
                    fichasJugadas(ListF);
                    mostrarFichas(nombre1,List2);                    
                    a++;               
         }
     }
  
    int i=0;
    
    while(true){  
       if(ganador(nombre1, List2))
           break;
       if(ganador(nombre, List1))
           break;
        
        switch(a){
            case 0:
                una=jugar(nombre1,List2,ListF);
                a++; 
            break;
          
          
            case 1: 
                dos=jugar(nombre,List1,ListF);
                a--;
            break;
        }
        i+=1;
        if(i==2){
            if(una==1 && dos==1){
                System.out.println(" ");
                System.out.println("....Se cerro el juego....");
                switch (ganarBloqueo(List1,List2)) {
                    case 1:
                        ganadorBloqueo(nombre,List1);
                        break;
                    case 2:
                        ganadorBloqueo(nombre1,List2);
                        break;
                    default:
                        empateBloqueo();
                        break;
                }
                
                break;
            }else{
                una=dos=0;
            }
            i=0;
        }
    } 
 }

    private boolean fichaValida(String jugador, ArrayList<Ficha> ListA, ArrayList<Ficha> ListB ) {
        Scanner entrada = new Scanner (System.in);
        int aux;
        int n,x;
        try{
        System.out.println("Ingrese la ficha que desea jugar: ");
        n = entrada.nextInt();
        n--;
        while(n<0 || n > ListA.size()){
            
            System.out.println(".......  ERROR  .......");
            System.out.println("Ingrese un numero ente 1 y "+ListA.size());
            System.out.println("Ingrese una ficha valida");
            n = entrada.nextInt();
            n--;
        }
        if(ListA.get(n).izq == ListB.get(0).izq && ListA.get(n).der == ListB.get(ListB.size()-1).der && ListA.get(n).der == ListB.get(0).izq && ListA.get(n).izq == ListB.get(ListB.size()-1).der){
               ListB.add(0,ListA.remove(n));
               fichasJugadas(ListB);
               mostrarFichas(jugador,ListA);
               return true;
               
            }else if (ListA.get(n).izq == ListB.get(0).izq && ListA.get(n).der == ListB.get(ListB.size()-1).der){
            System.out.println("Si desea jugar por la izquierda presione 1");
            System.out.println("Si desea jugar por la derecha presione 2");
            x = entrada.nextInt();
            if(x == 1){
                aux = ListA.get(n).izq;
                ListA.get(n).izq =ListA.get(n).der;
                ListA.get(n).der = aux;
                ListB.add(0,ListA.remove(n));                
                fichasJugadas(ListB);
                mostrarFichas(jugador,ListA);
                return true;
                
            }else if(x==2){
                aux = ListA.get(n).izq;
                ListA.get(n).izq =ListA.get(n).der;
                ListA.get(n).der = aux;
                ListB.add(ListA.remove(n));
                fichasJugadas(ListB);
                mostrarFichas(jugador,ListA);
                return true;
                
            }
        }else if( ListA.get(n).der == ListB.get(0).izq && ListA.get(n).izq == ListB.get(ListB.size()-1).der){
            System.out.println("Si desea jugar por la izquierda presione 1");
            System.out.println("Si desea jugar por la derecha presione 2");
            x = entrada.nextInt();
            
            if(x == 1){
               ListB.add(0,ListA.remove(n));
               fichasJugadas(ListB);
               mostrarFichas(jugador,ListA);
               return true;
            }
            if(x==2){
              ListB.add(ListA.remove(n));
              fichasJugadas(ListB);
              mostrarFichas(jugador,ListA);
              return true;
            }
        }else if(ListA.get(n).der == ListB.get(0).izq){
            ListB.add(0,ListA.remove(n));
            fichasJugadas(ListB);
            mostrarFichas(jugador,ListA);
            return true;
            
        }else if(ListA.get(n).izq == ListB.get(0).izq){
            aux = ListA.get(n).izq;
            ListA.get(n).izq =ListA.get(n).der;
            ListA.get(n).der = aux;
            ListB.add(0,ListA.remove(n));

            fichasJugadas(ListB);
            mostrarFichas(jugador,ListA);
            return true;
            
        }else if(ListA.get(n).izq == ListB.get(ListB.size()-1).der){
            ListB.add(ListA.remove(n));
            fichasJugadas(ListB);
            mostrarFichas(jugador,ListA);
            return true;
            
            }else if(ListA.get(n).der == ListB.get(ListB.size()-1).der){
                aux = ListA.get(n).izq;
                ListA.get(n).izq =ListA.get(n).der;
                ListA.get(n).der = aux;
                ListB.add(ListA.remove(n));
                fichasJugadas(ListB);
                mostrarFichas(jugador,ListA);
                return true;
            }else {
                System.out.println("No puede jugar esa ficha");
                return false;
            }
        return false;
        }catch(InputMismatchException e){
            System.out.println("Debes insertar un número");
                entrada.next();
        }
        return false;
     
    }


public void conteo(String jugadorA, ArrayList<Ficha> ListA , String jugadorB, ArrayList<Ficha> ListB){
    int a=0,b=0;
        for(int i=0; i<ListA.size();i++){
         a+= (ListA.get(i).izq + ListA.get(i).der);   
        }
        for(int j=0; j<ListB.size();j++){
         b+= (ListB.get(j).izq + ListB.get(j).der);   
        }
        System.out.println("Pintas de "+jugadorA+": "+a);
        System.out.println("Pintas de "+jugadorB+": "+b);
}

}