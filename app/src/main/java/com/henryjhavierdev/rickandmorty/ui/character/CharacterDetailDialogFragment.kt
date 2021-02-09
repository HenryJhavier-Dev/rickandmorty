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
import com.henryjhavierdev.data.CharacterRepository
import com.henryjhavierdev.data.EpisodeRepository
import com.henryjhavierdev.data.datasource.CharacterRemoteDataSource
import com.henryjhavierdev.data.datasource.LocalCharacterDataSource
import com.henryjhavierdev.domain.Character
import com.henryjhavierdev.rickandmorty.R
import com.henryjhavierdev.rickandmorty.adapters.EpisodeListAdapter
import com.henryjhavierdev.rickandmorty.database.CharacterDataBase
import com.henryjhavierdev.rickandmorty.database.CharacterLocalDataSourceImpl
import com.henryjhavierdev.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.henryjhavierdev.rickandmorty.dataservice.*
import com.henryjhavierdev.rickandmorty.presentation.Event
import com.henryjhavierdev.usecases.GetEpisodeFromCharacterUseCase
import com.henryjhavierdev.usecases.GetFavoriteCharacterStatusUseCase
import com.henryjhavierdev.usecases.UpdateFavoriteCharacterStatusUseCase
import com.henryjhavierdev.rickandmorty.utils.URL_BASE
import com.henryjhavierdev.rickandmorty.utils.bindCircularImageUrl
import com.henryjhavierdev.rickandmorty.utils.getViewModel
import com.henryjhavierdev.rickandmorty.utils.showLongToast
import com.henryjhavierdev.rickandmorty.viewmodel.CharacterDetailDialogFragmentViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*

class CharacterDetailDialogFragment : DialogFragment() {

    private val args: CharacterDetailDialogFragmentArgs by navArgs()

    private lateinit var episodeListAdapter: EpisodeListAdapter
    private lateinit var binding: FragmentCharacterDetailBinding

    //region Fields

    private val episodeRequest: EpisodeRequest by lazy {
        EpisodeRequest(URL_BASE)
    }

    //region Repository
    private val characterRequest: CharacterRequest by lazy {
        CharacterRequest(URL_BASE)
    }
    private val characterRemoteDataSource: CharacterRemoteDataSource by lazy {
        CharacterRemoteDataSourceImpl(characterRequest)
    }

    private val episodeRemoteDataSource: EpisodeRemoteDataSourceImpl by lazy {
        EpisodeRemoteDataSourceImpl(episodeRequest)
    }


    private val localCharacterDataSource: LocalCharacterDataSource by lazy {
        CharacterLocalDataSourceImpl(
            CharacterDataBase.getInstanceDataBase(requireActivity().applicationContext))
    }

    private val characterRepository: CharacterRepository by lazy {
        CharacterRepository(characterRemoteDataSource, localCharacterDataSource)
    }

    private val episodeRepository: EpisodeRepository by lazy {
        EpisodeRepository(episodeRemoteDataSource)
    }

    //endregion
    private val getFavoriteCharacterStatusUseCase: GetFavoriteCharacterStatusUseCase by lazy {
        GetFavoriteCharacterStatusUseCase(characterRepository)
    }

    private val updateFavoriteCharacterStatusUseCase: UpdateFavoriteCharacterStatusUseCase by lazy {
        UpdateFavoriteCharacterStatusUseCase(characterRepository)
    }

    private val getEpisodeFromCharacterUseCase: GetEpisodeFromCharacterUseCase by lazy {
        GetEpisodeFromCharacterUseCase(episodeRepository)
    }

    private val characterDetailViewModel: CharacterDetailDialogFragmentViewModel by lazy {
        getViewModel {
            CharacterDetailDialogFragmentViewModel(
                args.character.toCharacterDomain(),
                getEpisodeFromCharacterUseCase,
                getFavoriteCharacterStatusUseCase,
                updateFavoriteCharacterStatusUseCase
            )
        }
    }

    //endregion


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_character_detail,container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Listener
        episodeListAdapter = EpisodeListAdapter { episode ->
            activity?.showLongToast("Episode -> $episode")
        }

        rvEpisodeList.adapter = episodeListAdapter

        characterFavorite.setOnClickListener {
            characterDetailViewModel.onUpdateFavoriteCharacterStatus()
        }

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
            placeholder = R.drawable.ic_camera_alt_black,
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
                R.drawable.ic_favorite_border
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
                CharacterDetailDialogFragmentViewModel.CharacterDetailNavigation.CloseActivity -> {
                    activity?.showLongToast("No hay data relacionada con este character")
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