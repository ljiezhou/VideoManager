package com.ds.compress.widget

import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.blankj.utilcode.util.LogUtils
import com.ds.compress.databinding.CompressParamPercentLayoutBinding

/**
 * @Author ljiezhou
 * @date 2024/10/27
 * @Description
 */

fun CompressParamPercentLayoutBinding.setPercentListener(defailtValue: Int) {
    percentEt.setText(defailtValue.toString())

    percentSeekBar.progress = 50
    percentSeekBar.max = 100
    val maxValue = if (percentSeekBar.progress > 0) {
        defailtValue / (percentSeekBar.progress.toFloat() / percentSeekBar.max)
    } else {
        defailtValue.toFloat()
    }
    LogUtils.d("setPercentListener", "maxValue: $maxValue, defaultValue: $defailtValue")

    percentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val newValue = ((progress / percentSeekBar.max.toFloat()) * maxValue).toInt()
            percentEt.setText(newValue.toString())
            LogUtils.d("onProgressChanged", "progress: $progress, newValue: $newValue")
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}
        override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    })

    percentEt.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            val progress = s.toString().toIntOrNull() ?: 0
            percentSeekBar.progress = ((progress / maxValue) * percentSeekBar.max).toInt()
            LogUtils.d("afterTextChanged", "progress: $progress")
        }
    })
}

fun CompressParamPercentLayoutBinding.getValue(): Int {
    return percentEt.text.toString().toInt()
}