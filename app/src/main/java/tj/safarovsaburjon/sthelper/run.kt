fun main() {

    val opel = Moshin(
        "opel astra G",
        "surx",
        "classic",
        2.5f
    )

    val mers = Moshin(
        "mers c class",
        "black",
        "valtron",
        3.0f
    )
    mers.getCarInfo()

    opel.getCarInfo()


}

class Moshin(
    private var nom: String = "",
    private var rang: String = "",
    private var mator: String = "",
    private var vazn: Float = 0.0f
) {

    fun getCarInfo() {
        chop(this.nom)
        chop(this.mator)
        println(this.rang)
        println(this.vazn)
        chop(78)
    }

    private fun chop(qim: Any) {
        println(qim)
    }

}


