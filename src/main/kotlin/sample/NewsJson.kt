package sample

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import java.lang.reflect.Type
import java.util.*


class NewsJson {

    private val logger = KotlinLogging.logger {}

    var persistancy = persistancy()

    val JSON_FILE = "D:/COLLEGE/MobileAppAssignment/src/main/kotlin/sample/news.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    var listType: Type? = object : TypeToken<ArrayList<News?>?>() {}.type

    var myNews = mutableListOf<News>()

    fun generateRandomId(): Int {
        var myRandomId = Random().nextInt()
        if (myRandomId<0)
            myRandomId*(-1)
        return myRandomId
    }

    fun findAll(): MutableList<News> {
        return myNews
    }

    fun findOne(id: Int) : News? {
        var foundNews: News? = myNews.find { p -> p.newsId == id }
        return foundNews
    }
    internal fun logAll() {
        myNews.forEach { logger.info("${it}") }
    }

    fun create(news: News) {
        news.newsId = generateRandomId()
        myNews.add(news)
        serialize()
    }

    fun update(news: News) {
        var foundNews = findOne(news.newsId!!)
        if (foundNews != null) {
            foundNews.newsTitle = news.newsTitle
            foundNews.newsDescription = news.newsDescription
            foundNews.newsAuthor = news.newsAuthor
        }
        serialize()
    }

    fun delete(newsToDelete: News){
        //var foundNews = findOne(news.newsId!!)
        if (newsToDelete != null) {
            myNews.remove(newsToDelete)
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(myNews, listType)
        persistancy.write(JSON_FILE, jsonString)
    }

    fun deserialize() {
        val jsonString = persistancy.read(JSON_FILE)
        myNews = Gson().fromJson(jsonString, listType);
    }

}