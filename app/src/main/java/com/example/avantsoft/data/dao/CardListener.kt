package com.example.avantsoft.data.dao

interface CardListener {

    //Click para edição
    fun onEditClick(id: Int)

    //Click para remoção
    fun onDelete (user: Int)
}