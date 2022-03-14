package parra.daniel.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_dashboard.*
import parra.daniel.mydigimind.R
import parra.daniel.mydigimind.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    fun getCheckedDays() : ArrayList<String> {
        val days = ArrayList<String>()
        if (cb_monday.isChecked) {
            days.add("Monday")
        }
        if (cb_tuesday.isChecked) {
            days.add("Tuesday")
        }
        if (cb_wednesday.isChecked) {
            days.add("Wednesday")
        }
        if (cb_thursday.isChecked) {
            days.add("Thursday")
        }
        if (cb_friday.isChecked) {
            days.add("Friday")
        }
        if (cb_saturday.isChecked) {
            days.add("Saturday")
        }
        if (cb_sunday.isChecked) {
            days.add("Sunday")
        }
        return days
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}