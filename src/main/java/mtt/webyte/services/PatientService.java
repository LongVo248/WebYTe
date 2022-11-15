package mtt.webyte.services;

import mtt.webyte.dto.PatientDTO;
import mtt.webyte.model.Patient;

public interface PatientService {
    PatientDTO insertPatientSignup(String username, PatientDTO patient);
}
