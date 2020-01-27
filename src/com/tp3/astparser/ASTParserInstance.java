package com.tp3.astparser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class ASTParserInstance
{

	private ASTParser astParser;
	private Collection<String> sourceFiles = new ArrayList<String>();
	private String project_src;
	private ASTVisitorAnalyzer astVisitorAnalyzer;


	public ASTParserInstance(String project_src)
			throws IOException
	{
		this.project_src = project_src;

		astParser = ASTParser.newParser(AST.JLS4);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);

	}

	public void initialize() throws IOException
	{
		this.exploreProject(project_src);
		parseProject();

		ASTVisitorAnalyzer.percentOfClassWithManyMethods();
		ASTVisitorAnalyzer.percentOfClassWithManyAttributs();
		ASTVisitorAnalyzer.percentOfMethodsWithLargestCode();
		ASTVisitorAnalyzer.mergeBetweenClassWithManyAttributesAndMethods();
	}

	public void exploreProject(String directory) throws IOException
	{
		File root = new File(directory);

		for (File file : root.listFiles())
			if (file.isDirectory())
				exploreProject(file.getAbsolutePath());
			else
				sourceFiles.add(file.getAbsolutePath());
	}

	public void parseProject() throws IOException
	{
		for (String sourceFile : sourceFiles)
			parseFile(sourceFile, new ClassVisitor());
		
		for (String sourceFile : sourceFiles)
			parseFile(sourceFile, new ASTVisitorAnalyzer());
	}

	private void parseFile(String sourceFile, ASTVisitor astVisitor) throws IOException
	{

		astParser = ASTParser.newParser(AST.JLS4);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);

		astParser.setBindingsRecovery(true);


		astParser.setSource(ParsingHelper.fileToString(sourceFile).toCharArray());

		CompilationUnit compilationUnit = (CompilationUnit) astParser.createAST(null);

		compilationUnit.accept(astVisitor);
	}


	public ASTVisitorAnalyzer getAstVisitorAnalyzer() {
		return astVisitorAnalyzer;
	}

	public void setAstVisitorAnalyzer(ASTVisitorAnalyzer astVisitorAnalyzer) {
		this.astVisitorAnalyzer = astVisitorAnalyzer;
	}
}