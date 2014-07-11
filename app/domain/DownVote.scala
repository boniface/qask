package domain

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/08.
 */
case class DownVote(
                     subjectId: String,
                     voterId: String,
                     vote: String)

object DownVote {
  implicit val downVoteFmt = Json.format[DownVote]
}