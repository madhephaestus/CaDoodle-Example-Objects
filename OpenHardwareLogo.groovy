import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.svg.SVGLoad
import javafx.scene.paint.Color
File f = ScriptingEngine
.fileFromGit(
	"https://github.com/madhephaestus/CaDoodle-Example-Objects.git",//git repo URL
	null,//branch
	"OpenHardwareLogo.svg"// File from within the Git repo
)
SVGLoad s = new SVGLoad(f.toURI())
def holeParts = s.extrudeLayerToCSG(10,"Slice 1")
return holeParts.toZMin().moveToCenterX().moveToCenterY().setColor(Color.TEAL)
