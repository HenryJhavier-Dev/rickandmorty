package com.henryjhavierdev.rickandmorty.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henryjhavierdev.rickandmorty.MainApplication
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.CharacterGridAdapter
import com.henryjhavierdev.rickandmorty.adapters.CharacterListener
import com.henryjhavierdev.rickandmorty.databinding.FragmentHomeBinding
import com.henryjhavierdev.rickandmorty.di.CharacterListComponent
import com.henryjhavierdev.rickandmorty.di.CharacterListModule
import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultParcelable
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.utils.getViewModel
import com.henryjhavierdev.rickandmorty.utils.setItemDecorationSpacing
import com.henryjhavierdev.rickandmorty.utils.showLongToast
import com.henryjhavierdev.rickandmorty.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), CharacterListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var characterGridAdapter: CharacterGridAdapter

    private lateinit var characterListComponent: CharacterListComponent

    private val homeViewModel: HomeViewModel by lazy {
        getViewModel { characterListComponent.characterListViewModel}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = context?.applicationContext as MainApplication

        characterListComponent = context.component.inject(CharacterListModule())

    }
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState:
        Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container, false)

        binding.lifecycleOwner = this

        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterGridAdapter = CharacterGridAdapter(this)

        characterGridAdapter.setHasStableIds(true)

        rv_home_character.run{
            addOnScrollListener(onScrollListener)
            setItemDecorationSpacing(resources.getDimension(R.dimen.card_view_margin))

            adapter = characterGridAdapter

        }

        srw_character_list.setOnRefreshListener {
            homeViewModel.onRetryGetAllCharacter(rv_home_character.adapter?.itemCount ?: 0)
        }

        homeViewModel.events.observe(viewLifecycleOwner, Observer(this::validateEvents))

        homeViewModel.onGetAllCharacters()
    }

    private fun validateEvents(event: Event<HomeViewModel.CharacterListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when(navigation) {
                is HomeViewModel.CharacterListNavigation.ShowCharacterError -> navigation.run {
                    context?.showLongToast("Error -> ${error.message}")
                }
                is HomeViewModel.CharacterListNavigation.ShowCharacterList -> navigation.run {
                    characterGridAdapter.addData(characterList)
                }
                HomeViewModel.CharacterListNavigation.HideLoading -> {
                    srw_character_list.isRefreshing = false
                }
                HomeViewModel.CharacterListNavigation.ShowLoading -> {
                    srw_character_list.isRefreshing = true
                }
            }
        }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                homeViewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
            }
        }
    }


    override fun openCharacterDetail(character: CharacterResultParcelable) {
        val bundle = bundleOf("character" to character)
        findNavController().navigate(R.id.characterDetailFragment,bundle)    }

}