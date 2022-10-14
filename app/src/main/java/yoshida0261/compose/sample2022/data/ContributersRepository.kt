package yoshida0261.compose.sample2022.data

import kotlinx.coroutines.flow.Flow

interface ContributersRepository {
    val contributers: Flow<List<String>>

    suspend fun get(): Flow<List<String>>
}

class ContributersRepositoryImple(override val contributers: Flow<List<String>>) : ContributersRepository{
    override suspend fun get(): Flow<List<String>> {
        TODO("Not yet implemented")
    }
}

