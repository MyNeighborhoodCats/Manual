package kr.co.vacatio.ylee.my_neighborhood_cats.manual.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.catInfo.observe(this) {
            binding.title.text = "${it.character1} : ${it.value1}\n" +
                    "${it.character2} : ${it.value2}\n" +
                    "${it.character3} : ${it.value3}\n" +
                    "고양이 설명 : ${it.info}"
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
        viewModel.getCat(3)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}