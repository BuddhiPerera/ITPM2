package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddSessionDTO;

public interface SessionManageBO extends SuperBO {
    int getLastItemCode() throws Exception;

    void saveSession(AddSessionDTO addSessionDTO) throws Exception;
}
