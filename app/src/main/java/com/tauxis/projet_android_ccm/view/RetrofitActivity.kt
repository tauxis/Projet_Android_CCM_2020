package com.tauxis.projet_android_ccm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tauxis.projet_android_ccm.R
import com.tauxis.projet_android_ccm.model.TrumpQuote
import com.tauxis.projet_android_ccm.view.adapter.TrumpQuoteAdapter
import com.tauxis.projet_android_ccm.viewmodel.TrumpViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitActivity : AppCompatActivity() {

    private lateinit var mAdapter: TrumpQuoteAdapter
    private lateinit var mViewModel: TrumpViewModel
    private var mObserverTrumpQuote = Observer<List<TrumpQuote>> {
        updateRecyclerView(ArrayList(it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        mViewModel = ViewModelProvider(this)[TrumpViewModel::class.java]
        mAdapter = TrumpQuoteAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.adapter = mAdapter

        buttonAddItem.setOnClickListener { addTrumpQuote() }
        buttonDeleteItem.setOnClickListener { deleteTrumpQuote() }
    }
    override fun onStart() {
        super.onStart()
        mViewModel.mTrumpQuoteLiveData.observe(this, mObserverTrumpQuote)
    }

    override fun onStop() {
        mViewModel.mTrumpQuoteLiveData.removeObserver(mObserverTrumpQuote)
        super.onStop()
    }

    private fun updateRecyclerView(newList: ArrayList<TrumpQuote>) {
        mAdapter.rebuild(newList)
    }

    private fun addTrumpQuote() {
        mViewModel.fetchNewQuote()
    }

    private fun deleteTrumpQuote() {
        mViewModel.deleteAllQuote()
    }


}