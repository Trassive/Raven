package com.example.chat.data.remotedatasource


import androidx.core.net.toUri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class StorageRemoteDataSource @Inject constructor(
    private val storage: FirebaseStorage
){
    suspend fun uploadFile(localFile: File, remotePath: String) {
        val storageRef = storage.reference.child(remotePath)
        storageRef.putFile(localFile.toUri()).await()

    }
    suspend fun downloadFile(localFile: File, remotePath: String) {
        val storageRef = storage.reference.child(remotePath)
        storageRef.getFile(localFile).await()
    }
}