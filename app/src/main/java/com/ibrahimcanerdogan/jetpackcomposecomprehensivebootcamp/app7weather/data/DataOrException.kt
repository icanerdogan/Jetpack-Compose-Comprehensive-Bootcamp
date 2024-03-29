package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.data

class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null)