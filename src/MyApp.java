import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


public final class MyApp {
    private static Set<MyElement> knownElements;
    private static Set<MyElement> unknownElements;
    private static Set<Link> links;

    public static Set<MyElement> getKnownElements() {
        return knownElements;
    }

    public static Set<MyElement> getUnknownElements() {
        return unknownElements;
    }

    public static Set<Link> getLinks() {
        return links;
    }

    public static void Load()
    {
        System.out.println("Load DB");
        JSONObject knownJson =  loadJSON("known.json");
        JSONObject unknownJson =  loadJSON("unknown.json");
        JSONObject linksJson =  loadJSON("links.json");

        jsonToSet(knownElements, knownJson, "knownElements");
        jsonToSet(unknownElements, unknownJson, "unknownElements");
        jsonToSet(links, linksJson, "links");
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

    private static <T> void jsonToSet(Set<T> outSet, JSONObject jsonIn, String keyIn) {

        try {
            JSONArray array = jsonIn.getJSONArray(keyIn);
            Type listType = new TypeToken<List<T>>(){}.getType();
            List<T> yourClassList = new Gson().fromJson(array.toString(), listType);
            outSet.addAll(yourClassList);
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }
}
