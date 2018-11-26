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

//STATIC CLASS
//This is the status of the application
public final class MyApp {
    private static HashSet<MyElement> knownElements = new HashSet<MyElement>();
    private static HashSet<MyElement> unknownElements = new HashSet<MyElement>();
    private static HashSet<Link> links = new HashSet<Link>();
    private static String[] paths = new String[]{
            "src/main/resources/known.json",
            "src/main/resources/unknown.json",
            "src/main/resources/links.json"};
    private static JSONArray[] jsonObjectsBuffer = new JSONArray[3];

    // Gets an HashSet of the elements known by the user.
    public static HashSet<MyElement> getKnownElements() {
        return knownElements;
    }

    // Gets an HashSet of the elements unknown by the user.
    public static HashSet<MyElement> getUnknownElements() {
        return unknownElements;
    }

    // Gets an HashSet of the links between the elements.
    public static HashSet<Link> getLinks() {
        return links;
    }

    // Loads the JSON (links, unknownElements, knownElements).
    public static void load() {
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

    // Closes the app if the game is won.
    public static void askIfWin() {
        if (unknownElements.size()==0) {
            System.out.println("You finished the game.");
            System.exit(42);
        }
    }

    // Searches and returns a specific link if it is contained in the attribute "links".
    public static Link findLink(Link myLink){
        return getLinks().stream().filter(link -> myLink.compare(link)).findAny().orElseThrow();
    }

    // Returns a MyElement object referenced as a result in "myLink".
    public static MyElement getResult(Link myLink) {
        return getUnknownElements().stream().filter(element -> element.getID().equals(myLink.getCreated())).findAny().orElseThrow();
    }

    // Private constructor for static class.
    private MyApp () {
    }

    // Loads the jsons.
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

    // Transforms a JSONArray to an existing set of elements.
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

    // Transforms a JSONArray to an existing set of links.
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

    // Saves the known and unknown elements into json.
    public static void save(){
        try {
            BufferedWriter known = new BufferedWriter(new FileWriter(paths[0]));
            String knownToSave = new Gson().toJson(knownElements);
            known.write(knownToSave);
            known.close();

            BufferedWriter unknown = new BufferedWriter(new FileWriter(paths[1]));
            String unknownToSave = new Gson().toJson(unknownElements);
            unknown.write(unknownToSave);
            unknown.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Called when an element is merged. Changes the element to "known" and saves.
    public static void elementMerged(MyElement elem){
        unknownElements.remove(elem);
        knownElements.add(elem);
        save();
    }
}
