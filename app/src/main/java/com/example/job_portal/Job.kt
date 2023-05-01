package com.example.job_portal



data class Job(
    val sFullName: String = "",
    val sEmail: String = "",
    val sGender: Boolean = false,
    val sGender2: Boolean = false,
    val sAddress: String = "",
    val sMobile: String = "",
    val jobName:String = "",
    val cvUrl: String = ""
) {
    constructor() : this("", "", false, false, "", "", "", "")
}
