package com.example.samuraicase

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.samuraicase.core.base.BaseViewModel
import com.example.samuraicase.network.service.ApiClient
import com.example.samuraicase.network.service.pokeurllist.IPokemonService
import com.example.samuraicase.network.service.pokeurllist.Pokemon
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : BaseViewModel(application) {
    private val pokemonService: IPokemonService =
        ApiClient.getClient().create(IPokemonService::class.java)
    private val disposable = CompositeDisposable()
    var pokemonList = MutableLiveData<List<Pokemon>>()
    var isLoading = MutableLiveData<Boolean>()
    private val errMsg = MutableLiveData<Boolean>()
    private var list = ArrayList<Pokemon>()

    private fun getData(id: Int) {
        disposable.add(
            pokemonService.getPokemonById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Pokemon>() {
                    override fun onSuccess(t: Pokemon) {
                        println(t.sprites.other.home.front_default.toString())
                        addPokemon(t)
                        isLoading.value = false
                        errMsg.value = false
                    }

                    override fun onError(e: Throwable) {
                        errMsg.value = true
                        isLoading.value = false
                    }
                })
        )
    }

    fun addPokemon(t: Pokemon) {
        launch {
            list.add(t)
            pokemonList.value = list
            isLoading.value = false
        }
    }


    fun getPokeList(piece: IntRange) {
        list.clear()
        isLoading.value = true
        for (x in piece) {
            getData(x)
        }
    }


}