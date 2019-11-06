package org.oswayne.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.carder.sample.R
import org.oswayne.view.moment.provider.MomentProvider

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
        mv.setOnCommentListener { position, item, comment ->
            mv.addCommentData(position, CommentItem())
        }
        mv.setOnPraiseListener { position, item ->
            mv.addPraiseData(position, "大猪蹄子")
        }
    }
}
