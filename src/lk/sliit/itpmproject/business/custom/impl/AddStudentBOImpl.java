package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddStudentBO;

import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddStudentDAO;
import lk.sliit.itpmproject.dto.AddStudentDTO;
import lk.sliit.itpmproject.entity.AddStudent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AddStudentBOImpl implements AddStudentBO {
    private final AddStudentDAO addStudentDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_STUDENT);
    @Override
    public void saveStudent(AddStudentDTO addStudentDTO) throws SQLException {
        addStudentDAO.save(new AddStudent(
                addStudentDTO.getId(),
                addStudentDTO.getYear(),
                addStudentDTO.getSemester(),
                addStudentDTO.getProgramme(),
                addStudentDTO.getGroupNo(),
                addStudentDTO.getSubGroupNo(),
                addStudentDTO.getGroupId(),
                addStudentDTO.getSubGroupId()
        ));
    }

    @Override
    public int getLastItemCode() throws SQLException {

        return addStudentDAO.getLastStudentID();
    }

    @Override
    public List<AddStudentDTO> findAllStudent() throws SQLException {
        List<AddStudent> addStudentList = addStudentDAO.findAll();
        List<AddStudentDTO> addStudentDTOList = new ArrayList<>();
        for (AddStudent a:addStudentList
             ) {
            addStudentDTOList.add(new AddStudentDTO(
                    a.getId(),
                    a.getYear(),
                    a.getSemester(),
                    a.getProgramme(),
                    a.getGroupNo(),
                    a.getSubGroupNo(),
                    a.getGroupId(),
                    a.getSubGroupId()
            ));
        }
        return addStudentDTOList;
    }

    @Override
    public void updateStudent(AddStudentDTO addStudentDTO) throws SQLException {
        addStudentDAO.update(new AddStudent(
                addStudentDTO.getId(),
                addStudentDTO.getYear(),
                addStudentDTO.getSemester(),
                addStudentDTO.getProgramme(),
                addStudentDTO.getGroupNo(),
                addStudentDTO.getSubGroupNo(),
                addStudentDTO.getGroupId(),
                addStudentDTO.getSubGroupId()
        ));
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return addStudentDAO.delete(String.valueOf(id));
    }
}
