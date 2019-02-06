package com.altran.ibanarriola.teamworktest.repository.model

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter



object ProjectModel {
    data class MapProject(val name: String?, val description: String?, val logo: String?, val company: String?, val startDate: String?, val status: String?): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(description)
            parcel.writeString(logo)
            parcel.writeString(company)
            parcel.writeString(startDate)
            parcel.writeString(status)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MapProject> {
            override fun createFromParcel(parcel: Parcel): MapProject {
                return MapProject(parcel)
            }

            override fun newArray(size: Int): Array<MapProject?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class ProjectList(val projects: List<Project>)
    data class Project(val name: String?, val description: String?, val logo: String?, val company: Company?, val startDate: String?, val status: String?) {
        fun convertToMapProject(): MapProject {
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val parseDate = LocalDate.parse(startDate, formatter)
            return MapProject(name, description, logo, company?.name, parseDate.toString(), status)
        }
    }
    data class Company(val name: String?)
}