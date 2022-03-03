create table EMPLOYEE
(
    ID_EMPLOYEE INTEGER identity,
    FIRST_NAME  CHARACTER(40),
    LAST_NAME   CHARACTER(40) not null,
    PATRONYMIC  CHARACTER(40) not null,
    POSITION    CHARACTER(40),
    constraint EMPLOYEE_PK
        primary key (ID_EMPLOYEE)
);
create table PROJECT
(
    ID_PROJECT   INTEGER identity,
    NAME_PROJECT CHARACTER(40) not null,
    DESCRIPTION  CHARACTER(40),
    constraint PROJECT_PK
        primary key (ID_PROJECT)
);
create table STATUS_TASK
(
    ID_STATUS   INTEGER identity,
    NAME_STATUS CHARACTER(40) not null,
    constraint STATUS_TASK_PK
        primary key (ID_STATUS)
);
create table TASK
(
    ID_TASK     INTEGER identity,
    STATUS_TASK INTEGER       not null,
    NAME_TASK   CHARACTER(40) not null,
    PROJECT_ID  INTEGER       not null,
    NAME_JOB    INTEGER       not null,
    START_DATE  DATE,
    FINISH_DATE DATE,
    EMPLOYEE_ID INTEGER       not null,
    constraint TASK_PK
        primary key (ID_TASK),
    constraint "Employee"
        foreign key (EMPLOYEE_ID) references EMPLOYEE
            on delete cascade,
    constraint NAME_PROJECT
        foreign key (PROJECT_ID) references PROJECT
            on delete cascade,
    constraint STATUS_TASK
        foreign key (STATUS_TASK) references STATUS_TASK
            on delete cascade
);