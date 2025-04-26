package com.example.chat.data.remotedatasource

import com.example.chat.data.model.FirestoreMessageModel
import com.example.chat.data.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreMessagesDataSource @Inject constructor(
    private val firestore: FirebaseFirestoreProvider
) {
    fun getMessages(chatId: String, userId: String): Flow<Message> = callbackFlow {
        val chatRef = firestore.getFirebaseFirestore().collection("chats").document(chatId).collection("messages")
        val query = chatRef.orderBy("timestamp",Query.Direction.ASCENDING)
        val listener = query.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val messages = snapshot?.documents?.mapNotNull {
                it.toObject(FirestoreMessageModel::class.java)?.copy(id = it.id)
            }?: emptyList()
            val domainMessages = messages.map { it.toDomain(userId) }
            domainMessages.forEach{
                try{
                    trySend(it)
                }  catch (e: Exception){
                    close(e)
                    return@addSnapshotListener
                }
            }
        }
        awaitClose { listener.remove() }
    }
    fun sendMessage(chatId: String, message: Message){
        val chatRef = firestore.getFirebaseFirestore().collection("chats").document(chatId).collection("messages")
        chatRef.add(FirestoreMessageModel.fromDomain(message))
    }
}