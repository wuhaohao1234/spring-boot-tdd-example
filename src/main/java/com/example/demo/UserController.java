package com.example.demo;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@RestController
@RequestMapping(value = "/users")
public class UserController {
    public Map<Long, User> users;

    public UserController() {
        users = Collections.synchronizedMap(new HashMap<Long, User>());
    }

    @GetMapping("/")
    public List<User> getUserList() {
        List<User> result = new ArrayList<User>(users.values());
        return result;
    }
    @PostMapping("/")
    @ApiOperation(value = "create user", notes = "create User by user Object")
    public String postUser(@Valid @RequestBody User user) {
        users.put(user.getId(), user);
        return "success";
    }
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User result = users.get(id);
        result.setName(user.getName());
        result.setAge(user.getAge());
        users.put(id, result);
        return "success";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }
}
