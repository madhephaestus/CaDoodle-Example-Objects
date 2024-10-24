import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Icosahedron
import javafx.scene.paint.Color

return new Icosahedron(10).toCSG().toZMin().setColor(Color.RED)
