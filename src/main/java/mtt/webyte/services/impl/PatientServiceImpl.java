package mtt.webyte.services.impl;

import mtt.webyte.dto.PatientDTO;
import mtt.webyte.model.Patient;
import mtt.webyte.repository.PatientRepository;
import mtt.webyte.services.PatientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final static String BL_ID = "BL-PAT-IMPL";
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(PatientServiceImpl.class);
    @Autowired
    PatientRepository patientRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PatientDTO insertPatientSignup(String username, PatientDTO patientDTO) {
        // step 1 : check exist patient
        checkExistPatient(patientDTO);

        try{
            Patient patient = new Patient();
            patient.setAccountUsername(username);
            patient.setEmail(patientDTO.getEmail());
            patient.setPhone(patientDTO.getPhone());
            patient.setFirstName(patientDTO.getFirstName());
            patient.setLastName(patientDTO.getLastName());
            patient.setCreatedBy(username);
            patient.setCreatedDate(new Date());
            patient.setModifiedBy(username);
            patient.setModifiedDate(new Date());
            logger.info(BL_ID + patient.toString());
            patientRepository.save(patient);
            logger.info(BL_ID + " - insertPatientSignup - patient inserted successfully");
            return patientDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(BL_ID + " - " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void checkExistPatient(PatientDTO patientDTO) {
        Optional<Patient> patient = patientRepository.findByEmail(patientDTO.getEmail());
        if (patient.isPresent()) {
            logger.error(BL_ID + " - checkExistPatient - patient already exist");
            throw new RuntimeException("patient already exist");
        }
    }
}
