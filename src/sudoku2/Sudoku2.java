/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author AMARTINECI
 */
public class Sudoku2 {

    public static HashMap<String, SudoCuadro> dictMap = new HashMap<String, SudoCuadro>();
    public static SudoCuadro[][] matrizMaestra = new SudoCuadro[][]{};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UI.main(args); 
    }

    /**
     * Metodo encargado de toda la inicializaión de objetos y estruturas de soporte
     * Responsabilidades: Crear 81 objetos cuadro y asignarles respectivamente los conjuntos mark-up que les corresponden, crear matriz maestra de objetos cuadro
     */
    private void sudokuSetup(){
        // 1. Creando objetos cuadro y asignandoles conjuntos mark-up de soporte
        //Creando arreglo de conjuntos mark-up de columnas, para después iterar sobre el mismo y asignar
        ArraySet<Integer>[] columnas = (ArraySet<Integer>[]) new Object[10];
        for(int col=0; col<9; col++){
            columnas[col] = new ArraySet<>();
        }

        //Creando arreglo de conjuntos mark-up de filas, para después iterar sobre el mismo y asignar
        ArraySet<Integer>[] rows = (ArraySet<Integer>[]) new Object[10];
        for(int row=0; row<9; row++){
            rows[row] = new ArraySet<>();
        }

        //Creando bigSquares manualmente. Se les asigna letras griegas de acuerdo a lo descrito en parte escrita
        SetADT<Integer> alpha = new ArraySet<>();
        SetADT<Integer> beta = new ArraySet<>();
        SetADT<Integer> gamma = new ArraySet<>();
        SetADT<Integer> delta = new ArraySet<>();
        SetADT<Integer> epsilon = new ArraySet<>();
        SetADT<Integer> zeta = new ArraySet<>();
        SetADT<Integer> eta = new ArraySet<>();
        SetADT<Integer> tetha = new ArraySet<>();
        SetADT<Integer> iota = new ArraySet<>();

        //LLenando el hashmap principal para ligar objeto cuadro con objeto JTextField con llave <id> del textField
        //Se itera por columna y fila
        char first = 'A';
        String fullname;
        for (int letra=0; letra<26;letra++){
            for(int num=0; num<9; num++){
                fullname = String.valueOf(first) + String.valueOf(num);

                //Creando objeto y guardándolo en hashmap
                dictMap.put(fullname, new SudoCuadro(rows[letra],columnas[num]) );

                //Contenedor temporal
                SudoCuadro tempCuadro = dictMap.get(fullname);

                //Checando que bigsquare le pertenece y asignandole su objeto
                if(letra <= 2){ //columna es A, B o C
                    if(num <=2){ //fila 1,2 o 3
                        tempCuadro.assignBigSquare(alpha);
                    }else if(num <=5){//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(beta);
                    }else{//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(gamma);
                    }
                }else  if (letra <=5){ // columna es D, E o F
                    if(num <=2){ //fila 1,2 o 3
                        tempCuadro.assignBigSquare(delta);
                    }else if(num <=5){//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(epsilon);
                    }else{//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(zeta);
                    }
                } else{ //columna es G, H o I
                    if(num <=2){ //fila 1,2 o 3
                        tempCuadro.assignBigSquare(eta);
                    }else if(num <=5){//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(tetha);
                    }else{//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(iota);
                    }
                }
            }
            first++;
        }




    
}
