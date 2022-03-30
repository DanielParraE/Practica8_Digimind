package parra.daniel.mydigimind.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.view.*
import parra.daniel.mydigimind.R
import parra.daniel.mydigimind.Recordatorio
import parra.daniel.mydigimind.ReminderAdapter
import parra.daniel.mydigimind.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var adaptador: ReminderAdapter? = null

    companion object {
        var recordatorios = ArrayList<Recordatorio>()
        var first = true
    }

    private var _binding: FragmentHomeBinding? = null

    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first) {
            loadReminders()
            first = false
        }

        adaptador = ReminderAdapter(root.context, recordatorios)

        root.gw_home.adapter = adaptador

        return root
    }

    private fun loadReminders() {
        recordatorios.add(Recordatorio(arrayListOf("Tuesday"), "17:30", "Practice 1"))
        recordatorios.add(Recordatorio(arrayListOf("Monday", "Sunday"), "17:30", "Practice 2"))
        recordatorios.add(Recordatorio(arrayListOf("Wednesday"), "14:00", "Practice 3"))
        recordatorios.add(Recordatorio(arrayListOf("Saturday"), "11:00", "Practice 4"))
        recordatorios.add(Recordatorio(arrayListOf("Tuesday"), "13:00", "Practice 5"))
        recordatorios.add(Recordatorio(arrayListOf("Thursday"), "10:40", "Practice 6"))
        recordatorios.add(Recordatorio(arrayListOf("Monday"), "12:00", "Practice 7"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
