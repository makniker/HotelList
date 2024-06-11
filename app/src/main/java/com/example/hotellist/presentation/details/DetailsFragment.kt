package com.example.hotellist.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hotellist.R
import com.example.hotellist.databinding.FragmentDetailsBinding
import com.example.hotellist.presentation.core.CropOnePixelTransformation
import com.example.hotellist.presentation.core.FlipperStates
import com.example.hotellist.presentation.core.UiState
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.fetchDetails(args.idArg)
            viewModel.hotelLiveData.observe(viewLifecycleOwner) { result ->
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
                        mapSuccessState(result.data)
                    }
                }
            }
        }
    }

    private fun mapSuccessState(item: HotelDetailsUiModel) {
        with(binding.detailsLayout) {
            tvHotelName.text = item.name
            Glide.with(this@DetailsFragment).load(item.image)
                .placeholder(R.drawable.baseline_home_24).transform(CropOnePixelTransformation())
                .error(R.drawable.baseline_error_outline_24)
                .into(ivHotelImage)
            tvHotelAddress.text = item.address
            tvHotelDistance.text = item.distanceString
            tvHotelStars.text = item.starsString
            tvHotelSuites.text = item.suites
            btnShowOnMap.setOnClickListener {
                val uri = String.format(Locale.ENGLISH, "geo:%f,%f", item.lat, item.lon)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                requireContext().startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}