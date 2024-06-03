package com.d121211017.gamedealsnew.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.d121211017.gamedealsnew.R
import com.d121211017.gamedealsnew.data.entity.Images
import com.d121211017.gamedealsnew.data.entity.StoresItem
import com.d121211017.gamedealsnew.databinding.FragmentFilterBinding
import com.d121211017.gamedealsnew.ui.custom_components.CustomArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterFragment : BottomSheetDialogFragment() {
    private var _binding : FragmentFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sortBy = resources.getStringArray(R.array.sort_by)

        val storesList = listOf(
            StoresItem(
                storeID = "1",
                storeName = "Steam",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/0.png",
                    logo = "/img/stores/logos/0.png",
                    icon = "/img/stores/icons/0.png"
                )
            ),
            StoresItem(
                storeID = "2",
                storeName = "GamersGate",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/1.png",
                    logo = "/img/stores/logos/1.png",
                    icon = "/img/stores/icons/1.png"
                )
            ),
            StoresItem(
                storeID = "3",
                storeName = "GreenManGaming",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/2.png",
                    logo = "/img/stores/logos/2.png",
                    icon = "/img/stores/icons/2.png"
                )
            ),
            StoresItem(
                storeID = "4",
                storeName = "Amazon",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/3.png",
                    logo = "/img/stores/logos/3.png",
                    icon = "/img/stores/icons/3.png"
                )
            ),
            StoresItem(
                storeID = "5",
                storeName = "GameStop",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/4.png",
                    logo = "/img/stores/logos/4.png",
                    icon = "/img/stores/icons/4.png"
                )
            ),
            StoresItem(
                storeID = "6",
                storeName = "Direct2Drive",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/5.png",
                    logo = "/img/stores/logos/5.png",
                    icon = "/img/stores/icons/5.png"
                )
            ),
            StoresItem(
                storeID = "7",
                storeName = "GOG",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/6.png",
                    logo = "/img/stores/logos/6.png",
                    icon = "/img/stores/icons/6.png"
                )
            ),
            StoresItem(
                storeID = "8",
                storeName = "Origin",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/7.png",
                    logo = "/img/stores/logos/7.png",
                    icon = "/img/stores/icons/7.png"
                )
            ),
            StoresItem(
                storeID = "9",
                storeName = "Get Games",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/8.png",
                    logo = "/img/stores/logos/8.png",
                    icon = "/img/stores/icons/8.png"
                )
            ),
            StoresItem(
                storeID = "10",
                storeName = "Shiny Loot",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/9.png",
                    logo = "/img/stores/logos/9.png",
                    icon = "/img/stores/icons/9.png"
                )
            ),
            StoresItem(
                storeID = "11",
                storeName = "Humble Store",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/10.png",
                    logo = "/img/stores/logos/10.png",
                    icon = "/img/stores/icons/10.png"
                )
            ),
            StoresItem(
                storeID = "12",
                storeName = "Desura",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/11.png",
                    logo = "/img/stores/logos/11.png",
                    icon = "/img/stores/icons/11.png"
                )
            ),
            StoresItem(
                storeID = "13",
                storeName = "Uplay",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/12.png",
                    logo = "/img/stores/logos/12.png",
                    icon = "/img/stores/icons/12.png"
                )
            ),
            StoresItem(
                storeID = "14",
                storeName = "IndieGameStand",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/13.png",
                    logo = "/img/stores/logos/13.png",
                    icon = "/img/stores/icons/13.png"
                )
            ),
            StoresItem(
                storeID = "15",
                storeName = "Fanatical",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/14.png",
                    logo = "/img/stores/logos/14.png",
                    icon = "/img/stores/icons/14.png"
                )
            ),
            StoresItem(
                storeID = "16",
                storeName = "Gamesrocket",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/15.png",
                    logo = "/img/stores/logos/15.png",
                    icon = "/img/stores/icons/15.png"
                )
            ),
            StoresItem(
                storeID = "17",
                storeName = "Games Republic",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/16.png",
                    logo = "/img/stores/logos/16.png",
                    icon = "/img/stores/icons/16.png"
                )
            ),
            StoresItem(
                storeID = "18",
                storeName = "SilaGames",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/17.png",
                    logo = "/img/stores/logos/17.png",
                    icon = "/img/stores/icons/17.png"
                )
            ),
            StoresItem(
                storeID = "19",
                storeName = "Playfield",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/18.png",
                    logo = "/img/stores/logos/18.png",
                    icon = "/img/stores/icons/18.png"
                )
            ),
            StoresItem(
                storeID = "20",
                storeName = "ImperialGames",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/19.png",
                    logo = "/img/stores/logos/19.png",
                    icon = "/img/stores/icons/19.png"
                )
            ),
            StoresItem(
                storeID = "21",
                storeName = "WinGameStore",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/20.png",
                    logo = "/img/stores/logos/20.png",
                    icon = "/img/stores/icons/20.png"
                )
            ),
            StoresItem(
                storeID = "22",
                storeName = "FunStockDigital",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/21.png",
                    logo = "/img/stores/logos/21.png",
                    icon = "/img/stores/icons/21.png"
                )
            ),
            StoresItem(
                storeID = "23",
                storeName = "GameBillet",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/22.png",
                    logo = "/img/stores/logos/22.png",
                    icon = "/img/stores/icons/22.png"
                )
            ),
            StoresItem(
                storeID = "24",
                storeName = "Voidu",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/23.png",
                    logo = "/img/stores/logos/23.png",
                    icon = "/img/stores/icons/23.png"
                )
            ),
            StoresItem(
                storeID = "25",
                storeName = "Epic Games Store",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/24.png",
                    logo = "/img/stores/logos/24.png",
                    icon = "/img/stores/icons/24.png"
                )
            ),
            StoresItem(
                storeID = "26",
                storeName = "Razer Game Store",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/25.png",
                    logo = "/img/stores/logos/25.png",
                    icon = "/img/stores/icons/25.png"
                )
            ),
            StoresItem(
                storeID = "27",
                storeName = "Gamesplanet",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/26.png",
                    logo = "/img/stores/logos/26.png",
                    icon = "/img/stores/icons/26.png"
                )
            ),
            StoresItem(
                storeID = "28",
                storeName = "Gamesload",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/27.png",
                    logo = "/img/stores/logos/27.png",
                    icon = "/img/stores/icons/27.png"
                )
            ),
            StoresItem(
                storeID = "29",
                storeName = "2Game",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/28.png",
                    logo = "/img/stores/logos/28.png",
                    icon = "/img/stores/icons/28.png"
                )
            ),
            StoresItem(
                storeID = "30",
                storeName = "IndieGala",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/29.png",
                    logo = "/img/stores/logos/29.png",
                    icon = "/img/stores/icons/29.png"
                )
            ),
            StoresItem(
                storeID = "31",
                storeName = "Blizzard Shop",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/30.png",
                    logo = "/img/stores/logos/30.png",
                    icon = "/img/stores/icons/30.png"
                )
            ),
            StoresItem(
                storeID = "32",
                storeName = "AllYouPlay",
                isActive = 0,
                images = Images(
                    banner = "/img/stores/banners/31.png",
                    logo = "/img/stores/logos/31.png",
                    icon = "/img/stores/icons/31.png"
                )
            ),
            StoresItem(
                storeID = "33",
                storeName = "DLGamer",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/32.png",
                    logo = "/img/stores/logos/32.png",
                    icon = "/img/stores/icons/32.png"
                )
            ),
            StoresItem(
                storeID = "34",
                storeName = "Noctre",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/33.png",
                    logo = "/img/stores/logos/33.png",
                    icon = "/img/stores/icons/33.png"
                )
            ),
            StoresItem(
                storeID = "35",
                storeName = "DreamGame",
                isActive = 1,
                images = Images(
                    banner = "/img/stores/banners/34.png",
                    logo = "/img/stores/logos/34.png",
                    icon = "/img/stores/icons/34.png"
                )
            )
        )

        binding.apply {
            gamestoreList.adapter = CustomArrayAdapter(
                requireContext(),
                storesList
            )
            sortSpinner.adapter = ArrayAdapter(
                requireContext(),
                androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item,
                sortBy)
            priceSlider.addOnChangeListener{_, _, _ ->
                val values = priceSlider.values
                priceRangeValue.text = getString(R.string.price_range_value, values[0].toString(), values[1].toString())

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}