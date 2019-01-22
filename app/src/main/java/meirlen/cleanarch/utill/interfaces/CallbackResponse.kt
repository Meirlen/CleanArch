package meirlen.cleanarch.utill.interfaces


interface CallbackResponse<in T> {
    fun onResponse(response : T)
}