import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase

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


HashMap<String, HashMap<String, String>> allVitamins =  new HashMap<>()
HashMap<String, HashMap<String, String>> vex =  new HashMap<>()
HashMap<String, HashMap<String, String>> nutsandbolts =  new HashMap<>()
HashMap<String, HashMap<String, String>> motors =  new HashMap<>()

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
	//data.put("order", order)
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
		//order++;
		println "Vitamin took "+type+" "+mySize+" "+currentTimeMillisStart
		String code = 	"import com.neuronrobotics.bowlerstudio.vitamins.Vitamins; \n"+\
					"import eu.mihosoft.vrl.v3d.CSG\n"+
				"import eu.mihosoft.vrl.v3d.parametrics.StringParameter\n"+
				"import eu.mihosoft.vrl.v3d.parametrics.CSGDatabase;\n"+
				"CSG getObject(){\n"+
				"\tif(args==null)\n"+
					"\t\targs=[\"Test_key_here\"]\n"+\
				"\tStringParameter size = new StringParameter(	\""+type+"\"+\" Default\",\"\",Vitamins.listVitaminSizes(\""+type+"\"))	\n"+
				"\tStringParameter word = new StringParameter(	args[0]+\"_CaDoodle_TextGeneration_Size\",size.getStrValue(),Vitamins.listVitaminSizes(\""+type+"\"))\n"+
				"\tsize.setStrValue(word.getStrValue())\n"+
				"\tdef part= Vitamins.get(\""+type+"\",word.getStrValue()).setIsHole(true)\n"+
				"\tCSGDatabase.saveDatabase() \n"+
				"\treturn part.setParameter(word).setRegenerate({getObject()})\n"+\
					"}\n"+
				"return getObject()\n"
		vitaminGen.delete();
		Files.writeString(vitaminGen.toPath(), code, StandardOpenOption.CREATE);
		allVitamins.put(type, data)
		if(type.toLowerCase().contains("vex")) {
			vex.put(type, data)
		}
		if(type.toLowerCase().contains("nut")||type.toLowerCase().contains("bolt")||type.toLowerCase().contains("screw")) {
			nutsandbolts.put(type, data)
		}
		if(Vitamins.isActuator(type)||Vitamins.isShaft(type)) {
			motors.put(type, data)
		}
	}catch(Throwable t) {
		t.printStackTrace()
	}
}
String f = "VEX_Vitamins.json"
File fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);
String contents = gson.toJson(vex);
println contents
println fileFromGit.absolutePath
fileFromGit.delete();
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);

 f = "Vitamins.json"
 fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);
 contents = gson.toJson(allVitamins);
println contents
println fileFromGit.absolutePath
fileFromGit.delete();
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);

f = "Fastener_Vitamins.json"
fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);
contents = gson.toJson(nutsandbolts);
println contents
println fileFromGit.absolutePath
fileFromGit.delete();
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);

f = "Motor_Vitamins.json"
fileFromGit = ScriptingEngine.fileFromGit(gitULR, f);
contents = gson.toJson(motors);
println contents
println fileFromGit.absolutePath
fileFromGit.delete();
Files.writeString(fileFromGit.toPath(), contents, StandardOpenOption.CREATE);