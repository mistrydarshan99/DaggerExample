package com.darshan.daggerexample.response

data class User(
  val userId: Int,
  val id: Long,
  val title: String,
  val body: String
)

data class Company(
  val name: String,
  val catchPhrase: String,
  val bs: String
)
