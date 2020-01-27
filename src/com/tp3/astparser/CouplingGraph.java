package com.tp3.astparser;

import java.util.Map;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class CouplingGraph extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int	ROOT_X	= 100;
	private static final int	ROOT_Y	= 10;
	private static final int DEFAULT_SPACE = 100;
	private static final int	CLASS_WIDTH	= 400;
	private static final int	CLASS_HEIGHT	= 100;

	

	private mxGraph	graph;
	private Object		parent;

	public CouplingGraph(String className, ClassTree treeStructure, CouplingAlgo couplingStructure)
	{
		super("Graphe de couplage : " + className);

		graph = new mxGraph();
		parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		int xCounter = 0;
		int yCounter = 0;

		for(Map.Entry<String, MethodTree> classEntry : treeStructure.classTree.entrySet())
		{

			Object classFigure = graph.insertVertex(parent, null, classEntry.getKey(), ROOT_X + xCounter,
				ROOT_Y + yCounter, CLASS_WIDTH, CLASS_HEIGHT, "fillColor=#C0C0C0");
		
			couplingStructure.addFigure(classEntry.getKey(), classFigure);
			
			xCounter += DEFAULT_SPACE;
			yCounter += DEFAULT_SPACE;
		}

		connect(couplingStructure);

		graph.setAllowDanglingEdges(false);
		graph.setEdgeLabelsMovable(true);
		graph.setConnectableEdges(false);

		graph.setCellsDeletable(false);
		graph.setCellsCloneable(false);
		graph.setCellsDisconnectable(false);
		graph.setDropEnabled(false);
		graph.setSplitEnabled(false);
		graph.setDisconnectOnMove(false);

		graph.setCellsBendable(true);

		graph.getModel().endUpdate();

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public void connect(CouplingAlgo couplingStructure)
	{
		for(CouplingNode couplingNode : couplingStructure.couplingNodes)
			graph.insertEdge(parent, null, couplingNode.counter + " / " + couplingStructure.totalReferences, couplingNode.classFigureA,
				couplingNode.classFigureB);

	}
}
