package com.java.aitest.Dto;

public class GenerateQuestionDto {
    private String question;// 题目要求
    private Integer questionNum; // 题目数量使用postman进行请求
    private String questionFormat = "题型为单选题，题目要求是选择题。题目要求包含题干，选项，正确答案和解析，响应的内容以json的方式进行响应，格式要求如下[\n" +
            "\t{\n" +
            "\t\tquestion:string,\n" +
            "\t\toptionA:string,\n" +
            "\t\toptionB:string,\n" +
            "\t\toptionC:string,\n" +
            "\t\toptionD:string,\n" +
            "\t\tanswer:string,\n" +
            "\t\tanalysis:string\n" +
            "\t\t\n" +
            "\t}\n" +
            "],要求响应格式中的属性名严格按照规范进行响应";

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public String getQuestionFormat() {
        return questionFormat;
    }

    public void setQuestionFormat(String questionFormat) {
        this.questionFormat = questionFormat;
    }
}