package com.henryjhavierdev.rickandmorty.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.imagemanager.bindCircularImageUrl
import com.henryjhavierdev.rickandmorty.MainApplication
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.EpisodeListAdapter
import com.henryjhavierdev.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.henryjhavierdev.rickandmorty.di.CharacterDetailComponent
import com.henryjhavierdev.rickandmorty.di.CharacterDetailModule
import com.henryjhavierdev.rickandmorty.parcelables.toCharacterDomain
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.rickandmorty.utils.getViewModel
import com.henryjhavierdev.rickandmorty.utils.showLongToast
import com.henryjhavierdev.rickandmorty.viewmodel.CharacterDetailDialogFragmentViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailDialogFragment : DialogFragment() {

    private val args: CharacterDetailDialogFragmentArgs by navArgs()

    private lateinit var episodeListAdapter: EpisodeListAdapter
    private lateinit var binding: FragmentCharacterDetailBinding
    private lateinit var characterDetailComponent: CharacterDetailComponent

    private val characterDetailViewModel: CharacterDetailDialogFragmentViewModel by lazy {
        getViewModel { characterDetailComponent.characterDetailViewModel }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)

        val context = context?.applicationContext as MainApplication

        characterDetailComponent = context.component.inject(
            CharacterDetailModule(args.character.toCharacterDomain())
        )


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_character_detail,container, false)
        binding.viewModelCharacterDetail = characterDetailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Listener
        //Clicked rv  and show episode info
        episodeListAdapter = EpisodeListAdapter { episode ->
            //todo añadir funcionalidad para detalles de espisodios
           // activity?.showLongToast("Episode -> $episode")
        }

        rvEpisodeList.adapter = episodeListAdapter

        characterDetailViewModel.characterValues.observe(this, Observer(this::loadCharacter))

        //characterDetailViewModel.isFavorite.observe(this, Observer(this::updateFavoriteIcon))
        characterDetailViewModel.isFavorite.observe(this, Observer {
            updateFavoriteIcon(it)
        })

        characterDetailViewModel.events.observe(this, Observer(this::validateEvents))

        characterDetailViewModel.onCharacterValidation()
    }

    //region Private Methods

    private fun loadCharacter(character: Character){
        binding.characterImage.bindCircularImageUrl(
            url = character.image,
            placeholder = R.drawable.ic_downloading_24,
            errorPlaceholder = R.drawable.ic_broken_image_black
        )
        binding.characterDataName = character.name
        binding.characterDataStatus = character.status
        binding.characterDataSpecies = character.species
        binding.characterDataGender = character.gender
        binding.characterDataOriginName = character.origin?.name
        binding.characterDataLocationName = character.location?.name
    }

    private fun updateFavoriteIcon(isFavorite: Boolean?){
        characterFavorite.setImageResource(
            if (isFavorite != null && isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_not_favorite
            }
        )
    }

    private fun validateEvents(event: Event<CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.ShowEpisodeError -> navigation.run {
                    activity?.showLongToast("Error -> ${error.message}")
                }
                is CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.ShowEpisodeList -> navigation.run {
                    episodeListAdapter.updateData(episodeList)
                }
                CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.CloseApp -> {
                    activity?.onBackPressed()
                }
                CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.CloseActivity -> {
                    activity?.showLongToast(getString(R.string.em_get_character))
                    activity?.finish()
                }
                CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.HideEpisodeListLoading -> {
                    episodeProgressBar.isVisible = false
                }
                CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.ShowEpisodeListLoading -> {
                    episodeProgressBar.isVisible = true
                }
            }
        }
    }


}