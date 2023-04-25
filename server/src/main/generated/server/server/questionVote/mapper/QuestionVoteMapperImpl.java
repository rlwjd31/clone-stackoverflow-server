package server.server.questionVote.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import server.server.question.dto.QuestionDto;
import server.server.question.entity.Question;
import server.server.questionVote.dto.QuestionVoteDto;
import server.server.questionVote.entity.QuestionVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T15:03:35+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QuestionVoteMapperImpl implements QuestionVoteMapper {

    @Override
    public QuestionVote questionVotePostQuestionVote(QuestionVoteDto.PostDto postQuestionVote) {
        if ( postQuestionVote == null ) {
            return null;
        }

        QuestionVote questionVote = new QuestionVote();

        questionVote.setQuestion( postDtoToQuestion( postQuestionVote ) );
        questionVote.setQuestionVoteStatus( postQuestionVote.getQuestionVoteStatus() );

        return questionVote;
    }

    @Override
    public QuestionDto.Response questionVoteToResponseQuestionVote(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.Response response = new QuestionDto.Response();

        if ( question.getQuestionId() != null ) {
            response.setQuestionId( question.getQuestionId() );
        }
        response.setTitle( question.getTitle() );
        response.setContentProblem( question.getContentProblem() );
        response.setContentTried( question.getContentTried() );
        response.setViewCount( question.getViewCount() );
        response.setQuestionVoteCount( question.getQuestionVoteCount() );
        response.setModifiedAt( question.getModifiedAt() );

        return response;
    }

    protected Question postDtoToQuestion(QuestionVoteDto.PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( postDto.getQuestionId() );

        return question;
    }
}
