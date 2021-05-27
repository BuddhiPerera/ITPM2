package lk.sliit.itpmproject.business.custom.impl;

import lk.sliit.itpmproject.business.custom.AddLecturerBO;
import lk.sliit.itpmproject.dao.DAOFactory;
import lk.sliit.itpmproject.dao.DAOTypes;
import lk.sliit.itpmproject.dao.custom.AddLecturerDAO;
import lk.sliit.itpmproject.dto.AddLecturerDTO;
import lk.sliit.itpmproject.dto.DaysDTO;
import lk.sliit.itpmproject.entity.AddLectureWorkingDays;
import lk.sliit.itpmproject.entity.AddLecturer;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class AddLecturerBOImpl implements AddLecturerBO {
    private final AddLecturerDAO addLecturerDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADD_LECTURER);

    @Override
    public void saveLecturer(AddLecturerDTO addLecturerDTO) throws SQLException {
        addLecturerDAO.save(new AddLecturer(
                addLecturerDTO.getId(),
                addLecturerDTO.getEmpId(),
                addLecturerDTO.getlName(),
                addLecturerDTO.getDepartment(),
                addLecturerDTO.getFaculty(),
                addLecturerDTO.getCenter(),
                addLecturerDTO.getBuildingNo(),
                addLecturerDTO.getLevel(),
                addLecturerDTO.getRank()
        ));
    }

    @Override
    public int getLastItemCode() throws SQLException {
        return addLecturerDAO.getLastLecturerID();
    }

    @Override
    public List<AddLecturerDTO> findAllLecturers() throws SQLException {
        List<AddLecturer> addLecturerList = addLecturerDAO.findAll();
        List<AddLecturerDTO> addLecturerDTOList = new ArrayList<>();
        for (AddLecturer a : addLecturerList
        ) {
            addLecturerDTOList.add(new AddLecturerDTO(
                    a.getId(),
                    a.getEmpId(),
                    a.getlName(),
                    a.getDepartment(),
                    a.getFaculty(),
                    a.getCenter(),
                    a.getBuildingNo(),
                    a.getLevel(),
                    a.getRank()
            ));

        }
        return addLecturerDTOList;
    }

    @Override
    public void updateLecturer(AddLecturerDTO addLecturerDTO) throws SQLException {
        addLecturerDAO.update(new AddLecturer(
                addLecturerDTO.getId(),
                addLecturerDTO.getEmpId(),
                addLecturerDTO.getlName(),
                addLecturerDTO.getDepartment(),
                addLecturerDTO.getFaculty(),
                addLecturerDTO.getCenter(),
                addLecturerDTO.getBuildingNo(),
                addLecturerDTO.getLevel(),
                addLecturerDTO.getRank()
        ));
    }

    @Override
    public boolean deleteItem(int id) throws SQLException {
        return addLecturerDAO.delete(String.valueOf(id));
    }

    @Override
    public List<AddLecturerDTO> findAllLecturersName() throws SQLException {
        List<AddLecturer> addLecturerList = addLecturerDAO.findAllNames();
        List<AddLecturerDTO> addLecturerDTOList = new ArrayList<>();
        for (AddLecturer a : addLecturerList
        ) {
            addLecturerDTOList.add(new AddLecturerDTO(
                    a.getlName()
            ));

        }
        return addLecturerDTOList;
    }

    @Override
    public int checkExists(String empId) throws SQLException {
        return addLecturerDAO.checkExists(empId);
    }

    @Override
    public boolean saveLecturerDays(DaysDTO daysDTO) throws SQLException {
        boolean va = false;
        va = addLecturerDAO.saveDays(new AddLectureWorkingDays(
                daysDTO.getEmpId(),
                daysDTO.getSaturdayCB(),
                daysDTO.getSundayCB(),
                daysDTO.getMondayCB(),
                daysDTO.getTuesdayCB(),
                daysDTO.getWednesdayCB(),
                daysDTO.getThursdayCB(),
                daysDTO.getFridayCB()
        ));
        return va;
    }

}
