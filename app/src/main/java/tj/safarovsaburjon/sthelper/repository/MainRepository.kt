package tj.safarovsaburjon.sthelper.repository

import tj.safarovsaburjon.sthelper.model.StudentModel

/*
* tj.safarovsaburjon.sthelper.repository
* Created by Saburjon Safarov, on 18/05/2022
* email: safarovsaburjon2002@gmail.com
*/

class MainRepository {

companion object {
    var items = listOf(
        StudentModel(1, "aliboi", true),
        StudentModel(2, "aliboi", false),
        StudentModel(3, "gegeg", false),
        StudentModel(4, "aliboi", false),
        StudentModel(5, "fegegeg", true),
        StudentModel(6, "dwfw", false),
        StudentModel(7, "aliboi", true),
        StudentModel(8, "grdsgr", true),
        StudentModel(9, "alkoijoihoihiboi", true),
        StudentModel(10, "aliboi", false),
        StudentModel(11, "aliboi", true),
        StudentModel(12, "aliboi", true),
        StudentModel(13, "aliboi", false),
        StudentModel(14, "aliboi", true),
        StudentModel(15,"aliboi", true),
    )
}
    fun getList(): List<StudentModel> {
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