package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddStudentBO;

import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddStudentDAO;
import lk.sliit.itpmProject.dto.AddStudentDTO;
import lk.sliit.itpmProject.entity.AddStudent;

import java.util.ArrayList;
import java.util.List;


public class AddStudentBOImpl implements AddStudentBO {
    private final AddStudentDAO addStudentDAO = DAOFactory.getInstance().getDAO(DAOTypes.AddStudent);
    @Override
    public boolean saveStudent(AddStudentDTO addStudentDTO) throws Exception {
        return addStudentDAO.save(new AddStudent(
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
    public int getLastItemCode() throws Exception {

        return addStudentDAO.getLastStudentID();
    }

    @Override
    public List<AddStudentDTO> findAllStudent() throws Exception {
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
    public boolean updateStudent(AddStudentDTO addStudentDTO) throws Exception {
         return addStudentDAO.update(new AddStudent(
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
    public boolean deleteItem(int id) throws Exception {
        return addStudentDAO.delete(String.valueOf(id));
    }
}
