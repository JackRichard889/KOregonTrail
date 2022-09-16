package me.jackrichard.oregontrail.data

import com.soywiz.klock.days
import com.soywiz.klock.plus
import kotlin.random.Random

class Date(val month: Int, val day: Int) {
    enum class Season {
        WINTER,
        SUMMER,
        SPRING,
        FALL
    }

    enum class Weather {
        NONE,
        SNOW,
        RAIN,
        FOG,
        HOT
    }

    val weather: MutableMap<String, Weather> = mutableMapOf()
    var date = com.soywiz.klock.Date(1848, if (month < 1) Random.nextInt(1, 12) else month, if (day < 1) Random.nextInt(1, 25) else day)

    fun get_date() = date

    fun get_formatted_date(): String {
        val months = mapOf(
            1 to "January", 2 to "February", 3 to "March", 4 to "April",
            5 to "May", 6 to "June", 7 to "July", 8 to "August",
            9 to "September", 10 to "October", 11 to "November", 12 to "December"
        )
        return months[date.month.index1] + date.format(" %d, %Y")
    }

    fun get_season(): Season {
        val seasonForMonth = mapOf(
            1 to Season.WINTER, 2 to Season.WINTER, 3 to Season.WINTER, 4 to Season.SPRING,
            5 to Season.SPRING, 6 to Season.SPRING, 7 to Season.SUMMER, 8 to Season.SUMMER,
            9 to Season.FALL, 10 to Season.FALL, 11 to Season.FALL, 12 to Season.WINTER
        )
        return seasonForMonth[date.month.index1]!!
    }

    fun get_formatted_season(season: Season): String {
        val seasons = mapOf(
            Season.WINTER to "Winter", Date.Season.FALL to "Fall",
            Season.SPRING to "Spring", Date.Season.SUMMER to "Summer"
        )

        return seasons[season]!!
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

    fun get_formatted_weather(weather: Weather) : String {
        val weathers = mapOf(
            Weather.NONE to "clear", Weather.RAIN to "rain",
            Weather.SNOW to "snow", Weather.FOG to "fog", Weather.HOT to "hot"
        )

        return weathers[weather]!!
    }

    fun advance() = advance_days(1)

    fun advance_days(days: Int) { date += 1.days }
}