CREATE TABLE WorkingDays
(
    id                 varchar(10) NOT NULL,
    noOfDays           varchar(30) DEFAULT NULL,
    sunday             bit     not null default 0,
    monday             bit     not null default 0,
    tuesday            bit     not null default 0,
    wednesday          bit     not null default 0,
    thursday           bit     not null default 0,
    friday             bit     not null default 0,
    saturday           bit     not null default 0,
    workingTimeHours   int         DEFAULT NULL,
    workingTimeMinutes int         DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE AddLecturer
(
    id         int          NOT NULL,
    empId      varchar(50)  NOT NULL,
    lName      varchar(50)  NOT NULL,
    department varchar(60)  NOT NULL,
    faculty    varchar(40)  NOT NULL,
    center     varchar(40)  NOT NULL,
    buildingNO varchar(20)  NOT NULL,
    levelx     varchar(70)  NOT NULL,
    rankx       varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ;

CREATE TABLE AddStudentSubGroups
(
    id         int NOT NULL,
    yearx       int          DEFAULT NULL,
    semester   int          DEFAULT 1,
    programme  varchar(20)  DEFAULT NULL,
    groupNo    int          DEFAULT NULL,
    subGroupNo int          DEFAULT NULL,
    groupId    varchar(100) DEFAULT NULL,
    subGroupId varchar(100) DEFAULT NULL,
    PRIMARY KEY (id)
) ;

CREATE TABLE AddTags
(
    id         int NOT NULL,
    tagName    varchar(20) DEFAULT NULL,
    tagCode    int         DEFAULT NULL,
    relatedTag varchar(30) DEFAULT NULL,
    PRIMARY KEY (id)
) ;

CREATE TABLE AddLocations
(
    id           int NOT NULL,
    buildingName varchar(30) DEFAULT NULL,
    roomName     varchar(30) DEFAULT NULL,
    lectureHall   bit not null default 0,
    laboratory   bit not null default 0,
    Capacity     char(10)    DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE AddSubject
(
    id             int         NOT NULL,
    offeredYear    int         NOT NULL,
    semester1      bit not null default 0,
    semester2      bit not null default 0,
    NoOFLectureHrs int     NOT NULL,
    NoOfTutHrs     int     NOT NULL,
    NoOFlabHrs     int    NOT NULL,
    SubName        varchar(40) NOT NULL,
    NoOfEvlHrs     int    NOT NULL,
    SubCode        varchar(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE addsession
(
    id            int         NOT NULL,
    lecture1      CHAR(70)    NOT NULL,
    SelectTag     varchar(40) NOT NULL,
    lecture2      CHAR(70)    NOT NULL,
    SelectGroup   varchar(50) NOT NULL,
    NoOFStudent   int   NOT NULL,
    SelectSubject varchar(40) NOT NULL,
    DurationHrs   int     NOT NULL,
    room          varchar(30),
    PRIMARY KEY (id)
);


CREATE TABLE NotAvbSessionGroup
(
    id              int          NOT NULL,
    SelectGroup     varchar(250) NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

) ;

CREATE TABLE NotAvbSessionSibGroup
(
    id              int          NOT NULL,
    SelectSubGroup  varchar(200) NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

);
CREATE TABLE NotAvbSessionSession
(
    id              int          NOT NULL,
    SelectSessionId varchar(405) NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

);
CREATE TABLE NotAvbSessionRooms
(
    id              int          NOT NULL,
    selectRoom  varchar(70)  NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

);
CREATE TABLE NotAvbSessionLec
(
    id              int          NOT NULL,
    SelectLecturer  varchar(70)  NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

);

CREATE TABLE NotAvbSessionD
(
    id              int          NOT NULL,
    selectSession  varchar(70)  NOT NULL,
    selectTime      varchar(405) NOT NULL,
    PRIMARY KEY (id)

);


create table consecutive
(
    id          int          not null,
    rowId       int          not null,
    lectureOne  varchar(250) not null,
    lectureTwo  varchar(250) not null,
    subjectCode varchar(200) not null,
    subjectx     varchar(405) not null,
    groupId     varchar(405) not null,
    tag         varchar(200) not null,
    primary key (id, rowId)
);

CREATE TABLE Parallel
(
    id          int          NOT NULL,
    rowId       int          NOT NULL,
    lectureOne  varchar(250) NOT NULL,
    lectureTwo  varchar(250) NOT NULL,
    subjectCode varchar(200) NOT NULL,
    subjectx     varchar(405) NOT NULL,
    groupId     varchar(405) NOT NULL,
    tag         varchar(200) NOT NULL,
    PRIMARY KEY (id, rowId)
);

CREATE TABLE NonOverLapping
(
    id          int          NOT NULL,
    rowId       int          NOT NULL,
    lectureOne  varchar(250) NOT NULL,
    lectureTwo  varchar(250) NOT NULL,
    subjectCode varchar(200) NOT NULL,
    subject     varchar(405) NOT NULL,
    groupId     varchar(405) NOT NULL,
    tag         varchar(200) NOT NULL,
    PRIMARY KEY (id, rowId)
);

CREATE TABLE LecturerWorkDay
(
    empId     varchar(6) NOT NULL,
    sunday    bit not null default 0,
    monday    bit not null default 0,
    tuesday   bit not null default 0,
    wednesday bit not null default 0,
    thursday  bit not null default 0,
    friday    bit not null default 0,
    saturday  bit not null default 0,
    PRIMARY KEY (empId)
);