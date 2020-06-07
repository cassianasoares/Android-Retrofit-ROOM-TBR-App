package com.demo.android.cassianasoares

class BookModel(
    var id: String,
    var volumeInfo: VolumeInfoModel
){
    override fun toString(): String {
        if(volumeInfo.imageLinks == null){
            return "Book{" +
                    "id= " + id +
                    ", name= " + volumeInfo.title +
                    ", authors= " + volumeInfo.authors +
                    ", publisher= " + volumeInfo.publisher +
                    ", language= " + volumeInfo.language +
                    ", categories= " + volumeInfo.categories +
                    ", description= " + volumeInfo.description +
                    ", thumbnail= " + volumeInfo.imageLinks +
                    ", pageCount= " + volumeInfo.pageCount + '\'' +
                    "}"
        }else{
            return "Book{" +
                    "id= " + id +
                    ", name= " + volumeInfo.title +
                    ", authors= " + volumeInfo.authors +
                    ", publisher= " + volumeInfo.publisher +
                    ", language= " + volumeInfo.language +
                    ", categories= " + volumeInfo.categories +
                    ", description= " + volumeInfo.description +
                    ", thumbnail= " + volumeInfo.imageLinks.thumbnail +
                    ", pageCount= " + volumeInfo.pageCount + '\'' +
                    "}"
        }
    }
}