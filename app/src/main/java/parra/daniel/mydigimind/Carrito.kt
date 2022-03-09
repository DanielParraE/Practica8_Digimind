package parra.daniel.mydigimind

import java.io.Serializable
import java.util.ArrayList

class Carrito : Serializable {

    var recordatorios = ArrayList<Recordatorio>()

    fun agregar(p: Recordatorio): Boolean {
        return recordatorios.add(p)
    }

}