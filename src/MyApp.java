import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;

public final class MyApp {
    private static HashSet<MyElement> knownElements = new HashSet<MyElement>();
    private static HashSet<MyElement> unknownElements = new HashSet<MyElement>();
    private static HashSet<Link> links = new HashSet<Link>();
    private static String[] paths = new String[]{"known.json", "unknown.json", "links.json"};
    private static JSONArray[] jsonObjectsBuffer = new JSONArray[3];

    public static HashSet<MyElement> getKnownElements() {
        return knownElements;
    }

    public static HashSet<MyElement> getUnknownElements() {
        return unknownElements;
    }

    public static HashSet<Link> getLinks() {
        return links;
    }

    public static void load()
    {
        System.out.println("Load DB");
        int i = 0;
        for (String path: paths) {
            jsonObjectsBuffer[i] = loadJSON(path);
            i++;
        }

        jsonToSetOfElements(knownElements, jsonObjectsBuffer[0]);
        jsonToSetOfElements(unknownElements, jsonObjectsBuffer[1]);
        jsonToSetOfLinks(links, jsonObjectsBuffer[2]);
        askIfWin();
    }

    public static void askIfWin() {
        if (unknownElements.size()==0) {
            System.out.println("You finished the game.");
            System.exit(42);
        }
    }
    private MyApp () {
    }

    private static JSONArray loadJSON(String path) {
        try {
            File file = new File(path);
            String content = FileUtils.readFileToString(file, "utf-8");
            return new JSONArray(content);
        }
        catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    private static void jsonToSetOfElements(HashSet<MyElement> outSet, JSONArray jsonIn) {

        try {
            Type type = new TypeToken<MyElement>(){}.getType();
            for (int i = 0; i < jsonIn.length(); i ++) {
                MyElement target = new Gson().fromJson(jsonIn.optJSONObject(i).toString(), type);
                outSet.add(target);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static  void jsonToSetOfLinks(HashSet<Link> outSet, JSONArray jsonIn) {

        try {
            Type type = new TypeToken<Link>(){}.getType();
            for (int i = 0; i < jsonIn.length(); i ++) {
                Link target = new Gson().fromJson(jsonIn.optJSONObject(i).toString(), type);
                outSet.add(target);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void save(){
        try {
            BufferedWriter known = new BufferedWriter(new FileWriter(paths[0]));
            String knownToSave = new Gson().toJson(knownElements);
            System.out.println(knownToSave);
            known.write(knownToSave);
            known.close();

            BufferedWriter unknown = new BufferedWriter(new FileWriter(paths[1]));
            String unknownToSave = new Gson().toJson(unknownElements);
            System.out.println(knownToSave);
            unknown.write(unknownToSave);
            unknown.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void elementMerged(MyElement elem){
        unknownElements.remove(elem);
        knownElements.add(elem);
        save();
    }
}
