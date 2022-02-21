package net.jahez.jahezchallenge

import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : LocalizationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}