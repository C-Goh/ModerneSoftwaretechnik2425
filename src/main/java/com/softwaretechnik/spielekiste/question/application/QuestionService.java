import com.softwaretechnik.spielekiste.question.domain.QuestionDomainService;
import com.softwaretechnik.spielekiste.question.domain.entity.QuestionEntity;
import com.softwaretechnik.spielekiste.question.infrastructure.QuestionRepository;

import java.util.List;

public class QuestionService {
    
    private final QuestionRepository questionRepository;

    private final QuestionDomainService questionDomainService;

    public QuestionService(QuestionRepository questionRepository, QuestionDomainService questionDomainService) {
        this.questionRepository = questionRepository;
        this.questionDomainService = questionDomainService;
    }

    public void createQuestion(QuestionEntity frage) {
        questionDomainService.validateQuestion(frage);
        questionRepository.createQuestion(frage);
    }

    public QuestionEntity getQuestionById(int id) {
        return questionRepository.findQuestionById(id);
    }

    public void updateQuestion(QuestionEntity frage) {
        questionDomainService.validateQuestion(frage);
        questionRepository.updateQuestion(frage);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteQuestion(id);
    }

    public List<QuestionEntity> findAllQuestions() {
        return questionRepository.findAllQuestions();
    }
}
