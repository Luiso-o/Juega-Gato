package ClaseJuego;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JuegoDelGato extends JFrame implements ActionListener {
	
	//clase ejecutable
	 public static void main(String[] args) {
	        new JuegoDelGato();
	    }

	//constantes JuegoDelGato
    private static final int NUM_CASILLAS = 3;
    private JButton[][] tablero = new JButton[NUM_CASILLAS][NUM_CASILLAS];
    private boolean turnoX = true;
    
    //constructor
    public JuegoDelGato() {
        super("Juego del Gato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crea el tablero
        JPanel panelTablero = new JPanel(new GridLayout(NUM_CASILLAS, NUM_CASILLAS));
        for (int i = 0; i < NUM_CASILLAS; i++) {
            for (int j = 0; j < NUM_CASILLAS; j++) {
                JButton boton = new JButton("");
                boton.setFont(new Font("Arial", Font.BOLD, 32));
                boton.addActionListener(this);
                panelTablero.add(boton);
                tablero[i][j] = boton;
            }
        }
        getContentPane().add(panelTablero, BorderLayout.CENTER);
        
        // Crea el botón de reiniciar
        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        
        getContentPane().add(botonReiniciar, BorderLayout.SOUTH);
        
        // Muestra la ventana
        setSize(300, 300);//tamaño
        setLocationRelativeTo(null);//por defecto el marco aparecera en el centro de la pantalla
        setVisible(true);//muestra el marco en pantalla
    }
  
    //prosesamos los datosintroducidos
    public void actionPerformed(ActionEvent e) {
        JButton casilla = (JButton)e.getSource();
        if (casilla.getText().isEmpty()) {
            if (turnoX) {
                casilla.setText("X");
                casilla.setForeground(Color.BLUE);
            } else {
                casilla.setText("O");
                casilla.setForeground(Color.RED);
            }
            if (tieneTresLinea()) {
                JOptionPane.showMessageDialog(this, "¡" + (turnoX ? "X" : "O") + " ha ganado!");
                reiniciarJuego();
            } else if (tableroLleno()) {
                JOptionPane.showMessageDialog(this, "¡Empate!");
                reiniciarJuego();
            } else {
                turnoX = !turnoX;
            }
        }
    }
    
    private boolean tieneTresLinea() {
        String marca = turnoX ? "X" : "O";
        // Verifica filas
        for (int i = 0; i < NUM_CASILLAS; i++) {
            if (tablero[i][0].getText().equals(marca) &&
                tablero[i][1].getText().equals(marca) &&
                tablero[i][2].getText().equals(marca)) {
                return true;
            }
        }
        // Verifica columnas
        for (int j = 0; j < NUM_CASILLAS; j++) {
            if (tablero[0][j].getText().equals(marca) &&
                tablero[1][j].getText().equals(marca) &&
                tablero[2][j].getText().equals(marca)) {
                return true;
            }
        }
        // Verifica diagonal principal
        if (tablero[0][0].getText().equals(marca) &&
            tablero[1][1].getText().equals(marca) &&
            tablero[2][2].getText().equals(marca)) {
            return true;
        }
        // Verifica diagonal secundaria
        if (tablero[0][2].getText().equals(marca) &&
                tablero[1][1].getText().equals(marca) &&
                tablero[2][0].getText().equals(marca)) {
                return true;
            }
            // Si no se encontró ninguna línea de tres, devuelve false
            return false;
        }
    
    private boolean tableroLleno() {
        // Verifica si todas las casillas tienen una marca
        for (int i = 0; i < NUM_CASILLAS; i++) {
            for (int j = 0; j < NUM_CASILLAS; j++) {
                if (tablero[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void reiniciarJuego() {
        // Reinicia el tablero
        for (int i = 0; i < NUM_CASILLAS; i++) {
            for (int j = 0; j < NUM_CASILLAS; j++) {
                tablero[i][j].setText("");
            }
        }
        // Reinicia el turno
        turnoX = true;
    }
    
   
}

