package mtt.webyte.services.impl;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mtt.webyte.dto.CreatePrescriptionRequest;
import mtt.webyte.dto.PrescriptionDTO;
import mtt.webyte.mapper.MedicineMapper;
import mtt.webyte.mapper.PrescriptionMapper;
import mtt.webyte.mapper.PrescriptionMedicalMapper;
import mtt.webyte.model.AppointmentSchedule;
import mtt.webyte.model.HealthRecord;
import mtt.webyte.model.Medicine;
import mtt.webyte.model.Prescription;
import mtt.webyte.model.PrescriptionMedical;
import mtt.webyte.model.Sickness;
import mtt.webyte.repository.AppointmentScheduleRepository;
import mtt.webyte.repository.HealthRecordRepository;
import mtt.webyte.repository.MedicineRepository;
import mtt.webyte.repository.PrescriptionMedicalRepository;
import mtt.webyte.repository.PrescriptionRepository;
import mtt.webyte.repository.SicknessRepository;

@Service
@AllArgsConstructor
@Getter
@Setter
public class HealthRecordServiceImpl extends AbstractServiceImpl<HealthRecordRepository, PrescriptionMapper, PrescriptionDTO, Prescription>{
	private final PrescriptionRepository prescriptionRepository;
	private final HealthRecordRepository healthRecordRepository;
	private final PrescriptionMedicalRepository prescriptionMedicalRepository;
	private final SicknessRepository sicknessRepository;
	private final MedicineRepository medicineRepository;
	private final AppointmentScheduleRepository appointmentScheduleRepository;

	private final PrescriptionMedicalMapper prescriptionMedicalMapper;
	private final MedicineMapper medicineMapper;

	public boolean createHealthRecord(CreatePrescriptionRequest request) {
		AppointmentSchedule appointmentSchedule = appointmentScheduleRepository.findById(request.getAppointmentId()).get();
		Set<Sickness> sicknesses = sicknessRepository.findAllBySickIdIn(request.getSicknessIds());
		Set<PrescriptionMedical> prescriptionMedicals = new LinkedHashSet<>();

		Prescription prescription = new Prescription();
		prescriptionRepository.save(prescription);
		for (PrescriptionDTO prescriptionDTO : request.getPrescriptionDTOs()) {
			Medicine medicine = medicineRepository.findById(prescriptionDTO.getMedicineId()).get();
			PrescriptionMedical prescriptionMedical = prescriptionMedicalMapper.toEntity(prescriptionDTO, getCycleAvoidingMappingContext());
			prescriptionMedical.setPrescription(prescription);
			prescriptionMedical.setMedicine(medicine);
			prescriptionMedicals.add(prescriptionMedical);
		}
		prescriptionMedicalRepository.saveAll(prescriptionMedicals);
		
		HealthRecord healthRecord = new HealthRecord();
		healthRecord.setPrescription(prescription);
		healthRecord.setSicknesses(sicknesses);
		healthRecord.setAppointmentSchedule(appointmentSchedule);

		healthRecordRepository.save(healthRecord);

		appointmentSchedule.setAppointmentStatus("complete");
		appointmentScheduleRepository.save(appointmentSchedule);

		return true;
	}

	public List<PrescriptionDTO> getHealthRecord(Long id) {
		AppointmentSchedule appointmentSchedule = appointmentScheduleRepository.findById(id).get();		
		Set<HealthRecord> healthRecords = appointmentSchedule.getHealthRecords();
		Prescription prescription = healthRecords.iterator().next().getPrescription();
		Set<PrescriptionMedical> prescriptionMedicals = prescription.getPrescriptionMedicals();
		List<PrescriptionDTO> prescriptionMedicalDTOs = new ArrayList<>();

		for (PrescriptionMedical prescriptionMedical : prescriptionMedicals) {
			PrescriptionDTO prescriptionDTO = prescriptionMedicalMapper.toDto(prescriptionMedical, getCycleAvoidingMappingContext());
			prescriptionDTO.setMedicineDTO(medicineMapper.toDto(prescriptionMedical.getMedicine(),getCycleAvoidingMappingContext()));	
			prescriptionMedicalDTOs.add(prescriptionDTO);
		}

		return prescriptionMedicalDTOs;
	}
}
