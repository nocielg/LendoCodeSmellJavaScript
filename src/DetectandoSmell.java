import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.ast.*;

//import com.sun.org.apache.bcel.internal.classfile.Node;

import org.mozilla.javascript.Parser;
import org.mozilla.javascript.Token;

import java.util.*;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class DetectandoSmell {
	
	public void teste() {
	String evaluationScript =
		      "var earnings = stock.getNetIncome() * 10; " +
		      "earnings += (stock.getTotalCash() - stock.getTotalDebt()); " +
		      "if (earnings > stock.getMarketCap()) { " +
		      "    stock.setUndervalued(true); " +
		      "} else { " +
		      "    stock.setUndervalued(false); " +
		      "} ";
	
	

	SmellDetector smell = new SmellDetector();
	
	//pegando o contexto
	Context cx = Context.enter();
	
	try {
	      // Initialize the standard objects (Object, Function, etc.). This must be done before scripts can be
	      // executed. The null parameter tells initStandardObjects
	      // to create and return a scope object that we use
	      // in later calls.
	      Scriptable scope = cx.initStandardObjects();

	      // Pass the Stock Java object to the JavaScript context
	      Object wrappedStock = Context.javaToJS(smell, scope);
	      ScriptableObject.putProperty(scope, "stock", wrappedStock);

	      // Execute the script
	      cx.evaluateString(scope, evaluationScript, "EvaluationScript", 1, null);
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      // Exit the Context. This removes the association between the Context and the current thread and is an
	      // essential cleanup action. There should be a call to exit for every call to enter.
	      Context.exit();
	    }

	//Teste 1
	/*final Set<String> names = new HashSet<String>();

		private AstRoot parse (String src, int startLineNum) throws IOException {
			CompilerEnvirons env = new CompilerEnvirons();
			env.setRecoverFromErrors(true);
			env.setGenerateDebugInfo(true);
			env.setRecordingComments(true);
			StringReader strReader = new StringReader("var nome;\n" + 
					"nome = 'Fulano de Tal';\n" + 
					"var idade = 30;\n" + 
					"idade = 30 + 20 - 10*5;");
			
			IRFactory factory = new IRFactory (env);
			//Node irNode = factory.transform(node);
			return factory.parse(strReader, null, startLineNum);
		}*/	
			
		
		
		
		
		
		/*public Boolean longParameterList() {
			
			Boolean resultado = false;
			if (numOfParam >= MAX_NUMBER_OF_PARAMETERS){
				//System.out.println("function " + func.getName() + " has " + func.getParams().size() + " parameters in line " + (func.getLineno()+1));
				SmellLocation sl = new SmellLocation(fName, "src/javascript",lineNumber);
				longParameterListFound.add(sl);
				resultado = true;
			}
			else {
				resultado=false;
			}
			return resultado;
		}
		
	 	
	
		public Boolean booleanVerificaLongParameterList() throws FileNotFoundException, ScriptException, NoSuchMethodException{
			
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			Invocable invocable = (Invocable) engine;
			engine.eval(new FileReader("src/javascript"));
			Boolean resultado = (Boolean) invocable.invokeFunction("longParameterList");
			return resultado;
		}*/
		
		
		//AstNode ASTNode;// = new Parser().parse(javascript, "<cmd>", 1);
	 
	 
	 // aqui
	
	}
}
