package com.java.aitest.Controller;

import com.java.aitest.Dao.QuizSetDao;
import com.java.aitest.Dto.GenerateQuestionDto;
import com.java.aitest.Entity.QuizSet;
import com.java.aitest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz-bank")
public class QuizBankController {

    @Autowired
    private QuizSetDao quizSetDao;

    /**
     * API接口：获取所有题库列表
     * @return 题库列表
     */
    @GetMapping("/sets")
    public Result<List<QuizSet>> getAllQuizSets() {
        // 查询所有题库，并按创建时间倒序排列
        List<QuizSet> quizSets = quizSetDao.findAllByOrderByCreatedAtDesc();
        return Result.success("查询成功", quizSets);
    }

    /**
     * API接口：创建一条新的题库记录
     * 这个接口将在前端确认所有题目都生成完毕后被调用
     * @param createDto 包含题库元信息的DTO
     * @return 创建的QuizSet对象
     */
    @PostMapping("/sets")
    public Result<QuizSet> createQuizSet(@RequestBody GenerateQuestionDto createDto) {
        // 防止重复创建
        if (quizSetDao.existsByGenerateId(createDto.getGenerateId())) {
            return Result.fail("该题库记录已存在");
        }
        QuizSet newQuizSet = new QuizSet();
        newQuizSet.setGenerateId(createDto.getGenerateId());
        newQuizSet.setTitle(createDto.getQuestion());
        newQuizSet.setCategory(createDto.getCategory());
        newQuizSet.setTotalQuestions(createDto.getQuestionNum());

        QuizSet savedQuizSet = quizSetDao.save(newQuizSet);
        return Result.success("题库记录创建成功", savedQuizSet);
    }
}
