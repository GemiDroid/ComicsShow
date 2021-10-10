package com.gemidroid.comicshow.presentation.ui.view

import AuthenticationState
import Utilities.COMIC_ITEM
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gemidroid.comicshow.R
import com.gemidroid.comicshow.base.BaseFragment
import com.gemidroid.comicshow.data.models.Comic
import com.gemidroid.comicshow.data.models.NetworkError
import com.gemidroid.comicshow.presentation.ui.adapter.ComicsAdapter
import com.gemidroid.comicshow.presentation.ui.showShortSnackBar
import com.gemidroid.comicshow.presentation.viewmodel.ComicsViewModel
import kotlinx.android.synthetic.main.comic_list_fragment.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class ComicsListFragment : BaseFragment() {

    private val comicViewModel by viewModel<ComicsViewModel>()

        override fun resId() = R.layout.comic_list_fragment

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Adding lifeCycle to viewModel observing to respect view lifeCycle...
            lifecycleScope.launchWhenStarted {
                comicViewModel._getAllComics.collect {
                    when (it) {
                        is AuthenticationState.Loading -> {
                            // Observe on loading state...
                            if (it.isLoading) {
                                (requireActivity() as ComicsActivity).showLoading()
                            } else {
                                (requireActivity() as ComicsActivity).hideLoading()
                            }
                        }
                        is AuthenticationState.Error -> {
                            when (it.error) {
                                // handling error state; GeneralError, ServerError, Internet, ParsingError, etc...
                                is NetworkError.GeneralError -> {
                                    edt_search.showShortSnackBar(it.error.error)
                                }
                                is NetworkError.ServerError -> {
                                    edt_search.showShortSnackBar(it.error.error)
                                }
                                is NetworkError.Internet -> {
                                    edt_search.showShortSnackBar(getString(R.string.no_internet_issue))
                                }
                                is NetworkError.ParsingError -> {
                                    edt_search.showShortSnackBar(it.error.error)
                                }
                            }
                        }
                        is AuthenticationState.Success -> {
                            // Getting generic response depend on any service, then casting it...
                            val comics = it.response as Comic
                            val list = mutableListOf<Comic>()
                            list.add(comics)
                            rec_comics.adapter = ComicsAdapter(list) {
                                // Navigate to comic details...
                                findNavController().navigate(
                                    R.id.comicDetailsFragment,
                                    bundleOf(COMIC_ITEM to it)
                                )
                            }
                        }
                        else -> {
                        }
                    }
                }
            }

            // Adding onSearch bar changes....
            edt_search.addTextChangedListener {
                it?.let {
                    comicViewModel.getComicById(it.toString())
                }
            }
        }
}