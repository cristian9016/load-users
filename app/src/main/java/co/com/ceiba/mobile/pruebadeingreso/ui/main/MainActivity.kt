package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable
import co.com.ceiba.mobile.pruebadeingreso.util.buildViewModel
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val layoutManager = LinearLayoutManager(this)
    private val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>() }
    private val dis = LifeDisposable(this)
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewSearchResults.adapter = adapter
        recyclerViewSearchResults.layoutManager = layoutManager
        dialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))
    }

    override fun onStart() {
        super.onStart()
        dialog.show()
        dis add viewModel.getAllOffline()
                .subscribe(
                        {
                            if (it.isNotEmpty()) {
                                adapter.data = it
                                dialog.hide()
                            } else getAllOnline()
                        },
                        {
                            dialog.hide()
                            toast(it.message!!)
                        }
                )
    }

    private fun getAllOnline() =
            dis add viewModel.getAllOnline()
                    .subscribe(
                            {
                                adapter.data = it
                                dialog.hide()
                            },
                            {
                                dialog.hide()
                                toast(it.message!!)
                            }
                    )


}