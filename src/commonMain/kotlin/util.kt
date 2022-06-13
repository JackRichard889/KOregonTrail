fun money_for_profession(occupation: Int) : Double {
    return when (occupation) {
        0 -> 1600.00
        1 -> 800.00
        2 -> 400.00
        else -> 0.0
    }
}