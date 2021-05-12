package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddSessionDTO;
import lk.sliit.itpmProject.dto.LoadSessionDataDTO;

import java.util.List;

public interface SessionManageBO extends SuperBO {
    int getLastItemCode() throws Exception;

    void saveSession(AddSessionDTO addSessionDTO) throws Exception;

    List<LoadSessionDataDTO> loadSessionTable() throws Exception;


    AddSessionDTO findAllSessions(String id) throws Exception;
}
