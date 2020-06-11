package com.demo.android.cassianasoares.api.model

class VolumeInfoModel (
    var title: String,
    var authors: List<String>,
    var publisher: String,
    var language: String,
    var categories: List<String>,
    var description: String,
    var imageLinks: ImageLinksModel,
    var pageCount: Int
)

    /*
    var title: String? = null
    var authors: List<String>? = null
    var publisher: String? = null
    var language: String? = null
    var categories: List<String>? = null
    var description: String? = null
    var imageLinks: ImageLinksModel? = null
    var pageCount: Int? = null


    constructor(title: String, authors: List<String>, publisher: String, language: String,
                categories: List<String>, description: String, imageLinks: ImageLinksModel, pageCount: Int){
        this.title = title
        this.authors = authors
        this.publisher = publisher
        this.language = language
        this.categories = categories
        this.description = description
        this.imageLinks = imageLinks
        this.pageCount = pageCount
    }

  constructor(publisher: String, language: String, categories: List<String>, description: String){
      this.publisher = publisher
      this.language = language
      this.categories = categories
      this.description = description
  }
}*/

class ImageLinksModel (
    var thumbnail: String )