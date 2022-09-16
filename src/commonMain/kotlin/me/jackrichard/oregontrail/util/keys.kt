package me.jackrichard.oregontrail.util

import com.soywiz.korev.Key
import com.soywiz.korge.input.InputKeys

fun getPressedKeys(input: InputKeys) : String {
    val letter = when {
        input.justPressed(Key.A) -> "a"
        input.justPressed(Key.B) -> "b"
        input.justPressed(Key.C) -> "c"
        input.justPressed(Key.D) -> "d"
        input.justPressed(Key.E) -> "e"
        input.justPressed(Key.F) -> "f"
        input.justPressed(Key.G) -> "g"
        input.justPressed(Key.H) -> "h"
        input.justPressed(Key.I) -> "i"
        input.justPressed(Key.J) -> "j"
        input.justPressed(Key.K) -> "k"
        input.justPressed(Key.L) -> "l"
        input.justPressed(Key.M) -> "m"
        input.justPressed(Key.N) -> "n"
        input.justPressed(Key.O) -> "o"
        input.justPressed(Key.P) -> "p"
        input.justPressed(Key.Q) -> "q"
        input.justPressed(Key.R) -> "r"
        input.justPressed(Key.S) -> "s"
        input.justPressed(Key.T) -> "t"
        input.justPressed(Key.U) -> "u"
        input.justPressed(Key.V) -> "v"
        input.justPressed(Key.W) -> "w"
        input.justPressed(Key.X) -> "x"
        input.justPressed(Key.Y) -> "y"
        input.justPressed(Key.Z) -> "z"
        input.justPressed(Key.SPACE) -> " "
        else -> ""
    }

    return if (input.pressing(Key.LEFT_SHIFT) || input.pressing(Key.RIGHT_SHIFT)) letter.uppercase() else letter
}