package parra.daniel.mydigimind

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.recordatorio.view.*

class ReminderAdapter: BaseAdapter {

    var reminders = ArrayList<Recordatorio>()
    var context: Context? = null

    constructor(context: Context, movies: ArrayList<Recordatorio>) {
        this.context = context
        this.reminders = movies
    }

    override fun getCount(): Int {
        return reminders.size
    }

    override fun getItem(p0: Int): Any {
        return reminders[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var reminder = reminders[p0]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.recordatorio, null)
        view.tv_dias_recordatorio.setText(reminder.days.toString())
        view.tv_nombre_recordatorio.setText(reminder.name)
        view.tv_tiempo_recordatorio.setText(reminder.time)
        return view
    }

}