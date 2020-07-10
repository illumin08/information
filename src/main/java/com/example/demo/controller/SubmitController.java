package com.example.demo.controller;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.model.Question;
import com.sun.org.apache.xml.internal.serializer.EmptySerializer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class SubmitController {

    @Autowired
    private QuestionMapper questionMapper;



    @GetMapping("/question")
    public String question() {
        return "question";
    }







    @RequestMapping("/question")
    public String addUser(HttpServletRequest request, Map<String, Object> map, HttpSession session) {
        String question = request.getParameter("question");
//        String type = request.getParameter("type");
        String select = request.getParameter("select");
        String author = request.getParameter("author");

        Question que=new Question();
//        que.setType(type);
        que.setAuthor(author);
        que.setQuestion(question);
        que.setSelect(select);


            questionMapper.ses(que);

//                questionMapper.save(que);
        return "redirect:/display/type";
    }


    @RequestMapping("/display")
    public String findAllQuestion(Model model,
                                HttpServletRequest request
                                )
//                                @RequestParam(name = "type", required = false) String type)

    {
        String type = request.getParameter("type");
        List<Question> list=questionMapper.ser(type);
        model.addAttribute("list", list);
        return "display";

    }


    @RequestMapping("/display/type")
    public String findSchool(Model model,
                                HttpServletRequest request
    )
//                                @RequestParam(name = "type", required = false) String type)

    {

        List<Question> list=questionMapper.questionList();
        model.addAttribute("list", list);
        return "display";
    }


    @GetMapping("/display/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        questionMapper.del(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/display/type";
    }



    @GetMapping("/display/{id}/edit")
    public String editInput(@PathVariable String id, Model model) {
        List<Question> list=questionMapper.get(id);
        model.addAttribute("tag", list);

        return "edit";
    }



    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @RequestMapping("/edit")
    public String updateUser(HttpServletRequest request, Map<String, Object> map, HttpSession session) {
        String question=request.getParameter("question");
        String type=request.getParameter("type");


            Question que=new Question();
            que.setType(type);
            que.setQuestion(question);
            questionMapper.update(que);


        return "redirect:/display/type";
    }
}
