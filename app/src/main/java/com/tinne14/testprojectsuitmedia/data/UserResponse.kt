package com.tinne14.testprojectsuitmedia.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
	val perPage: Int,
	val total: Int,
	val data: List<DataItem>,
	val page: Int,
	val totalPages: Int,
)

data class DataItem(
	@SerializedName("id")
	val id: Int,

	@SerializedName("email")
	val email: String,

	@SerializedName("first_name")
	val firstName: String,

	@SerializedName("last_name")
	val lastName: String,

	@SerializedName("avatar")
	val avatar: String
)

