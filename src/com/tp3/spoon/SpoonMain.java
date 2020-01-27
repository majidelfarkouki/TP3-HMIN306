package com.tp3.spoon;

import java.io.IOException;

import javax.swing.JFrame;


import com.tp3.astparser.*;

public class SpoonMain
{
	//mettez ici le chemin source de l'application avec laquelle vous souhaitez exécuter cette application
	private final static String	PROJECT_SRC = ".\\src";
	
	public static void main(String[] Args) throws IOException
	{		
		SpoonInstance<Void> spoonInstance = new SpoonInstance<Void>(PROJECT_SRC);
		
		spoonInstance.analyse();
		
		callGraph(spoonInstance);
		
		couplingGraph(spoonInstance);
		
	}
	
	
	public static void callGraph(SpoonInstance spoonInstance)
	{
		CallGraph graph = new CallGraph("TP3-HMIN306", spoonInstance.classTree);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(800, 740);
		graph.setVisible(true);
	}
	
	public static void couplingGraph(SpoonInstance spoonInstance)
	{
		CouplingGraph graph = new CouplingGraph("TP3-HMIN306", spoonInstance.classTree, spoonInstance.couplingAlgo);
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graph.setSize(800, 740);
		graph.setVisible(true);
	}
	
	
}
