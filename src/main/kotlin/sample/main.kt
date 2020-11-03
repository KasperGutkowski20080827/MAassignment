package sample

import mu.KotlinLogging

private val log = KotlinLogging.logger {}

var news = News()
var newsArray = ArrayList<News>()
var count = 1

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
    println(" 4. Delete News by ID")
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
    print("Enter a news id: ")
    news.newsId = count
    count++
    print("Enter a news title: ")
    news.newsTitle = readLine()!!
    print("Enter a news description: ")
    news.newsDescription = readLine()!!
    print("Enter your author name or allias: ")
    news.newsAuthor = readLine()!!

    if(news.newsTitle.isNotEmpty() && news.newsDescription.isNotEmpty() && news.newsAuthor.isNotEmpty()){
        var myNews = News(news.newsId,news.newsTitle, news.newsDescription, news.newsAuthor)
        newsArray.add(myNews)
    }
}

fun updateNews() {
    println("Enter ID: ")
    var updateId = readLine()!!.toInt()
    var updateNews = newsArray.find { item -> item.newsId == updateId }
    println("What do wish to update: ")
    println("Press 1 to change the title of the news.")
    println("Press 2 to change the description of the news.")
    println("Press 3 to change the author name or allias of the news.")
    println("Press 4 to go back")
    if (updateNews != null) {
        var choice = readLine()!!.toInt()
        if (choice == 1) {
            print("Enter a new news title: ")
            var newNewsTitle = readLine()!!
            if (newNewsTitle.isNotEmpty()) {
                updateNews.newsTitle = newNewsTitle
            }
        }
        if (choice == 2) {
            print("Enter a new description: ")
            var newNewsDescription = readLine()!!
            if (newNewsDescription.isNotEmpty()) {
                updateNews.newsDescription = newNewsDescription
            }
        }
        if (choice == 3) {
            print("Enter a new author name or allias: ")
            var newNewsAuthor = readLine()!!
            if(newNewsAuthor.isNotEmpty()){
                updateNews.newsAuthor = newNewsAuthor
            }
        }
        if(choice == 4){
            menu()
        }
    }
}

fun listAllNews() {
    print("All news available")
    newsArray.forEach({ log.info { "${it}" }})

}
