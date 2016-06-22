package edu.sjtu.stap.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yfy on 5/26/16.
 * Config from config.iut
 */
public class Config {

  public static void init() throws Exception {
    JsonParser parser = new JsonParser();
    JsonObject object = (JsonObject)parser.parse(new FileReader(configFile));

    baseDir = object.get("baseDir").getAsString();
    baseDirInst = baseDir + ".inst";

    baseDirNew = object.get("baseDirNew").getAsString();

    make = object.get("make").getAsString();
    makeClean = make + " clean";

    JsonArray array = object.get("testCmd").getAsJsonArray();
    testCmd = new ArrayList<>(array.size());
    for (JsonElement e : array)
      testCmd.add(e.getAsString());
  }

  public final static String configFile = "config.iut";

  private static String baseDir;

  private static String baseDirInst;

  private static String baseDirNew;

  private static String make;

  private static String makeClean;

  private static List<String> testCmd;

  public static String getBaseDir() {
    return baseDir;
  }

  public static String getBaseDirInst() {
    return baseDirInst;
  }

  public static String getBaseDirNew() {
    return baseDirNew;
  }

  public static String getMake() {
    return make;
  }

  public static String getMakeClean() {
    return makeClean;
  }

  public static List<String> getTestCmd() {
    return testCmd;
  }

  //    public static String baseDir = "/Users/weizhaoy/Documents/workspace/iut/benchmarks/cctz";
//
//    public static String baseDirInst = baseDir + ".inst";
//
//    public static String baseDirNew = "/Users/weizhaoy/Documents/workspace/iut/benchmarks/cctz2";

//  public static String srcRelativeDir = "/src";
//
//  public static String srcDir = baseDir + srcRelativeDir;
//
//  public static String srcDirInst = baseDirInst + srcRelativeDir;

  //public static String srcDir = "/home/yfy/iut/src/C++/copy of test/";

}
