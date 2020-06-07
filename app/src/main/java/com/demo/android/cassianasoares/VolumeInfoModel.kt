package com.demo.android.cassianasoares

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

class ImageLinksModel (var thumbnail: String)