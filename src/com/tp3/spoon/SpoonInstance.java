package com.tp3.spoon;

import java.util.Collection;
import java.util.List;

import com.tp3.astparser.*;
import spoon.Launcher;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.visitor.Query;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonInstance <T>
{
	public ClassTree classTree = new ClassTree();
	
	public CouplingAlgo couplingAlgo;
	
	public int classCounter = 0;
	public int methodCounter = 0;
	public int methodPerClassAverage;
	
	private Launcher launcher = new Launcher();
	
	public SpoonInstance(String sourcePath)
	{			
		launcher.getEnvironment().setNoClasspath(true);

		launcher.addInputResource(sourcePath);
		launcher.buildModel();
	}
	
	public void analyse()
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			analyse(ctType);
		
		couplingAlgo = new CouplingAlgo(classTree);
		 
		methodPerClassAverage = methodCounter/classCounter;
	}
	
	private void analyse(CtType<?> ctType)
	{
		Collection<CtMethod<?>> methods = ctType.getMethods();
		
		classTree.addClassDeclaration(ctType.getQualifiedName());
		
		classCounter++;
		
		for(CtMethod<?> methodDeclaration : methods)
		{
			methodCounter++;
			
			classTree.addMethodDeclaration(ctType.getQualifiedName(), methodDeclaration.getSimpleName());
			
			for(CtInvocation<?> methodInvocation : (List<CtInvocation>) Query.getElements(methodDeclaration, new TypeFilter<CtInvocation>(CtInvocation.class)))
			{
				if(methodInvocation.getTarget() != null && 
					methodInvocation.getTarget().getType() != null)
					if(isProjectClass(methodInvocation.getTarget().getType().toString()))
						classTree.addMethodInvocation(ctType.getQualifiedName(), methodDeclaration.getSimpleName(), methodInvocation.getTarget().getType().toString(), methodInvocation.getExecutable().getSimpleName());
			}
		}
	}
	
	public boolean isProjectClass(String className)
	{
		for(CtType<?> ctType : launcher.getFactory().Type().getAll())
			if(ctType.getQualifiedName().equals(className))
				return true;
		
		return false;
	}
}
