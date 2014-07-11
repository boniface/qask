package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/08.
 */
case class UpVote(subjectId: String,
                  voterId: String,
                  vote: String)

object UpVote {
  implicit val upvoteFmt = Json.format[UpVote]
}