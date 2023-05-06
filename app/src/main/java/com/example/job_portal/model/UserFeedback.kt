package com.example.job_portal.model

import android.os.Parcel
import android.os.Parcelable

class UserFeedback() : Parcelable {

    var id: String = ""
    var rating: Int = 0
    var feedback: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString() ?: ""
        rating = parcel.readInt()
        feedback = parcel.readString() ?: ""
    }

    constructor(id: String, rating: Int, feedback: String) : this() {
        this.id = id
        this.rating = rating
        this.feedback = feedback
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(rating)
        parcel.writeString(feedback)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserFeedback> {
        override fun createFromParcel(parcel: Parcel): UserFeedback {
            return UserFeedback(parcel)
        }

        override fun newArray(size: Int): Array<UserFeedback?> {
            return arrayOfNulls(size)
        }
    }
}
