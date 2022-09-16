package me.jackrichard.oregontrail.data

enum class Occupation(private val identifier: String, private val location: String, val money: Int) {
    BANKER("banker", "Boston", 1600),
    CARPENTER("carpenter", "Ohio", 800),
    FARMER("farmer", "Illinois", 400),
    NONE("nothing", "Nowhere", 0);

    override fun toString() = "Be a $identifier from $location"
}