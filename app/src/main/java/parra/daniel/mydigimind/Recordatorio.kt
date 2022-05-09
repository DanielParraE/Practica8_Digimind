package parra.daniel.mydigimind

import java.io.Serializable

data class Recordatorio(var days: ArrayList<String>, var time: String, var name: String) : Serializable {
    constructor(): this(ArrayList<String>(), "", "")
}