package com.sunmoonblog.roomdemo.demo2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_table")
data class User @Ignore constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "u_id", index = true)
        var id: Long = 0,

        @ColumnInfo(name = "u_name")
        var name: String? = null,

        @ColumnInfo(name = "u_phone")
        var phone: String? = null,

        @ColumnInfo(name = "u_create_date")
        var createDate: Date? = null,

        @ColumnInfo(name = "u_update_date")
        var updateDate: Date? = null,

        @ColumnInfo(name = "u_status")
        var status: Int? = 0

) {
        constructor() : this(0)
}