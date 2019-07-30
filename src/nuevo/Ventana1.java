/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nuevo;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.ClienteChat;

/**
 *
 * @author diogenes
 */
public class Ventana1 extends JFrame {

    private boolean recordar;
    private Usuario amigo;

    public Ventana1(Usuario amigo) {
        this.amigo =amigo;
        Login();

    }

    private void Login() {
        
        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        JCheckBox recor = new JCheckBox();
        Object[] message = {
            "Username:", username,
            "Password:", password,
            "Recuerdame", recor
        };
        if (recordar) {
            username.setText(amigo.getUser());
            password.setText(amigo.getPass());
        }
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if (username.getText().equals(amigo.getUser()) && password.getText().equals(amigo.getPass())) {
                recordar = recor.isSelected();
                Ventana();
                Objetos();
                setVisible(true);
                
                
            } else {
                System.out.println("login failed");
            }
        } else {
            System.out.println("Login canceled");
        }

    }

    private void Ventana() {
        this.setTitle("Chat bien chido de Dioge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setResizable(false);
        this.setLayout(null);
    }

    private void Objetos() {
        JTextArea chat = new JTextArea();
        chat.setText("");
        chat.setBounds(50, 10, 400, 300); //(x,y,largo,alto)
        chat.setEditable(false);
        add(chat);
        
        ClienteChat s1 = new ClienteChat("192.168.61.11","2000", chat);
        s1.conectar();
        
        JScrollPane vertical = new JScrollPane(chat);
        vertical.setBounds(50, 10, 400, 300);
        add(vertical);
        vertical.setVisible(true);

        JTextField text = new JTextField();
        text.setText("Ingrese su Texto");
        text.setBounds(50, 390, 400, 30);

        JCheckBox fin = new JCheckBox();
        fin.setText("agregar al final");
        fin.setBounds(20, 340, 400, 40);
        fin.setSelected(true);

        JButton Enviar = new JButton("Enviar");
        Enviar.setBounds(345, 430, 125, 40);
        Enviar.setMnemonic(KeyEvent.VK_S);
        Enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String env = text.getText();
                String nom = amigo.getUser();
                text.setText("");
                s1.EnviarMensaje(nom, env);
                /*
                if (fin.isSelected()) {
                    chat.append(nom + env);
                    chat.append(System.getProperty("line.separator"));
                } else {
                    String ch = chat.getText();
                    chat.setText("Cargando...");// hay q tener amor a laspc lentas <3
                    chat.setText("");
                    chat.append(nom + env);
                    chat.append(System.getProperty("line.separator"));
                    chat.append(ch);
                }*/
            }
        });

        JButton Borrar = new JButton("Borrar");
        Borrar.setBounds(187, 430, 125, 40);
        Borrar.setMnemonic(KeyEvent.VK_E);
        Borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Borrar();
                int optionpane = JOptionPane
                        .showConfirmDialog(rootPane, "Esta seguro?", "Cuidado", JOptionPane.YES_NO_CANCEL_OPTION);
                if (optionpane == JOptionPane.YES_OPTION) {
                    text.setText("");
                    chat.setText("");
                }
            }
        });

        JButton exit = new JButton();
        exit.setBounds(30, 430, 125, 40);
        exit.setText("Salir");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                Login();
            }

        });

        add(text);
        add(fin);
        add(Enviar);
        add(Borrar);
        add(exit);
        
    }
}
