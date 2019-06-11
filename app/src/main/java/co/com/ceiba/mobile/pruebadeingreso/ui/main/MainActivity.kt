package co.com.ceiba.mobile.pruebadeingreso.ui.main

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable
import co.com.ceiba.mobile.pruebadeingreso.util.buildViewModel
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.ui.post.PostActivity
import co.com.ceiba.mobile.pruebadeingreso.util.gone
import co.com.ceiba.mobile.pruebadeingreso.util.visible
import com.jakewharton.rxbinding2.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val adapter: UserAdapter by lazy { UserAdapter() }
    private val layoutManager = LinearLayoutManager(this)
    private val viewModel: MainViewModel by lazy { buildViewModel<MainViewModel>() }
    private val dis = LifeDisposable(this)
    private lateinit var dialog: ProgressDialog

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewSearchResults.adapter = adapter
        recyclerViewSearchResults.layoutManager = layoutManager
        dialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))

        adapter.showPost
                .subscribe {
                    startActivity<PostActivity>(EXTRA_USER to it)
                }

    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()
        dialog.show()

        editTextSearch.textChanges()
                .skip(1)
                .subscribe {
                    searchUser(it.toString())
                }

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
                            toast(getString(R.string.generic_error))
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
                                toast(getString(R.string.generic_error))
                            }
                    )

    private fun searchUser(query: String) =
            dis add viewModel.searchUser("%$query%")
                    .subscribe {
                        adapter.data = it
                        if (it.isEmpty()) adapter.data = listOf(0)
                    }

    companion object {
        const val EXTRA_USER = "user"
    }

}