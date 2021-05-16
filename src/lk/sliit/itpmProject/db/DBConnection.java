package lk.sliit.itpmProject.db;

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;
    private static DBConnection dBconnection;


    private DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fff?createDatabaseIfNotExist=true&allowMultiQueries=true","root","1234");
            PreparedStatement show_tables = connection.prepareStatement("SHOW TABLES");
            ResultSet execute = show_tables.executeQuery();
            if(!execute.next()){
                PreparedStatement createTable =
                        connection.prepareStatement("CREATE TABLE `WorkingDays`\n" +
                                "(\n" +
                                "    `id`                 varchar(10) NOT NULL,\n" +
                                "    `noOfDays`           varchar(30) DEFAULT NULL,\n" +
                                "    `sunday`             boolean     DEFAULT 0,\n" +
                                "    `monday`             boolean     DEFAULT 0,\n" +
                                "    `tuesday`            boolean     DEFAULT 0,\n" +
                                "    `wednesday`          boolean     DEFAULT 0,\n" +
                                "    `thursday`           boolean     DEFAULT 0,\n" +
                                "    `friday`             boolean     DEFAULT 0,\n" +
                                "    `saturday`           boolean     DEFAULT 0,\n" +
                                "    `workingTimeHours`   int         DEFAULT NULL,\n" +
                                "    `workingTimeMinutes` int         DEFAULT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddLecturer`\n" +
                                "(\n" +
                                "    `id`         int          NOT NULL,\n" +
                                "    `empId`      varchar(50)  NOT NULL,\n" +
                                "    `lName`      varchar(50)  NOT NULL,\n" +
                                "    `department` varchar(60)  NOT NULL,\n" +
                                "    `faculty`    varchar(40)  NOT NULL,\n" +
                                "    `center`     varchar(40)  NOT NULL,\n" +
                                "    `buildingNO` varchar(20)  NOT NULL,\n" +
                                "    `level`      varchar(70)  NOT NULL,\n" +
                                "    `rank`       varchar(100) NOT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddStudentSubGroups`\n" +
                                "(\n" +
                                "    `id`         int NOT NULL,\n" +
                                "    `year`       int          DEFAULT NULL,\n" +
                                "    `semester`   int          DEFAULT 1,\n" +
                                "    `programme`  varchar(20)  DEFAULT NULL,\n" +
                                "    `groupNo`    int          DEFAULT NULL,\n" +
                                "    `subGroupNo` int          DEFAULT NULL,\n" +
                                "    `groupId`    varchar(100) DEFAULT NULL,\n" +
                                "    `subGroupId` varchar(100) DEFAULT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddTags`\n" +
                                "(\n" +
                                "    `id`         int NOT NULL,\n" +
                                "    `tagName`    varchar(20) DEFAULT NULL,\n" +
                                "    `tagCode`    int         DEFAULT NULL,\n" +
                                "    `relatedTag` varchar(30) DEFAULT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddLocations`\n" +
                                "(\n" +
                                "    `id`           int NOT NULL,\n" +
                                "    `buildingName` varchar(30) DEFAULT NULL,\n" +
                                "    `roomName`     varchar(30) DEFAULT NULL,\n" +
                                "    `lectureHall`  boolean     DEFAULT 0,\n" +
                                "    `laboratory`   boolean     DEFAULT 0,\n" +
                                "    `Capacity`     char(10)    DEFAULT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddSubject`\n" +
                                "(\n" +
                                "    `id`             int         NOT NULL,\n" +
                                "    `offeredYear`    int         NOT NULL,\n" +
                                "    `semester1`      boolean DEFAULT 0,\n" +
                                "    `semester2`      boolean DEFAULT 0,\n" +
                                "    `NoOFLectureHrs` int(20)     NOT NULL,\n" +
                                "    `NoOfTutHrs`     int(40)     NOT NULL,\n" +
                                "    `NoOFlabHrs`     int(40)     NOT NULL,\n" +
                                "    `SubName`        varchar(40) NOT NULL,\n" +
                                "    `NoOfEvlHrs`     int(70)     NOT NULL,\n" +
                                "    `SubCode`        varchar(10) NOT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `AddSession`\n" +
                                "(\n" +
                                "    `id`               int         NOT NULL,\n" +
                                "    `lecture1`   varchar(70) NOT NULL,\n" +
                                "    `SelectTag`        varchar(40) NOT NULL,\n" +
                                "    `lecture2` varchar(70) NOT NULL,\n" +
                                "    `SelectGroup`      varchar(50) NOT NULL,\n" +
                                "    `NoOFStudent`      int(200)    NOT NULL,\n" +
                                "    `SelectSubject`    varchar(40) NOT NULL,\n" +
                                "    `DurationHrs`      int(40)     NOT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                "\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n" +
                                "CREATE TABLE `NotAvbSessionLec`\n" +
                                "(\n" +
                                "    `id`              int          NOT NULL,\n" +
                                "    `SelectLecturer`  varchar(70)  NOT NULL,\n" +
                                "    `SelectGroup`     varchar(250) NOT NULL,\n" +
                                "    `SelectSubGroup`  varchar(200) NOT NULL,\n" +
                                "    `SelectSessionId` varchar(405) NOT NULL,\n" +
                                "    `selectTime`      varchar(405) NOT NULL,\n" +
                                "    PRIMARY KEY (`id`)\n" +
                                "\n" +
                                ") ENGINE = InnoDB\n" +
                                "  DEFAULT CHARSET = latin1;\n" +
                                "\n");

                createTable.execute();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static DBConnection getInstance(){
        return (dBconnection==null)?(dBconnection=new DBConnection()):dBconnection;
    }

    public Connection getConnection(){return connection;}


}
