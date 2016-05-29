package com.mateo.videotalk.service;

import com.mateo.videotalk.model.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {

    Course getCoursebyId(Integer courseId);

}
