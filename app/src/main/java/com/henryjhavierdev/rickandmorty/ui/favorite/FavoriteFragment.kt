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
import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.FavoriteListAdapter
import com.henryjhavierdev.rickandmorty.adapters.FavoriteListener
import com.henryjhavierdev.databasemanager.CharacterDataBase
import com.henryjhavierdev.databasemanager.CharacterLocalDataSourceImpl
import com.henryjhavierdev.rickandmorty.databinding.FragmentFavoriteBinding
import com.henryjhavierdev.rickandmorty.dataservice.CharacterRemoteDataSourceImpl
import com.henryjhavierdev.rickandmorty.dataservice.CharacterRequest
import com.henryjhavierdev.rickandmorty.parcelables.CharacterResultRs
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.usecases.GetAllFavoriteCharactersUseCase
import com.henryjhavierdev.rickandmorty.utils.URL_BASE
import com.henryjhavierdev.rickandmorty.utils.setItemDecorationSpacing
import com.henryjhavierdev.rickandmorty.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment(), FavoriteListener {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favoriteListAdapter: FavoriteListAdapter

    //region Repository
    private val characterRequest: CharacterRequest by lazy {
        CharacterRequest(URL_BASE)
    }
    private val characterRemoteDataSource: CharacterRemoteDataSource by lazy {
        CharacterRemoteDataSourceImpl(characterRequest)
    }

    private val localCharacterDataSource: LocalCharacterDataSource by lazy {
        CharacterLocalDataSourceImpl(
            CharacterDataBase.getInstanceDataBase(requireActivity().applicationContext))
    }

    private val characterRepository: CharacterRepository by lazy {
        CharacterRepository(characterRemoteDataSource, localCharacterDataSource)
    }

    //endregion

    private val getAllFavoriteCharactersUseCase: GetAllFavoriteCharactersUseCase by lazy {
        GetAllFavoriteCharactersUseCase(characterRepository)
    }

    private val favoriteViewModel: FavoriteViewModel by lazy {
            FavoriteViewModel(getAllFavoriteCharactersUseCase)
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
            setItemDecorationSpacing(resources.getDimension(R.dimen.cardv_view_margin))
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

    override fun onFavoriteListener(character: CharacterResultRs) {
        val bundle = bundleOf("character" to character)
        findNavController().navigate(R.id.characterDetailFragment,bundle)       }

}