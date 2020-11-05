package com.ridwanjuanda.news.models

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Failed get data")