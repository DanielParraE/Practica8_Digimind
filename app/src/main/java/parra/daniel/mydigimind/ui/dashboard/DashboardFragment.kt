package parra.daniel.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import parra.daniel.mydigimind.R
import parra.daniel.mydigimind.Recordatorio
import parra.daniel.mydigimind.databinding.FragmentDashboardBinding
import parra.daniel.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private var _binding: FragmentDashboardBinding? = null

    val db = Firebase.firestore

    companion object {
        private val TAG = "DocSnippets"
    }

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

        root.btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                txt_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                true).show()
        }

        root.btn_save.setOnClickListener {
            var title = et_remember_text.text.toString()

            var time = txt_time.text.toString()

            if (!time.isBlank() && !time.isEmpty() && time != "Set Time" && time != "Set time") {
                var days = getCheckedDays()

                var reminder = Recordatorio(days, time, title)

                db.collection("actividades").add(reminder)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG,  "Error adding document", e)
                    }

                // HomeFragment.recordatorios.add(reminder)

                Toast.makeText(root.context, "New Task Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(root.context, "Select time", Toast.LENGTH_SHORT).show()
            }

        }

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