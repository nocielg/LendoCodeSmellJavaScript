import java.io.*;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.ast.*;
import java.util.*;
import org.mozilla.javascript.ast.*;
import org.mozilla.javascript.Token;
import org.mozilla.javascript.ast.*;
import org.w3c.dom.Node;


//
public class Analise  {
	//final Set<String> names = new HashSet<String>();
	//String script = "function abc(x,y) {return x+y;}";
	String script = "var o = {\n" +
            "  _x: 123, " +
            "  get x() {" +
            "                                           "
            + ""
            + ""
            + ""
            + ""
            + ""
            + "return this._x;\n" +
            "   }\n" +
            ", \n" +
            "        set x(value) {\n" +
            "                "
            + ""
            + "this._x = value;\n" +
            "  }" +
            "};";
	private static final int MAX_METHODO_TAMANHO = 50;
	private static final int MIN_OBJECTO_PROPRIEDADES = 3;
	private int profundidadeFuncaoAnterior = 0;
	private static final int TAMANHO_MAXIMO_DO_SCOPE_CHAIN = 3;
	private static final int MAX_NUMBER_OF_SWITCHCASE = 3;
	private static final int Max_LINHAS_GOD_CLASS = 200;
	private static final int MAX_NUMERO_DE_PARAMETROS = 5;
	private int scopeChainLength = 0;
	private AstRoot astRoot;
	private AstNode ASTNode;
	FunctionNode f = (FunctionNode) ASTNode;
	
	
	public Analise() throws IOException {
		CompilerEnvirons environment = new CompilerEnvirons();
		Parser parser = new Parser(environment);
	    astRoot = parser.parse(new StringReader(script), null, 1);
	}
	public void testando() {
		Context context = Context.enter();
		try {
			ScriptableObject scope = context.initStandardObjects();
			Scriptable that = context.newObject(scope);
			Function fct = context.compileFunction(scope, script, "script", 1, null);
			Object result = fct.call(
					context, scope, that, new Object[] {2, 3});
			//System.out.println(Context.jsToJava(result, int.class));
			System.out.println(Context.jsToJava(result, int.class));
			//Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
		} finally {
			Context.exit();
		}
	}
	
	public void testePegarArgumentos() {
		Context cx = Context.enter();
        Scriptable scope = cx.initStandardObjects();
        cx.evaluateString(scope, "function f(x,y){ return x+y}", "<cmd>", 1, null);
        try {
            String result = (String) cx.evaluateString(scope, "f.toString()", "<cmd>", 1, null);
        	//String result = (String) cx.newObject(scope, "f.toString()", "<cmd>", 1, null);
            System.out.println(result);
        } catch (org.mozilla.javascript.EcmaError ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void identificaFaltaDeIdentacaoeIdenta() throws IOException {
		//CompilerEnvirons environment = new CompilerEnvirons();
		//Parser parser = new Parser(environment);
	    //astRoot = parser.parse(new StringReader(script), null, 1);
	    System.out.println(astRoot.toSource());
	    
	}
	
	//Detecta LongMethod
	public void detectaLongMethod() {
		//FunctionNode f = (FunctionNode) ASTNode;
		//CompilerEnvirons environment = new CompilerEnvirons();
		//Parser parser = new Parser(environment);
	    //astRoot = parser.parse(new StringReader(script), null, 1);
		
		
		int tamanhodaFuncao = astRoot.getEndLineno() - astRoot.getLineno();
		if (tamanhodaFuncao > MAX_METHODO_TAMANHO) {
			System.out.println(tamanhodaFuncao);
			System.out.println("O método possui o code smell Long Method");
		}
		else {
			System.out.println("O método não possui o code smell Long Method");
			System.out.println(tamanhodaFuncao);
		}
	}
	
	//dúvida
	//Detecta nested function
	public void detectaClosureSmell() throws IOException {
		script = "foo(42) { (x: Int) in\n" + 
				"    bar(x) { (x: Int) in\n" + 
				"      foobar(x) { // Noncompliant\n" + 
				"        print(x * 42)\n" + 
				"			foobar(x,y) {"+
				"      			foobar(x,y,g) {\"+\n" + 
				"							}\\n\" + 	" +
				"						}\n" + 
				"      print(x + 42)\n" + 
				"    }\n" + 
				"    print(x - 42)\n" + 
				"}";
		int fDepth = astRoot.depth();
		//String name = astRoot.shortName();
		
		if(fDepth > profundidadeFuncaoAnterior) {
			scopeChainLength++;
			if(scopeChainLength>TAMANHO_MAXIMO_DO_SCOPE_CHAIN) {
				System.out.println("O método possui closure smell" );
				System.out.println(fDepth);
			}
			else {
				System.out.println("O método não possui closure smell");
				System.out.println(fDepth);
			}
		}
		else {
			System.out.println(fDepth);
			System.out.println("O método não possui closure smell");
			scopeChainLength = 1;
		}
		profundidadeFuncaoAnterior = fDepth;
		
	}
	
	//Detecta GodClass
	public void detectaGodClass() {
		int tamanhodaClasse = astRoot.getEndLineno() - astRoot.getLineno();
		if (tamanhodaClasse > Max_LINHAS_GOD_CLASS) {
			System.out.println("O método possui o code smell God Class");
			System.out.println(tamanhodaClasse);
		}
		else {
			System.out.println("O método não possui o code smell God Class");
			System.out.println(tamanhodaClasse);
		}
	}
	
	//testando
	/*public void detectaSwitchSmell() {

		//astRoot.getCases();
		
		//System.out.println("switch found at line: " + (ASTNode.getLineno()+1) + " and has " + s.getCases().size() + " statements");

		if (((Object) ASTNode).getCases().size() >3 ){
		
		}	
	}*/
	
	
	//testando
	//checa css
	// checking css in javascript 
	/*
	public void detectaCSSSmell(
	if (astRoot.getCommentType.equals("style")){
		SmellLocation sl = new SmellLocation("CSS in JavaScript", jsFileName,(ASTNode.getLineno()+1));
		//System.out.println("CSSinJS : at line " + (ASTNode.getLineno()+1) + " of file: " + jsFileName);
		CSSinJS.add(sl);
	}else {
		
	}*/
	
	
	//Detecta long parameter list
	/*
	public void detectaLongParameterList() {
		//FunctionNode f = (FunctionNode) astRoot;
		int numOfParam = f.getParams().size();
		if (numOfParam >= MAX_NUMERO_DE_PARAMETROS){
			System.out.println("A funcao possui o code smell long parameter list");
		}
		else {
			System.out.println("A funcao nao possui o code smell long parameter list");
		}
	}
	*/
	
	/*
	//Detecta lazy objects
	/*public void detectaLazyObjects() {
		astRoot.size();
		if (astRoot.size() < MIN_OBJECTO_PROPRIEDADES) {
			
		}
	}
	
	/*
	@Override
	public boolean visit(AstNode no) {
		String script = "(V1ND < 0 ? Math.abs(V1ND) : 0)";
	    AstNode node = new Parser().parse(script, "<cmd>", 1);
	    node.visit(new Visitor());
	    System.out.println(names);
		
	}*/
}
	
