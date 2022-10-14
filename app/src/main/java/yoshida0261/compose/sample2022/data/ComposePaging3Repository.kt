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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yoshida0261.compose.sample2022.data.local.database.ComposePaging3
import yoshida0261.compose.sample2022.data.local.database.ComposePaging3Dao
import javax.inject.Inject

interface ComposePaging3Repository {
    val composePaging3s: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultComposePaging3Repository @Inject constructor(
    private val composePaging3Dao: ComposePaging3Dao
) : ComposePaging3Repository {

    override val composePaging3s: Flow<List<String>> =
        composePaging3Dao.getComposePaging3s().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        composePaging3Dao.insertComposePaging3(ComposePaging3(name = name))
    }
}
