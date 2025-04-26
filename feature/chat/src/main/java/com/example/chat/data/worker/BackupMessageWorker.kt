package com.example.chat.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.chat.domain.usecase.BackupUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class BackupMessageWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val backupUseCase: BackupUseCase
): CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try{
            backupUseCase()
            return Result.success()
        } catch (e: Exception) {
            if(runAttemptCount < MAX_ATTEMPTS){
                 Result.retry()
            }else{
                 Result.failure()
            }
        }
    }
    companion object{
        const val MAX_ATTEMPTS = 5
    }
}