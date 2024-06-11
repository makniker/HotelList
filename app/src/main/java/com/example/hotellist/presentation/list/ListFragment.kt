package com.example.hotellist.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotellist.R
import com.example.hotellist.databinding.FragmentListBinding
import com.example.hotellist.presentation.core.FlipperStates
import com.example.hotellist.presentation.core.UiState
import com.example.hotellist.presentation.core.SortByDistance
import com.example.hotellist.presentation.core.SortBySuites
import com.example.hotellist.presentation.core.SortingState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var hotelAdapter: HotelAdapter
    private val viewModel: HotelListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            listLayout.recyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(), RecyclerView.VERTICAL
                )
            )
            hotelAdapter = HotelAdapter { item: HotelListUiModel ->
                navigateWithCurrentData(item)
            }
            listLayout.recyclerView.adapter = hotelAdapter
            val llm = GridLayoutManager(activity, 1)
            listLayout.recyclerView.layoutManager = llm
            ArrayAdapter.createFromResource(
                requireContext(), R.array.sort_array, android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                listLayout.sortSpinner.adapter = adapter
            }
            listLayout.sortOrderIcon.setOnClickListener {
                viewModel.changeOrder()
            }
            listLayout.sortSpinner.onItemSelectedListener = this@ListFragment
            viewModel.fetchHotelList()
            viewModel.hotelsLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is UiState.Failure -> {
                        flipper.displayedChild = FlipperStates.FAILURE_VIEW.ordinal
                        errorLayout.errorDetails.text = result.e.localizedMessage ?: "Unknown error"
                    }

                    is UiState.Loading -> {
                        flipper.displayedChild = FlipperStates.LOADING_VIEW.ordinal
                    }

                    is UiState.Success -> {
                        flipper.displayedChild = FlipperStates.SUCCESS_VIEW.ordinal
                        hotelAdapter.submitList(result.data)
                    }
                }
            }
            viewModel.sortLiveData.observe(viewLifecycleOwner) { result ->
                when (result.stateOfSorting) {
                    SortingState.DEFAULT -> hotelAdapter.restoreDefaultList()
                    SortingState.BY_DISTANCE -> hotelAdapter.setSortStrategy(SortByDistance(result.isAscending))
                    SortingState.BY_SUITES -> hotelAdapter.setSortStrategy(SortBySuites(result.isAscending))
                }
            }

        }
    }

    private fun navigateWithCurrentData(hotelData: HotelListUiModel) {
        val id = hotelData.id
        val action = ListFragmentDirections.actionListFragmentToDetailsFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (binding.listLayout.sortSpinner.selectedItemPosition) {
            0 -> viewModel.setSort(SortingState.DEFAULT)
            1 -> viewModel.setSort(SortingState.BY_DISTANCE)
            2 -> viewModel.setSort(SortingState.BY_SUITES)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) =
        binding.listLayout.sortSpinner.setSelection(0)

}