package com.example.job_portal

import java.io.Serializable

data class Customer(
    var sfullName: String? = null,
    val semail: String? = null,
    val saddress: String? = null,
    val smobile: String? = null,
    val jobName: String? = null,
    val cvUrl: String? = null
) : Serializable {
    constructor() : this("", "", "", "", "", "")
}
