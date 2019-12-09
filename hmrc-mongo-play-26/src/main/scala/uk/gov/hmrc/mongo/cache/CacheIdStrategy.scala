/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.mongo.cache

import play.api.mvc.Request

case class CacheIdStrategy(unwrap: () => CacheId) extends AnyVal

object CacheIdStrategy {
  def const(cacheId: CacheId): CacheIdStrategy =
    CacheIdStrategy(() => cacheId)

  def sessionUuid(sessionIdKey: String)(implicit request: Request[Any]): CacheIdStrategy =
    CacheIdStrategy(
      () =>
        request.session
          .get(sessionIdKey)
          .map(CacheId.apply)
          .getOrElse(CacheId(java.util.UUID.randomUUID.toString))
    )

  def sessionId(implicit request: Request[Any]): CacheIdStrategy =
    CacheIdStrategy(
      () =>
        request.session
          .get("sessionId")
          .map(CacheId.apply)
          .getOrElse(throw NoSessionException)
    )

  case object NoSessionException extends Exception("Could not find sessionId")
}
