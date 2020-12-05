package com.psychotechbd.psyche.registration

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class UserHelper(
    val token: String,
    val id: String,
    val email: String,
    val password: String,
    val uid: String,
) :
    Parcelable {
    constructor() : this( "", "", "", "", "")
}