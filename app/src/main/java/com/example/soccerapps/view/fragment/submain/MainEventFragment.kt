package com.example.soccerapps.view.fragment.submain


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.soccerapps.R
import com.example.soccerapps.view.activity.SearchEventActivity
import com.example.soccerapps.view.adapter.ViewPageAdapter
import com.example.soccerapps.view.fragment.submain.event.LastEventFragment
import com.example.soccerapps.view.fragment.submain.event.NextEventFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_main_event.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class MainEventFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var idLeague: String? = null
    private var nameLeague: String? = null
    private lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_event, container, false)
        viewPager = view.findViewById(R.id.viewpager_event)
        tabLayout = view.findViewById(R.id.tab_event)
        setHasOptionsMenu(true)
        idLeague = arguments?.getString("idLeague")
        nameLeague = arguments?.getString("nameLeague")
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = nameLeague
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bundle = Bundle()
        bundle.putString("idLeague", idLeague)
        setViewPager(viewPager)
        tab_event.setupWithViewPager(viewPager)
    }

    private fun setViewPager(pager: ViewPager?) {
        val adapter = fragmentManager?.let { ViewPageAdapter(it) }
        val last = LastEventFragment.lastInstance()
        last.arguments = bundle
        adapter?.addFragment(last, getString(R.string.last_match_text))
        val next = NextEventFragment.nextInstance()
        next.arguments = bundle
        adapter?.addFragment(next, getString(R.string.next_match_text))
        pager?.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView?
        searchView?.queryHint = resources.getString(R.string.search_event_text)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                context?.startActivity<SearchEventActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
