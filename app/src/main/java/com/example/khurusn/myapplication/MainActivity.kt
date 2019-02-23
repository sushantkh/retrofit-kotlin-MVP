package com.example.khurusn.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import com.example.khurusn.myapplication.R.id.seekBar
import com.example.khurusn.myapplication.R.id.seekBar






class MainActivity : AppCompatActivity() {

    private lateinit var mSeekBar: SeekBar
    private lateinit var mTvProgress:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSeekBar = seekBar
        mTvProgress= tvProgress

        mSeekBar.max = 100

        mSeekBar.progress = 50

        mTvProgress.text= mSeekBar.progress.toString()

        mSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                Log.d("onProgressChanged() :: ", "progress $progress")
                mTvProgress.text="$progress"

               /* var progress :Int=0
                progress = Math.round(progress / interval) as Int * interval
                seekBar.progress = progress*/
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

              /*  val progressStep = getMax() / dotsCount

                val lastDotProgress = Math.round(getProgress() / progressStep) * progressStep
                val nextDotProgress = lastDotProgress + progressStep
                val midBetweenDots = lastDotProgress + progressStep / 2

                if (getProgress() > midBetweenDots)
                    seekBar.progress = nextDotProgress
                else
                    seekBar.progress = lastDotProgress*/

            }
        })
    }
}
