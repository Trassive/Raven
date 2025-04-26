package com.example.data.reposiroty

import com.example.domain.repository.InternalTokenRepository
import javax.inject.Inject

class ImplInternalTokenRepository @Inject constructor(
): InternalTokenRepository {
    override suspend fun storeToken(userId: String, token: String) {

    }

}
