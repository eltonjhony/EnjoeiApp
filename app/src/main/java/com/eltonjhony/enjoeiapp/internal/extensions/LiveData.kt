package com.eltonjhony.enjoeiapp.internal.extensions

import androidx.lifecycle.MutableLiveData
import com.eltonjhony.enjoeiapp.presentation.common.Resource
import com.eltonjhony.enjoeiapp.presentation.common.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setFailure(throwable: Throwable? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, throwable))