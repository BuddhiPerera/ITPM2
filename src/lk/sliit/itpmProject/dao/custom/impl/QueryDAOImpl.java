package lk.sliit.itpmProject.dao.custom.impl;

import javafx.scene.control.Alert;
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
    public List<CustomEntity> getInfoSelect(int i,String val) throws Exception {
        val =val.trim();
        ResultSet rst = null;
        if(i==0){
             rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                    "s.SubName,a.SelectGroup,a.SelectTag " +
                    "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject` s ON s.subCode=a.`selectSubject` WHERE a.`lecture1`=?",val);
        }else if (i==1){
             rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                    "s.SubName,a.SelectGroup,a.SelectTag " +
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where lecture2 =?",val);
        }else if (i==2){
             rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                    "s.SubName,a.SelectGroup,a.SelectTag " +
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where SubCode=?",val);
        }else if (i==3){
             rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                    "s.SubName,a.SelectGroup,a.SelectTag " +
                     "FROM MFkwg22AgC.AddSession a INNER JOIN MFkwg22AgC.`AddSubject`  s ON s.subCode=a.selectSubject where SubName =?",val);
        }else if (i==4){
             rst = CrudUtil.execute("SELECT a.id,a.lecture1, a.lecture2,a.SelectSubject AS subjectCode," +
                    "s.SubName,a.SelectGroup,a.SelectTag " +
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
