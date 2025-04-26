package com.example.chat.domain.usecase

import com.example.chat.domain.repository.BackupRepository
import javax.inject.Inject

class BackupUseCase @Inject constructor(
    private val backupRepository: BackupRepository
) {
    suspend operator fun invoke() {
        backupRepository.backupAllMessages()
    }
}
