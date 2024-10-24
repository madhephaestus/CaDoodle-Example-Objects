import eu.mihosoft.vrl.v3d.CSG

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
	String Options = "["
	for(String size:listVitaminSizes) {
		mySize=size
		Options+="\""+size+"\","
	}
	Options=Options.substring(0,Options.length()-1)
	Options+="]"
	HashMap<String,String> data = new HashMap<>();
	data.put("git", gitULR)
	data.put("order", order++)
	String name = type+".groovy"
	data.put("file", name)

	File vitaminGen =  ScriptingEngine.fileFromGit(gitULR, name);
	try {
		long start = System.currentTimeMillis();
		if(Vitamins.get(type,mySize)==null) {
			throw new RuntimeException("Vitamin did not load "+type+" "+mySize)
		}
		long currentTimeMillisStart = System.currentTimeMillis()-start
		if(currentTimeMillisStart>900) {
			throw new RuntimeException("Vitamin took too long not load "+type+" "+mySize+" "+currentTimeMillisStart);
			
		}
		println "Vitamin took "+type+" "+mySize+" "+currentTimeMillisStart
		String code = 	"import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; \n"+\
					"import eu.mihosoft.vrl.v3d.CSG\n"+
				"import eu.mihosoft.vrl.v3d.parametrics.StringParameter\n"+
				"CSG getObject(){\n"+
				"\tif(args==null)\n"+
				"\t\targs=[\"Test_key_here\"]\n"+\
					"\t	StringParameter word = new StringParameter(	args[0]+\"_CaDoodle_TextGeneration_Size\",\""+mySize+"\",Vitamins.listVitaminSizes(\""+type+"\"))\n"+
				"\tdef part= Vitamins.get(\""+type+"\",word.getStrValue()).setIsHole(true)\n"+
				"\treturn part.setParameter(word).setRegenerate({getObject()})\n"+\
					"}\n"+
				"return getObject()\n"
		vitaminGen.delete();
		Files.writeString(vitaminGen.toPath(), code, StandardOpenOption.CREATE);
		tmp.put(type, data)
	}catch(Throwable t) {
		t.printStackTrace()
	}
}

String contents = gson.toJson(tmp);
println contents
println fileFromGit.absolutePath
fileFromGit.delete();
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);

