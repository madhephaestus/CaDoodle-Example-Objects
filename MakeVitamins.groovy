import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins

String gitULR = "https://github.com/madhephaestus/CaDoodle-Example-Objects.git";
HashMap<String, HashMap<String, HashMap<String, String>>> nameToFile = new HashMap<>();
Type TT = new TypeToken<HashMap<String, HashMap<String, String>>>() {
		}.getType();
Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
String f = "Vitamins.json"
File fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);

HashMap<String, HashMap<String, String>> tmp =  new HashMap<>()
int order =0;
for(String type:Vitamins.listVitaminTypes()) {
	ArrayList<String> listVitaminSizes = Vitamins.listVitaminSizes(type)
	String mySize ="";
	for(String size:listVitaminSizes) {
		mySize=size
	}
	HashMap<String,String> data = new HashMap<>();
	data.put("git", gitULR)
	data.put("order", order++)
	String name = type+".groovy"
	data.put("file", name)
	
	File vitaminGen =  ScriptingEngine.fileFromGit(gitULR, name);
	String code = "import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; \n"+\
"return Vitamins.get(\""+type+"\",\""+mySize+"\").setIsHole(true)"
	Files.writeString(vitaminGen.toPath(), code, StandardOpenOption.CREATE);
	tmp.put(type, data)
}

String contents = gson.toJson(tmp);
println contents
println fileFromGit.absolutePath
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);