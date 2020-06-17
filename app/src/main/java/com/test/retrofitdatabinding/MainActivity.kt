package com.test.retrofitdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.retrofitdatabinding.adapter.UserListAdapter
import com.test.retrofitdatabinding.model.Data
import com.test.retrofitdatabinding.model.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private var usersAdapter:UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var notesObservable: Observable<Data> =
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        usersAdapter = UserListAdapter(arrayListOf(),applicationContext)
        usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()

    }


    fun observeViewModel(){

        viewModel.users.observe(this, Observer { countries ->
            countries?.let {
                usersList.visibility = View.VISIBLE
                usersAdapter?.updateUsers(it as ArrayList<Data>)
            }
        })
        viewModel.userLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })
        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    usersList.visibility = View.GONE
                }
            }
        })
    }
}