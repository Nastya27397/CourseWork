package com.company.gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Table {

    private JFrame frame;

    public Table () {
        mainGUI();
        startMenu();

    }

    public void mainGUI () {
        frame = new JFrame("Manchkin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocation(300, 100);
    }

    public void startMenu () {
        JPanel startPanel = new JPanel();
        startPanel.setLayout(null);
        frame.add(startPanel);

        JLabel label_1 = new JLabel("Enter your Name:");
        startPanel.add(label_1);
        label_1.setBounds(324, 125, 101, 75);

        JButton startButton = new JButton("Start Game");
        startPanel.add(startButton);
        //startButton.setPreferredSize(new Dimension(300, 300));
        startButton.setBounds(325, 200, 100, 75);
        JTextField name = new JTextField();
        startPanel.add(name);
        name.setBounds(325, 170, 100, 20);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(name.getText().isEmpty()) {
                    label_1.setForeground(Color.red);
                } else {
                    String playerName = name.getText();

                    startPanel.removeAll();
                    startPanel.repaint();
                    startPanel.revalidate();

                    frame.remove(startPanel);
                    frame.revalidate();
                    frame.repaint();
                    startGame(playerName);
                }
                /*if(name.getText() != null) {
                    startPanel.removeAll();
                    frame.remove(startPanel);
                    startGame(name.getText());
                } else {
                    label_1.setForeground(Color.red);
                }*/
            }
        });
    }

    public void startGame(String playerName) {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(null);
        frame.add(gamePanel);

        JButton showCards = new JButton("Show Cards");
        gamePanel.add(showCards);
        showCards.setBounds(5, 480, 90, 65);

        JButton finishRound = new JButton("Finish Round");
        gamePanel.add(finishRound);
        finishRound.setBounds(690, 480, 90, 65);
    }
}