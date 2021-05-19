package lk.sliit.itpmProject.business.custom.impl;

import lk.sliit.itpmProject.business.custom.AddLecturerBO;
import lk.sliit.itpmProject.dao.DAOFactory;
import lk.sliit.itpmProject.dao.DAOTypes;
import lk.sliit.itpmProject.dao.custom.AddLecturerDAO;
import lk.sliit.itpmProject.dto.AddLecturerDTO;
import lk.sliit.itpmProject.dto.DaysDTO;
import lk.sliit.itpmProject.entity.AddLectureWorkingDays;
import lk.sliit.itpmProject.entity.AddLecturer;

import java.util.ArrayList;
import java.util.List;


public class AddLecturerBOImpl implements AddLecturerBO {
    private final AddLecturerDAO addLecturerDAO = DAOFactory.getInstance().getDAO(DAOTypes.AddLecturer);

    @Override
    public void saveLecturer(AddLecturerDTO addLecturerDTO) throws Exception {
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
    public int getLastItemCode() throws Exception {
        return addLecturerDAO.getLastLecturerID();
    }

    @Override
    public List<AddLecturerDTO> findAllLecturers() throws Exception {
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
    public boolean updateLecturer(AddLecturerDTO addLecturerDTO) throws Exception {
        return addLecturerDAO.update(new AddLecturer(
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
    public boolean deleteItem(int id) throws Exception {
        return addLecturerDAO.delete(String.valueOf(id));
    }

    @Override
    public List<AddLecturerDTO> findAllLecturersName() throws Exception {
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
    public int checkExists(String empId) throws Exception {
        return addLecturerDAO.checkExists(empId);
    }

    @Override
    public boolean saveLecturerDays(DaysDTO daysDTO) throws Exception {
        boolean va = addLecturerDAO.saveDays(new AddLectureWorkingDays(
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
