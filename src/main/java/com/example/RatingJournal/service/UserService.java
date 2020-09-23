package com.example.RatingJournal.service;

import com.example.RatingJournal.model.Student;
import com.example.RatingJournal.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
// Надстройка над репозитореем.
@Service
public class UserService {

    private final LearnerRepository learnerRepository; // Интерфейс нужен для работы с бд

    @Autowired
    public UserService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }
    //1. Находим всех пользователей.
    public List<Student> findAll() {
        return learnerRepository.findAll();
    }
    //2. Получения Student по ID
    public Student findById(Integer id) {
        return learnerRepository.getOne(id);
    }
    //3. Сохранение пользователя.
    public Student saveUser(Student student) {
        return learnerRepository.save(student);

    }
    //4. Удаление пользователя по id
    public void deleteById(Integer id) {
        learnerRepository.deleteById(id);
    }
    //5. Задание от Нарана находим только отличников.
    public List<Student> findGoodStudent(Integer rating) {
        return learnerRepository.countAllStudentByRating(rating);
    }


}
