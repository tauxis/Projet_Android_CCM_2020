package com.tauxis.projet_android_ccm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.model.ObjectDataSample
import com.tauxis.projet_android_ccm.view.adapter.CustomAdapter
import com.tauxis.projet_android_ccm.viewmodel.CustomViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity(){

    private lateinit var adapter: CustomAdapter
    private lateinit var viewModel : CustomViewModel
    private var observerMyData = Observer<List<ObjectDataSample>> {
        updateUi(ArrayList(it))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        viewModel = ViewModelProvider(this)[CustomViewModel::class.java]

        adapter= CustomAdapter(this)
        myRecyclerView.layoutManager= GridLayoutManager(this,2)
        myRecyclerView.adapter=adapter

        buttonAddItem.setOnClickListener { addValue() }
        buttonDeleteAll.setOnClickListener { deleteAll() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.myDataMutableLiveData.observe(this, observerMyData)

    }

    override fun onStop() {
        viewModel.myDataMutableLiveData.removeObserver(observerMyData)
        super.onStop()
    }

    private fun updateUi(data : ArrayList<ObjectDataSample>) {
        adapter.rebuild(data)
    }

    private fun addValue() {
        val random = Random.nextInt(0,1000)
        viewModel.insertData("https://picsum.photos/id/$random/200", "Image N°$random")
    }

    private fun deleteAll() {
        viewModel.deleteData()
    }

}