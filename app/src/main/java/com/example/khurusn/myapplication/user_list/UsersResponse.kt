package com.example.khurusn.myapplication.user_list

class UsersResponse {
    var per_page: String? = null

    var total: String? = null

    var data: Array<Data>? = null

    var page: String? = null

    var total_pages: String? = null

    override fun toString(): String {
        return "UsersResponse [per_page = $per_page, total = $total, data = $data, page = $page, total_pages = $total_pages]"
    }

    inner class Data {
        var last_name: String? = null

        var id: String? = null

        var avatar: String? = null

        var first_name: String? = null

        override fun toString(): String {
            return "Data [last_name = $last_name, id = $id, avatar = $avatar, first_name = $first_name]"
        }
    }
}
