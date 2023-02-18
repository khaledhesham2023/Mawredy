package com.udacity.mawardy.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.udacity.mawardy.loading.LoadingDialog
import com.udacity.mawardy.repo.SharedPreferencesRepo

abstract class BaseFragment<VB:ViewDataBinding> : Fragment() {

    protected lateinit var viewBinding:VB

    abstract val layout:Int

    protected lateinit var sharedPreferencesRepo: SharedPreferencesRepo

    protected lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater,layout,container,false)
        sharedPreferencesRepo = SharedPreferencesRepo(requireContext())
        loadingDialog = LoadingDialog(requireContext())
        return viewBinding.root
    }
}