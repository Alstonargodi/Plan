package com.example.wetterbericht.viewmodel.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wetterbericht.repo.api.mainrepo

class Vmfactory(val repo: mainrepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Mainviewmodel(repo) as T
    }

}