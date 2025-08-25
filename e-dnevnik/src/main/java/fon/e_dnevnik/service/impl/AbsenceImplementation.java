package fon.e_dnevnik.service.impl;

import fon.e_dnevnik.dao.AbsenceRepository;
import fon.e_dnevnik.dto.AbsenceDTO;
import fon.e_dnevnik.entity.Absence;
import fon.e_dnevnik.entity.primarykey.AbsencePK;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fon.e_dnevnik.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceImplementation implements ServiceInterface<AbsenceDTO> {

    private final AbsenceRepository absenceRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AbsenceImplementation(AbsenceRepository absenceRepository, ModelMapper modelMapper) {
        this.absenceRepository = absenceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AbsenceDTO> findAll() {
        List<Absence> absences = absenceRepository.findAll();
        List<AbsenceDTO> absenceDTOS = new ArrayList<>();
        for(Absence absence :absences){
            AbsenceDTO absenceDTO = modelMapper.map(absence, AbsenceDTO.class);
            absenceDTOS.add(absenceDTO);
        }
        return absenceDTOS;
    }

    @Override
    public AbsenceDTO findById(Object id) throws Exception {
        Optional<Absence> absence=absenceRepository.findById((AbsencePK) id);
        AbsenceDTO absenceDTO;
        if(absence.isPresent()) {
            absenceDTO = modelMapper.map(absence.get(), AbsenceDTO.class);
            return absenceDTO;
        }
        else{
            throw new Exception("Ne postoji odsustvo");
        }    }

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) throws Exception {
        Absence absence = modelMapper.map(absenceDTO, Absence.class);
        Absence savedAbsence = absenceRepository.save(absence);
        return modelMapper.map(savedAbsence, AbsenceDTO.class);    }

    public List<AbsenceDTO> findByStudentUsername(String username){
        List<Absence> absences = absenceRepository.findByStudentUsername(username);
        List<AbsenceDTO> absenceDTOS = new ArrayList<>();
        for(Absence absence :absences){
            AbsenceDTO absenceDTO = modelMapper.map(absence, AbsenceDTO.class);
            absenceDTOS.add(absenceDTO);
        }
        return absenceDTOS;
    }

    public AbsenceDTO modify(AbsencePK id, boolean excused, boolean isFinal) throws Exception {
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if (optionalAbsence.isPresent()) {
            Absence absence = optionalAbsence.get();
            absence.setExcused(excused);
            absence.setIsfinal(isFinal);
            Absence updatedAbsence = absenceRepository.save(absence);
            return modelMapper.map(updatedAbsence, AbsenceDTO.class);
        } else {
            throw new Exception("Ne postoji odsustvo sa zadatim ID-jem");
        }
    }
}
