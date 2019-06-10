package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.exts.LifeDisposable
import co.com.ceiba.mobile.pruebadeingreso.exts.buildViewModel
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog

class MainActivity : AppCompatActivity() {

    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val layoutManager = LinearLayoutManager(this)
    private val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>() }
    private val dis = LifeDisposable(this)
    private val dialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewSearchResults.adapter = adapter
        recyclerViewSearchResults.layoutManager = layoutManager
    }

    override fun onStart() {
        super.onStart()
        dialog.show()
        dis add viewModel.getAll()
                .subscribe(
                        {
                            adapter.data = it
                            dialog.hide()
                        },
                        {
                            dialog.hide()
                        }
                )
    }


}