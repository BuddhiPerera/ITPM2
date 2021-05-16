package lk.sliit.itpmProject.dao.custom.impl;

import lk.sliit.itpmProject.dao.CrudUtil;
import lk.sliit.itpmProject.dao.custom.QueryDAO;
import lk.sliit.itpmProject.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomEntity> getInfo() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                "s.SubName,a.SelectGroup,a.SelectTag " +
                "FROM addsession a INNER JOIN `addSubject` s ON s.subCode=a.selectSubject");
        List<CustomEntity> al = new ArrayList<>();
        while (rst.next()){
            al.add(new CustomEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)));
        }
        System.out.println("Hello" + al);
        return al;
    }
}
