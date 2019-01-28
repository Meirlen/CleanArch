/*
 * Copyright (C) 2017 The Android Open Source Project
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

package meirlen.cleanarch.utill


import com.example.gateway.entity.Board
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import meirlen.cleanarch.base.constants.Constant

object TestUtil {


    const val DEFAULT_ID = "DEFAULT_ID"
    const val DEFAULT_FIRST_NAME = "default_first_name"
    const val DEFAULT_LAST_NAME = "default_last_name"

    fun <T> newList(item: T, size: Int = Constant.DEFAULT_PER_PAGE_COUNT): List<T> {
        val list: MutableList<T> = mutableListOf()
        repeat(size) {
            list.add(item)
        }
        return list
    }

    fun newBoard(): Board = mock {
        on { id } doReturn DEFAULT_ID
        on { name } doReturn DEFAULT_FIRST_NAME
    }

}
