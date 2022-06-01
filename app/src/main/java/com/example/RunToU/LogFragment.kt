import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RunToU.ExpandableListAdapter
import com.example.RunToU.R
import com.example.RunToU.databinding.FragmentLogBinding
import kotlinx.android.synthetic.main.activity_ordersheet.*

import kotlinx.android.synthetic.main.fragment_log.*
import net.daum.android.map.MapView
import java.util.*

class LogFragment : Fragment() {
    private lateinit var binding: FragmentLogBinding


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val data: MutableList<ExpandableListAdapter.Item> = ArrayList() // 데이터를 담을 List


        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "진행 중 심부름"))
        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "벌레 잡아주세요"))



        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "완료된 심부름"))
        data.add(ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "편의점에서 우유 사주세요"))


        recyclerview.adapter = ExpandableListAdapter(data)

    }

}