package com.teacomputers.moviesapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.teacomputers.moviesapp.common.connectionlivedata.ConnectionLiveData
import com.teacomputers.moviesapp.common.connectionlivedata.CustomDialogConnection
import com.teacomputers.moviesapp.common.getStringText
import com.teacomputers.moviesapp.common.gone
import com.teacomputers.moviesapp.common.visible
import com.teacomputers.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ViewsManager {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        initializeConnection()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    private fun initializeConnection() {
        ConnectionLiveData(this).observe(this) {
            if (!it) {
                CustomDialogConnection.showHintDialogWifi(this)
                binding.tvConnection.getStringText(R.string.disconnected)
            } else {
                binding.tvConnection.getStringText(R.string.connected)
                CustomDialogConnection.dialog2?.dismiss()
            }
        }
    }

    override fun showLoading() {
        if (binding.stub.inflatedId != View.NO_ID) {
            binding.stub.inflate()
        }
        binding.stub.rootView.visible()
    }

    override fun hideLoading() {
        binding.stub.gone()
    }
}
