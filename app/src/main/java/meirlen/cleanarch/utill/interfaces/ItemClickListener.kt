package meirlen.cleanarch.utill.interfaces

interface ItemClickListener<in T> {
    fun onItemClick(dataObject : T)
}