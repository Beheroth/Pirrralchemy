import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashSet;



public final class MyApp {
    private static HashSet<MyElement> knownElements = new HashSet<MyElement>();
    private static HashSet<MyElement> unknownElements = new HashSet<MyElement>();
    private static HashSet<Link> links = new HashSet<Link>();

    public static HashSet<MyElement> getKnownElements() {
        return knownElements;
    }

    public static HashSet<MyElement> getUnknownElements() {
        return unknownElements;
    }

    public static HashSet<Link> getLinks() {
        return links;
    }

    public static void Load()
    {
        System.out.println("Load DB");
        JSONObject knownJson =  loadJSON("known.json");
        JSONObject unknownJson =  loadJSON("unknown.json");
        JSONObject linksJson =  loadJSON("links.json");

        jsonToSetOfElements(knownElements, knownJson, "knownElements");
        jsonToSetOfElements(unknownElements, unknownJson, "unknownElements");
        jsonToSetOfLinks(links, linksJson, "links");
    }
    private MyApp () {
        Load();
    }

    private static JSONObject loadJSON(String path) {
        try {
            File file = new File(path);
            String content = FileUtils.readFileToString(file, "utf-8");
            return new JSONObject(content);
        }
        catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    private static void jsonToSetOfElements(HashSet<MyElement> outSet, JSONObject jsonIn, String keyIn) {

        try {
            JSONArray array = jsonIn.getJSONArray(keyIn);
            Type type = new TypeToken<MyElement>(){}.getType();
            for (int i = 0; i < array.length(); i ++) {
                MyElement target = new Gson().fromJson(array.optJSONObject(i).toString(), type);
                outSet.add(target);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static  void jsonToSetOfLinks(HashSet<Link> outSet, JSONObject jsonIn, String keyIn) {

        try {
            JSONArray array = jsonIn.getJSONArray(keyIn);
            Type type = new TypeToken<Link>(){}.getType();
            for (int i = 0; i < array.length(); i ++) {
                Link target = new Gson().fromJson(array.optJSONObject(i).toString(), type);
                outSet.add(target);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
