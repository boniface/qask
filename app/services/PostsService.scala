/*
 * Copyright (c) 2014 Hashcode (Z) Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package services

import respository.PostRespository
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2014/07/12.
 */
class PostsService {
  def repo = PostRespository

  def getPostsBySite(domain: String) = {
    repo.getAllPosts.map(p => p.filter(_.domain.equals(domain)))
  }

  def getPostById(id: String) = {

  }

  def getPostsByDate() = {}

}
