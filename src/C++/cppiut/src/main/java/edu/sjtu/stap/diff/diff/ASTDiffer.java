package edu.sjtu.stap.diff.diff;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.sjtu.stap.ast.Ast;
import edu.sjtu.stap.ast.AstWarehouse;
import edu.sjtu.stap.diff.ast.MyASTVisitor;
import edu.sjtu.stap.diff.ast.Parser;
import edu.sjtu.stap.diff.ast.XFunctionDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTPreprocessorFunctionStyleMacroDefinition;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.parser.ParserLanguage;



public class ASTDiffer {

	//	List<String> funcAdded;
	//	List<String> funcModified;
	//	List<String> funcDeleted;

	List<DUFunction.Added> functionAdded;
	List<DUFunction.Deleted> functionDeleted;
	List<DUFunction.Modified> functionModified;

	private Parser parserOld;
	private Parser parserNew;

	private Boolean isOtherChanged = null;

	public List<DUFunction.Added> getFunctionAdded() {
		return functionAdded;
	}

	public List<DUFunction.Deleted> getFunctionDeleted() {
		return functionDeleted;
	}

	public List<DUFunction.Modified> getFunctionModified() {
		return functionModified;
	}

	public boolean isFuncChanged(){
		return this.functionAdded.size() + this.functionDeleted.size() + this.functionModified.size() != 0;
	}


	public ASTDiffer(String oldContent, String newContent){

//		Parser parserOld, parserNew;
		parserOld = new Parser(oldContent);
		parserNew = new Parser(newContent);

		List<XFunctionDeclaration> oldFuncs = parserOld.getFunctionDefinitions();
		List<XFunctionDeclaration> newFuncs = parserNew.getFunctionDefinitions();



		HashMap<String, XFunctionDeclaration> mapNew;
		mapNew = new HashMap<>();

		functionAdded = new ArrayList<>();
		functionModified = new ArrayList<>();
		functionDeleted = new ArrayList<>();

		for(XFunctionDeclaration func : newFuncs){
			mapNew.put(func.getId(), func);
		}

		for(XFunctionDeclaration func : oldFuncs){
			XFunctionDeclaration funcInNew = mapNew.get(func.getId());
			if(funcInNew != null){if(!funcInNew.getOrigin().getBody().getRawSignature().equals(func.getOrigin().getBody().getRawSignature()) ){ //modified
				functionModified.add(new DUFunction.Modified(func));

			}
			mapNew.remove(func.getId());
			}else{//deleted
				functionDeleted.add(new DUFunction.Deleted(func));
			}

		}
		//TODO high priority for debugging
		for(XFunctionDeclaration func : mapNew.values()){
			//added
			functionAdded.add(new DUFunction.Added(func));
		}
	}


	public ASTDiffer(File oldFile, File newFile) throws Exception {

//		Parser parserOld, parserNew;
		parserOld = new Parser(oldFile);
		parserNew = new Parser(newFile);

		List<XFunctionDeclaration> oldFuncs = parserOld.getFunctionDefinitions();
		List<XFunctionDeclaration> newFuncs = parserNew.getFunctionDefinitions();



		HashMap<String, XFunctionDeclaration> mapNew;
		mapNew = new HashMap<>();

		functionAdded = new ArrayList<>();
		functionModified = new ArrayList<>();
		functionDeleted = new ArrayList<>();

		for(XFunctionDeclaration func : newFuncs){
			mapNew.put(func.getId(), func);
		}

		for(XFunctionDeclaration func : oldFuncs){
			XFunctionDeclaration funcInNew = mapNew.get(func.getId());
			if(funcInNew != null){if(!funcInNew.getOrigin().getBody().getRawSignature().equals(func.getOrigin().getBody().getRawSignature()) ){ //modified
				functionModified.add(new DUFunction.Modified(func));

			}
				mapNew.remove(func.getId());
			}else{//deleted
				functionDeleted.add(new DUFunction.Deleted(func));
			}

		}
		//TODO high priority for debugging
		for(XFunctionDeclaration func : mapNew.values()){
			//added
			functionAdded.add(new DUFunction.Added(func));
		}
	}

	public Boolean getIsOtherChanged() {
		if(isOtherChanged == null){
			MyASTVisitor oldAstVisitor = parserOld.getVisitor();
			MyASTVisitor newAstVisitor = parserNew.getVisitor();
			isOtherChanged = DiffUtils.whetherOtherChanged(oldAstVisitor, newAstVisitor);
		}
		return isOtherChanged;
	}
}
