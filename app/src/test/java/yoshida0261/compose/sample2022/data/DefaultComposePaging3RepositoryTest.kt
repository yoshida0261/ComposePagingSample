/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yoshida0261.compose.sample2022.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import yoshida0261.compose.sample2022.data.local.database.ComposePaging3
import yoshida0261.compose.sample2022.data.local.database.ComposePaging3Dao

/**
 * Unit tests for [DefaultComposePaging3Repository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultComposePaging3RepositoryTest {

    @Test
    fun composePaging3s_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultComposePaging3Repository(FakeComposePaging3Dao())

        repository.add("Repository")

        assertEquals(repository.composePaging3s.first().size, 1)
    }

}

private class FakeComposePaging3Dao : ComposePaging3Dao {

    private val data = mutableListOf<ComposePaging3>()

    override fun getComposePaging3s(): Flow<List<ComposePaging3>> = flow {
        emit(data)
    }

    override suspend fun insertComposePaging3(item: ComposePaging3) {
        data.add(0, item)
    }
}
