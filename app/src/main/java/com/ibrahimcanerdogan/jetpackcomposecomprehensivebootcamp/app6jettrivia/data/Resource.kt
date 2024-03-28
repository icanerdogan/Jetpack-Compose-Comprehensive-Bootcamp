package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app6jettrivia.data

data class Resource<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var exception: E? = null
)