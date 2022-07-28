package com.ramadan.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ramadan.home.databinding.FragmentFirstBinding
import com.ramadan.netwrok.RequestTask

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentWordList : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var adapter: Adapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val execute = RequestTask()
        val task = execute.execute("http://instabug.com/")
        Log.i("TAGTAG", task.status.name)
        val ss = task.get().toString().replace(Regex("[$&+,:;=?@#|/©‘'\\[\\]<>→“\".^*()%!-]"), "")
            .split(" ").groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .toMap()

        Log.i("TAGTAG", ss.toString())
        Log.i("TAGTAG", task.status.name)

        binding.rvRepeatedWords.adapter = Adapter(ss)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}