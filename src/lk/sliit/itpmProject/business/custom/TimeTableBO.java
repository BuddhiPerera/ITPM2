package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.TimeTableDTO;

import java.util.List;

public interface TimeTableBO extends SuperBO {
    TimeTableDTO loadTimeTable(String s) throws Exception;


}
