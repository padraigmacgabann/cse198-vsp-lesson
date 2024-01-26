package cse198_lesson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;


public class SurgerySimulator extends JFrame {
	
	// have an ArrayList of fibula[0] to get from the bone
	private ArrayList<Fibula> fibulaList;
	private Fibula fibula;
    private Fibula fibula2;
    private int fibulaIndex = 0;
    
    private MesialMandible mesialMd;
    private DistalMandible distalMd;

    //private Implant implant;
    private final int STEP = 10;
    private final double ROTATION_STEP = Math.PI / 30;

    public SurgerySimulator() {
        setTitle("UCSD Computer Free Flap Surgery Simulator");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vspGui();
        fibula = new Fibula(100, 100, 250, 50); // Example dimensions
        fibula2 = new Fibula(50, 50, 150, 50);
        
        mesialMd = new MesialMandible();
        distalMd = new DistalMandible();
        
        fibulaList = new ArrayList<Fibula>();
        fibulaList.add(fibula);
        fibulaList.add(fibula2);   
        
        
    }

    private void vspGui() {
        JPanel controlPanel = new JPanel(new GridLayout(3, 2));
        String[] commands = {"Up", "Down", "Left", "Right", "Clockwise Rotate", "CounterClockwise Rotate"};
        
        // adding JPanel for fibula selector 
        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //time to add radio buttons for the fibula selector
        ButtonGroup fibulaSelectorGroup = new ButtonGroup();
        JRadioButton f1Button = new JRadioButton("Fibula 1");
        JRadioButton f2Button = new JRadioButton("Fibula 2");
        fibulaSelectorGroup.add(f1Button);
        fibulaSelectorGroup.add(f2Button);
        // add action listeners that change a private int passed to surgicalLogic()
        f1Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fibulaIndex = 0;
        	}
        });
        
        f2Button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fibulaIndex = 1;
        	}
        });
        
        selectorPanel.add(f1Button);
        selectorPanel.add(f2Button);
        
        for (String command : commands) {
            JButton button = new JButton(command);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	//
                	System.out.println(fibulaIndex);
                	//
                	surgicalLogic(e.getActionCommand(), fibulaIndex);
                    repaint();
                }
            });
            controlPanel.add(button);
        }
        add(controlPanel, BorderLayout.EAST);
       
        add(selectorPanel,BorderLayout.SOUTH);
       

    }

    private void surgicalLogic(String command) {
        switch (command) {
            case "Up":
                fibula.move(0, -STEP);
                break;
            case "Down":
                fibula.move(0, STEP);
                break;
            case "Left":
                fibula.move(-STEP, 0);
                break;
            case "Right":
                fibula.move(STEP, 0);
                break;
            case "Clockwise Rotate":
                fibula.rotate(-ROTATION_STEP);
                break;
            case "CounterClockwise Rotate":
                fibula.rotate(ROTATION_STEP);
                break;
        }
    }
    
    // i is the index in fibulaList
    private void surgicalLogic(String command, int i) {
        switch (command) {
            case "Up":
                fibulaList.get(i).move(0, -STEP);
                break;
            case "Down":
            	fibulaList.get(i).move(0, STEP);
                break;
            case "Left":
            	fibulaList.get(i).move(-STEP, 0);
                break;
            case "Right":
            	fibulaList.get(i).move(STEP, 0);
                break;
            case "Clockwise Rotate":
            	fibulaList.get(i).rotate(-ROTATION_STEP);
                break;
            case "CounterClockwise Rotate":
            	fibulaList.get(i).rotate(ROTATION_STEP);
                break;
        }
    }
    //

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //fibula.draw(g);
        //fibula2.draw(g);
        
        mesialMd.draw(g);
        distalMd.draw(g);
        
        for(Fibula f : fibulaList) {
        	f.draw(g);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SurgerySimulator().setVisible(true);
        });
    }

    class Fibula {
        private int x, y, width, height;
        private double angle = 0.0;

        public Fibula(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }

        public void rotate(double delta) {
            angle += delta;
        }

        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform old = g2d.getTransform();
            g2d.rotate(angle, x + width / 2.0, y + height / 2.0);
            g2d.drawRect(x, y, width, height);
            g2d.setTransform(old);
        }
    }
    
    class MesialMandible{
    	//need to draw a POLYGON with g2d
    	
    	public void draw(Graphics g) {
    		Graphics2D g2d = (Graphics2D) g;
    		
    		// origin + displacement
    		
    		int x1 = 50 + 50;
    		int x2 = 50 + 50;
    		int x3 = 50 + 25;
    		int x4 = 50 + 25;
    		int x5 = 50  + 100;
    		int x6 = 50 + 100;
    		int x7 = 50 + 85;
    		int x8 = 50 + 75;
    		
    		int y1 = 220 + 50;
    		int y2 = 220 + 100;
    		int y3 = 220 + 200 - 50;//
    		int y4 = 220 + 250 - 50;//
    		int y5 = 220 + 250 - 50;//
    		int y6 = 220 + 125;
    		int y7 = 220 + 100;
    		int y8 = 220 + 50;
    				
    		int[] xPoints = {x1, x2, x3, x4, x5, x6, x7, x8};		
    		int[] yPoints = {y1, y2, y3, y4, y5, y6, y7, y8};
    		
    		g2d.drawPolygon(xPoints, yPoints, 8);
    	}
    }
    
    class DistalMandible{
    	//need to draw a POLYGON with g2d
    	
    	public void draw(Graphics g) {
    		Graphics2D g2d = (Graphics2D) g;
    		
    		// origin + displacement
    		
    		int x1 = 0 + 475;
    		int x2 = 0 + 485;
    		int x3 = 0 + 510;
    		int x4 = 0 + 520;
    		int x5 = 0 + 530;
    		int x6 = 0 + 500;
    		int x7 = 0 + 475;
    		int x8 = 0 + 400;
    		int x9 = 0 + 400;
    		int x10 = 0 + 445;
    		
    		int y1 = 0 + 150;
    		int y2 = 0 + 110;
    		int y3 = 0 + 100;
    		int y4 = 0 + 110;
    		int y5 = 0 + 140;
    		int y6 = 0 + 220;
    		int y7 = 0 + 380;
    		int y8 = 0 + 400;
    		int y9 = 0 + 230;
    		int y10 = 0 + 210;
    				
    		int[] xPoints = {x1, x2, x3, x4, x5, x6, x7, x8, x9, x10};		
    		int[] yPoints = {y1, y2, y3, y4, y5, y6, y7, y8, y9, y10};
    		
    		g2d.drawPolygon(xPoints, yPoints, 10);
    	}
    }
}

//Lesson 1: import packages
// Lesson 2: initialize GUI objects
//Lesson 3: setting up the elements
// Lesson: Add fixed Coordinates for each button		
//Lesson: initialize buttons