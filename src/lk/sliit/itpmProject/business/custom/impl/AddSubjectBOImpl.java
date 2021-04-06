package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddSubjectBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddSubjectDAO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;
import lk.sliit.itpmProject.entity.AddSubject;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public int getLastItemCode() throws Exception {
        return addSubjectDAO.getLastSubjectId();
    }

    @Override
    public List<AddSubjectDTO> findAllSubjects() throws Exception {
        List<AddSubject> addSubjectList = addSubjectDAO.findAll();
        List<AddSubjectDTO> addSubjectDTOList = new ArrayList<>();
        for (AddSubject a:addSubjectList
        ) {
            addSubjectDTOList.add(new AddSubjectDTO(
                    a.getId(),
                    a.getOffredYear(),
                    a.isSemester1(),
                    a.isSemester2(),
                    a.getNoOfLecHrs(),
                    a.getNoOfTutHrs(),
                    a.getNoOflabHrs(),
                    a.getSubName(),
                    a.getNoOfEvlHrs(),
                    a.getSubCode()
            ));
        }
        return addSubjectDTOList;
    }

    @Override
    public boolean deleteItem(int id) throws Exception {
        return addSubjectDAO.delete(String.valueOf(id));
    }
}