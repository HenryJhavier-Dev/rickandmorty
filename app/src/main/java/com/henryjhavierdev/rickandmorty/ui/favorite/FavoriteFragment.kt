package com.henryjhavierdev.rickandmorty.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.henryjhavierdev.rickandmorty.MainApplication
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.FavoriteListAdapter
import com.henryjhavierdev.rickandmorty.adapters.FavoriteListener
import com.henryjhavierdev.rickandmorty.databinding.FragmentFavoriteBinding
import com.henryjhavierdev.rickandmorty.di.FavoriteListComponent
import com.henryjhavierdev.rickandmorty.di.FavoriteListModule
import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultParcelable
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.utils.getViewModel
import com.henryjhavierdev.rickandmorty.utils.setItemDecorationSpacing
import com.henryjhavierdev.rickandmorty.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment(), FavoriteListener {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private lateinit var favoriteListComponent: FavoriteListComponent

    private val favoriteViewModel: FavoriteViewModel by lazy {
        getViewModel { favoriteListComponent.favoriteListViewModel }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = context?.applicationContext as MainApplication

        favoriteListComponent = context.component.inject(FavoriteListModule())

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorite,container, false)

        binding.lifecycleOwner = this

        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteListAdapter = FavoriteListAdapter(this)

        favoriteListAdapter.setHasStableIds(true)

        rvFavoriteList.run {
            setItemDecorationSpacing(resources.getDimension(R.dimen.card_view_margin))
            adapter = favoriteListAdapter
        }

        //favoriteViewModel.favoriteCharacterList.observe(viewLifecycleOwner,
        //    Observer(favoriteViewModel::onFavoriteCharacterList))
        favoriteViewModel.favoriteCharacterList.observe(viewLifecycleOwner, Observer {
            favoriteViewModel.onFavoriteCharacterList(it)
        })

        favoriteViewModel.events.observe(viewLifecycleOwner,
            Observer(this::validateEvents))


    }
    //endregion

    //region Private Methods

    private fun validateEvents(event: Event<FavoriteViewModel.FavoriteListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is FavoriteViewModel.FavoriteListNavigation.ShowCharacterList
                -> navigation.run {
                    tvEmptyListMessage.isVisible = false
                    favoriteListAdapter.updateData(characterList)
                }
                FavoriteViewModel.FavoriteListNavigation.ShowEmptyListMessage -> {
                    tvEmptyListMessage.isVisible = true
                    favoriteListAdapter.updateData(emptyList())
                }
            }
        }

    }
    //endregion

    override fun onFavoriteListener(character: CharacterResultParcelable) {
        val bundle = bundleOf("character" to character)
        findNavController().navigate(R.id.characterDetailFragment,bundle)       }

}