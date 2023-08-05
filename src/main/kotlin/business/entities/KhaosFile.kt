package business.entities

import java.io.File

data class KhaosFile(
    val id: FileId,
    val file: File,
    val uploadUserId: UserId,
    val extension: ExtensionType,
) {
    enum class ExtensionType {
        PDF, HTML, DOC, DOCX, XLS, TXT
    }
}
