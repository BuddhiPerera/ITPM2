package lk.sliit.itpmProject.business.custom;

import lk.sliit.itpmProject.business.SuperBO;
import lk.sliit.itpmProject.dto.AddSubjectDTO;

public interface AddSubjectBO extends SuperBO {
    boolean saveSubject(AddSubjectDTO addSubjectDTO) throws Exception;
}
