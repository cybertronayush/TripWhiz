package `in`.ayushsingh.backendrestaurant.fragment

import `in`.ayushsingh.backendrestaurant.R
import `in`.ayushsingh.backendrestaurant.adapter.ListResturentsInCityAdapter
import `in`.ayushsingh.backendrestaurant.util.myutil
import `in`.ayushsingh.backendrestaurant.viewmodel.MainViewModel
import android.os.Build
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_resturents_in_city_fragment.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.list_resturents_in_city_fragment.view.*


class ListResturentsInCityFragment : androidx.fragment.app.Fragment() {

    private lateinit var viewModel: MainViewModel
    lateinit var adapter: ListResturentsInCityAdapter
    lateinit var rvRestuarantInCityResults: androidx.recyclerview.widget.RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.list_resturents_in_city_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rvRestuarantInCityResults=v.rvRestuarantInCityResults

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // if app is running on post lolipop then transitions are supported
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }


        arguments?.let {
            val args = ListResturentsInCityFragmentArgs.fromBundle(it)

            val city_id = args.cityId

            myutil.showLoadind(v?.context!!)

            viewModel.LvListResturentResult.observe(this, Observer {
                it?.let {
                    if(viewInLandScape==null) {
                        // phone is in portrait mode
                        rvRestuarantInCityResults.layoutManager=LinearLayoutManager(v.context)
                    }
                    else{
                        // phone is in landscape mode
                        rvRestuarantInCityResults.layoutManager=GridLayoutManager(v.context,2)
                    }
                    adapter = ListResturentsInCityAdapter(it)
                    rvRestuarantInCityResults.adapter = adapter
                    myutil.hideLoading()
                }
            })

            viewModel.searchResturentInCity(city_id = city_id)
        }


        return v
    }

}
