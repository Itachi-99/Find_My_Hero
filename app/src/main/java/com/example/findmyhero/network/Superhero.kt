package com.example.findmyhero.network

import android.os.Parcel
import android.os.Parcelable

@Suppress("INFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION", "DEPRECATION")
data class Superhero(
    val id: Int,
    val name: String?,
    val slug: String?,
    val powerstats: Powerstats?,
    val appearance: Appearance?,
    val biography: Biography?,
    val work: Work?,
    val connections: Connections?,
    val images: Images?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Powerstats::class.java.classLoader),
        parcel.readParcelable(Appearance::class.java.classLoader),
        parcel.readParcelable(Biography::class.java.classLoader),
        parcel.readParcelable(Work::class.java.classLoader),
        parcel.readParcelable(Connections::class.java.classLoader),
        parcel.readParcelable(Images::class.java.classLoader)
    )

    data class Powerstats(
        val intelligence: Int,
        val strength: Int,
        val speed: Int,
        val durability: Int,
        val power: Int,
        val combat: Int
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(intelligence)
            parcel.writeInt(strength)
            parcel.writeInt(speed)
            parcel.writeInt(durability)
            parcel.writeInt(power)
            parcel.writeInt(combat)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Powerstats> {
            override fun createFromParcel(parcel: Parcel): Powerstats {
                return Powerstats(parcel)
            }

            override fun newArray(size: Int): Array<Powerstats?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Appearance(
        val gender: String?,
        val race: String?,
        val height: List<String>?,
        val weight: List<String>?,
        val eyeColor: String?,
        val hairColor: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(gender)
            parcel.writeString(race)
            parcel.writeStringList(height)
            parcel.writeStringList(weight)
            parcel.writeString(eyeColor)
            parcel.writeString(hairColor)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Appearance> {
            override fun createFromParcel(parcel: Parcel): Appearance {
                return Appearance(parcel)
            }

            override fun newArray(size: Int): Array<Appearance?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Biography(
        val fullName: String?,
        val alterEgos: String?,
        val aliases: List<String>?,
        val placeOfBirth: String?,
        val firstAppearance: String?,
        val publisher: String?,
        val alignment: String?
    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )
        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(fullName)
            parcel.writeString(alterEgos)
            parcel.writeStringList(aliases)
            parcel.writeString(placeOfBirth)
            parcel.writeString(firstAppearance)
            parcel.writeString(publisher)
            parcel.writeString(alignment)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Biography> {
            override fun createFromParcel(parcel: Parcel): Biography {
                return Biography(parcel)
            }

            override fun newArray(size: Int): Array<Biography?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Work(
        val occupation: String?,
        val base: String?
    ):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(occupation)
            parcel.writeString(base)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Work> {
            override fun createFromParcel(parcel: Parcel): Work {
                return Work(parcel)
            }

            override fun newArray(size: Int): Array<Work?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Connections(
        val groupAffiliation: String,
        val relatives: String
    )

    data class Images(
        val xs: String?,
        val sm: String?,
        val md: String?,
        val lg: String?
    ):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(xs)
            parcel.writeString(sm)
            parcel.writeString(md)
            parcel.writeString(lg)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Images> {
            override fun createFromParcel(parcel: Parcel): Images {
                return Images(parcel)
            }

            override fun newArray(size: Int): Array<Images?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(slug)
        parcel.writeParcelable(powerstats, flags)
        parcel.writeParcelable(appearance, flags)
        parcel.writeParcelable(biography, flags)
        parcel.writeParcelable(work, flags)
        parcel.writeParcelable(images, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Superhero> {
        override fun createFromParcel(parcel: Parcel): Superhero {
            return Superhero(parcel)
        }

        override fun newArray(size: Int): Array<Superhero?> {
            return arrayOfNulls(size)
        }
    }
}