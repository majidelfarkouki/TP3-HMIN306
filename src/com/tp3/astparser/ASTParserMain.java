package com.tp3.astparser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class ASTParserMain
{
	//mettez ici le chemin source de l'application avec laquelle vous souhaitez exécuter cette application
	private final static String	PROJECT_SRC	= ".\\src";

	private final static int XMethods = 2;

	public static void main(String[] args) throws IOException
	{
		ASTParserInstance astParserInstance = new ASTParserInstance(PROJECT_SRC);

		astParserInstance.initialize();
		
		statisticalCalculation();
		statisticalCalculationJFrame();

		callGraph();
		couplingGraph();
		
	}
	
	
	public static void statisticalCalculation()
	{
		System.out.println("Nombre de classes de l'application : " + ASTVisitorAnalyzer.classCounter);
		
		System.out.println("Nombre de lignes de code de l'application : " + ASTVisitorAnalyzer.lineCounter);
		
		System.out.println("Nombre total de méthodes de l’application : " + ASTVisitorAnalyzer.methodCounter);
		
		System.out.println("Nombre total de	packages de l’application : " + ASTVisitorAnalyzer.packageCounter);
		
		System.out.println("Nombre moyen de	méthodes par classe : " + ASTVisitorAnalyzer.getMethodsAverage());
		
		System.out.println("Nombre moyen de lignes de code par méthode : " + ASTVisitorAnalyzer.getCodeLineMethodAverage());
		
		System.out.println("Nombre moyen d’attributs par classe : " + ASTVisitorAnalyzer.getAttributeAverage());
		
		System.out.println("Les 10% des classes qui possèdent le plus grand nombre de méthodes : "
			+ ASTVisitorAnalyzer.percentClassWithManyMethods.toString());
		
		System.out.println("Les 10% des classes qui possèdent le plus grand nombre d’attributs : "
			+ ASTVisitorAnalyzer.percentClassWithManyAttributes.toString());

		System.out.println("Les classes qui possèdent le plus grand nombre de méthodes et le plus grand nombre d’attributs : "
			+ ASTVisitorAnalyzer.classWithManyMethodsAndAttributes.toString());

		System.out.println("Les classes qui possèdent plus de " + XMethods + " méthodes : "
			+ ASTVisitorAnalyzer.classWithMoreThanXMethods.toString());
		
		System.out.println("Les 10% des méthodes qui possèdent le plus grandnombre de lignes de code(par classe) : "
			+ ASTVisitorAnalyzer.methodsWithLargestCode.toString());

		System.out.println("Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application : " + ASTVisitorAnalyzer.maximumMethodParameter);
	}
	
	
	public static void statisticalCalculationJFrame() {
		// create a new frame to store text field and button 
		JFrame f;
        f = new JFrame(); 
        f.setTitle("Statistical calculation for an Object Oriented Application");

  
        // create a label to display text 
  
        JLabel l1=new JLabel("Nombre de classes de l'application : " + ASTVisitorAnalyzer.classCounter);  
        
        JLabel l2=new JLabel("Nombre de lignes de code de l'application : " + ASTVisitorAnalyzer.lineCounter);  
                
        JLabel l3 = new JLabel("Nombre total de méthodes de l’application : " + ASTVisitorAnalyzer.methodCounter);
		
        JLabel l4 = new JLabel("Nombre total de	packages de l’application : " + ASTVisitorAnalyzer.packageCounter);
		
        JLabel l5 = new JLabel("Nombre moyen de	méthodes par classe : " + ASTVisitorAnalyzer.getMethodsAverage());
		
        JLabel l6 = new JLabel("Nombre moyen de lignes de code par méthode : " + ASTVisitorAnalyzer.getCodeLineMethodAverage());
		
        JLabel l7 = new JLabel("Nombre moyen d’attributs par classe : " + ASTVisitorAnalyzer.getAttributeAverage());
		
        JLabel l8 = new JLabel("Les 10% des classes qui possèdent le plus grand nombre de méthodes : "
			+ ASTVisitorAnalyzer.percentClassWithManyMethods.toString());
		
        JLabel l9 = new JLabel("Les 10% des classes qui possèdent le plus grand nombre d’attributs : "
			+ ASTVisitorAnalyzer.percentClassWithManyAttributes.toString());

        JLabel l10 = new JLabel("Les classes qui possèdent le plus grand nombre de méthodes et le plus grand nombre d’attributs : "
			+ ASTVisitorAnalyzer.classWithManyMethodsAndAttributes.toString());

        JLabel l11 = new JLabel("Les classes qui possèdent plus de " + XMethods + " méthodes : "
			+ ASTVisitorAnalyzer.classWithMoreThanXMethods.toString());
		
        JLabel l12 = new JLabel("Les 10% des méthodes qui possèdent le plus grandnombre de lignes de code(par classe) : "
			+ ASTVisitorAnalyzer.methodsWithLargestCode.toString());

        JLabel l13 = new JLabel("Le nombre maximal de paramètres par rapport à toutes les méthodes de l’application : " + ASTVisitorAnalyzer.maximumMethodParameter);
        
        // create a panel 
        JPanel p = new JPanel(); 
  
        // add label to panel 
        
        p.add(l1); p.add(l2);
        p.add(l13); p.add(l4);
        p.add(l5); p.add(l6);
        p.add(l7); p.add(l8);
        p.add(l9); p.add(l10);
        p.add(l11); p.add(l12);        
        p.add(l13);
  
        // add panel to frame 
        f.add(p); 
  
        // set the size of frame 
        f.setSize(900, 400); 
  
        f.show(); 
	}
	
	
	public static void callGraph()
	{
		CallGraph graph = new CallGraph("TP3-HMIN306", ASTVisitorAnalyzer.classTree);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(700, 600);
		graph.setVisible(true);
	}
	
	public static void couplingGraph()
	{
		CouplingGraph graph = new CouplingGraph("TP3-HMIN306", ASTVisitorAnalyzer.classTree, new CouplingAlgo(ASTVisitorAnalyzer.classTree));
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(800, 740);
		graph.setVisible(true);
	}
}