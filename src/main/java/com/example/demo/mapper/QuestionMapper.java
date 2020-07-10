package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question(question,type,author) values(#{question},#{type},#{author})")
     void save(Question question);






    @Select("select *from question  where type=#{type}")
    List<Question> ser(String type);

    @Insert("insert into question(question,type,author) values(#{question},#{select},#{author})")
    void ses(Question que);

    @Delete("delete from question where question=#{id}")
    void del(String id);

    @Select("select *from question  where question=#{id}")
    List<Question> get(String id);

    @Update("update question set type=#{type} where question=#{question} ")
    void update(Question que);


    @Select("Select * from question ")
    List<Question> questionList();
}
