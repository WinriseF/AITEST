package com.java.aitest.Controller;

import com.java.aitest.Dao.QuizSetDao;
import com.java.aitest.Entity.QuizSet;
import com.java.aitest.Vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz-bank")
public class QuizBankController {

    @Autowired
    private QuizSetDao quizSetDao;

    @GetMapping("/sets")
    public Result<List<QuizSet>> getAllQuizSets() {
        // 查询所有题库，并按创建时间倒序排列
        List<QuizSet> quizSets = quizSetDao.findAllByOrderByCreatedAtDesc();
        return Result.success("查询成功", quizSets);
    }
}
