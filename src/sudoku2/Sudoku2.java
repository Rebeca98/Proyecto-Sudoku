/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku2;

import java.util.HashMap;


/**
 *
 * @author AMARTINECI
 */
public class Sudoku2 {

    public static HashMap<String, SudoCuadro> dictMap = new HashMap<>();
    public static SudoCuadro[][] matrizMaestra = new SudoCuadro[9][9];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UI.main(args);
        sudokuSetup();
    }

    /**
     * Metodo encargado de toda la inicializaión de objetos y estruturas de soporte
     * Responsabilidades: Crear 81 objetos cuadro y asignarles respectivamente los conjuntos mark-up que les corresponden, crear matriz maestra de objetos cuadro
     */
    private static void sudokuSetup() {
        // 1. Creando objetos cuadro y asignandoles conjuntos mark-up de soporte
        //Creando arreglo de conjuntos mark-up de columnas, para después iterar sobre el mismo y asignar
        Object[] columnas = new Object[10];
        for (int col = 0; col < 9; col++) {
            columnas[col] = new ArraySet<>("columna " + col);
        }

        //Creando arreglo de conjuntos mark-up de filas, para después iterar sobre el mismo y asignar
        Object[] rows = new Object[10];
        for (int row = 0; row < 9; row++) {
            rows[row] = new ArraySet<>("row " + (char) row );
        }

        //Creando bigSquares manualmente. Se les asigna letras griegas de acuerdo a lo descrito en parte escrita
        SetADT<Integer> alpha = new ArraySet<>("alpha");
        SetADT<Integer> beta = new ArraySet<>("beta");
        SetADT<Integer> gamma = new ArraySet<>("gamma");
        SetADT<Integer> delta = new ArraySet<>("delta");
        SetADT<Integer> epsilon = new ArraySet<>("epsilon");
        SetADT<Integer> zeta = new ArraySet<>("zeta");
        SetADT<Integer> eta = new ArraySet<>("eta");
        SetADT<Integer> tetha = new ArraySet<>("tetha");
        SetADT<Integer> iota = new ArraySet<>("iota");

        //LLenando el hashmap principal para ligar objeto cuadro con objeto JTextField con llave <id> del textField
        //Se itera por columna y fila
        char first = 'A';
        String fullname;
        for (int letra = 0; letra < 9; letra++) {
            for (int num = 0; num < 9; num++) {
                fullname = String.valueOf(first) + String.valueOf(num+1);

                System.out.println(fullname);
                //Creando objeto y guardándolo en hashmap
                dictMap.put(fullname, new SudoCuadro((SetADT<Integer>) rows[letra], (SetADT<Integer>) columnas[num]));

                //Contenedor temporal
                SudoCuadro tempCuadro = dictMap.get(fullname);

                matrizMaestra[letra][num] = tempCuadro;

                //Checando que bigsquare le pertenece y asignandole su objeto
                if (letra <= 2) { //columna es A, B o C
                    if (num <= 2) { //fila 1,2 o 3
                        tempCuadro.assignBigSquare(alpha);
                    } else if (num <= 5) {//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(beta);
                    } else {//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(gamma);
                    }
                } else if (letra <= 5) { // columna es D, E o F
                    if (num <= 2) { //fila 1,2 o 3
                        tempCuadro.assignBigSquare(delta);
                    } else if (num <= 5) {//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(epsilon);
                    } else {//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(zeta);
                    }
                } else { //columna es G, H o I
                    if (num <= 2) { //fila 1,2 o 3
                        tempCuadro.assignBigSquare(eta);
                    } else if (num <= 5) {//fila es 4,5 o 6
                        tempCuadro.assignBigSquare(tetha);
                    } else {//Fila es 7,8 o 9
                        tempCuadro.assignBigSquare(iota);
                    }
                }
            }
            first++;
        }

    }



    
}
