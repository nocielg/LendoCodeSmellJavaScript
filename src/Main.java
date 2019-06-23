import java.io.FileNotFoundException;
import java.io.IOException;

import javax.script.ScriptException;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

public class Main {
	
	public static void main (String [] args) throws ScriptException, NoSuchMethodException, IOException {
		//System.out.println("rodou");
		//SmellDetector.setJSName("javascript");
		//SmellDetector smell = new SmellDetector();
		//smell.analyseObjectLiteralNode();
		//SmellDetector.writeReportTofile();
		//SmellDetector.getcandidateJSObjectList();
		//SmellDetector.generateReport(true);
		//DetectandoSmell smell= new DetectandoSmell();
		//String scopename;
		//SmellDetector.setJSName("/src/javascript");
		//SmellDetector.analyseCoupling("/src/javascript", "", "");
		//SmellDetector.generateReport(true);
		//AstRoot ast = null;
		//Context cx = Context.enter();
		//Parser rhinoParser = new Parser(new CompilerEnvirons(), cx.getErrorReporter());
		//ast = rhinoParser.parse(new String(input), scopename, 0);
		//JSASTModifier modifier; //.setScopeName(scopename);
//
		//modifier.start();
		//ast.visit(modifier);
		//SmellDetector.generateReport(false);

		//DetectandoSmell detectando = new DetectandoSmell();
		//detectando.teste();
		Analise print = new Analise();
		//print.testando();
		//print.testePegarArgumentos();
		//print.identificaFaltaDeIdentacaoeIdenta();
		//print.detectaLongMethod();
		print.detectaClosureSmell();
		//print.detectaGodClass();
		//print.detectaLongParameterList();
		
	}
}