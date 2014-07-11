package services

import domain.{DownVote, UpVote}
import respository.{UpVoteRepository, DownVoteRepository}

/**
 * Created by hashcode on 2014/07/08.
 */
class VoteService {
  def getDownVotes(subjectId: String) = {
    DownVoteRepository.getVotesBySubjectId(subjectId)
  }

  def getUpVotes(subjectId: String) = {
    UpVoteRepository.getVotesBySubjectId(subjectId)
  }

  def castUpVote(vote: UpVote) = {
    val checkvote = UpVoteRepository.getVoteBySubjectAndVoterId(vote.subjectId, vote.voterId)
    checkvote map (voteresult => {
      voteresult match {
        case Some(_) => UpVoteRepository.deleteVoteBySubjectAndVoterId(vote.subjectId, vote.voterId)
        case None => UpVoteRepository.save(vote)
      }
    }
      )
  }

  def castDownVote(vote: DownVote) = {
    val checkvote = DownVoteRepository.getVoteBySubjectAndVoterId(vote.subjectId, vote.voterId)
    checkvote map (voteresult => {
      voteresult match {
        case Some(_) => DownVoteRepository.deleteVoteBySubjectAndVoterId(vote.subjectId, vote.voterId)
        case None => DownVoteRepository.save(vote)
      }
    }
      )

  }

}
