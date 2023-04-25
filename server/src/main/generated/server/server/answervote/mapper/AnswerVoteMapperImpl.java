package server.server.answervote.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import server.server.answer.entity.Answer;
import server.server.answervote.dto.AnswerVoteDto;
import server.server.answervote.entity.AnswerVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-21T23:28:23+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerVoteMapperImpl implements AnswerVoteMapper {

    @Override
    public AnswerVote postDtoToAnswerVote(AnswerVoteDto.VotePost post) {
        if ( post == null ) {
            return null;
        }

        AnswerVote answerVote = new AnswerVote();

        answerVote.setAnswer( votePostToAnswer( post ) );
        answerVote.setAnswerVoteStatus( post.getAnswerVoteStatus() );

        return answerVote;
    }

    @Override
    public AnswerVoteDto.VoteResponse answerVoteToResponseDto(AnswerVote answerVote) {
        if ( answerVote == null ) {
            return null;
        }

        AnswerVoteDto.VoteResponse.VoteResponseBuilder voteResponse = AnswerVoteDto.VoteResponse.builder();

        voteResponse.answerId( answerVoteAnswerAnswerId( answerVote ) );
        voteResponse.answerVoteId( answerVote.getAnswerVoteId() );
        voteResponse.answerVoteStatus( answerVote.getAnswerVoteStatus() );

        return voteResponse.build();
    }

    protected Answer votePostToAnswer(AnswerVoteDto.VotePost votePost) {
        if ( votePost == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( votePost.getAnswerId() );

        return answer;
    }

    private long answerVoteAnswerAnswerId(AnswerVote answerVote) {
        if ( answerVote == null ) {
            return 0L;
        }
        Answer answer = answerVote.getAnswer();
        if ( answer == null ) {
            return 0L;
        }
        long answerId = answer.getAnswerId();
        return answerId;
    }
}
