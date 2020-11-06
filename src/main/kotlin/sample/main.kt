package sample

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import kotlin.collections.ArrayList

private val log = KotlinLogging.logger {}

var newsJson = NewsJson()

var news = News()
var newsArray = ArrayList<News>()

const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"

fun main(args: Array<String>) {
    log.info { "Launching Game News App the GNA" }
    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addNews()
            2 -> updateNews()
            3 -> listAllNews()
            4 -> searchId()
            5 -> deleteNews()
            -1 -> println(ANSI_BLACK +"Exiting App"+ ANSI_RESET)
            else -> println(ANSI_BLACK +"Invalid Option please enter a number for a menu"+ ANSI_RESET)
        }
        println()
    } while (input != -1)
    log.info { "Shutting Down GNA App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    newsJson.deserialize()

    println(ANSI_BLACK + "MAIN MENU" + ANSI_RESET)
    println(ANSI_GREEN + " 1. Add News Report" + ANSI_RESET)
    println(ANSI_BLUE + " 2. Update News Report" + ANSI_RESET)
    println(ANSI_YELLOW + " 3. List News" + ANSI_RESET)
    println(ANSI_PURPLE + " 4. List News by searching ID" + ANSI_RESET)
    println(ANSI_CYAN + " 5. Delete News by ID" + ANSI_RESET)
    println(ANSI_RED + "-1. Exit" + ANSI_RESET)
    println()
    print(ANSI_BLACK +"Enter an integer : "+ ANSI_RESET)
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addNews(){
    println(ANSI_BLACK +"Add a news report"+ ANSI_RESET)
    news.newsId = 1
    //count++
    print(ANSI_BLACK +"Enter a news title: "+ ANSI_RESET)
    news.newsTitle = readLine()!!
    print(ANSI_BLACK +"Enter a news description: "+ ANSI_RESET)
    news.newsDescription = readLine()!!
    print(ANSI_BLACK +"Enter your author name or allias: "+ ANSI_RESET)
    news.newsAuthor = readLine()!!

    if(news.newsTitle.isNotEmpty() && news.newsDescription.isNotEmpty() && news.newsAuthor.isNotEmpty()){
        var myNews = News(news.newsId, news.newsTitle, news.newsDescription, news.newsAuthor)
        //newsArray.add(myNews)
        newsJson.create(myNews)
    }
}

fun updateNews() {
    println(ANSI_BLACK +"Enter ID: "+ ANSI_RESET)
    var updateId = readLine()!!.toInt()
    var updateNews = newsJson.findOne(updateId)
    println(ANSI_BLACK +"What do you wish to update: "+ ANSI_RESET)
    println(ANSI_GREEN+"Press 1 to change the title of the news."+ ANSI_RESET)
    println(ANSI_BLUE+"Press 2 to change the description of the news."+ ANSI_RESET)
    println(ANSI_PURPLE+"Press 3 to change the author name or allias of the news."+ ANSI_RESET)
    println(ANSI_RED +"Press 4 to go back"+ ANSI_RESET)
    if (updateNews != null) {
        print(ANSI_BLACK +"Please enter your choice: "+ ANSI_RESET)
        var choice = readLine()!!.toInt()
        if (choice == 1) {
            print(ANSI_BLACK +"Enter a new news title: "+ ANSI_RESET)
            var newNewsTitle = readLine()!!
            if (newNewsTitle.isNotEmpty()) {
                updateNews.newsTitle = newNewsTitle
                newsJson.update(updateNews)
                updateNews()
            }
        }
        if (choice == 2) {
            print(ANSI_BLACK +"Enter a new description: "+ ANSI_RESET)
            var newNewsDescription = readLine()!!
            if (newNewsDescription.isNotEmpty()) {
                updateNews.newsDescription = newNewsDescription
                newsJson.update(updateNews)
                updateNews()
            }
        }
        if (choice == 3) {
            print(ANSI_BLACK +"Enter a new author name or allias: "+ ANSI_RESET)
            var newNewsAuthor = readLine()!!
            if(newNewsAuthor.isNotEmpty()){
                updateNews.newsAuthor = newNewsAuthor
                newsJson.update(updateNews)
                updateNews()
            }
        }
        if(choice == 4){
            menu()
        }
    }
}

fun deleteNews(){
    println(ANSI_BLACK +"Enter ID: "+ ANSI_RESET)
    var deleteId = readLine()!!.toInt()
    var deleteNews = newsArray.find { item -> item.newsId == deleteId }
    //newsArray.remove(deleteNews)
    if (deleteNews != null) {
        newsArray.remove(deleteNews)
        newsJson.delete(deleteNews)
    }
}

fun searchId(){
    println(ANSI_BLACK +"Enter ID: "+ ANSI_RESET)
    var searchId = readLine()!!.toInt()
    var searchItem = newsJson.findOne(searchId)
    println(searchItem)
}

fun listAllNews() {
    println(ANSI_BLACK +"All news available"+ ANSI_RESET)
    newsArray = newsJson.findAll() as ArrayList<News>
    newsArray.forEach{
        println(it)
    }
}