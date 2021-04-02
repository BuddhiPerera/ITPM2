package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddSubjectBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddSubjectDAO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;
import lk.sliit.itpmProject.entity.AddSubject;

public class AddSubjectBOImpl implements AddSubjectBO {
    private final AddSubjectDAO addSubjectDAO = DAOFactory.getInstance().getDAO(DAOTypes.AddSubject);

    @Override
    public boolean saveSubject(AddSubjectDTO addSubjectDTO) throws Exception {
        return addSubjectDAO.save(new AddSubject(
                addSubjectDTO.getId(),
                addSubjectDTO.getOffredYear(),
                addSubjectDTO.isSemester1(),
                addSubjectDTO.isSemester2(),
                addSubjectDTO.getNoOfLecHrs(),
                addSubjectDTO.getNoOfTutHrs(),
                addSubjectDTO.getNoOflabHrs(),
                addSubjectDTO.getSubName(),
                addSubjectDTO.getNoOfEvlHrs(),
                addSubjectDTO.getSubCode()
        ));
    }
}
