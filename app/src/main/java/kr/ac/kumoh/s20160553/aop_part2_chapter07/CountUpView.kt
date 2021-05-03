package kr.ac.kumoh.s20160553.aop_part2_chapter07

import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CountUpView(
    context: Context,
    attrs: AttributeSet
): AppCompatTextView(context, attrs) {

    private var startTimestamp: Long = 0L

    private val countUpAction: Runnable = object: Runnable {
        override fun run() {
            val currentTimeStamp = SystemClock.elapsedRealtime()

            val countTImeSeconds =  ((currentTimeStamp - startTimestamp) / 1000L).toInt()
            updateCountTime(countTImeSeconds)

            handler?.postDelayed(this, 1000L)
        }
    }

    fun startCountUp() {
        startTimestamp = SystemClock.elapsedRealtime()
        handler?.post(countUpAction)
    }

    fun stopCount() {
        handler?.removeCallbacks(countUpAction)
    }

    fun clearCountTime() {
        updateCountTime(0)
    }

    @SuppressLint("SetTextI18n")
    private fun updateCountTime(countTimeSeconds: Int){
        val minutes = countTimeSeconds / 60
        val seconds = countTimeSeconds % 60

        text = "%02d:%02d".format(minutes, seconds)
    }
}