package edu.sjtu.stap.iut;

import edu.sjtu.stap.ast.Ast;
import javafx.util.Pair;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.parser.IToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yfy on 5/27/16.
 * ThreadInst
 */
public class ThreadInst extends AbstractThreadInst implements Runnable {

  public ThreadInst() {}

  public ThreadInst(File file) {
    super(file);
  }

  /**
   * @param ast
   * @return Instrumented code
   * @throws Exception
   */
  @Override
  protected String inst(Ast ast) throws Exception {
    String fileContent = ast.getFileContent();
    StringBuilder sb = new StringBuilder(ast.getFileContent());
    sb.insert(0, "#include <stdio.h>\n");
    int offset = 19;

    List<IASTFunctionDefinition> funcList = ast.getFunctionDecl();
    for (IASTFunctionDefinition func : funcList) {
      String funcName = funcName(func, fileContent);
      if (funcName == null)
        continue;
      String log = String.format("puts(\"IUTLOG %s\");", funcName)
          .replaceAll("[\n\r]", " ").replaceAll(" {2,}", " ");
      sb.insert(brOffset + 1 + offset, log);
      offset += log.length();
    }

    return sb.toString();
  }

  /**
   * @param func
   * @param fileContent
   * @return Global unique name. May be null.
   * @throws Exception
   */
  public String funcName(IASTFunctionDefinition func, String fileContent) throws Exception {
    // file
    String fileName = func.getFileLocation().getFileName();
    int pos = fileName.lastIndexOf(File.separatorChar);
    fileName = fileName.substring(pos + 1);

    // prefix
    String prefix = "";
    IASTNode node = func.getParent();
    while (node != null) {
      IToken token = node.getSyntax();
      //System.out.println(token);
      String keyword = token.getImage();
      if (keyword.equals("class") || keyword.equals("namespace") || keyword.equals("struct")) {
        //System.out.println(token.getNext());
        String name = token.getNext().getImage();
        if (name.equals("{"))
          name = " ";
        prefix = name + "::" + prefix;
      }
      node = node.getParent();
    }

    // signature
    IASTStatement sm = func.getBody();
    if (sm == null) return null;
    brOffset = sm.getFileLocation().getNodeOffset();

    String decl = func.getDeclarator().getRawSignature();

    return String.format("%s: %s%s", fileName, prefix, decl);
  }

  private int brOffset;

  private String inst2(String buf) {
    Pattern pattern = Pattern.compile(Inst.functionDefPattern());
    //String test = "int a(const bool a){};";
    Matcher matcher = pattern.matcher(buf);
    ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      list.add(new Pair<>(start, end));
      //System.out.printf("%s\n(%d, %d): %s\n", file, start, end, buf.substring(start, end));
    }

    StringBuilder sb = new StringBuilder(buf);
    sb.insert(0, "#include <stdio.h>\n");
    int offset = 19;

    String log = "puts(\"heihei\");";
    for (Pair<Integer, Integer> pair : list) {
      sb.insert(pair.getValue() + offset, log);
      offset += log.length();
    }

    return sb.toString();
  }
}
