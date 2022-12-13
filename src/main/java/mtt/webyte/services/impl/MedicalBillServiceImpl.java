package mtt.webyte.services.impl;

import mtt.webyte.dto.MedicalBillDTO;
import mtt.webyte.mapper.MedicalBillMapper;
import mtt.webyte.model.MedicalBill;
import mtt.webyte.repository.MedicalBillRepository;
import mtt.webyte.services.MedicalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalBillServiceImpl extends AbstractServiceImpl<MedicalBillRepository, MedicalBillMapper, MedicalBillDTO, MedicalBill> implements MedicalBillService {
    @Autowired
    MedicalBillMapper medicalBillMapper;

    @Override
    public MedicalBill insertMedicalBill(MedicalBill medicalBill) {
        if (medicalBill != null) {
            return repository.save(medicalBill);
        }
        return null;
    }
}
