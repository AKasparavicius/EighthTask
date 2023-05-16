package lt.arnas.notes

import java.time.LocalDateTime

data class Note(
    val id: Long, var name: String, var details: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    private var updateDate: LocalDateTime = LocalDateTime.now())
{
    fun updateDetails(name: String, details: String): String{
        this.name = name
        this.details = details
        this.updateDate = LocalDateTime.now()
        return LocalDateTime.now().toString()
    }
}