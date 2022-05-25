package tj.safarovsaburjon.sthelper.repository

import tj.safarovsaburjon.sthelper.model.NewsModel

/*
* tj.safarovsaburjon.sthelper.repository
* Created by Saburjon Safarov, on 18/05/2022
* email: safarovsaburjon2002@gmail.com
*/

class MainRepository {

companion object {
    var items = listOf(
        NewsModel(1, "aliboi", true),
        NewsModel(2, "aliboi", false),
        NewsModel(3, "gegeg", false),
        NewsModel(4, "aliboi", false),
        NewsModel(5, "fegegeg", true),
        NewsModel(6, "dwfw", false),
        NewsModel(7, "aliboi", true),
        NewsModel(8, "grdsgr", true),
        NewsModel(9, "alkoijoihoihiboi", true),
        NewsModel(10, "aliboi", false),
        NewsModel(11, "aliboi", true),
        NewsModel(12, "aliboi", true),
        NewsModel(13, "aliboi", false),
        NewsModel(14, "aliboi", true),
        NewsModel(15,"aliboi", true),
    )
}
    fun getList(): List<NewsModel> {
        return items
    }

    fun getNews() {

    }

    fun getBal() {

    }

    fun getStId() {

    }

    fun getLessonId() {

    }
}