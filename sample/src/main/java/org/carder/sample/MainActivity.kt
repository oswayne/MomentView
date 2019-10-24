package org.carder.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.carder.view.moment.MomentProvider
import org.carder.view.moment.MomentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMomentView();
    }

    private fun setMomentView() {
        val items = ArrayList<MomentProvider>()
        items.add(MomentItem())
        items.add(MomentItem())
        mv.setData(items)
        mv.setOnCommentListener { item, comment ->
            println(comment)
        }
        mv.setOnPraiseListener { item ->
            println("点赞")
        }
    }
}
