package Pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by helly.p on 01/02/23.
 */

data class PageData(@SerializedName("logo-url")
                    val logoUrl: String? =  null,
                    @SerializedName("heading-text")
                    val headingText: String? = null,
                    val uidata: List<UiElements>? = null)

data class UiElements(val uitype: String? = null,
                      val key: String? = null,
                      val value: String? = null,
                      val hint: String? = null)

enum class UiType {
    label,
    edittext,
    button
}