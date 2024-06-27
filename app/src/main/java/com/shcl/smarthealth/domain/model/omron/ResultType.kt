package com.shcl.smarthealth.domain.model.omron

import androidx.annotation.StringRes
import com.shcl.smarthealth.R

enum class ResultType(@field:StringRes @param:StringRes var id: Int) {
    Success(R.string.success),
    Failure(R.string.failure);

    @StringRes
    fun stringResId(): Int {
        return this.id
    }
}