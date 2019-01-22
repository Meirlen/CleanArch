package meirlen.cleanarch.base.constants

object Constant {

    /**
     * Endpoint
     */
    const val BASE_URL: String = "https://api.trello.com"
    /**
     * Connection timeout duration
     */
    const val CONNECT_TIMEOUT = (60 * 1000).toLong()
    /**
     * Connection Read timeout duration
     */
    const val READ_TIMEOUT = (60 * 1000).toLong()
    /**
     * Connection write timeout duration
     */
    const val WRITE_TIMEOUT = (60 * 1000).toLong()


    const val DEFAULT_PER_PAGE_COUNT = 10
    const val DEFAULT_STRING = ""


}