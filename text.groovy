import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import javafx.scene.paint.Color

import eu.mihosoft.vrl.v3d.parametrics.*;
CSG getObject(){
	if(args==null)
		args=["Test_key_here"]

	StringParameter word = new StringParameter(csgdb,	args[0]+"_CaDoodle_TextGeneration_Value",
										"Text",[])
	HashSet<String> fontOptions = new HashSet<>();
	//fontOptions.addAll(javafx.scene.text.Font.getFontNames() )
	int index = 0;
	int numFonts=6
	for(String font:javafx.scene.text.Font.getFontNames()) {
		if(font.toLowerCase().contains("mono")&&!fontOptions.contains(font)) {
			fontOptions.add(font);
			index++;
		}
		if(index>numFonts)
			break;
	}
	index = 0;
	for(String font:javafx.scene.text.Font.getFontNames()) {
		if(font.toLowerCase().contains("serif")&&!fontOptions.contains(font)) {
			fontOptions.add(font);
			index++;
		}
		if(index>numFonts)
			break;
	}
	index = 0;
	for(String font:javafx.scene.text.Font.getFontNames()) {
		if(font.toLowerCase().contains("sans")&&!fontOptions.contains(font)) {
			fontOptions.add(font);
			index++;
		}
		if(index>numFonts)
			break;
	}
	
	ArrayList<String> option=new ArrayList<>()
	option.addAll(fontOptions)
	option.add("System Regular")
	StringParameter font = new StringParameter(csgdb,	args[0]+"_CaDoodle_TextGeneration_Font",
		option.get(option.size()-1),option)
	CSG text = CSG.text(word.getStrValue() ,  10,  20, font.getStrValue())
	csgdb.saveDatabase();
	text=   text.toZMin()
				.rotz(-90)
				.moveToCenterX()
				.moveToCenterY()
				.setColor(Color.PINK)
	return text
		.setParameter(csgdb,word)
		.setParameter(csgdb,font)
		.setRegenerate({getObject()})
}
return getObject()
