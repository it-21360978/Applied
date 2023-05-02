data class Job(
    var fullName: String = "",
    var email: String = "",
    val gender: Boolean = false,
    val gender2: Boolean = false,
    var address: String = "",
    var mobile: String = "",
    var jobName:String = "",
    var cvUrl: String = ""
) {
    constructor() : this("", "", false, false, "", "", "", "")
}
