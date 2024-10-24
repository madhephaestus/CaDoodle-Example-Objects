import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

String gitULR = "https://github.com/madhephaestus/CaDoodle-Example-Objects.git";
HashMap<String, HashMap<String, HashMap<String, String>>> nameToFile = new HashMap<>();
Type TT = new TypeToken<HashMap<String, HashMap<String, String>>>() {
		}.getType();
Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
String f = "Vitamins.json"
File fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);

HashMap<String, HashMap<String, String>> tmp =  new HashMap<>()

String contents = gson.toJson(tmp);

println contents
println fileFromGit.absolutePath
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);