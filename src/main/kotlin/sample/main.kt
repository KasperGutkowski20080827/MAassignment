package sample

import mu.KotlinLogging

private val log = KotlinLogging.logger {}

var newsTitle = ""
var newsDescription = ""
var newsAuthor = ""
val newsList = ArrayList<String>()

fun main(args: Array<String>) {
    log.info { "Launching Placemark Console App" }

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addNews()
            2 -> updateNews()
            3 -> listAllNews()
            -1 -> println("Exiting App")
            else -> println("Invalid Option please enter a number for a menu")
        }
        println()
    } while (input != -1)
    log.info { "Shutting Down GDC App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("MAIN MENU")
    println(" 1. Add News Report")
    println(" 2. Update News Report")
    println(" 3. List News")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addNews(){
    println("Add a news report")
    print("Enter a news title: ")
    newsTitle = readLine()!!
    print("Enter a news description: ")
    newsDescription = readLine()!!
    print("Enter your author name or allias: ")
    newsAuthor = readLine()!!

    if(newsTitle.isNotEmpty() && newsDescription.isNotEmpty() && newsAuthor.isNotEmpty()){
        newsList.add(newsTitle)
        newsList.add(newsDescription)
        newsList.add(newsAuthor)
    }
}

fun updateNews() {

}

fun listAllNews() {
    print("All news available")
    newsList.forEach({ log.info { "${it}" }})
}
