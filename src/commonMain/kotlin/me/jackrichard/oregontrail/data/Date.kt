package me.jackrichard.oregontrail.data

import com.soywiz.klock.DateTime
import com.soywiz.klock.days
import kotlin.random.Random

class Date private constructor(private var date: DateTime = DateTime(1848, Random.nextInt(1, 12), Random.nextInt(1, 25))) {
    constructor(month: Int, day: Int) : this(DateTime(1848, month, day))

    enum class Season { WINTER, SUMMER, SPRING, FALL }
    enum class Weather { NONE, SNOW, RAIN, FOG, HOT }

    private val weatherHistory: MutableMap<DateTime, Weather> = mutableMapOf()

    override fun toString(): String {
        val months = mapOf(
            1 to "January", 2 to "February", 3 to "March", 4 to "April",
            5 to "May", 6 to "June", 7 to "July", 8 to "August",
            9 to "September", 10 to "October", 11 to "November", 12 to "December"
        )
        return months[date.month.index1] + date.format(" %d, %Y")
    }

    val season: Season
        get() = mapOf(
            1 to Season.WINTER, 2 to Season.WINTER, 3 to Season.WINTER, 4 to Season.SPRING,
            5 to Season.SPRING, 6 to Season.SPRING, 7 to Season.SUMMER, 8 to Season.SUMMER,
            9 to Season.FALL, 10 to Season.FALL, 11 to Season.FALL, 12 to Season.WINTER
        )[date.month.index1]!!

    fun getWeather(environment: Environment): Weather {
        if (weatherHistory.containsKey(date)) { return weatherHistory[date]!! }
        else if (environment != null) {

        } else {

        }
    }

    fun get_weather(environment: Environment? = null) : Weather {
        if (get_date().format("%b %d %Y") in weather.keys) {
            return weather[get_date().format("%b %d %Y")]!!
        } else if (environment != null) {
            var weather = Weather.NONE
            if ((get_season() == Date.Season.WINTER && environment.temp < 0.6) || environment.temp < 0.4) {
                weather = Weather.SNOW
            } else if (get_season() == Date.Season.FALL && environment.temp == 0.5) {
                weather = Weather.FOG
            } else if (get_season() == Date.Season.SPRING && environment.temp == 0.5) {
                weather = Weather.RAIN
            } else if (environment.temp > 0.7 || (get_season() == Date.Season.SUMMER && environment.temp > 0.4)) {
                weather = Weather.HOT
            }

            return if(Random.nextInt(0, 2) == 0) {
                this.weather[get_date().format("%b %d %Y")] = weather
                weather
            } else {
                this.weather[get_date().format("%b %d %Y")] = Weather.NONE
                Weather.NONE
            }
        } else {
            val weather = when (get_season()) {
                Season.WINTER -> Weather.SNOW
                Season.SUMMER -> Weather.HOT
                Season.SPRING -> Weather.RAIN
                Season.FALL -> Weather.FOG
            }

            return if (Random.nextInt(0, 2) == 0) {
                this.weather[get_date().format("%b %d %Y")] = weather
                weather
            } else {
                this.weather[get_date().format("%b %d %Y")] = Weather.NONE
                Weather.NONE
            }
        }
    }

    fun advance(days: Int = 1) { date += 1.days }
}