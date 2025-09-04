package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.TopicRepository;
import fon.e_dnevnik.dto.TopicDTO;
import fon.e_dnevnik.service.ServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicImplementation implements ServiceInterface<TopicDTO> {
    private final TopicRepository topicRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<TopicDTO> findAll() {
        return null;
    }

    @Override
    public TopicDTO findById(Object id) throws Exception {
        return null;
    }

    @Override
    public TopicDTO save(TopicDTO topicDTO) throws Exception {
        return null;
    }

    @Transactional(readOnly = true)
    public List<TopicDTO> findBySubjectid(Integer subjectid) {
        if (subjectid == null) throw new IllegalArgumentException("subjectid je obavezan");
        return topicRepository.findBySubjectidOrderByNameAsc(subjectid).stream()
                .map(t -> modelMapper.map(t, TopicDTO.class))
                .toList();
    }
}
