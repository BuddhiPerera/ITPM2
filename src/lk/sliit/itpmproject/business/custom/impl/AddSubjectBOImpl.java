package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddSubjectBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddSubjectDAO;
import lk.sliit.itpmproject.dto.AddSubjectDTO;
import lk.sliit.itpmproject.entity.AddSubject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddSubjectBOImpl implements AddSubjectBO {
    private final AddSubjectDAO addSubjectDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_SUBJECT);

    @Override
    public void saveSubject(AddSubjectDTO addSubjectDTO) throws SQLException {
        addSubjectDAO.save(new AddSubject(
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
    public int getLastItemCode() throws SQLException {
        return addSubjectDAO.getLastSubjectId();
    }

    @Override
    public List<AddSubjectDTO> findAllSubjects() throws SQLException {
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
    public boolean deleteItem(int id) throws SQLException {
        return addSubjectDAO.delete(String.valueOf(id));
    }

    @Override
    public void updateSubject(AddSubjectDTO addSubjectDTO) throws SQLException {
        addSubjectDAO.update(new AddSubject(
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
    public String findSubjectName(String selectSubject) throws SQLException {
        return addSubjectDAO.findOne(selectSubject);
    }
}