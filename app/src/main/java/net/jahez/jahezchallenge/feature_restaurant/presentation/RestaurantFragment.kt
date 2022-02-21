package net.jahez.jahezchallenge.feature_restaurant.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import dagger.hilt.android.AndroidEntryPoint
import net.jahez.jahezchallenge.R
import net.jahez.jahezchallenge.core.util.Resource
import net.jahez.jahezchallenge.databinding.FragmentRestaurantBinding
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Language
import net.jahez.jahezchallenge.feature_restaurant.domain.model.Restaurant
import net.jahez.jahezchallenge.feature_restaurant.domain.model.RestaurantOrder
import javax.inject.Inject


@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private val viewModel: RestaurantViewModel by viewModels()
    private var binding: FragmentRestaurantBinding? = null
    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    private fun initialization(){
        setupRV()
        initObservation()
    }

    private fun setupRV() {
     binding!!.restaurantRecyclerView.adapter = restaurantAdapter
    }

    private fun initObservation(){
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner,restaurantsObserver)
        viewModel.sortedRestaurantLiveData.observe(viewLifecycleOwner,sortedRestaurantObserver)
    }

    private var restaurantsObserver : Observer<Resource<List<Restaurant>>> = Observer {
        when (it.status) {
            Resource.Status.LOADING -> {
                binding!!.progressBar.visibility = View.VISIBLE
            }
            Resource.Status.SUCCESS -> {
                binding!!.progressBar.visibility = View.GONE
                restaurantAdapter.submitList(it.data!!)
            }
            Resource.Status.ERROR -> {
                binding!!.progressBar.visibility = View.GONE
                restaurantAdapter.submitList(it.data)
            }
        }
    }

    private var sortedRestaurantObserver : Observer<List<Restaurant>> = Observer {
        restaurantAdapter.submitList(it)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_sort_by_distance -> {
                viewModel.sortedRestaurantsLiveData(RestaurantOrder.Distance)
            }
            R.id.action_sort_by_rating -> {
                viewModel.sortedRestaurantsLiveData(RestaurantOrder.Rating)
            }
            R.id.action_sort_by_offer -> {
                viewModel.sortedRestaurantsLiveData(RestaurantOrder.Offers)
            }
            R.id.action_change_language -> {
                showLanguagesDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLanguagesDialog(){
        val items = arrayOf(getString(R.string.arabic), getString(R.string.english))
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.change_language))
            .setIcon(R.drawable.ic_language_black)
            .setSingleChoiceItems(items, 0,null)
            .setPositiveButton(getString(R.string.done)) { dialog, _ ->
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                dialog.dismiss()
                changeLanguage(selectedPosition)
            }
            .show()
    }

    private fun changeLanguage(selectionId:Int){
        if (selectionId == 0){
            (requireActivity() as LocalizationActivity).setLanguage(Language.ARABIC.toString())
        }else if (selectionId == 1){
            (requireActivity() as LocalizationActivity).setLanguage(Language.ENGLISH.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}