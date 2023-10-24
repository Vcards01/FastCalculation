package br.edu.ifsp.scl.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.scl.fastcalculation.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding
    private var points : Float = 0.0f
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            points= it.getFloat("points") ?: 0.0f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)
        val onClickListener = View.OnClickListener {
            (context as OnPlayGame).onPlayGame()
        }
        fragmentResultBinding.apply {
            pointsTv.text = points.toString()
            restartBt.setOnClickListener(onClickListener)
        }
        setHasOptionsMenu(true)

        return fragmentResultBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(result: Float) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putFloat("points", result)
                }
            }

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible = false
        menu.findItem(R.id.exitMi).setShowAsAction(2)
    }
}