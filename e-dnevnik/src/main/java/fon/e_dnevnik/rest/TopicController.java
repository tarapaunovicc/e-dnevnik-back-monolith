package fon.e_dnevnik.rest;

import fon.e_dnevnik.dto.TopicDTO;
import fon.e_dnevnik.service.impl.TopicImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicImplementation topicImplementation;

    @GetMapping(params = "subjectid")
    public ResponseEntity<List<TopicDTO>> getBySubjectQuery(@RequestParam("subjectid") Integer subjectId) {
        return ResponseEntity.ok(topicImplementation.findBySubjectid(subjectId));
    }
}
