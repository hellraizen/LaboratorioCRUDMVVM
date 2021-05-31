package br.com.douglasmotta.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.douglasmotta.mysubscribers.R
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.douglasmotta.mysubscribers.data.db.AppDatabase
import br.com.douglasmotta.mysubscribers.data.db.dao.SubscriberDAO
import br.com.douglasmotta.mysubscribers.extension.navigateWithAnimations
import br.com.douglasmotta.mysubscribers.repository.DatabaseDataSource
import br.com.douglasmotta.mysubscribers.repository.SubscriberRepository
import kotlinx.android.synthetic.main.subscriber_list_fragment.*

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configureViewListeners()

    }

    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner){allSubscribers ->
            val subscriberListAdapter = SubscriberListAdapter(allSubscribers).apply {
                onItemClick = {subscriber ->
                    val direction =SubscriberListFragmentDirections
                        .actionSubscriberListFragmentToSubscriberFragment(subscriber)
                    findNavController().navigateWithAnimations(direction)
                }
            }

            with(recycler_subscribers) {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscribers()
    }

    private fun configureViewListeners(){
        fabAddSubscriber.setOnClickListener{
            findNavController().navigateWithAnimations(
                R.id.action_subscriberListFragment_to_subscriberFragment
            )
        }
    }

}