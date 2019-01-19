package com.alexmmd.blog.controller;

import com.alexmmd.blog.domain.User;
import com.alexmmd.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 描述:
 * 用户 控制器
 *
 * @author alex
 * @create 2019-01-12 10:47
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 从 用户存储库 获取用户列表
     *
     * @return
     */
    private List<User> getUserlist() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(user);
        }
        return users;
    }

    /**
     * 查询所用用户
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList", getUserlist());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取 form 表单页面
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(0, null, 0));
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 新建用户
     *
     * @param user
     */
    @PostMapping
    public ModelAndView create(User user) {
        user = userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 删除用户
     */
    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);

        model.addAttribute("userList", getUserlist());
        model.addAttribute("title", "删除用户");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 修改用户
     */
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);

        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("users/form", "userModel", model);
    }

}
