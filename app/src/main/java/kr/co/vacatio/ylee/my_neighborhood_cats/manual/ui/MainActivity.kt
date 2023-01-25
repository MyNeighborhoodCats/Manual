package kr.co.vacatio.ylee.my_neighborhood_cats.manual.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainFrame.id, MainFragment.newInstance())
            .commit()

        viewModel.fragmentName.observe(this) {
            changeFrame(it)
        }

        setContentView(binding.root)
    }



    private fun changeFrame(fragmentName: String) {
        val fragment = when (fragmentName) {
            MainFragment::class.java.name -> MainFragment.newInstance()
            AboutFragment::class.java.name -> AboutFragment.newInstance()
            GuideFragment::class.java.name -> GuideFragment.newInstance()
            SearchFragment::class.java.name -> SearchFragment.newInstance()
            else -> null
        } ?: return

        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrame.id, fragment, fragmentName)
            .addToBackStack(fragmentName)
            .commit()
    }

}