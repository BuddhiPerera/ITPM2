CREATE TABLE `WorkingDays` (
                               `id` varchar(10) NOT NULL,
                               `noOfDays` varchar(30) DEFAULT NULL,
                               `sunday` boolean  DEFAULT 0,
                               `monday` boolean  DEFAULT 0,
                               `tuesday` boolean  DEFAULT 0,
                               `wednesday` boolean  DEFAULT 0,
                               `thursday` boolean  DEFAULT 0,
                               `friday` boolean  DEFAULT 0,
                               `saturday` boolean  DEFAULT 0,
                               `workingTimeHours` int DEFAULT NULL,
                               `workingTimeMinutes` int DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




CREATE TABLE `AddLocations` (
                                `id` varchar(10) NOT NULL,
                                `buildingName` varchar(30) DEFAULT NULL,
                                `roomName` varchar(30) DEFAULT NULL,
                                `lectureHall` boolean  DEFAULT 0,
                                `laboratory` boolean  DEFAULT 0,
                                `Capacity` char(10) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

