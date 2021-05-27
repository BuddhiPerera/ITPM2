package lk.sliit.itpmproject.dao.custom.impl;

import lk.sliit.itpmproject.dao.CrudUtil;
import lk.sliit.itpmproject.dao.custom.QueryDAO;
import lk.sliit.itpmproject.entity.CustomEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
String query1 ="SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
        "s.SubName,a.SelectGroup,a.SelectTag ";

    @Override
    public List<CustomEntity> getInfo() throws SQLException {
        ResultSet rst = CrudUtil.execute(query1 +
                "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject` s ON s.subCode=a.selectSubject");
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
        return al;
    }

    @Override
    public List<CustomEntity> getInfoSelect(int i,String val) throws SQLException {
        val =val.trim();
        ResultSet rst = null;
        if(i==0){
             rst = CrudUtil.execute(query1+
                    "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject` s ON s.subCode=a.`selectSubject` WHERE a.`lecture1`=?",val);
        }else if (i==1){
             rst = CrudUtil.execute(query1+
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where lecture2 =?",val);
        }else if (i==2){
             rst = CrudUtil.execute(query1+
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where SubCode=?",val);
        }else if (i==3){
             rst = CrudUtil.execute(query1 +
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where SubName =?",val);
        }else {
             rst = CrudUtil.execute(query1 +
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where SelectGroup =?",val);
        }

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
        return al;
    }

}
