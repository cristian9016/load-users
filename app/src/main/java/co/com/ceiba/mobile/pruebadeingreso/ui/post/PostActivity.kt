package co.com.ceiba.mobile.pruebadeingreso.ui.post

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem

import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.models.User
import co.com.ceiba.mobile.pruebadeingreso.ui.adapters.PostAdapter
import co.com.ceiba.mobile.pruebadeingreso.ui.main.MainActivity.Companion.EXTRA_USER
import co.com.ceiba.mobile.pruebadeingreso.util.LifeDisposable
import co.com.ceiba.mobile.pruebadeingreso.util.buildViewModel
import kotlinx.android.synthetic.main.activity_post.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.toast

class PostActivity : AppCompatActivity() {

    private val user: User by lazy { intent.getParcelableExtra<User>(EXTRA_USER) }
    private val adapter: PostAdapter by lazy { PostAdapter() }
    private val layoutManager = LinearLayoutManager(this)
    private val viewModel: PostViewModel by lazy { buildViewModel<PostViewModel>() }
    private lateinit var dialog: ProgressDialog
    private val dis = LifeDisposable(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        title = getString(R.string.post_activity_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dialog = indeterminateProgressDialog(getString(R.string.generic_message_progress))
        recyclerViewPostsResults.adapter = adapter
        recyclerViewPostsResults.layoutManager = layoutManager
        name.text = user.name
        phone.text = user.phone
        email.text = user.email
    }

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()
        dialog.show()
        dis add viewModel.getOfflinePost(user.id)
                .subscribe(
                        {
                            if (it.isNotEmpty()) {
                                adapter.data = it
                                dialog.hide()
                            } else getOnlinePost(user.id)
                        },
                        {
                            dialog.hide()
                            toast(getString(R.string.generic_error))                        }
                )
    }

    private fun getOnlinePost(id: Int) =
            dis add viewModel.getOnlinePost(id)
                    .subscribe(
                            {
                                adapter.data = it
                                dialog.hide()
                            },
                            {
                                dialog.hide()
                                toast(getString(R.string.generic_error))                            }
                    )

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}
