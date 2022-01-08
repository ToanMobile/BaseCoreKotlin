/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.data.model.ui.contact

import com.toan_mobile.module.data.model.ui.NeUser

class NeContact(
    val _neHeader: String? = null,
    var _itemNeContact: ItemNeContact? = null,
    var isBlocked: Boolean = false
) {
    fun isHeader(): Boolean {
        return _neHeader != null && _itemNeContact == null
    }

    fun isItemUser(): Boolean {
        return _itemNeContact != null && _neHeader == null
    }

    fun getItemUser(): ItemNeContact? {
        return _itemNeContact
    }

    override fun toString(): String {
        return "NeContact(_neHeader=$_neHeader, _itemNeContact=$_itemNeContact, isBlocked=$isBlocked)"
    }
}

class ItemNeContact(
    val _neContact: NeUser? = null,
    var _checked: Boolean = false
) {
    fun isChecked(): Boolean {
        return _checked
    }

    fun setChecked(checked: Boolean) {
        _checked = checked
    }

    override fun toString(): String {
        return "ItemNeContact(_neContact=$_neContact, _checked=$_checked)"
    }
}
