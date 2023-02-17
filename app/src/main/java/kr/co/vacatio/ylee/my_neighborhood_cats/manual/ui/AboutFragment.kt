package kr.co.vacatio.ylee.my_neighborhood_cats.manual.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private val binding by lazy { FragmentAboutBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.gameIntro.observe(this) {
            binding.title.text = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.homeBtn.setOnClickListener {
            viewModel.setFragment(MainFragment::class.java.name)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGameIntro()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AboutFragment()
    }
}