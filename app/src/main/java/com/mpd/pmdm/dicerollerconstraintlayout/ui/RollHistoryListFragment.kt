package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.DiceRollApplication
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentRollHistoryListBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date

/**
 * A fragment representing a list of Items.
 */
class RollHistoryListFragment : Fragment() {

    private var _binding: FragmentRollHistoryListBinding? = null
    private val binding get() = _binding!!

    private val twoDicesViewModel: TwoDicesViewModel by activityViewModels {
        TwoDicesViewModelFactory(
            6,
            (activity?.application as DiceRollApplication).diceRollsRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRollHistoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(ExperimentalFoundationApi::class) //Para usar stickyHeader() sin warnings, ya que es experimental
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            MaterialTheme {
                Surface {
                    val diceRollsList =
                        twoDicesViewModel.getAllDiceRolls().observeAsState(emptyList())
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 56.dp)
                    ) {
                        stickyHeader {
                            Header()
                        }
                        items(diceRollsList.value.size) {
                            BodyList(diceRollsList, it)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BodyList(
        diceRollsList: State<List<DiceRoll>>,
        it: Int
    ) {
        val bgColor = if(it % 2 == 0) Color(0xFFCCCCCC) else Color(0xFFFFFFFF)

        Row(
            modifier = Modifier.fillMaxWidth()
                .background(bgColor),
        ) {
            Text(
                text = diceRollsList.value[it].id.toString(),
                modifier = Modifier.weight(1f)
            )
            val dateTime = diceRollsList.value[it].dateTime
            Text(
                text = SimpleDateFormat("d/MM/y h:mm a").format(Date(dateTime)),
                modifier = Modifier.weight(3f)
            )
            Text(
                text = diceRollsList.value[it].sideDice1.toString(),
                modifier = Modifier.weight(2f)
            )
            Text(
                text = diceRollsList.value[it].siceDice2.toString(),
                modifier = Modifier.weight(2f)
            )
        }
    }

    @Composable
    private fun Header(modifier: Modifier = Modifier) {
        Surface{ //Lo meto en una Surface para que no sea transparente
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("ID", modifier = Modifier.weight(1f))
                Text("Fecha y hora", modifier = Modifier.weight(3f))
                Text("Dado 1", modifier = Modifier.weight(2f))
                Text("Dado 2", modifier = Modifier.weight(2f))
            }
        }

    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }