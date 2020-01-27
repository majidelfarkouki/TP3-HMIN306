package com.tp3.astparser;


public class CouplingNode
{
	public String classNameA;
	public Object classFigureA;
	
	public String classNameB;
	public Object classFigureB;
	
	public int counter;

	public CouplingNode(String classNameA, String classNameB, int counter)
	{
		this.classNameA = classNameA;
		this.classNameB = classNameB;
		this.counter = counter;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		
		CouplingNode couplingNode = (CouplingNode) o;
		
		return (classNameA.equals(couplingNode.classNameA) && classNameB.equals(couplingNode.classNameB)) ||
			(classNameA.equals(couplingNode.classNameB) && classNameB.equals(couplingNode.classNameA));
	}
	
	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		
		toString.append(classNameA);
		toString.append(System.getProperty("line.separator"));
		toString.append(classNameB);
		toString.append(System.getProperty("line.separator"));
		toString.append(counter);
		return toString.toString();
	}
}
