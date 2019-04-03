package com.sunmoonblog.roomdemo.demo2

import android.arch.persistence.room.*

@Entity(tableName = "book_table", foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("u_id"),
        childColumns = arrayOf("user_id"))])
data class Book @Ignore constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "b_id")
        var id: Long = 0,

        @ColumnInfo(name = "b_name")
        var name: String? = null,

        @ColumnInfo(name = "b_author")
        var author: String? = null,

        @ColumnInfo(name = "b_copyright")
        var copyright: String? = null,

        @ColumnInfo(name = "b_price")
        var price: Double? = null,

        @ColumnInfo(name = "b_status")
        var status: Int? = null,

        @ColumnInfo(name = "user_id")
        var userId: Long? = null
) {
    constructor(): this(0)
}
